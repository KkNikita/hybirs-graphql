/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at Sep 12, 2022, 12:04:28 PM
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2022 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.commercefacades.quote.data;

import java.io.Serializable;


import java.util.Objects;
/**
 * @deprecated true
 */
@Deprecated(since = "6.4", forRemoval = true)
public  class DiscountTypeData  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** <i>Generated property</i> for <code>DiscountTypeData.code</code> property defined at extension <code>commercefacades</code>. */
	
	private String code;

	/** <i>Generated property</i> for <code>DiscountTypeData.name</code> property defined at extension <code>commercefacades</code>. */
	
	private String name;
	
	public DiscountTypeData()
	{
		// default constructor
	}
	
	public void setCode(final String code)
	{
		this.code = code;
	}

	public String getCode() 
	{
		return code;
	}
	
	public void setName(final String name)
	{
		this.name = name;
	}

	public String getName() 
	{
		return name;
	}
	

}