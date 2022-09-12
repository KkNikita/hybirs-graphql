/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at Sep 12, 2022, 12:04:28 PM
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2022 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.commerce.queues.data;

import java.io.Serializable;
import java.util.List;
import org.commerce.queues.data.ProductExpressUpdateElementData;


import java.util.Objects;
public  class ProductExpressUpdateElementDataList  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** <i>Generated property</i> for <code>ProductExpressUpdateElementDataList.productExpressUpdateElements</code> property defined at extension <code>mockcommercewebservices</code>. */
	
	private List<ProductExpressUpdateElementData> productExpressUpdateElements;
	
	public ProductExpressUpdateElementDataList()
	{
		// default constructor
	}
	
	public void setProductExpressUpdateElements(final List<ProductExpressUpdateElementData> productExpressUpdateElements)
	{
		this.productExpressUpdateElements = productExpressUpdateElements;
	}

	public List<ProductExpressUpdateElementData> getProductExpressUpdateElements() 
	{
		return productExpressUpdateElements;
	}
	

}