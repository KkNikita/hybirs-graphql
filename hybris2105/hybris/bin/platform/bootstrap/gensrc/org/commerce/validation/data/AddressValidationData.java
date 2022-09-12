/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at Sep 12, 2022, 12:04:28 PM
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2022 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.commerce.validation.data;

import java.io.Serializable;
import de.hybris.platform.webservicescommons.dto.error.ErrorListWsDTO;
import org.commerce.user.data.AddressDataList;


import java.util.Objects;
public  class AddressValidationData  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** <i>Generated property</i> for <code>AddressValidationData.errors</code> property defined at extension <code>mockcommercewebservices</code>. */
	
	private ErrorListWsDTO errors;

	/** <i>Generated property</i> for <code>AddressValidationData.decision</code> property defined at extension <code>mockcommercewebservices</code>. */
	
	private String decision;

	/** <i>Generated property</i> for <code>AddressValidationData.suggestedAddressesList</code> property defined at extension <code>mockcommercewebservices</code>. */
	
	private AddressDataList suggestedAddressesList;
	
	public AddressValidationData()
	{
		// default constructor
	}
	
	public void setErrors(final ErrorListWsDTO errors)
	{
		this.errors = errors;
	}

	public ErrorListWsDTO getErrors() 
	{
		return errors;
	}
	
	public void setDecision(final String decision)
	{
		this.decision = decision;
	}

	public String getDecision() 
	{
		return decision;
	}
	
	public void setSuggestedAddressesList(final AddressDataList suggestedAddressesList)
	{
		this.suggestedAddressesList = suggestedAddressesList;
	}

	public AddressDataList getSuggestedAddressesList() 
	{
		return suggestedAddressesList;
	}
	

}