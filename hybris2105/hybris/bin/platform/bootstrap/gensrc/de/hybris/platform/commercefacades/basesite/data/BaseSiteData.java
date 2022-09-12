/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at Sep 12, 2022, 12:04:28 PM
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2022 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.commercefacades.basesite.data;

import java.io.Serializable;
import de.hybris.platform.commercefacades.basestore.data.BaseStoreData;
import de.hybris.platform.commercefacades.storesession.data.LanguageData;
import java.util.List;


import java.util.Objects;
public  class BaseSiteData  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** <i>Generated property</i> for <code>BaseSiteData.uid</code> property defined at extension <code>commercefacades</code>. */
	
	private String uid;

	/** <i>Generated property</i> for <code>BaseSiteData.name</code> property defined at extension <code>commercefacades</code>. */
	
	private String name;

	/** <i>Generated property</i> for <code>BaseSiteData.stores</code> property defined at extension <code>commercefacades</code>. */
	
	private List<BaseStoreData> stores;

	/** <i>Generated property</i> for <code>BaseSiteData.theme</code> property defined at extension <code>commercefacades</code>. */
	
	private String theme;

	/** <i>Generated property</i> for <code>BaseSiteData.defaultLanguage</code> property defined at extension <code>commercefacades</code>. */
	
	private LanguageData defaultLanguage;

	/** <i>Generated property</i> for <code>BaseSiteData.locale</code> property defined at extension <code>commercefacades</code>. */
	
	private String locale;

	/** <i>Generated property</i> for <code>BaseSiteData.channel</code> property defined at extension <code>commercefacades</code>. */
	
	private String channel;
	
	public BaseSiteData()
	{
		// default constructor
	}
	
	public void setUid(final String uid)
	{
		this.uid = uid;
	}

	public String getUid() 
	{
		return uid;
	}
	
	public void setName(final String name)
	{
		this.name = name;
	}

	public String getName() 
	{
		return name;
	}
	
	public void setStores(final List<BaseStoreData> stores)
	{
		this.stores = stores;
	}

	public List<BaseStoreData> getStores() 
	{
		return stores;
	}
	
	public void setTheme(final String theme)
	{
		this.theme = theme;
	}

	public String getTheme() 
	{
		return theme;
	}
	
	public void setDefaultLanguage(final LanguageData defaultLanguage)
	{
		this.defaultLanguage = defaultLanguage;
	}

	public LanguageData getDefaultLanguage() 
	{
		return defaultLanguage;
	}
	
	public void setLocale(final String locale)
	{
		this.locale = locale;
	}

	public String getLocale() 
	{
		return locale;
	}
	
	public void setChannel(final String channel)
	{
		this.channel = channel;
	}

	public String getChannel() 
	{
		return channel;
	}
	

}