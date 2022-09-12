/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.05.12 at 07:19:30 PM EDT 
//



package org.cxml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "itemID",
    "punchoutDetail"
})
@XmlRootElement(name = "IndexItemPunchout")
public class IndexItemPunchout {

    @XmlElement(name = "ItemID", required = true)
    protected ItemID itemID;
    @XmlElement(name = "PunchoutDetail", required = true)
    protected PunchoutDetail punchoutDetail;

    /**
     * Gets the value of the itemID property.
     * 
     * @return
     *     possible object is
     *     {@link ItemID }
     *     
     */
    public ItemID getItemID() {
        return itemID;
    }

    /**
     * Sets the value of the itemID property.
     * 
     * @param value
     *     allowed object is
     *     {@link ItemID }
     *     
     */
    public void setItemID(ItemID value) {
        this.itemID = value;
    }

    /**
     * Gets the value of the punchoutDetail property.
     * 
     * @return
     *     possible object is
     *     {@link PunchoutDetail }
     *     
     */
    public PunchoutDetail getPunchoutDetail() {
        return punchoutDetail;
    }

    /**
     * Sets the value of the punchoutDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link PunchoutDetail }
     *     
     */
    public void setPunchoutDetail(PunchoutDetail value) {
        this.punchoutDetail = value;
    }

}
