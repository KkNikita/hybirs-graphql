/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at Sep 12, 2022, 12:04:28 PM
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2022 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.commerce.order.data;

import java.io.Serializable;
import de.hybris.platform.commercefacades.order.data.OrderEntryData;
import java.util.List;


import java.util.Objects;
public  class OrderEntryDataList  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** <i>Generated property</i> for <code>OrderEntryDataList.orderEntries</code> property defined at extension <code>mockcommercewebservices</code>. */
	
	private List<OrderEntryData> orderEntries;
	
	public OrderEntryDataList()
	{
		// default constructor
	}
	
	public void setOrderEntries(final List<OrderEntryData> orderEntries)
	{
		this.orderEntries = orderEntries;
	}

	public List<OrderEntryData> getOrderEntries() 
	{
		return orderEntries;
	}
	

}