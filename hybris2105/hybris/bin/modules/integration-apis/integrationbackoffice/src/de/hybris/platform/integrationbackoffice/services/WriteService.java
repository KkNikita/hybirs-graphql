/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.integrationbackoffice.services;

import de.hybris.platform.core.model.type.CollectionTypeModel;
import de.hybris.platform.core.model.type.ComposedTypeModel;
import de.hybris.platform.core.model.type.MapTypeModel;
import de.hybris.platform.core.model.type.TypeModel;
import de.hybris.platform.integrationbackoffice.dto.AbstractListItemDTO;
import de.hybris.platform.integrationbackoffice.dto.ListItemAttributeDTO;
import de.hybris.platform.integrationbackoffice.dto.ListItemClassificationAttributeDTO;
import de.hybris.platform.integrationbackoffice.dto.ListItemVirtualAttributeDTO;
import de.hybris.platform.integrationservices.model.AbstractIntegrationObjectItemAttributeModel;
import de.hybris.platform.integrationservices.model.IntegrationObjectItemAttributeModel;
import de.hybris.platform.integrationservices.model.IntegrationObjectItemClassificationAttributeModel;
import de.hybris.platform.integrationservices.model.IntegrationObjectItemModel;
import de.hybris.platform.integrationservices.model.IntegrationObjectItemVirtualAttributeModel;
import de.hybris.platform.integrationservices.model.IntegrationObjectModel;
import de.hybris.platform.integrationservices.model.IntegrationObjectVirtualAttributeDescriptorModel;
import de.hybris.platform.integrationservices.util.Log;
import de.hybris.platform.odata2webservices.enums.IntegrationType;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;

/**
 * Handles the write requests of the extension's widgets
 */
public class WriteService
{
	private static final Logger LOG = Log.getLogger(WriteService.class);
	private ModelService modelService;
	private ReadService readService;

	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	public void setReadService(final ReadService readService)
	{
		this.readService = readService;
	}

	/**
	 * Creates a base integration object.
	 *
	 * @param name the integration object's name
	 * @param type the type of integration object (Inbound, Outbound)
	 * @return an empty integration object
	 */
	public IntegrationObjectModel createIntegrationObject(final String name, final IntegrationType type)
	{
		final IntegrationObjectModel ioModel = modelService.create(IntegrationObjectModel.class);
		ioModel.setCode(name);
		ioModel.setIntegrationType(type);
		ioModel.setItems(Collections.emptySet());
		LOG.info("Integration object {} created", ioModel.getCode());
		return ioModel;
	}

	/**
	 * Creates, persists and returns a Virtual Attribute Descriptor.
	 *
	 * @param code the descriptor's code
	 * @param logicLocation the logicLocation of a script. eg, model://modelScript
	 * @param type the type of Virtual Attribute Descriptor. eg, java.lang.String
	 * @return newly created Virtual Attribute Descriptor
	 */
	public IntegrationObjectVirtualAttributeDescriptorModel persistVirtualAttributeDescriptor(final String code,
	                                                                                          final String logicLocation,
	                                                                                          final String type)
	{
		final IntegrationObjectVirtualAttributeDescriptorModel descriptorModel = modelService.create(
				IntegrationObjectVirtualAttributeDescriptorModel.class);
		descriptorModel.setCode(code);
		descriptorModel.setLogicLocation(logicLocation);
		descriptorModel.setType(readService.getAtomicTypeModelByCode(type));
		modelService.save(descriptorModel);
		LOG.info("IntegrationObjectVirtualAttributeDescriptorModel {} persisted", descriptorModel.getCode());
		return descriptorModel;
	}

