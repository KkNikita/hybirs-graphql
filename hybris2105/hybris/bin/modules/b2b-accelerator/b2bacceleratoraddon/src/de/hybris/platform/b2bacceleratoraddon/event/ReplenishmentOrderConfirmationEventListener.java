/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.b2bacceleratoraddon.event;

import de.hybris.platform.b2bacceleratorservices.event.ReplenishmentOrderConfirmationEvent;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.orderprocessing.model.OrderProcessModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.event.impl.AbstractEventListener;
import de.hybris.platform.servicelayer.model.ModelService;

import org.springframework.beans.factory.annotation.Required;


public class ReplenishmentOrderConfirmationEventListener extends AbstractEventListener<ReplenishmentOrderConfirmationEvent>
{
	private ModelService modelService;
	private BusinessProcessService businessProcessService;

	protected BusinessProcessService getBusinessProcessService()
	{
		return businessProcessService;
	}

	@Required
	public void setBusinessProcessService(final BusinessProcessService businessProcessService)
	{
		this.businessProcessService = businessProcessService;
	}

	protected ModelService getModelService()
	{
		return modelService;
	}

	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}


	@Override
	protected void onEvent(final ReplenishmentOrderConfirmationEvent event)
	{
		final OrderModel orderModel = event.getOrder();
		final OrderProcessModel orderProcessModel = (OrderProcessModel) getBusinessProcessService().createProcess(
				"replenishmentOrderConfirmationEmailProcess" + "-" + orderModel.getCode() + "-" + System.currentTimeMillis(),
				"replenishmentOrderConfirmationEmailProcess");
		orderProcessModel.setOrder(orderModel);
		getModelService().save(orderProcessModel);
		getBusinessProcessService().startProcess(orderProcessModel);
	}
}
