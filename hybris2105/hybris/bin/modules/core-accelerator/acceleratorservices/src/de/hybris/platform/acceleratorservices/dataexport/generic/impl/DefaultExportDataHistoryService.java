/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.acceleratorservices.dataexport.generic.impl;

import de.hybris.platform.acceleratorservices.dataexport.generic.ExportDataHistoryService;
import de.hybris.platform.acceleratorservices.dataexport.generic.event.ExportDataEvent;
import de.hybris.platform.acceleratorservices.enums.ExportDataStatus;
import de.hybris.platform.acceleratorservices.model.export.ExportDataCronJobModel;
import de.hybris.platform.acceleratorservices.model.export.ExportDataHistoryEntryModel;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.CronJobDao;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.handler.annotation.Header;


/**
 * Default implementation of {@link DefaultExportDataHistoryService}.
 */
public class DefaultExportDataHistoryService implements ExportDataHistoryService
{
	private static final Logger LOG = Logger.getLogger(DefaultExportDataHistoryService.class);
	private ModelService modelService;
	private CronJobDao cronJobDao;

	protected ModelService getModelService()
	{
		return modelService;
	}

	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	protected CronJobDao getCronJobDao()
	{
		return cronJobDao;
	}

	@Required
	public void setCronJobDao(final CronJobDao cronJobDao)
	{
		this.cronJobDao = cronJobDao;
	}

	@Override
	public ExportDataEvent recordExportStart(final ExportDataEvent payload, @Header(value = "timestamp") final Long timestamp)
	{
		final ExportDataHistoryEntryModel exportDataHistory = new ExportDataHistoryEntryModel();
		exportDataHistory.setStartTime(new Date(timestamp.longValue()));
		exportDataHistory.setCode(timestamp.toString());
		exportDataHistory.setStatus(ExportDataStatus.RUNNING);
		final CronJobModel cronJobModel = cronJobDao.findCronJobs(payload.getCode()).get(0);
		if (cronJobModel instanceof ExportDataCronJobModel)
		{
			exportDataHistory.setExportDataCronJob((ExportDataCronJobModel) cronJobModel);
		}

		modelService.save(exportDataHistory);
		return payload;
	}

	@Override
	public void recordExportEnd(final Message<?> message, @Header(value = "event") final ExportDataEvent ede,
			@Header(value = "processedCount") final Integer count)
	{
		final CronJobModel cronJobModel = cronJobDao.findCronJobs(ede.getCode()).get(0);
		if (cronJobModel instanceof ExportDataCronJobModel)
		{
			final ExportDataCronJobModel exportDataCronJob = (ExportDataCronJobModel) cronJobModel;
			final ExportDataHistoryEntryModel exportDataHistory = getLatestHistoryModel(exportDataCronJob);
			if (exportDataHistory != null)
			{
				exportDataHistory.setProcessedResultCount(count);
				exportDataHistory.setFinishTime(new Date());
				exportDataHistory.setStatus(ExportDataStatus.COMPLETE);
				modelService.save(exportDataHistory);
			}
		}
	}


	protected ExportDataHistoryEntryModel getLatestHistoryModel(final ExportDataCronJobModel exportDataCronJob)
	{
		final List<ExportDataHistoryEntryModel> historyList = new ArrayList<ExportDataHistoryEntryModel>(
				exportDataCronJob.getHistoryEntries());
		if (!historyList.isEmpty())
		{
			Collections.sort(historyList, new Comparator<ExportDataHistoryEntryModel>()
			{
				@Override
				public int compare(final ExportDataHistoryEntryModel exportDataHistoryEntryModel1,
						final ExportDataHistoryEntryModel exportDataHistoryEntryModel2)
				{
					return exportDataHistoryEntryModel2.getStartTime().compareTo(exportDataHistoryEntryModel1.getStartTime());
				}
			});

			return historyList.get(0);
		}
		return null;
	}

	/**
	 * Saves a historical entry for the error in case it is a {@link MessagingException}
	 * 
	 * @param payload
	 *           the error payload
	 */
	public void handleError(final Throwable payload)
	{
		LOG.info("An error occurred. Logging exception message in History");
		if (payload instanceof MessagingException)
		{
			final MessagingException handlingException = (MessagingException) payload;
			final Message<?> failedMessage = handlingException.getFailedMessage();
			final MessageHeaders headers = failedMessage.getHeaders();
			if (headers.containsKey("event"))
			{
				final Object event = headers.get("event");
				if (event instanceof ExportDataEvent)
				{
					prepareExportDataHistory(handlingException, event);
				}
			}
		}
	}

	protected void prepareExportDataHistory(final MessagingException handlingException, final Object event)
	{
		final ExportDataEvent ede = (ExportDataEvent) event;
		final CronJobModel cronJobModel = cronJobDao.findCronJobs(ede.getCode()).get(0);
		if (cronJobModel instanceof ExportDataCronJobModel)
		{
			final ExportDataCronJobModel exportDataCronJob = (ExportDataCronJobModel) cronJobModel;
			final ExportDataHistoryEntryModel exportDataHistory = getLatestHistoryModel(exportDataCronJob);
			if (exportDataHistory != null)
			{
				exportDataHistory.setFailureMessage(handlingException.getMessage());
				exportDataHistory.setFinishTime(new Date());
				exportDataHistory.setStatus(ExportDataStatus.FAILED);
				modelService.save(exportDataHistory);
			}
		}
	}
}