	/**
	 * Clears the old definition of an integration object. The integration object is not deleted, this method simply removes all
	 * of its items and their associated attributes.
	 *
	 * @param integrationObject the integration object to be cleared
	 * @return a cleared integration object
	 */
	public IntegrationObjectModel clearIntegrationObject(final IntegrationObjectModel integrationObject)
	{
		final Set<IntegrationObjectItemModel> items = new HashSet<>(integrationObject.getItems());
		items.forEach(item -> {
			if (item.getPk() != null)
			{
				modelService.removeAll(item.getAttributes());
				modelService.remove(item);
			}
		});
		integrationObject.setItems(null);
		items.clear();
		return integrationObject;
	}

	/**
	 * Builds an integration object's items and their associated attributes from a map.
	 *
	 * @param integrationObject the integration object to build
	 * @param objectMap         the map used to build the integration object's content
	 * @param rootCode          code of root item
	 * @return a built integration object
	 */
	public IntegrationObjectModel createDefinitions(final IntegrationObjectModel integrationObject,
	                                                final Map<ComposedTypeModel, List<AbstractListItemDTO>> objectMap,
	                                                final String rootCode)
	{
		final IntegrationObjectModel clearedIntegrationObject = clearIntegrationObject(integrationObject);
		final Set<IntegrationObjectItemModel> items = new HashSet<>();
		objectMap.forEach((key, value) -> {
			final IntegrationObjectItemModel item = buildIntegrationObjectItem(clearedIntegrationObject, key, rootCode);
			final Set<ListItemAttributeDTO> attributeDTOs = new HashSet<>();
			final Set<ListItemClassificationAttributeDTO> classificationAttributesDTOs = new HashSet<>();
			final Set<ListItemVirtualAttributeDTO> virtualAttributeDTOs = new HashSet<>();
			categorizeDTOs(value, attributeDTOs, classificationAttributesDTOs, virtualAttributeDTOs);
			item.setAttributes(buildIntegrationObjectItemAttribute(attributeDTOs, item));
			item.setClassificationAttributes(
					buildIntegrationObjectItemClassificationAttribute(classificationAttributesDTOs, item));
			item.setVirtualAttributes(buildIntegrationObjectItemVirtualAttribute(virtualAttributeDTOs, item));
			items.add(item);
		});

		clearedIntegrationObject.setItems(items);
		return setReturnIntegrationObjectItem(clearedIntegrationObject, objectMap);
	}

	/**
	 * Clones an integration object's items and their associated attributes from an original integration object.
	 *
	 * @param integrationObjectModel      the original integration object to clone
	 * @param integrationObjectModelClone the integration object clone to add cloned items and attributes onto
	 * @return an integration object with its contents cloned
	 */
	public IntegrationObjectModel cloneIntegrationObject(final IntegrationObjectModel integrationObjectModel,
	                                                     final IntegrationObjectModel integrationObjectModelClone)
	{
		final Map<String, IntegrationObjectItemModel> itemsCloneMap =
				integrationObjectModel.getItems().stream().collect(Collectors.toMap(item -> item.getType().getCode(),
						(IntegrationObjectItemModel item) -> {
							final IntegrationObjectItemModel itemClone = modelService.create(IntegrationObjectItemModel.class);
							itemClone.setCode(item.getCode());
							itemClone.setIntegrationObject(integrationObjectModelClone);
							itemClone.setType(item.getType());
							itemClone.setRoot(item.getRoot());
							itemClone.setItemTypeMatch(item.getItemTypeMatch());
							return itemClone;
						}));

		final Set<IntegrationObjectItemModel> itemsClone = new HashSet<>();
		integrationObjectModel.getItems().forEach((IntegrationObjectItemModel item) ->
				itemsClone.add(cloneIntegrationObjectItem(item, itemsCloneMap)));
		integrationObjectModelClone.setItems(itemsClone);
		return integrationObjectModelClone;
	}

	/**
	 * Saves an integration object with the model service.
	 *
	 * @param integrationObject the integration object to be saved
	 */
	public void persistIntegrationObject(final IntegrationObjectModel integrationObject)
	{
		modelService.save(integrationObject);
		LOG.info("Integration object {} updated", integrationObject.getCode());
	}

