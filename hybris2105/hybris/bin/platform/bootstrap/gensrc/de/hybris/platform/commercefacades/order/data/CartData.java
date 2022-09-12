/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at Sep 12, 2022, 12:04:28 PM
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2022 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.commercefacades.order.data;

import de.hybris.platform.commercefacades.order.data.AbstractOrderData;
import de.hybris.platform.commercefacades.product.data.PromotionResultData;
import de.hybris.platform.commercefacades.quote.data.QuoteData;
import de.hybris.platform.commercefacades.user.data.PrincipalData;
import java.util.Date;
import java.util.List;


import java.util.Objects;
public  class CartData extends AbstractOrderData 

{



	/** <i>Generated property</i> for <code>CartData.potentialOrderPromotions</code> property defined at extension <code>commercefacades</code>. */
	
	private List<PromotionResultData> potentialOrderPromotions;

	/** <i>Generated property</i> for <code>CartData.potentialProductPromotions</code> property defined at extension <code>commercefacades</code>. */
	
	private List<PromotionResultData> potentialProductPromotions;

	/** <i>Generated property</i> for <code>CartData.saveTime</code> property defined at extension <code>commercefacades</code>. */
	
	private Date saveTime;

	/** <i>Generated property</i> for <code>CartData.savedBy</code> property defined at extension <code>commercefacades</code>. */
	
	private PrincipalData savedBy;

	/** <i>Generated property</i> for <code>CartData.quoteData</code> property defined at extension <code>commercefacades</code>. */
	
	private QuoteData quoteData;
	
	public CartData()
	{
		// default constructor
	}
	
	public void setPotentialOrderPromotions(final List<PromotionResultData> potentialOrderPromotions)
	{
		this.potentialOrderPromotions = potentialOrderPromotions;
	}

	public List<PromotionResultData> getPotentialOrderPromotions() 
	{
		return potentialOrderPromotions;
	}
	
	public void setPotentialProductPromotions(final List<PromotionResultData> potentialProductPromotions)
	{
		this.potentialProductPromotions = potentialProductPromotions;
	}

	public List<PromotionResultData> getPotentialProductPromotions() 
	{
		return potentialProductPromotions;
	}
	
	public void setSaveTime(final Date saveTime)
	{
		this.saveTime = saveTime;
	}

	public Date getSaveTime() 
	{
		return saveTime;
	}
	
	public void setSavedBy(final PrincipalData savedBy)
	{
		this.savedBy = savedBy;
	}

	public PrincipalData getSavedBy() 
	{
		return savedBy;
	}
	
	public void setQuoteData(final QuoteData quoteData)
	{
		this.quoteData = quoteData;
	}

	public QuoteData getQuoteData() 
	{
		return quoteData;
	}
	

}