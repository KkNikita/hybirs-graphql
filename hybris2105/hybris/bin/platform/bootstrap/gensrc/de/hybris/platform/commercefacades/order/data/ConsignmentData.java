/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at Sep 12, 2022, 12:04:28 PM
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2022 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.commercefacades.order.data;

import java.io.Serializable;
import de.hybris.platform.basecommerce.enums.ConsignmentStatus;
import de.hybris.platform.commercefacades.order.data.ConsignmentEntryData;
import de.hybris.platform.commercefacades.storelocator.data.PointOfServiceData;
import de.hybris.platform.commercefacades.user.data.AddressData;
import java.util.Date;
import java.util.List;


import java.util.Objects;
public  class ConsignmentData  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** <i>Generated property</i> for <code>ConsignmentData.code</code> property defined at extension <code>commercefacades</code>. */
	
	private String code;

	/** <i>Generated property</i> for <code>ConsignmentData.trackingID</code> property defined at extension <code>commercefacades</code>. */
	
	private String trackingID;

	/** <i>Generated property</i> for <code>ConsignmentData.status</code> property defined at extension <code>commercefacades</code>. */
	
	private ConsignmentStatus status;

	/** <i>Generated property</i> for <code>ConsignmentData.statusDate</code> property defined at extension <code>commercefacades</code>. */
	
	private Date statusDate;

	/** <i>Generated property</i> for <code>ConsignmentData.entries</code> property defined at extension <code>commercefacades</code>. */
	
	private List<ConsignmentEntryData> entries;

	/** <i>Generated property</i> for <code>ConsignmentData.shippingAddress</code> property defined at extension <code>commercefacades</code>. */
	
	private AddressData shippingAddress;

	/** <i>Generated property</i> for <code>ConsignmentData.deliveryPointOfService</code> property defined at extension <code>commercefacades</code>. */
	
	private PointOfServiceData deliveryPointOfService;
	
	public ConsignmentData()
	{
		// default constructor
	}
	
	public void setCode(final String code)
	{
		this.code = code;
	}

	public String getCode() 
	{
		return code;
	}
	
	public void setTrackingID(final String trackingID)
	{
		this.trackingID = trackingID;
	}

	public String getTrackingID() 
	{
		return trackingID;
	}
	
	public void setStatus(final ConsignmentStatus status)
	{
		this.status = status;
	}

	public ConsignmentStatus getStatus() 
	{
		return status;
	}
	
	public void setStatusDate(final Date statusDate)
	{
		this.statusDate = statusDate;
	}

	public Date getStatusDate() 
	{
		return statusDate;
	}
	
	public void setEntries(final List<ConsignmentEntryData> entries)
	{
		this.entries = entries;
	}

	public List<ConsignmentEntryData> getEntries() 
	{
		return entries;
	}
	
	public void setShippingAddress(final AddressData shippingAddress)
	{
		this.shippingAddress = shippingAddress;
	}

	public AddressData getShippingAddress() 
	{
		return shippingAddress;
	}
	
	public void setDeliveryPointOfService(final PointOfServiceData deliveryPointOfService)
	{
		this.deliveryPointOfService = deliveryPointOfService;
	}

	public PointOfServiceData getDeliveryPointOfService() 
	{
		return deliveryPointOfService;
	}
	

}