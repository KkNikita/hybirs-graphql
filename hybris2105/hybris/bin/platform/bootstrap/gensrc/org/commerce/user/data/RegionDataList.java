/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at Sep 12, 2022, 12:04:28 PM
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2022 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.commerce.user.data;

import java.io.Serializable;
import de.hybris.platform.commercefacades.user.data.RegionData;
import java.util.List;


import java.util.Objects;
public  class RegionDataList  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** <i>Generated property</i> for <code>RegionDataList.regions</code> property defined at extension <code>mockcommercewebservices</code>. */
	
	private List<RegionData> regions;
	
	public RegionDataList()
	{
		// default constructor
	}
	
	public void setRegions(final List<RegionData> regions)
	{
		this.regions = regions;
	}

	public List<RegionData> getRegions() 
	{
		return regions;
	}
	

}