	/**
	 * Saves an integration object item with the model service.
	 *
	 * @param integrationObjectItemModels the integration object item to be saved
	 */
	public void persistIntegrationObjectItems(final Collection<IntegrationObjectItemModel> integrationObjectItemModels)
	{
		modelService.saveAll(integrationObjectItemModels);
		LOG.info("Integration object item collection updated.");
	}

	/**
	 * Delete an integration object from the type system
	 *
	 * @param integrationObject the integration object to be deleted
	 */
	public void deleteIntegrationObject(final IntegrationObjectModel integrationObject)
	{
		modelService.remove(integrationObject.getPk());
		LOG.info("Integration object {} deleted", integrationObject.getCode());
	}

	IntegrationObjectItemModel buildIntegrationObjectItem(final IntegrationObjectModel integrationObject,
	                                                      final ComposedTypeModel composedType, final String rootCode)
	{
		final IntegrationObjectItemModel item = modelService.create(IntegrationObjectItemModel.class);
		item.setCode(composedType.getCode());
		item.setIntegrationObject(integrationObject);
		item.setType(composedType);
		item.setRoot(composedType.getCode().equals(rootCode));
		return item;
	}

	void categorizeDTOs(final List<AbstractListItemDTO> value, final Set<ListItemAttributeDTO> attributeDTOs,
	                    final Set<ListItemClassificationAttributeDTO> classificationAttributesDTOs,
	                    final Set<ListItemVirtualAttributeDTO> virtualAttributeDTOs)
	{
		for (final AbstractListItemDTO dto : value)
		{
			if (dto instanceof ListItemAttributeDTO)
			{
				attributeDTOs.add((ListItemAttributeDTO) dto);
			}
			else if (dto instanceof ListItemClassificationAttributeDTO)
			{
				classificationAttributesDTOs.add((ListItemClassificationAttributeDTO) dto);
			}
			else if (dto instanceof ListItemVirtualAttributeDTO)
			{
				virtualAttributeDTOs.add((ListItemVirtualAttributeDTO) dto);
			}
		}
	}

	Set<IntegrationObjectItemAttributeModel> buildIntegrationObjectItemAttribute(final Set<ListItemAttributeDTO> dtos,
	                                                                             final IntegrationObjectItemModel item)
	{
		return dtos.stream().map(dto -> {
			final IntegrationObjectItemAttributeModel attribute = modelService.create(IntegrationObjectItemAttributeModel.class);
			attribute.setAttributeDescriptor(dto.getAttributeDescriptor());
			attribute.setAttributeName(dto.getAlias());
			attribute.setIntegrationObjectItem(item);
			attribute.setUnique(dto.getAttributeDescriptor().getUnique() || dto.isCustomUnique());
			attribute.setReturnIntegrationObjectItem(null);
			attribute.setAutoCreate(dto.isAutocreate());
			return attribute;
		}).collect(Collectors.toSet());
	}

	Set<IntegrationObjectItemClassificationAttributeModel> buildIntegrationObjectItemClassificationAttribute(
			final Set<ListItemClassificationAttributeDTO> dtos, final IntegrationObjectItemModel item)
	{
		return dtos.stream().map(dto -> {
			final IntegrationObjectItemClassificationAttributeModel attribute = modelService.create(
					IntegrationObjectItemClassificationAttributeModel.class);
			attribute.setClassAttributeAssignment(dto.getClassAttributeAssignmentModel());
			attribute.setAttributeName(dto.getAlias());
			attribute.setIntegrationObjectItem(item);
			attribute.setAutoCreate(dto.isAutocreate());
			return attribute;
		}).collect(Collectors.toSet());
	}

