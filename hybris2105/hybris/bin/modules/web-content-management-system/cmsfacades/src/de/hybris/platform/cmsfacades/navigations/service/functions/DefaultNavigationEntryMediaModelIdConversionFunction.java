/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.cmsfacades.navigations.service.functions;

import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.util.function.Function;

/**
 * Default implementation for conversion of {@link ItemModel} into {@code MediaModel#getCode()}
 * @deprecated since 1811 - no longer needed
 */
@Deprecated(since = "1811", forRemoval = true)
public class DefaultNavigationEntryMediaModelIdConversionFunction implements Function<ItemModel, String> 
{
	@Override
	public String apply(final ItemModel itemModel) 
	{
		if (!(MediaModel.class.isAssignableFrom(itemModel.getClass()))) 
		{
			throw new ConversionException("Invalid Media Component: " + itemModel);
		}
		return ((MediaModel) itemModel).getCode();
	}
}
