/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at Sep 12, 2022, 12:04:28 PM
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2022 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.commerce.product.data;

import java.io.Serializable;
import de.hybris.platform.commercefacades.product.data.ReviewData;
import java.util.List;


import java.util.Objects;
public  class ReviewDataList  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** <i>Generated property</i> for <code>ReviewDataList.reviews</code> property defined at extension <code>mockcommercewebservices</code>. */
	
	private List<ReviewData> reviews;
	
	public ReviewDataList()
	{
		// default constructor
	}
	
	public void setReviews(final List<ReviewData> reviews)
	{
		this.reviews = reviews;
	}

	public List<ReviewData> getReviews() 
	{
		return reviews;
	}
	

}