	Set<IntegrationObjectItemVirtualAttributeModel> buildIntegrationObjectItemVirtualAttribute(
			final Set<ListItemVirtualAttributeDTO> dtos, final IntegrationObjectItemModel item)
	{
		return dtos.stream().map(dto -> {
			final IntegrationObjectItemVirtualAttributeModel attribute = modelService.create(
					IntegrationObjectItemVirtualAttributeModel.class);
			attribute.setRetrievalDescriptor(dto.getRetrievalDescriptor());
			attribute.setAttributeName(dto.getAlias());
			attribute.setIntegrationObjectItem(item);
			attribute.setAutoCreate(false);
			return attribute;
		}).collect(Collectors.toSet());
	}

	IntegrationObjectModel setReturnIntegrationObjectItem(final IntegrationObjectModel integrationObject,
	                                                      final Map<ComposedTypeModel, List<AbstractListItemDTO>> objectMap)
	{
		final Set<IntegrationObjectItemModel> integrationObjectItems = integrationObject.getItems();
		final Set<AbstractIntegrationObjectItemAttributeModel> integrationObjectItemAttributes = new HashSet<>();
		final List<AbstractListItemDTO> relatedDTOs = new ArrayList<>();
		integrationObjectItems.forEach(ioi -> {
			integrationObjectItemAttributes.clear();
			integrationObjectItemAttributes.addAll(ioi.getAttributes());
			integrationObjectItemAttributes.addAll(ioi.getClassificationAttributes());
			relatedDTOs.clear();
			relatedDTOs.addAll(objectMap.get(ioi.getType()));

			integrationObjectItemAttributes.forEach(attribute -> {
				final TypeModel typeModel = getMatchingListItemDTOType(relatedDTOs, attribute.getAttributeName());
				final String attributeCode = determineAttributeCode(typeModel);
				if (attributeCode != null)
				{
					integrationObjectItems.forEach(item -> {
						if (attributeCode.equals(item.getCode()))
						{
							attribute.setReturnIntegrationObjectItem(item);
						}
					});
				}
			});
		});
		return integrationObject;
	}

	TypeModel getMatchingListItemDTOType(final List<AbstractListItemDTO> relatedDTOs, final String attributeName)
	{
		TypeModel match = new TypeModel();
		for (final AbstractListItemDTO dto : relatedDTOs)
		{
			if (dto.getAlias().equals(attributeName))
			{
				match = (dto instanceof ListItemAttributeDTO) ? ((ListItemAttributeDTO) dto).getType() :
						((ListItemClassificationAttributeDTO) dto).getClassAttributeAssignmentModel().getReferenceType();
				break;
			}
		}
		return match;
	}

	String determineAttributeCode(final TypeModel attributeType)
	{
		if (attributeType == null)
		{
			return null;
		}

		final String attributeTypeValue = attributeType.getItemtype();

		if (readService.isCollectionType(attributeTypeValue))
		{
			return ((CollectionTypeModel) attributeType).getElementType().getCode();
		}
		else if (readService.isMapType(attributeTypeValue))
		{
			final MapTypeModel mapTypeModel = (MapTypeModel) attributeType;
			return determineAttributeCode(mapTypeModel.getReturntype());
		}
		else if (readService.isComposedType(attributeTypeValue) || readService.isEnumerationMetaType(attributeTypeValue))
		{
			return attributeType.getCode();
		}
		else
		{
			return null;
		}
	}

