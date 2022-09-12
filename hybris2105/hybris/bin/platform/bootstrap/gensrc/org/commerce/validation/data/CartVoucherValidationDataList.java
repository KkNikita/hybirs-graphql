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
import java.util.List;
import org.commerce.validation.data.CartVoucherValidationData;


import java.util.Objects;
public  class CartVoucherValidationDataList  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** <i>Generated property</i> for <code>CartVoucherValidationDataList.CartVoucherValidationDataList</code> property defined at extension <code>mockcommercewebservices</code>. */
	
	private List<CartVoucherValidationData> CartVoucherValidationDataList;
	
	public CartVoucherValidationDataList()
	{
		// default constructor
	}
	
	public void setCartVoucherValidationDataList(final List<CartVoucherValidationData> CartVoucherValidationDataList)
	{
		this.CartVoucherValidationDataList = CartVoucherValidationDataList;
	}

	public List<CartVoucherValidationData> getCartVoucherValidationDataList() 
	{
		return CartVoucherValidationDataList;
	}
	

}