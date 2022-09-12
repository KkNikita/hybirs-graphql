/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at Sep 12, 2022, 12:04:27 PM                   ---
 * ----------------------------------------------------------------
 *  
 * Copyright (c) 2022 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.store;

import de.hybris.bootstrap.annotations.Accessor;
import de.hybris.platform.basecommerce.enums.DistanceUnit;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.catalog.model.CatalogModel;
import de.hybris.platform.commerceservices.enums.PickupInStoreMode;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.core.model.order.delivery.DeliveryModeModel;
import de.hybris.platform.europe1.enums.UserTaxGroup;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.searchservices.model.SnIndexTypeModel;
import de.hybris.platform.servicelayer.model.ItemModelContext;
import de.hybris.platform.solrfacetsearch.model.config.SolrFacetSearchConfigModel;
import de.hybris.platform.storelocator.model.PointOfServiceModel;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * Generated model class for type BaseStore first defined at extension basecommerce.
 */
@SuppressWarnings("all")
public class BaseStoreModel extends ItemModel
{
	/**<i>Generated model type code constant.</i>*/
	public static final String _TYPECODE = "BaseStore";
	
	/**<i>Generated relation code constant for relation <code>StoresForCMSSite</code> defining source attribute <code>cmsSites</code> in extension <code>basecommerce</code>.</i>*/
	public static final String _STORESFORCMSSITE = "StoresForCMSSite";
	
	/**<i>Generated relation code constant for relation <code>BaseStore2CountryRel</code> defining source attribute <code>deliveryCountries</code> in extension <code>commerceservices</code>.</i>*/
	public static final String _BASESTORE2COUNTRYREL = "BaseStore2CountryRel";
	
	/**<i>Generated relation code constant for relation <code>BaseStore2BillingCountryRel</code> defining source attribute <code>billingCountries</code> in extension <code>commerceservices</code>.</i>*/
	public static final String _BASESTORE2BILLINGCOUNTRYREL = "BaseStore2BillingCountryRel";
	
	/** <i>Generated constant</i> - Attribute key of <code>BaseStore.uid</code> attribute defined at extension <code>basecommerce</code>. */
	public static final String UID = "uid";
	
	/** <i>Generated constant</i> - Attribute key of <code>BaseStore.name</code> attribute defined at extension <code>basecommerce</code>. */
	public static final String NAME = "name";
	
	/** <i>Generated constant</i> - Attribute key of <code>BaseStore.storelocatorDistanceUnit</code> attribute defined at extension <code>basecommerce</code>. */
	public static final String STORELOCATORDISTANCEUNIT = "storelocatorDistanceUnit";
	
	/** <i>Generated constant</i> - Attribute key of <code>BaseStore.cmsSites</code> attribute defined at extension <code>basecommerce</code>. */
	public static final String CMSSITES = "cmsSites";
	
	/** <i>Generated constant</i> - Attribute key of <code>BaseStore.catalogs</code> attribute defined at extension <code>basecommerce</code>. */
	public static final String CATALOGS = "catalogs";
	
	/** <i>Generated constant</i> - Attribute key of <code>BaseStore.pointsOfService</code> attribute defined at extension <code>basecommerce</code>. */
	public static final String POINTSOFSERVICE = "pointsOfService";
	
	/** <i>Generated constant</i> - Attribute key of <code>BaseStore.net</code> attribute defined at extension <code>commerceservices</code>. */
	public static final String NET = "net";
	
	/** <i>Generated constant</i> - Attribute key of <code>BaseStore.taxGroup</code> attribute defined at extension <code>commerceservices</code>. */
	public static final String TAXGROUP = "taxGroup";
	
	/** <i>Generated constant</i> - Attribute key of <code>BaseStore.defaultLanguage</code> attribute defined at extension <code>commerceservices</code>. */
	public static final String DEFAULTLANGUAGE = "defaultLanguage";
	
	/** <i>Generated constant</i> - Attribute key of <code>BaseStore.defaultCurrency</code> attribute defined at extension <code>commerceservices</code>. */
	public static final String DEFAULTCURRENCY = "defaultCurrency";
	
