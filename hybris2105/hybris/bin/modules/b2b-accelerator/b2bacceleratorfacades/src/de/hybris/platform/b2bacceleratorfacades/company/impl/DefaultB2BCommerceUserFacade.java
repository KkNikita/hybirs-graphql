/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.b2bacceleratorfacades.company.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import de.hybris.platform.b2b.constants.B2BConstants;
import de.hybris.platform.b2b.model.B2BCustomerModel;
import de.hybris.platform.b2b.model.B2BPermissionModel;
import de.hybris.platform.b2b.model.B2BUserGroupModel;
import de.hybris.platform.b2bacceleratorfacades.company.B2BCommerceUserFacade;
import de.hybris.platform.b2bapprovalprocessfacades.company.data.B2BPermissionData;
import de.hybris.platform.b2bcommercefacades.company.data.B2BSelectionData;
import de.hybris.platform.b2bcommercefacades.company.data.B2BUnitData;
import de.hybris.platform.b2bcommercefacades.company.data.B2BUserGroupData;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;

import org.apache.commons.beanutils.BeanPropertyValueEqualsPredicate;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;


/**
 * @deprecated Since 6.0. Use {@link de.hybris.platform.b2bcommercefacades.company.impl.DefaultB2BUserFacade} instead.
 */
@Deprecated(since = "6.0", forRemoval = true)
public class DefaultB2BCommerceUserFacade extends DefaultCompanyB2BCommerceFacade implements B2BCommerceUserFacade
{
	@Override
	public SearchPageData<CustomerData> getPagedCustomers(final PageableData pageableData)
	{
		final SearchPageData<B2BCustomerModel> b2bCustomer = getB2BCommerceUserService().getPagedCustomers(pageableData);
		return convertPageData(b2bCustomer, getB2BCustomerConverter());
	}

	@Override
	public SearchPageData<CustomerData> getPagedApproversForCustomer(final PageableData pageableData, final String uid)
	{
		final SearchPageData<B2BCustomerModel> approvers = getB2BCommerceUserService()
				.getPagedCustomersByGroupMembership(pageableData, B2BConstants.B2BAPPROVERGROUP);
		final SearchPageData<CustomerData> searchPageData = convertPageData(approvers, getB2BUserConverter());
		// update the results with approvers that already have been selected.
		final CustomerData customer = this.getCustomerForUid(uid);
		validateParameterNotNull(customer, String.format("No customer found for uid %s", uid));
		for (final CustomerData userData : searchPageData.getResults())
		{
			userData.setSelected(CollectionUtils.find(customer.getApprovers(),
					new BeanPropertyValueEqualsPredicate(B2BCustomerModel.UID, userData.getUid())) != null);
		}

		return searchPageData;
	}

	@Override
	public B2BSelectionData addApproverForCustomer(final String user, final String approver)
	{
		validateParameterNotNullStandardMessage("user", user);
		validateParameterNotNullStandardMessage("approver", approver);

		final B2BCustomerModel approverModel = getB2BApproverService().addApproverToCustomer(user, approver);
		final B2BSelectionData b2BSelectionData = createB2BSelectionData(approverModel.getUid(), true,
				approverModel.getActive().booleanValue());
		return populateRolesForCustomer(approverModel, b2BSelectionData);
	}

	@Override
	public B2BSelectionData removeApproverFromCustomer(final String user, final String approver)
	{
		validateParameterNotNullStandardMessage("user", user);
		validateParameterNotNullStandardMessage("approver", approver);

		final B2BCustomerModel approverModel = getB2BApproverService().removeApproverFromCustomer(user, approver);
		final B2BSelectionData b2BSelectionData = createB2BSelectionData(approverModel.getUid(), false,
				approverModel.getActive().booleanValue());
		return populateRolesForCustomer(approverModel, b2BSelectionData);
	}

	@Override
	public SearchPageData<B2BPermissionData> getPagedPermissionsForCustomer(final PageableData pageableData, final String uid)
	{
		final SearchPageData<B2BPermissionModel> permissions = getB2BCommercePermissionService().getPagedPermissions(pageableData);
		final SearchPageData<B2BPermissionData> searchPageData = convertPageData(permissions, getB2BPermissionConverter());
		final CustomerData customer = this.getCustomerForUid(uid);
		validateParameterNotNull(customer, String.format("No customer found for uid %s", uid));
		for (final B2BPermissionData permissionData : searchPageData.getResults())
		{
			permissionData.setSelected(CollectionUtils.find(customer.getPermissions(),
					new BeanPropertyValueEqualsPredicate(B2BPermissionModel.CODE, permissionData.getCode())) != null);
		}

		return searchPageData;
	}

	@Override
	public B2BUnitData getParentUnitForCustomer(final String uid)
	{
		Assert.hasText(uid, "The field [uid] cannot be empty");
		return getB2BUnitConverter().convert(getB2BCommerceUserService().getParentUnitForCustomer(uid));
	}

	@Override
	public void updateCustomer(final CustomerData customerData)
	{
		validateParameterNotNullStandardMessage("customerData", customerData);
		Assert.hasText(customerData.getFirstName(), "The field [FirstName] cannot be empty");
		Assert.hasText(customerData.getLastName(), "The field [LastName] cannot be empty");
		final B2BCustomerModel customerModel;
		if (StringUtils.isEmpty(customerData.getUid()))
		{
			customerModel = this.getModelService().create(B2BCustomerModel.class);
		}
		else
		{
			customerModel = getCompanyB2BCommerceService().getCustomerForUid(customerData.getUid());
		}
		getB2BCustomerReversePopulator().populate(customerData, customerModel);
		getCompanyB2BCommerceService().saveModel(customerModel);
	}

