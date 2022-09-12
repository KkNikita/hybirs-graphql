/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at Sep 12, 2022, 12:04:27 PM                   ---
 * ----------------------------------------------------------------
 *  
 * Copyright (c) 2022 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.basecommerce.model.site;

import de.hybris.bootstrap.annotations.Accessor;
import de.hybris.platform.commerceservices.enums.SiteChannel;
import de.hybris.platform.commerceservices.enums.SiteTheme;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.promotions.model.PromotionGroupModel;
import de.hybris.platform.searchservices.model.SnIndexTypeModel;
import de.hybris.platform.servicelayer.model.ItemModelContext;
import de.hybris.platform.solrfacetsearch.model.config.SolrFacetSearchConfigModel;
import de.hybris.platform.store.BaseStoreModel;
import java.util.List;
import java.util.Locale;

/**
 * Generated model class for type BaseSite first defined at extension basecommerce.
 */
@SuppressWarnings("all")
public class BaseSiteModel extends ItemModel
{
	/**<i>Generated model type code constant.</i>*/
	public static final String _TYPECODE = "BaseSite";
	
	/** <i>Generated constant</i> - Attribute key of <code>BaseSite.uid</code> attribute defined at extension <code>basecommerce</code>. */
	public static final String UID = "uid";
	
	/** <i>Generated constant</i> - Attribute key of <code>BaseSite.name</code> attribute defined at extension <code>basecommerce</code>. */
	public static final String NAME = "name";
	
	/** <i>Generated constant</i> - Attribute key of <code>BaseSite.stores</code> attribute defined at extension <code>basecommerce</code>. */
	public static final String STORES = "stores";
	
	/** <i>Generated constant</i> - Attribute key of <code>BaseSite.theme</code> attribute defined at extension <code>commerceservices</code>. */
	public static final String THEME = "theme";
	
	/** <i>Generated constant</i> - Attribute key of <code>BaseSite.defaultLanguage</code> attribute defined at extension <code>commerceservices</code>. */
	public static final String DEFAULTLANGUAGE = "defaultLanguage";
	
	/** <i>Generated constant</i> - Attribute key of <code>BaseSite.locale</code> attribute defined at extension <code>commerceservices</code>. */
	public static final String LOCALE = "locale";
	
	/** <i>Generated constant</i> - Attribute key of <code>BaseSite.channel</code> attribute defined at extension <code>commerceservices</code>. */
	public static final String CHANNEL = "channel";
	
	/** <i>Generated constant</i> - Attribute key of <code>BaseSite.defaultPromotionGroup</code> attribute defined at extension <code>commerceservices</code>. */
	public static final String DEFAULTPROMOTIONGROUP = "defaultPromotionGroup";
	
	/** <i>Generated constant</i> - Attribute key of <code>BaseSite.solrFacetSearchConfiguration</code> attribute defined at extension <code>commerceservices</code>. */
	public static final String SOLRFACETSEARCHCONFIGURATION = "solrFacetSearchConfiguration";
	
	/** <i>Generated constant</i> - Attribute key of <code>BaseSite.defaultStockLevelThreshold</code> attribute defined at extension <code>commerceservices</code>. */
	public static final String DEFAULTSTOCKLEVELTHRESHOLD = "defaultStockLevelThreshold";
	
	/** <i>Generated constant</i> - Attribute key of <code>BaseSite.productIndexType</code> attribute defined at extension <code>commerceservices</code>. */
	public static final String PRODUCTINDEXTYPE = "productIndexType";
	
	
	/**
	 * <i>Generated constructor</i> - Default constructor for generic creation.
	 */
	public BaseSiteModel()
	{
		super();
	}
	
	/**
	 * <i>Generated constructor</i> - Default constructor for creation with existing context
	 * @param ctx the model context to be injected, must not be null
	 */
	public BaseSiteModel(final ItemModelContext ctx)
	{
		super(ctx);
	}
	
	/**
	 * <i>Generated constructor</i> - Constructor with all mandatory attributes.
	 * @deprecated since 4.1.1 Please use the default constructor without parameters
	 * @param _uid initial attribute declared by type <code>BaseSite</code> at extension <code>basecommerce</code>
	 */
	@Deprecated(since = "4.1.1", forRemoval = true)
	public BaseSiteModel(final String _uid)
	{
		super();
		setUid(_uid);
	}
	
