/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at Sep 12, 2022, 12:04:28 PM
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2022 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.commerce.storesession.data;

import java.io.Serializable;
import de.hybris.platform.commercefacades.storesession.data.LanguageData;
import java.util.Collection;


import java.util.Objects;
public  class LanguageDataList  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** <i>Generated property</i> for <code>LanguageDataList.languages</code> property defined at extension <code>mockcommercewebservices</code>. */
	
	private Collection<LanguageData> languages;
	
	public LanguageDataList()
	{
		// default constructor
	}
	
	public void setLanguages(final Collection<LanguageData> languages)
	{
		this.languages = languages;
	}

	public Collection<LanguageData> getLanguages() 
	{
		return languages;
	}
	

}