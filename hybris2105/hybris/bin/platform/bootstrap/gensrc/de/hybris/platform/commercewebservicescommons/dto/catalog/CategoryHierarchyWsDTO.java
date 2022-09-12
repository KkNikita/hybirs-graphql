/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at Sep 12, 2022, 12:04:28 PM
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2022 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.commercewebservicescommons.dto.catalog;

import de.hybris.platform.commercewebservicescommons.dto.catalog.AbstractCatalogItemWsDTO;
import de.hybris.platform.commercewebservicescommons.dto.catalog.CategoryHierarchyWsDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;


import java.util.Objects;
/**
 * Representation of a Category Hierarchy
 */
@ApiModel(value="CategoryHierarchy", description="Representation of a Category Hierarchy")
public  class CategoryHierarchyWsDTO extends AbstractCatalogItemWsDTO 

{



	/** List of subcategory hierarchies<br/><br/><i>Generated property</i> for <code>CategoryHierarchyWsDTO.subcategories</code> property defined at extension <code>commercewebservicescommons</code>. */
@ApiModelProperty(name="subcategories", value="List of subcategory hierarchies") 	
	private List<CategoryHierarchyWsDTO> subcategories;
	
	public CategoryHierarchyWsDTO()
	{
		// default constructor
	}
	
	public void setSubcategories(final List<CategoryHierarchyWsDTO> subcategories)
	{
		this.subcategories = subcategories;
	}

	public List<CategoryHierarchyWsDTO> getSubcategories() 
	{
		return subcategories;
	}
	

}