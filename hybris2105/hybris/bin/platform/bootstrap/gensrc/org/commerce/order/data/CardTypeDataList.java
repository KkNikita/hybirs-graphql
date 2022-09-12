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
import de.hybris.platform.commercefacades.order.data.CardTypeData;
import java.util.List;


import java.util.Objects;
public  class CardTypeDataList  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** <i>Generated property</i> for <code>CardTypeDataList.cardTypes</code> property defined at extension <code>mockcommercewebservices</code>. */
	
	private List<CardTypeData> cardTypes;
	
	public CardTypeDataList()
	{
		// default constructor
	}
	
	public void setCardTypes(final List<CardTypeData> cardTypes)
	{
		this.cardTypes = cardTypes;
	}

	public List<CardTypeData> getCardTypes() 
	{
		return cardTypes;
	}
	

}