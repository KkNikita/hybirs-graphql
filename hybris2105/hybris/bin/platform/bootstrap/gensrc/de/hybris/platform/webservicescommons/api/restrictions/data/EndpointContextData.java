/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at Sep 12, 2022, 12:04:28 PM
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2022 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.webservicescommons.api.restrictions.data;

import java.io.Serializable;
import java.lang.reflect.Method;


import java.util.Objects;
public  class EndpointContextData  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** <i>Generated property</i> for <code>EndpointContextData.method</code> property defined at extension <code>webservicescommons</code>. */
	
	private Method method;
	
	public EndpointContextData()
	{
		// default constructor
	}
	
	public void setMethod(final Method method)
	{
		this.method = method;
	}

	public Method getMethod() 
	{
		return method;
	}
	

}