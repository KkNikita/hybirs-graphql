/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved
 */
package de.hybris.platform.cockpit.reports.impl;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.verify;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.cockpit.enums.RefreshTimeOption;
import de.hybris.platform.cockpit.reports.JasperReportCacheService;
import de.hybris.platform.cockpit.reports.model.JasperWidgetPreferencesModel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


/**
 * Unit test for DefaultJasperReportsRefreshService
 */
@UnitTest
public class DefaultJasperReportsRefreshServiceTest
{
	DefaultJasperReportsRefreshService jasperReportsRefreshService = new DefaultJasperReportsRefreshService();

	@Mock
	private JasperReportCacheService jasperReportsCacheService;

	@Mock
	private JasperWidgetPreferencesModel widget;


	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
		jasperReportsRefreshService.setJasperReportsCacheService(jasperReportsCacheService);
	}

	@Test
	public void testRefreshingWithSuccess() throws InterruptedException
	{
		//given
		jasperReportsRefreshService.startRefreshing(widget, RefreshTimeOption.FIVESEC);
		Thread.sleep(6000);

		//when
		final boolean anyRefreshed = jasperReportsRefreshService.onRefresh();

		//then
		assertThat(anyRefreshed).isTrue();
		verify(jasperReportsCacheService).update(widget);
	}

	@Test
	public void testRefreshingWithFailure() throws InterruptedException
	{
		//given
		jasperReportsRefreshService.startRefreshing(widget, RefreshTimeOption.HALFMIN);

		//when
		final boolean anyRefreshed = jasperReportsRefreshService.onRefresh();

		//then
		assertThat(anyRefreshed).isFalse();
	}

	@Test
	public void testRefreshingWithFailureBecauseNeverSet() throws InterruptedException
	{
		//given
		jasperReportsRefreshService.startRefreshing(widget, RefreshTimeOption.NEVER);

		//when
		final boolean anyRefreshed = jasperReportsRefreshService.onRefresh();

		//then
		assertThat(anyRefreshed).isFalse();
	}
}
