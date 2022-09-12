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
import de.hybris.platform.commercefacades.user.data.TitleData;
import java.util.List;


import java.util.Objects;
public  class TitleDataList  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** <i>Generated property</i> for <code>TitleDataList.titles</code> property defined at extension <code>mockcommercewebservices</code>. */
	
	private List<TitleData> titles;
	
	public TitleDataList()
	{
		// default constructor
	}
	
	public void setTitles(final List<TitleData> titles)
	{
		this.titles = titles;
	}

	public List<TitleData> getTitles() 
	{
		return titles;
	}
	

}