	IntegrationObjectItemModel cloneIntegrationObjectItem(final IntegrationObjectItemModel item,
	                                                      final Map<String, IntegrationObjectItemModel> itemsCloneMap)
	{
		final IntegrationObjectItemModel itemClone = itemsCloneMap.get(item.getType().getCode());

		final Set<IntegrationObjectItemAttributeModel> attributesClone =
				item.getAttributes()
				    .stream()
				    .map((IntegrationObjectItemAttributeModel attribute) ->
						    cloneIntegrationObjectItemAttribute(attribute, itemClone, itemsCloneMap))
				    .collect(Collectors.toSet());

		final Set<IntegrationObjectItemClassificationAttributeModel> classificationAttributesClone =
				item.getClassificationAttributes()
				    .stream()
				    .map((IntegrationObjectItemClassificationAttributeModel classificationAttribute) ->
						    cloneIntegrationObjectItemClassificationAttribute(classificationAttribute, itemClone,
								    itemsCloneMap))
				    .collect(Collectors.toSet());

		final Set<IntegrationObjectItemVirtualAttributeModel> virtualAttributesClone =
				item.getVirtualAttributes()
				    .stream()
				    .map((IntegrationObjectItemVirtualAttributeModel virtualAttribute) ->
						    cloneIntegrationObjectItemVirtualAttributeModel(virtualAttribute, itemClone))
				    .collect(Collectors.toSet());

		itemClone.setAttributes(attributesClone);
		itemClone.setClassificationAttributes(classificationAttributesClone);
		itemClone.setVirtualAttributes(virtualAttributesClone);
		return itemClone;
	}

	IntegrationObjectItemAttributeModel cloneIntegrationObjectItemAttribute(final IntegrationObjectItemAttributeModel attribute,
	                                                                        final IntegrationObjectItemModel itemClone,
	                                                                        final Map<String, IntegrationObjectItemModel> itemsCloneMap)
	{
		final IntegrationObjectItemAttributeModel attributeClone = modelService.create(IntegrationObjectItemAttributeModel.class);
		attributeClone.setAttributeDescriptor(attribute.getAttributeDescriptor());
		attributeClone.setAttributeName(attribute.getAttributeName());
		attributeClone.setIntegrationObjectItem(itemClone);
		attributeClone.setUnique(attribute.getUnique());
		final IntegrationObjectItemModel returnIntegrationObjectItem = attribute.getReturnIntegrationObjectItem();
		attributeClone.setReturnIntegrationObjectItem(returnIntegrationObjectItem != null ?
				itemsCloneMap.get(returnIntegrationObjectItem.getType().getCode()) : null);
		attributeClone.setAutoCreate(attribute.getAutoCreate());
		return attributeClone;
	}

	IntegrationObjectItemClassificationAttributeModel cloneIntegrationObjectItemClassificationAttribute(
			final IntegrationObjectItemClassificationAttributeModel classificationAttribute,
			final IntegrationObjectItemModel itemClone, final Map<String, IntegrationObjectItemModel> itemsCloneMap)
	{
		final IntegrationObjectItemClassificationAttributeModel classificationAttributeClone = modelService.create(
				IntegrationObjectItemClassificationAttributeModel.class);
		classificationAttributeClone.setClassAttributeAssignment(
				classificationAttribute.getClassAttributeAssignment());
		classificationAttributeClone.setAttributeName(classificationAttribute.getAttributeName());
		classificationAttributeClone.setIntegrationObjectItem(itemClone);
		classificationAttributeClone.setAutoCreate(classificationAttribute.getAutoCreate());
		final IntegrationObjectItemModel returnIntegrationObjectItem = classificationAttribute.getReturnIntegrationObjectItem();
		classificationAttributeClone.setReturnIntegrationObjectItem(returnIntegrationObjectItem != null ?
				itemsCloneMap.get(returnIntegrationObjectItem.getType().getCode()) : null);
		return classificationAttributeClone;
	}

	IntegrationObjectItemVirtualAttributeModel cloneIntegrationObjectItemVirtualAttributeModel(
			final IntegrationObjectItemVirtualAttributeModel virtualAttribute, final IntegrationObjectItemModel itemClone)
	{
		final IntegrationObjectItemVirtualAttributeModel virtualAttributeClone = modelService.create(
				IntegrationObjectItemVirtualAttributeModel.class);
		virtualAttributeClone.setRetrievalDescriptor(virtualAttribute.getRetrievalDescriptor());
		virtualAttributeClone.setAttributeName(virtualAttribute.getAttributeName());
		virtualAttributeClone.setIntegrationObjectItem(itemClone);
		virtualAttributeClone.setAutoCreate(false);
		return virtualAttributeClone;
	}
}