	/** <i>Generated constant</i> - Attribute key of <code>BaseStore.defaultDeliveryOrigin</code> attribute defined at extension <code>commerceservices</code>. */
	public static final String DEFAULTDELIVERYORIGIN = "defaultDeliveryOrigin";
	
	/** <i>Generated constant</i> - Attribute key of <code>BaseStore.solrFacetSearchConfiguration</code> attribute defined at extension <code>commerceservices</code>. */
	public static final String SOLRFACETSEARCHCONFIGURATION = "solrFacetSearchConfiguration";
	
	/** <i>Generated constant</i> - Attribute key of <code>BaseStore.submitOrderProcessCode</code> attribute defined at extension <code>commerceservices</code>. */
	public static final String SUBMITORDERPROCESSCODE = "submitOrderProcessCode";
	
	/** <i>Generated constant</i> - Attribute key of <code>BaseStore.createReturnProcessCode</code> attribute defined at extension <code>commerceservices</code>. */
	public static final String CREATERETURNPROCESSCODE = "createReturnProcessCode";
	
	/** <i>Generated constant</i> - Attribute key of <code>BaseStore.externalTaxEnabled</code> attribute defined at extension <code>commerceservices</code>. */
	public static final String EXTERNALTAXENABLED = "externalTaxEnabled";
	
	/** <i>Generated constant</i> - Attribute key of <code>BaseStore.pickupInStoreMode</code> attribute defined at extension <code>commerceservices</code>. */
	public static final String PICKUPINSTOREMODE = "pickupInStoreMode";
	
	/** <i>Generated constant</i> - Attribute key of <code>BaseStore.maxRadiusForPoSSearch</code> attribute defined at extension <code>commerceservices</code>. */
	public static final String MAXRADIUSFORPOSSEARCH = "maxRadiusForPoSSearch";
	
	/** <i>Generated constant</i> - Attribute key of <code>BaseStore.productSearchStrategy</code> attribute defined at extension <code>commerceservices</code>. */
	public static final String PRODUCTSEARCHSTRATEGY = "productSearchStrategy";
	
	/** <i>Generated constant</i> - Attribute key of <code>BaseStore.customerAllowedToIgnoreSuggestions</code> attribute defined at extension <code>commerceservices</code>. */
	public static final String CUSTOMERALLOWEDTOIGNORESUGGESTIONS = "customerAllowedToIgnoreSuggestions";
	
	/** <i>Generated constant</i> - Attribute key of <code>BaseStore.paymentProvider</code> attribute defined at extension <code>commerceservices</code>. */
	public static final String PAYMENTPROVIDER = "paymentProvider";
	
	/** <i>Generated constant</i> - Attribute key of <code>BaseStore.currencies</code> attribute defined at extension <code>commerceservices</code>. */
	public static final String CURRENCIES = "currencies";
	
	/** <i>Generated constant</i> - Attribute key of <code>BaseStore.languages</code> attribute defined at extension <code>commerceservices</code>. */
	public static final String LANGUAGES = "languages";
	
	/** <i>Generated constant</i> - Attribute key of <code>BaseStore.deliveryCountries</code> attribute defined at extension <code>commerceservices</code>. */
	public static final String DELIVERYCOUNTRIES = "deliveryCountries";
	
	/** <i>Generated constant</i> - Attribute key of <code>BaseStore.billingCountries</code> attribute defined at extension <code>commerceservices</code>. */
	public static final String BILLINGCOUNTRIES = "billingCountries";
	
	/** <i>Generated constant</i> - Attribute key of <code>BaseStore.warehouses</code> attribute defined at extension <code>commerceservices</code>. */
	public static final String WAREHOUSES = "warehouses";
	
	/** <i>Generated constant</i> - Attribute key of <code>BaseStore.deliveryModes</code> attribute defined at extension <code>commerceservices</code>. */
	public static final String DELIVERYMODES = "deliveryModes";
	
	/** <i>Generated constant</i> - Attribute key of <code>BaseStore.productIndexType</code> attribute defined at extension <code>commerceservices</code>. */
	public static final String PRODUCTINDEXTYPE = "productIndexType";
	
	
	/**
	 * <i>Generated constructor</i> - Default constructor for generic creation.
	 */
	public BaseStoreModel()
	{
		super();
	}
	