	@Override
	public void resetCustomerPassword(final String uid, final String updatedPassword)
	{
		validateParameterNotNull(uid, "The given uid is null!");
		validateParameterNotNull(updatedPassword, "The given updatedPassword is null!");

		final B2BCustomerModel customerModel = getCompanyB2BCommerceService().getCustomerForUid(uid);
		getUserService().setPassword(customerModel, updatedPassword, customerModel.getPasswordEncoding());
		getCompanyB2BCommerceService().saveModel(customerModel);
	}

	@Override
	public void disableCustomer(final String uid)
	{
		validateParameterNotNullStandardMessage("uid", uid);
		getB2BCommerceUserService().disableCustomer(uid);
	}

	@Override
	public void enableCustomer(final String uid)
	{
		validateParameterNotNullStandardMessage("uid", uid);
		getB2BCommerceUserService().enableCustomer(uid);
	}

	@Override
	public B2BSelectionData removeUserRole(final String user, final String role)
	{
		validateParameterNotNullStandardMessage("user", user);
		validateParameterNotNullStandardMessage("role", role);
		final B2BCustomerModel customerModel = getB2BCommerceUserService().removeUserRole(user, role);
		final B2BSelectionData b2BSelectionData = createB2BSelectionData(customerModel.getUid(), false,
				customerModel.getActive().booleanValue());
		return populateRolesForCustomer(customerModel, b2BSelectionData);
	}

	@Override
	public B2BSelectionData addUserRole(final String user, final String role)
	{
		validateParameterNotNullStandardMessage("user", user);
		validateParameterNotNullStandardMessage("role", role);
		final B2BCustomerModel customerModel = getB2BCommerceUserService().addUserRole(user, role);
		final B2BSelectionData b2BSelectionData = createB2BSelectionData(customerModel.getUid(), true,
				customerModel.getActive().booleanValue());
		return populateRolesForCustomer(customerModel, b2BSelectionData);
	}

	@Override
	public B2BSelectionData addPermissionToCustomer(final String user, final String permission)
	{
		validateParameterNotNullStandardMessage("permission", permission);
		validateParameterNotNullStandardMessage("user", user);

		final B2BPermissionModel permissionModel = getB2BCommercePermissionService().addPermissionToCustomer(user, permission);
		return createB2BSelectionData(permissionModel.getCode(), true, permissionModel.getActive().booleanValue());
	}

	@Override
	public B2BSelectionData removePermissionFromCustomer(final String user, final String permission)
	{
		validateParameterNotNullStandardMessage("permission", permission);
		validateParameterNotNullStandardMessage("user", user);

		final B2BPermissionModel permissionModel = getB2BCommercePermissionService().removePermissionFromCustomer(user, permission);
		return createB2BSelectionData(permissionModel.getCode(), false, permissionModel.getActive().booleanValue());
	}

	@Override
	public SearchPageData<B2BUserGroupData> getPagedB2BUserGroupsForCustomer(final PageableData pageableData, final String user)
	{
		final SearchPageData<B2BUserGroupModel> userGroups = getB2BCommerceB2BUserGroupService()
				.getPagedB2BUserGroups(pageableData);
		final SearchPageData<B2BUserGroupData> searchPageData = convertPageData(userGroups, getB2BUserGroupConverter());
		final CustomerData customer = this.getCustomerForUid(user);
		validateParameterNotNull(customer, String.format("No customer found for uid %s", user));
		for (final B2BUserGroupData userGroupData : searchPageData.getResults())
		{
			userGroupData.setSelected(CollectionUtils.find(customer.getPermissionGroups(),
					new BeanPropertyValueEqualsPredicate(B2BUserGroupModel.UID, userGroupData.getUid())) != null);
		}

		return searchPageData;
	}

	@Override
	public B2BSelectionData addB2BUserGroupToCustomer(final String user, final String usergroup)
	{
		validateParameterNotNullStandardMessage("user", user);
		validateParameterNotNullStandardMessage("usergroup", usergroup);

		final B2BUserGroupModel b2BUserGroupModel = this.getB2BCommerceUserService().addB2BUserGroupToCustomer(user, usergroup);
		final B2BSelectionData b2BSelectionData = createB2BSelectionData(b2BUserGroupModel.getUid(), true,
				!b2BUserGroupModel.getMembers().isEmpty());
		return b2BSelectionData;
	}

	@Override
	public void removeB2BUserGroupFromCustomerGroups(final String user, final String usergroup)
	{
		validateParameterNotNullStandardMessage("user", user);
		validateParameterNotNullStandardMessage("usergroup", usergroup);

		this.getB2BCommerceUserService().removeB2BUserGroupFromCustomerGroups(user, usergroup);
	}

	@Override
	public B2BSelectionData deselectB2BUserGroupFromCustomer(final String user, final String usergroup)
	{
		validateParameterNotNullStandardMessage("user", user);
		validateParameterNotNullStandardMessage("usergroup", usergroup);

		final B2BUserGroupModel b2BUserGroupModel = this.getB2BCommerceUserService().deselectB2BUserGroupFromCustomer(user,
				usergroup);
		final B2BSelectionData b2BSelectionData = createB2BSelectionData(b2BUserGroupModel.getUid(), false,
				!b2BUserGroupModel.getMembers().isEmpty());
		return b2BSelectionData;
	}

	/**
	 * @deprecated Since 5.0.
	 */
	@Override
	@Deprecated(since = "5.0", forRemoval = true)
	public B2BSelectionData removeB2BUserGroupFromCustomer(final String user, final String usergroup)
	{
		return deselectB2BUserGroupFromCustomer(user, usergroup);
	}

}