	/**
	 * <i>Generated constructor</i> - for all mandatory and initial attributes.
	 * @deprecated since 4.1.1 Please use the default constructor without parameters
	 * @param _owner initial attribute declared by type <code>Item</code> at extension <code>core</code>
	 * @param _uid initial attribute declared by type <code>BaseSite</code> at extension <code>basecommerce</code>
	 */
	@Deprecated(since = "4.1.1", forRemoval = true)
	public BaseSiteModel(final ItemModel _owner, final String _uid)
	{
		super();
		setOwner(_owner);
		setUid(_uid);
	}
	
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BaseSite.channel</code> attribute defined at extension <code>commerceservices</code>. 
	 * @return the channel - The channel for this site.
	 */
	@Accessor(qualifier = "channel", type = Accessor.Type.GETTER)
	public SiteChannel getChannel()
	{
		return getPersistenceContext().getPropertyValue(CHANNEL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BaseSite.defaultLanguage</code> attribute defined at extension <code>commerceservices</code>. 
	 * @return the defaultLanguage - The default language for the site.
	 */
	@Accessor(qualifier = "defaultLanguage", type = Accessor.Type.GETTER)
	public LanguageModel getDefaultLanguage()
	{
		return getPersistenceContext().getPropertyValue(DEFAULTLANGUAGE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BaseSite.defaultPromotionGroup</code> attribute defined at extension <code>commerceservices</code>. 
	 * @return the defaultPromotionGroup - The default promotion group for the site.
	 */
	@Accessor(qualifier = "defaultPromotionGroup", type = Accessor.Type.GETTER)
	public PromotionGroupModel getDefaultPromotionGroup()
	{
		return getPersistenceContext().getPropertyValue(DEFAULTPROMOTIONGROUP);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BaseSite.defaultStockLevelThreshold</code> attribute defined at extension <code>commerceservices</code>. 
	 * @return the defaultStockLevelThreshold - Indicates the threshold default value.
	 */
	@Accessor(qualifier = "defaultStockLevelThreshold", type = Accessor.Type.GETTER)
	public Integer getDefaultStockLevelThreshold()
	{
		return getPersistenceContext().getPropertyValue(DEFAULTSTOCKLEVELTHRESHOLD);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BaseSite.locale</code> attribute defined at extension <code>commerceservices</code>. 
	 * @return the locale - The locale to use for each language.
	 */
	@Accessor(qualifier = "locale", type = Accessor.Type.GETTER)
	public String getLocale()
	{
		return getLocale(null);
	}
	/**
	 * <i>Generated method</i> - Getter of the <code>BaseSite.locale</code> attribute defined at extension <code>commerceservices</code>. 
	 * @param loc the value localization key 
	 * @return the locale - The locale to use for each language.
	 * @throws IllegalArgumentException if localization key cannot be mapped to data language
	 */
	@Accessor(qualifier = "locale", type = Accessor.Type.GETTER)
	public String getLocale(final Locale loc)
	{
		return getPersistenceContext().getLocalizedValue(LOCALE, loc);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BaseSite.name</code> attribute defined at extension <code>basecommerce</code>. 
	 * @return the name
	 */
	@Accessor(qualifier = "name", type = Accessor.Type.GETTER)
	public String getName()
	{
		return getName(null);
	}
	/**
	 * <i>Generated method</i> - Getter of the <code>BaseSite.name</code> attribute defined at extension <code>basecommerce</code>. 
	 * @param loc the value localization key 
	 * @return the name
	 * @throws IllegalArgumentException if localization key cannot be mapped to data language
	 */
	@Accessor(qualifier = "name", type = Accessor.Type.GETTER)
	public String getName(final Locale loc)
	{
		return getPersistenceContext().getLocalizedValue(NAME, loc);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BaseSite.productIndexType</code> attribute defined at extension <code>commerceservices</code>. 
	 * @return the productIndexType
	 */
	@Accessor(qualifier = "productIndexType", type = Accessor.Type.GETTER)
	public SnIndexTypeModel getProductIndexType()
	{
		return getPersistenceContext().getPropertyValue(PRODUCTINDEXTYPE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BaseSite.solrFacetSearchConfiguration</code> attribute defined at extension <code>commerceservices</code>. 
	 * @return the solrFacetSearchConfiguration - Solr search configuration for this site.
	 */
	@Accessor(qualifier = "solrFacetSearchConfiguration", type = Accessor.Type.GETTER)
	public SolrFacetSearchConfigModel getSolrFacetSearchConfiguration()
	{
		return getPersistenceContext().getPropertyValue(SOLRFACETSEARCHCONFIGURATION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BaseSite.stores</code> attribute defined at extension <code>basecommerce</code>. 
	 * Consider using FlexibleSearchService::searchRelation for pagination support of large result sets.
	 * @return the stores
	 */
	@Accessor(qualifier = "stores", type = Accessor.Type.GETTER)
	public List<BaseStoreModel> getStores()
	{
		return getPersistenceContext().getPropertyValue(STORES);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BaseSite.theme</code> attribute defined at extension <code>commerceservices</code>. 
	 * @return the theme - The site theme that is used in this site.
	 */
	@Accessor(qualifier = "theme", type = Accessor.Type.GETTER)
	public SiteTheme getTheme()
	{
		return getPersistenceContext().getPropertyValue(THEME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BaseSite.uid</code> attribute defined at extension <code>basecommerce</code>. 
	 * @return the uid
	 */
	@Accessor(qualifier = "uid", type = Accessor.Type.GETTER)
	public String getUid()
	{
		return getPersistenceContext().getPropertyValue(UID);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>BaseSite.channel</code> attribute defined at extension <code>commerceservices</code>. 
	 *  
	 * @param value the channel - The channel for this site.
	 */
	@Accessor(qualifier = "channel", type = Accessor.Type.SETTER)
	public void setChannel(final SiteChannel value)
	{
		getPersistenceContext().setPropertyValue(CHANNEL, value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>BaseSite.defaultLanguage</code> attribute defined at extension <code>commerceservices</code>. 
	 *  
	 * @param value the defaultLanguage - The default language for the site.
	 */
	@Accessor(qualifier = "defaultLanguage", type = Accessor.Type.SETTER)
	public void setDefaultLanguage(final LanguageModel value)
	{
		getPersistenceContext().setPropertyValue(DEFAULTLANGUAGE, value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>BaseSite.defaultPromotionGroup</code> attribute defined at extension <code>commerceservices</code>. 
	 *  
	 * @param value the defaultPromotionGroup - The default promotion group for the site.
	 */
	@Accessor(qualifier = "defaultPromotionGroup", type = Accessor.Type.SETTER)
	public void setDefaultPromotionGroup(final PromotionGroupModel value)
	{
		getPersistenceContext().setPropertyValue(DEFAULTPROMOTIONGROUP, value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>BaseSite.defaultStockLevelThreshold</code> attribute defined at extension <code>commerceservices</code>. 
	 *  
	 * @param value the defaultStockLevelThreshold - Indicates the threshold default value.
	 */
	@Accessor(qualifier = "defaultStockLevelThreshold", type = Accessor.Type.SETTER)
	public void setDefaultStockLevelThreshold(final Integer value)
	{
		getPersistenceContext().setPropertyValue(DEFAULTSTOCKLEVELTHRESHOLD, value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>BaseSite.locale</code> attribute defined at extension <code>commerceservices</code>. 
	 *  
	 * @param value the locale - The locale to use for each language.
	 */
	@Accessor(qualifier = "locale", type = Accessor.Type.SETTER)
	public void setLocale(final String value)
	{
		setLocale(value,null);
	}
	/**
	 * <i>Generated method</i> - Setter of <code>BaseSite.locale</code> attribute defined at extension <code>commerceservices</code>. 
	 *  
	 * @param value the locale - The locale to use for each language.
	 * @param loc the value localization key 
	 * @throws IllegalArgumentException if localization key cannot be mapped to data language
	 */
	@Accessor(qualifier = "locale", type = Accessor.Type.SETTER)
	public void setLocale(final String value, final Locale loc)
	{
		getPersistenceContext().setLocalizedValue(LOCALE, loc, value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>BaseSite.name</code> attribute defined at extension <code>basecommerce</code>. 
	 *  
	 * @param value the name
	 */
	@Accessor(qualifier = "name", type = Accessor.Type.SETTER)
	public void setName(final String value)
	{
		setName(value,null);
	}
	/**
	 * <i>Generated method</i> - Setter of <code>BaseSite.name</code> attribute defined at extension <code>basecommerce</code>. 
	 *  
	 * @param value the name
	 * @param loc the value localization key 
	 * @throws IllegalArgumentException if localization key cannot be mapped to data language
	 */
	@Accessor(qualifier = "name", type = Accessor.Type.SETTER)
	public void setName(final String value, final Locale loc)
	{
		getPersistenceContext().setLocalizedValue(NAME, loc, value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>BaseSite.productIndexType</code> attribute defined at extension <code>commerceservices</code>. 
	 *  
	 * @param value the productIndexType
	 */
	@Accessor(qualifier = "productIndexType", type = Accessor.Type.SETTER)
	public void setProductIndexType(final SnIndexTypeModel value)
	{
		getPersistenceContext().setPropertyValue(PRODUCTINDEXTYPE, value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>BaseSite.solrFacetSearchConfiguration</code> attribute defined at extension <code>commerceservices</code>. 
	 *  
	 * @param value the solrFacetSearchConfiguration - Solr search configuration for this site.
	 */
	@Accessor(qualifier = "solrFacetSearchConfiguration", type = Accessor.Type.SETTER)
	public void setSolrFacetSearchConfiguration(final SolrFacetSearchConfigModel value)
	{
		getPersistenceContext().setPropertyValue(SOLRFACETSEARCHCONFIGURATION, value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>BaseSite.stores</code> attribute defined at extension <code>basecommerce</code>. 
	 *  
	 * @param value the stores
	 */
	@Accessor(qualifier = "stores", type = Accessor.Type.SETTER)
	public void setStores(final List<BaseStoreModel> value)
	{
		getPersistenceContext().setPropertyValue(STORES, value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>BaseSite.theme</code> attribute defined at extension <code>commerceservices</code>. 
	 *  
	 * @param value the theme - The site theme that is used in this site.
	 */
	@Accessor(qualifier = "theme", type = Accessor.Type.SETTER)
	public void setTheme(final SiteTheme value)
	{
		getPersistenceContext().setPropertyValue(THEME, value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>BaseSite.uid</code> attribute defined at extension <code>basecommerce</code>. 
	 *  
	 * @param value the uid
	 */
	@Accessor(qualifier = "uid", type = Accessor.Type.SETTER)
	public void setUid(final String value)
	{
		getPersistenceContext().setPropertyValue(UID, value);
	}
	
}
