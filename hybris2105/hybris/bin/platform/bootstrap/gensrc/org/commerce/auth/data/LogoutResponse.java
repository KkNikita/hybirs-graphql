/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at Sep 12, 2022, 12:04:28 PM
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2022 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.commerce.auth.data;

import java.io.Serializable;


import java.util.Objects;
public  class LogoutResponse  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** <i>Generated property</i> for <code>LogoutResponse.success</code> property defined at extension <code>mockcommercewebservices</code>. */
	
	private boolean success;
	
	public LogoutResponse()
	{
		// default constructor
	}
	
	public void setSuccess(final boolean success)
	{
		this.success = success;
	}

	public boolean isSuccess() 
	{
		return success;
	}
	

}