	/**
	 * <i>Generated constructor</i> - Default constructor for creation with existing context
	 * @param ctx the model context to be injected, must not be null
	 */
	public BaseStoreModel(final ItemModelContext ctx)
	{
		super(ctx);
	}
	
	/**
	 * <i>Generated constructor</i> - Constructor with all mandatory attributes.
	 * @deprecated since 4.1.1 Please use the default constructor without parameters
	 * @param _uid initial attribute declared by type <code>BaseStore</code> at extension <code>basecommerce</code>
	 */
	@Deprecated(since = "4.1.1", forRemoval = true)
	public BaseStoreModel(final String _uid)
	{
		super();
		setUid(_uid);
	}
	
	/**
	 * <i>Generated constructor</i> - for all mandatory and initial attributes.
	 * @deprecated since 4.1.1 Please use the default constructor without parameters
	 * @param _owner initial attribute declared by type <code>Item</code> at extension <code>core</code>
	 * @param _uid initial attribute declared by type <code>BaseStore</code> at extension <code>basecommerce</code>
	 */
	@Deprecated(since = "4.1.1", forRemoval = true)
	public BaseStoreModel(final ItemModel _owner, final String _uid)
	{
		super();
		setOwner(_owner);
		setUid(_uid);
	}
	
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BaseStore.billingCountries</code> attribute defined at extension <code>commerceservices</code>. 
	 * Consider using FlexibleSearchService::searchRelation for pagination support of large result sets.
	 * @return the billingCountries
	 */
	@Accessor(qualifier = "billingCountries", type = Accessor.Type.GETTER)
	public Collection<CountryModel> getBillingCountries()
	{
		return getPersistenceContext().getPropertyValue(BILLINGCOUNTRIES);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BaseStore.catalogs</code> attribute defined at extension <code>basecommerce</code>. 
	 * Consider using FlexibleSearchService::searchRelation for pagination support of large result sets.
	 * @return the catalogs
	 */
	@Accessor(qualifier = "catalogs", type = Accessor.Type.GETTER)
	public List<CatalogModel> getCatalogs()
	{
		return getPersistenceContext().getPropertyValue(CATALOGS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BaseStore.cmsSites</code> attribute defined at extension <code>basecommerce</code>. 
	 * Consider using FlexibleSearchService::searchRelation for pagination support of large result sets.
	 * @return the cmsSites
	 */
	@Accessor(qualifier = "cmsSites", type = Accessor.Type.GETTER)
	public Collection<BaseSiteModel> getCmsSites()
	{
		return getPersistenceContext().getPropertyValue(CMSSITES);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BaseStore.createReturnProcessCode</code> attribute defined at extension <code>commerceservices</code>. 
	 * @return the createReturnProcessCode - The process name for return business process.
	 */
	@Accessor(qualifier = "createReturnProcessCode", type = Accessor.Type.GETTER)
	public String getCreateReturnProcessCode()
	{
		return getPersistenceContext().getPropertyValue(CREATERETURNPROCESSCODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BaseStore.currencies</code> attribute defined at extension <code>commerceservices</code>. 
	 * Consider using FlexibleSearchService::searchRelation for pagination support of large result sets.
	 * @return the currencies
	 */
	@Accessor(qualifier = "currencies", type = Accessor.Type.GETTER)
	public Set<CurrencyModel> getCurrencies()
	{
		return getPersistenceContext().getPropertyValue(CURRENCIES);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BaseStore.defaultCurrency</code> attribute defined at extension <code>commerceservices</code>. 
	 * @return the defaultCurrency - The default currency for the site.
	 */
	@Accessor(qualifier = "defaultCurrency", type = Accessor.Type.GETTER)
	public CurrencyModel getDefaultCurrency()
	{
		return getPersistenceContext().getPropertyValue(DEFAULTCURRENCY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BaseStore.defaultDeliveryOrigin</code> attribute defined at extension <code>commerceservices</code>. 
	 * @return the defaultDeliveryOrigin - The default delivery origin for the site.
	 */
	@Accessor(qualifier = "defaultDeliveryOrigin", type = Accessor.Type.GETTER)
	public PointOfServiceModel getDefaultDeliveryOrigin()
	{
		return getPersistenceContext().getPropertyValue(DEFAULTDELIVERYORIGIN);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BaseStore.defaultLanguage</code> attribute defined at extension <code>commerceservices</code>. 
	 * @return the defaultLanguage - The default language for the site.
	 */
	@Accessor(qualifier = "defaultLanguage", type = Accessor.Type.GETTER)
	public LanguageModel getDefaultLanguage()
	{
		return getPersistenceContext().getPropertyValue(DEFAULTLANGUAGE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BaseStore.deliveryCountries</code> attribute defined at extension <code>commerceservices</code>. 
	 * Consider using FlexibleSearchService::searchRelation for pagination support of large result sets.
	 * @return the deliveryCountries
	 */
	@Accessor(qualifier = "deliveryCountries", type = Accessor.Type.GETTER)
	public Collection<CountryModel> getDeliveryCountries()
	{
		return getPersistenceContext().getPropertyValue(DELIVERYCOUNTRIES);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BaseStore.deliveryModes</code> attribute defined at extension <code>commerceservices</code>. 
	 * Consider using FlexibleSearchService::searchRelation for pagination support of large result sets.
	 * @return the deliveryModes
	 */
	@Accessor(qualifier = "deliveryModes", type = Accessor.Type.GETTER)
	public Set<DeliveryModeModel> getDeliveryModes()
	{
		return getPersistenceContext().getPropertyValue(DELIVERYMODES);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BaseStore.externalTaxEnabled</code> attribute defined at extension <code>commerceservices</code>. 
	 * @return the externalTaxEnabled - Determines whether the site should use external tax calculation
	 */
	@Accessor(qualifier = "externalTaxEnabled", type = Accessor.Type.GETTER)
	public Boolean getExternalTaxEnabled()
	{
		return getPersistenceContext().getPropertyValue(EXTERNALTAXENABLED);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BaseStore.languages</code> attribute defined at extension <code>commerceservices</code>. 
	 * Consider using FlexibleSearchService::searchRelation for pagination support of large result sets.
	 * @return the languages
	 */
	@Accessor(qualifier = "languages", type = Accessor.Type.GETTER)
	public Set<LanguageModel> getLanguages()
	{
		return getPersistenceContext().getPropertyValue(LANGUAGES);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BaseStore.maxRadiusForPoSSearch</code> attribute defined at extension <code>commerceservices</code>. 
	 * @return the maxRadiusForPoSSearch - The max radius when searching for PoS for a basestore.
	 */
	@Accessor(qualifier = "maxRadiusForPoSSearch", type = Accessor.Type.GETTER)
	public Double getMaxRadiusForPoSSearch()
	{
		return getPersistenceContext().getPropertyValue(MAXRADIUSFORPOSSEARCH);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BaseStore.name</code> attribute defined at extension <code>basecommerce</code>. 
	 * @return the name
	 */
	@Accessor(qualifier = "name", type = Accessor.Type.GETTER)
	public String getName()
	{
		return getName(null);
	}
	/**
	 * <i>Generated method</i> - Getter of the <code>BaseStore.name</code> attribute defined at extension <code>basecommerce</code>. 
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
	 * <i>Generated method</i> - Getter of the <code>BaseStore.paymentProvider</code> attribute defined at extension <code>commerceservices</code>. 
	 * @return the paymentProvider - The name of the payment provider that will get used for credit card subscriptions and any psp interaction
	 */
	@Accessor(qualifier = "paymentProvider", type = Accessor.Type.GETTER)
	public String getPaymentProvider()
	{
		return getPersistenceContext().getPropertyValue(PAYMENTPROVIDER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BaseStore.pickupInStoreMode</code> attribute defined at extension <code>commerceservices</code>. 
	 * @return the pickupInStoreMode - The pickup mode for this store.
	 */
	@Accessor(qualifier = "pickupInStoreMode", type = Accessor.Type.GETTER)
	public PickupInStoreMode getPickupInStoreMode()
	{
		return getPersistenceContext().getPropertyValue(PICKUPINSTOREMODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BaseStore.pointsOfService</code> attribute defined at extension <code>basecommerce</code>. 
	 * Consider using FlexibleSearchService::searchRelation for pagination support of large result sets.
	 * @return the pointsOfService
	 */
	@Accessor(qualifier = "pointsOfService", type = Accessor.Type.GETTER)
	public List<PointOfServiceModel> getPointsOfService()
	{
		return getPersistenceContext().getPropertyValue(POINTSOFSERVICE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BaseStore.productIndexType</code> attribute defined at extension <code>commerceservices</code>. 
	 * @return the productIndexType
	 */
	@Accessor(qualifier = "productIndexType", type = Accessor.Type.GETTER)
	public SnIndexTypeModel getProductIndexType()
	{
		return getPersistenceContext().getPropertyValue(PRODUCTINDEXTYPE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BaseStore.productSearchStrategy</code> attribute defined at extension <code>commerceservices</code>. 
	 * @return the productSearchStrategy - The bean name of the product search strategy.
	 */
	@Accessor(qualifier = "productSearchStrategy", type = Accessor.Type.GETTER)
	public String getProductSearchStrategy()
	{
		return getPersistenceContext().getPropertyValue(PRODUCTSEARCHSTRATEGY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BaseStore.solrFacetSearchConfiguration</code> attribute defined at extension <code>commerceservices</code>. 
	 * @return the solrFacetSearchConfiguration - Solr search configuration for this store.
	 */
	@Accessor(qualifier = "solrFacetSearchConfiguration", type = Accessor.Type.GETTER)
	public SolrFacetSearchConfigModel getSolrFacetSearchConfiguration()
	{
		return getPersistenceContext().getPropertyValue(SOLRFACETSEARCHCONFIGURATION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BaseStore.storelocatorDistanceUnit</code> attribute defined at extension <code>basecommerce</code>. 
	 * @return the storelocatorDistanceUnit - Specifies the unit the distances in the store will be presented
	 */
	@Accessor(qualifier = "storelocatorDistanceUnit", type = Accessor.Type.GETTER)
	public DistanceUnit getStorelocatorDistanceUnit()
	{
		return getPersistenceContext().getPropertyValue(STORELOCATORDISTANCEUNIT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BaseStore.submitOrderProcessCode</code> attribute defined at extension <code>commerceservices</code>. 
	 * @return the submitOrderProcessCode - The process name for fullfilment business process.
	 */
	@Accessor(qualifier = "submitOrderProcessCode", type = Accessor.Type.GETTER)
	public String getSubmitOrderProcessCode()
	{
		return getPersistenceContext().getPropertyValue(SUBMITORDERPROCESSCODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BaseStore.taxGroup</code> attribute defined at extension <code>commerceservices</code>. 
	 * @return the taxGroup - The site specific tax group.
	 */
	@Accessor(qualifier = "taxGroup", type = Accessor.Type.GETTER)
	public UserTaxGroup getTaxGroup()
	{
		return getPersistenceContext().getPropertyValue(TAXGROUP);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BaseStore.uid</code> attribute defined at extension <code>basecommerce</code>. 
	 * @return the uid
	 */
	@Accessor(qualifier = "uid", type = Accessor.Type.GETTER)
	public String getUid()
	{
		return getPersistenceContext().getPropertyValue(UID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BaseStore.warehouses</code> attribute defined at extension <code>commerceservices</code>. 
	 * Consider using FlexibleSearchService::searchRelation for pagination support of large result sets.
	 * @return the warehouses
	 */
	@Accessor(qualifier = "warehouses", type = Accessor.Type.GETTER)
	public List<WarehouseModel> getWarehouses()
	{
		return getPersistenceContext().getPropertyValue(WAREHOUSES);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BaseStore.customerAllowedToIgnoreSuggestions</code> attribute defined at extension <code>commerceservices</code>. 
	 * @return the customerAllowedToIgnoreSuggestions - Determines whether the customer is allowed to bypass the list of suggested addresses and proceed with their unverified entry.
	 */
	@Accessor(qualifier = "customerAllowedToIgnoreSuggestions", type = Accessor.Type.GETTER)
	public boolean isCustomerAllowedToIgnoreSuggestions()
	{
		return toPrimitive((Boolean)getPersistenceContext().getPropertyValue(CUSTOMERALLOWEDTOIGNORESUGGESTIONS));
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BaseStore.net</code> attribute defined at extension <code>commerceservices</code>. 
	 * @return the net - Determines if the prices are treated as net or gross prices in the given base
	 * 							store.
	 */
	@Accessor(qualifier = "net", type = Accessor.Type.GETTER)
	public boolean isNet()
	{
		return toPrimitive((Boolean)getPersistenceContext().getPropertyValue(NET));
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>BaseStore.billingCountries</code> attribute defined at extension <code>commerceservices</code>. 
	 *  
	 * @param value the billingCountries
	 */
	@Accessor(qualifier = "billingCountries", type = Accessor.Type.SETTER)
	public void setBillingCountries(final Collection<CountryModel> value)
	{
		getPersistenceContext().setPropertyValue(BILLINGCOUNTRIES, value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>BaseStore.catalogs</code> attribute defined at extension <code>basecommerce</code>. 
	 *  
	 * @param value the catalogs
	 */
	@Accessor(qualifier = "catalogs", type = Accessor.Type.SETTER)
	public void setCatalogs(final List<CatalogModel> value)
	{
		getPersistenceContext().setPropertyValue(CATALOGS, value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>BaseStore.cmsSites</code> attribute defined at extension <code>basecommerce</code>. 
	 *  
	 * @param value the cmsSites
	 */
	@Accessor(qualifier = "cmsSites", type = Accessor.Type.SETTER)
	public void setCmsSites(final Collection<BaseSiteModel> value)
	{
		getPersistenceContext().setPropertyValue(CMSSITES, value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>BaseStore.createReturnProcessCode</code> attribute defined at extension <code>commerceservices</code>. 
	 *  
	 * @param value the createReturnProcessCode - The process name for return business process.
	 */
	@Accessor(qualifier = "createReturnProcessCode", type = Accessor.Type.SETTER)
	public void setCreateReturnProcessCode(final String value)
	{
		getPersistenceContext().setPropertyValue(CREATERETURNPROCESSCODE, value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>BaseStore.currencies</code> attribute defined at extension <code>commerceservices</code>. 
	 *  
	 * @param value the currencies
	 */
	@Accessor(qualifier = "currencies", type = Accessor.Type.SETTER)
	public void setCurrencies(final Set<CurrencyModel> value)
	{
		getPersistenceContext().setPropertyValue(CURRENCIES, value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>BaseStore.customerAllowedToIgnoreSuggestions</code> attribute defined at extension <code>commerceservices</code>. 
	 *  
	 * @param value the customerAllowedToIgnoreSuggestions - Determines whether the customer is allowed to bypass the list of suggested addresses and proceed with their unverified entry.
	 */
	@Accessor(qualifier = "customerAllowedToIgnoreSuggestions", type = Accessor.Type.SETTER)
	public void setCustomerAllowedToIgnoreSuggestions(final boolean value)
	{
		getPersistenceContext().setPropertyValue(CUSTOMERALLOWEDTOIGNORESUGGESTIONS, toObject(value));
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>BaseStore.defaultCurrency</code> attribute defined at extension <code>commerceservices</code>. 
	 *  
	 * @param value the defaultCurrency - The default currency for the site.
	 */
	@Accessor(qualifier = "defaultCurrency", type = Accessor.Type.SETTER)
	public void setDefaultCurrency(final CurrencyModel value)
	{
		getPersistenceContext().setPropertyValue(DEFAULTCURRENCY, value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>BaseStore.defaultDeliveryOrigin</code> attribute defined at extension <code>commerceservices</code>. 
	 *  
	 * @param value the defaultDeliveryOrigin - The default delivery origin for the site.
	 */
	@Accessor(qualifier = "defaultDeliveryOrigin", type = Accessor.Type.SETTER)
	public void setDefaultDeliveryOrigin(final PointOfServiceModel value)
	{
		getPersistenceContext().setPropertyValue(DEFAULTDELIVERYORIGIN, value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>BaseStore.defaultLanguage</code> attribute defined at extension <code>commerceservices</code>. 
	 *  
	 * @param value the defaultLanguage - The default language for the site.
	 */
	@Accessor(qualifier = "defaultLanguage", type = Accessor.Type.SETTER)
	public void setDefaultLanguage(final LanguageModel value)
	{
		getPersistenceContext().setPropertyValue(DEFAULTLANGUAGE, value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>BaseStore.deliveryCountries</code> attribute defined at extension <code>commerceservices</code>. 
	 *  
	 * @param value the deliveryCountries
	 */
	@Accessor(qualifier = "deliveryCountries", type = Accessor.Type.SETTER)
	public void setDeliveryCountries(final Collection<CountryModel> value)
	{
		getPersistenceContext().setPropertyValue(DELIVERYCOUNTRIES, value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>BaseStore.deliveryModes</code> attribute defined at extension <code>commerceservices</code>. 
	 *  
	 * @param value the deliveryModes
	 */
	@Accessor(qualifier = "deliveryModes", type = Accessor.Type.SETTER)
	public void setDeliveryModes(final Set<DeliveryModeModel> value)
	{
		getPersistenceContext().setPropertyValue(DELIVERYMODES, value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>BaseStore.externalTaxEnabled</code> attribute defined at extension <code>commerceservices</code>. 
	 *  
	 * @param value the externalTaxEnabled - Determines whether the site should use external tax calculation
	 */
	@Accessor(qualifier = "externalTaxEnabled", type = Accessor.Type.SETTER)
	public void setExternalTaxEnabled(final Boolean value)
	{
		getPersistenceContext().setPropertyValue(EXTERNALTAXENABLED, value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>BaseStore.languages</code> attribute defined at extension <code>commerceservices</code>. 
	 *  
	 * @param value the languages
	 */
	@Accessor(qualifier = "languages", type = Accessor.Type.SETTER)
	public void setLanguages(final Set<LanguageModel> value)
	{
		getPersistenceContext().setPropertyValue(LANGUAGES, value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>BaseStore.maxRadiusForPoSSearch</code> attribute defined at extension <code>commerceservices</code>. 
	 *  
	 * @param value the maxRadiusForPoSSearch - The max radius when searching for PoS for a basestore.
	 */
	@Accessor(qualifier = "maxRadiusForPoSSearch", type = Accessor.Type.SETTER)
	public void setMaxRadiusForPoSSearch(final Double value)
	{
		getPersistenceContext().setPropertyValue(MAXRADIUSFORPOSSEARCH, value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>BaseStore.name</code> attribute defined at extension <code>basecommerce</code>. 
	 *  
	 * @param value the name
	 */
	@Accessor(qualifier = "name", type = Accessor.Type.SETTER)
	public void setName(final String value)
	{
		setName(value,null);
	}
	/**
	 * <i>Generated method</i> - Setter of <code>BaseStore.name</code> attribute defined at extension <code>basecommerce</code>. 
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
	 * <i>Generated method</i> - Setter of <code>BaseStore.net</code> attribute defined at extension <code>commerceservices</code>. 
	 *  
	 * @param value the net - Determines if the prices are treated as net or gross prices in the given base
	 * 							store.
	 */
	@Accessor(qualifier = "net", type = Accessor.Type.SETTER)
	public void setNet(final boolean value)
	{
		getPersistenceContext().setPropertyValue(NET, toObject(value));
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>BaseStore.paymentProvider</code> attribute defined at extension <code>commerceservices</code>. 
	 *  
	 * @param value the paymentProvider - The name of the payment provider that will get used for credit card subscriptions and any psp interaction
	 */
	@Accessor(qualifier = "paymentProvider", type = Accessor.Type.SETTER)
	public void setPaymentProvider(final String value)
	{
		getPersistenceContext().setPropertyValue(PAYMENTPROVIDER, value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>BaseStore.pickupInStoreMode</code> attribute defined at extension <code>commerceservices</code>. 
	 *  
	 * @param value the pickupInStoreMode - The pickup mode for this store.
	 */
	@Accessor(qualifier = "pickupInStoreMode", type = Accessor.Type.SETTER)
	public void setPickupInStoreMode(final PickupInStoreMode value)
	{
		getPersistenceContext().setPropertyValue(PICKUPINSTOREMODE, value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>BaseStore.pointsOfService</code> attribute defined at extension <code>basecommerce</code>. 
	 *  
	 * @param value the pointsOfService
	 */
	@Accessor(qualifier = "pointsOfService", type = Accessor.Type.SETTER)
	public void setPointsOfService(final List<PointOfServiceModel> value)
	{
		getPersistenceContext().setPropertyValue(POINTSOFSERVICE, value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>BaseStore.productIndexType</code> attribute defined at extension <code>commerceservices</code>. 
	 *  
	 * @param value the productIndexType
	 */
	@Accessor(qualifier = "productIndexType", type = Accessor.Type.SETTER)
	public void setProductIndexType(final SnIndexTypeModel value)
	{
		getPersistenceContext().setPropertyValue(PRODUCTINDEXTYPE, value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>BaseStore.productSearchStrategy</code> attribute defined at extension <code>commerceservices</code>. 
	 *  
	 * @param value the productSearchStrategy - The bean name of the product search strategy.
	 */
	@Accessor(qualifier = "productSearchStrategy", type = Accessor.Type.SETTER)
	public void setProductSearchStrategy(final String value)
	{
		getPersistenceContext().setPropertyValue(PRODUCTSEARCHSTRATEGY, value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>BaseStore.solrFacetSearchConfiguration</code> attribute defined at extension <code>commerceservices</code>. 
	 *  
	 * @param value the solrFacetSearchConfiguration - Solr search configuration for this store.
	 */
	@Accessor(qualifier = "solrFacetSearchConfiguration", type = Accessor.Type.SETTER)
	public void setSolrFacetSearchConfiguration(final SolrFacetSearchConfigModel value)
	{
		getPersistenceContext().setPropertyValue(SOLRFACETSEARCHCONFIGURATION, value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>BaseStore.storelocatorDistanceUnit</code> attribute defined at extension <code>basecommerce</code>. 
	 *  
	 * @param value the storelocatorDistanceUnit - Specifies the unit the distances in the store will be presented
	 */
	@Accessor(qualifier = "storelocatorDistanceUnit", type = Accessor.Type.SETTER)
	public void setStorelocatorDistanceUnit(final DistanceUnit value)
	{
		getPersistenceContext().setPropertyValue(STORELOCATORDISTANCEUNIT, value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>BaseStore.submitOrderProcessCode</code> attribute defined at extension <code>commerceservices</code>. 
	 *  
	 * @param value the submitOrderProcessCode - The process name for fullfilment business process.
	 */
	@Accessor(qualifier = "submitOrderProcessCode", type = Accessor.Type.SETTER)
	public void setSubmitOrderProcessCode(final String value)
	{
		getPersistenceContext().setPropertyValue(SUBMITORDERPROCESSCODE, value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>BaseStore.taxGroup</code> attribute defined at extension <code>commerceservices</code>. 
	 *  
	 * @param value the taxGroup - The site specific tax group.
	 */
	@Accessor(qualifier = "taxGroup", type = Accessor.Type.SETTER)
	public void setTaxGroup(final UserTaxGroup value)
	{
		getPersistenceContext().setPropertyValue(TAXGROUP, value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>BaseStore.uid</code> attribute defined at extension <code>basecommerce</code>. 
	 *  
	 * @param value the uid
	 */
	@Accessor(qualifier = "uid", type = Accessor.Type.SETTER)
	public void setUid(final String value)
	{
		getPersistenceContext().setPropertyValue(UID, value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>BaseStore.warehouses</code> attribute defined at extension <code>commerceservices</code>. 
	 *  
	 * @param value the warehouses
	 */
	@Accessor(qualifier = "warehouses", type = Accessor.Type.SETTER)
	public void setWarehouses(final List<WarehouseModel> value)
	{
		getPersistenceContext().setPropertyValue(WAREHOUSES, value);
	}
	
}
