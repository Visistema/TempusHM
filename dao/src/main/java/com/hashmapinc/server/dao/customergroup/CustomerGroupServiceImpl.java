<<<<<<< HEAD
<<<<<<< HEAD
/**
 * Copyright © 2016-2018 The Thingsboard Authors
 * Modifications © 2017-2018 Hashmap, Inc
=======
/**
<<<<<<< HEAD
 * Copyright © 2017-2018 Hashmap, Inc
>>>>>>> 178530f... Adding license header to CustomerGroup Service
=======
 * Copyright © 2016-2018 The Thingsboard Authors
 * Modifications © 2017-2018 Hashmap, Inc
>>>>>>> 8ca597b... changing license header
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hashmapinc.server.dao.customergroup;

import com.google.common.base.Function;
import com.google.common.util.concurrent.Futures;
<<<<<<< HEAD
=======
package com.hashmapinc.server.dao.customergroup;

>>>>>>> c48afe8... Adding CustomerGroupService, need to check delete functionality
=======
>>>>>>> 48f766b... #Updated user and groups model to have only corresponding ids as a property instead of whole objects. Also, updated service and controller methods accordingly.
import com.google.common.util.concurrent.ListenableFuture;
import com.hashmapinc.server.common.data.Customer;
import com.hashmapinc.server.common.data.CustomerGroup;
import com.hashmapinc.server.common.data.Tenant;
import com.hashmapinc.server.common.data.id.CustomerGroupId;
import com.hashmapinc.server.common.data.id.CustomerId;
import com.hashmapinc.server.common.data.id.TenantId;
<<<<<<< HEAD
<<<<<<< HEAD
import com.hashmapinc.server.common.data.id.UserId;
=======
>>>>>>> c48afe8... Adding CustomerGroupService, need to check delete functionality
=======
import com.hashmapinc.server.common.data.id.UserId;
>>>>>>> 48f766b... #Updated user and groups model to have only corresponding ids as a property instead of whole objects. Also, updated service and controller methods accordingly.
import com.hashmapinc.server.common.data.page.TextPageData;
import com.hashmapinc.server.common.data.page.TextPageLink;
import com.hashmapinc.server.dao.customer.CustomerDao;
import com.hashmapinc.server.dao.entity.AbstractEntityService;
import com.hashmapinc.server.dao.exception.DataValidationException;
import com.hashmapinc.server.dao.exception.IncorrectParameterException;
<<<<<<< HEAD
<<<<<<< HEAD
import com.hashmapinc.server.dao.model.ModelConstants;
import com.hashmapinc.server.dao.service.DataValidator;
import com.hashmapinc.server.dao.service.PaginatedRemover;
=======
=======
import com.hashmapinc.server.dao.model.ModelConstants;
>>>>>>> b50cf49... Updating install service to load System Group and Tenant Group
import com.hashmapinc.server.dao.service.DataValidator;
<<<<<<< HEAD
>>>>>>> c48afe8... Adding CustomerGroupService, need to check delete functionality
=======
import com.hashmapinc.server.dao.service.PaginatedRemover;
>>>>>>> 48f766b... #Updated user and groups model to have only corresponding ids as a property instead of whole objects. Also, updated service and controller methods accordingly.
import com.hashmapinc.server.dao.service.Validator;
import com.hashmapinc.server.dao.tenant.TenantDao;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
import java.util.stream.Collectors;

=======
=======
import java.util.concurrent.Future;
=======
>>>>>>> c08cec8... #Added assingUsers to group support.
import java.util.stream.Collectors;
>>>>>>> 48f766b... #Updated user and groups model to have only corresponding ids as a property instead of whole objects. Also, updated service and controller methods accordingly.

<<<<<<< HEAD
import static com.hashmapinc.server.dao.model.ModelConstants.NULL_UUID;
>>>>>>> c48afe8... Adding CustomerGroupService, need to check delete functionality
=======
>>>>>>> c08cec8... #Added assingUsers to group support.
import static com.hashmapinc.server.dao.service.Validator.validateId;

@Service
@Slf4j
public class CustomerGroupServiceImpl extends AbstractEntityService implements CustomerGroupService {

    public static final String INCORRECT_CUSTOMER_ID = "Incorrect customerId ";
    public static final String INCORRECT_CUSTOMER_GROUP_ID = "Incorrect customerGroupId ";
    public static final String INCORRECT_TENANT_ID = "Incorrect tenantId ";
<<<<<<< HEAD
<<<<<<< HEAD
    private static final String INCORRECT_USER_ID = "Incorrect userId";

    @Autowired
    protected CustomerGroupDao customerGroupDao;
=======

    @Autowired
    private CustomerGroupDao customerGroupDao;
>>>>>>> c48afe8... Adding CustomerGroupService, need to check delete functionality
=======
    private static final String INCORRECT_USER_ID = "Incorrect userId";

    @Autowired
    protected CustomerGroupDao customerGroupDao;
>>>>>>> 48f766b... #Updated user and groups model to have only corresponding ids as a property instead of whole objects. Also, updated service and controller methods accordingly.

    @Autowired
    private TenantDao tenantDao;

    @Autowired
    private CustomerDao customerDao;


<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 48f766b... #Updated user and groups model to have only corresponding ids as a property instead of whole objects. Also, updated service and controller methods accordingly.
    private List<UserId> findUserIdsByCustomerGroupId(CustomerGroupId customerGroupId){
        return customerGroupDao.findUserIdsByCustomerGroupId(customerGroupId.getId());
    }


<<<<<<< HEAD
=======
>>>>>>> c48afe8... Adding CustomerGroupService, need to check delete functionality
=======
>>>>>>> 48f766b... #Updated user and groups model to have only corresponding ids as a property instead of whole objects. Also, updated service and controller methods accordingly.
    @Override
    public CustomerGroup findByCustomerGroupId(CustomerGroupId customerGroupId) {
        log.trace("Executing findByCustomerGroupId [{}]", customerGroupId);
        Validator.validateId(customerGroupId, INCORRECT_CUSTOMER_ID + customerGroupId);
<<<<<<< HEAD
<<<<<<< HEAD
        CustomerGroup customerGroup = customerGroupDao.findById(customerGroupId.getId());
        if (customerGroup != null) {
            customerGroup.setUserIds(findUserIdsByCustomerGroupId(customerGroupId));
        }
=======
        CustomerGroup customerGroup = customerGroupDao.findById(customerGroupId.getId());
<<<<<<< HEAD
        customerGroup.setUserIds(findUserIdsByCustomerGroupId(customerGroupId));
>>>>>>> 48f766b... #Updated user and groups model to have only corresponding ids as a property instead of whole objects. Also, updated service and controller methods accordingly.
=======
        if (customerGroup != null) {
            customerGroup.setUserIds(findUserIdsByCustomerGroupId(customerGroupId));
        }
>>>>>>> cff5b04... Adding changes in customer group entity
        return customerGroup;
    }

    @Override
    public TextPageData<CustomerGroup> findByUserId(UserId userId, TextPageLink pageLink) {
        log.trace("Executing findByUserId userId: [{}]", userId);
        Validator.validateId(userId, INCORRECT_USER_ID + userId);
        List<CustomerGroup> customerGroups = customerGroupDao.findByUserId(userId.getId(), pageLink);
        return new TextPageData<>(customerGroups, pageLink);
<<<<<<< HEAD
    }


=======
        return customerGroupDao.findById(customerGroupId.getId());
    }

>>>>>>> c48afe8... Adding CustomerGroupService, need to check delete functionality
=======
    }


>>>>>>> 48f766b... #Updated user and groups model to have only corresponding ids as a property instead of whole objects. Also, updated service and controller methods accordingly.
    @Override
    public ListenableFuture<CustomerGroup> findCustomerGroupByIdAsync(CustomerGroupId customerGroupId) {
        log.trace("Executing findCustomerGroupByIdAsync [{}]", customerGroupId);
        validateId(customerGroupId, INCORRECT_CUSTOMER_ID + customerGroupId);
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 48f766b... #Updated user and groups model to have only corresponding ids as a property instead of whole objects. Also, updated service and controller methods accordingly.
        ListenableFuture<CustomerGroup> customerGroupDaoByIdAsync = customerGroupDao.findByIdAsync(customerGroupId.getId());

        return Futures.transform(customerGroupDaoByIdAsync, (Function<? super CustomerGroup, ? extends CustomerGroup>) customerGroup -> {
            if (customerGroup != null) {
                customerGroup.setUserIds(findUserIdsByCustomerGroupId(customerGroupId));
            }
            return customerGroup;
        });

<<<<<<< HEAD
=======
        return customerGroupDao.findByIdAsync(customerGroupId.getId());
>>>>>>> c48afe8... Adding CustomerGroupService, need to check delete functionality
=======
>>>>>>> 48f766b... #Updated user and groups model to have only corresponding ids as a property instead of whole objects. Also, updated service and controller methods accordingly.
    }

    @Override
    public Optional<CustomerGroup> findCustomerByTenantIdAndCustomerIdAndTitle(TenantId tenantId , CustomerId customerId , String title) {
        log.trace("Executing findCustomerByTenantIdAndCustomerIdAndTitle [{}] [{}] [{}]", tenantId, customerId, title);
        validateId(tenantId, INCORRECT_TENANT_ID + tenantId);
        validateId(customerId, INCORRECT_CUSTOMER_ID + customerId);
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 48f766b... #Updated user and groups model to have only corresponding ids as a property instead of whole objects. Also, updated service and controller methods accordingly.
        Optional<CustomerGroup> optionalCustomerGroup = customerGroupDao.findCustomerGroupsByTenantIdAndCustomerIdAndTitle(tenantId.getId(), customerId.getId(), title);
        return optionalCustomerGroup.map(customerGroup -> {
            customerGroup.setUserIds(findUserIdsByCustomerGroupId(customerGroup.getId()));
            return customerGroup;
        });
<<<<<<< HEAD
=======
        return customerGroupDao.findCustomerGroupsByTenantIdAndCustomerIdAndTitle(tenantId.getId(), customerId.getId(), title);
>>>>>>> c48afe8... Adding CustomerGroupService, need to check delete functionality
=======
>>>>>>> 48f766b... #Updated user and groups model to have only corresponding ids as a property instead of whole objects. Also, updated service and controller methods accordingly.
    }

    @Override
    public CustomerGroup saveCustomerGroup(CustomerGroup customerGroup) {
        log.trace("Executing saveCustomerGroup [{}]", customerGroup);
        customerGroupValidator.validate(customerGroup);
        CustomerGroup savedCustomerGroup = customerGroupDao.save(customerGroup);
<<<<<<< HEAD
<<<<<<< HEAD
        savedCustomerGroup.setUserIds(findUserIdsByCustomerGroupId(savedCustomerGroup.getId()));
=======
//        dashboardService.updateCustomerDashboards(savedCustomer.getId());    TODO : Need to confirm this implementation
>>>>>>> c48afe8... Adding CustomerGroupService, need to check delete functionality
=======
        savedCustomerGroup.setUserIds(findUserIdsByCustomerGroupId(savedCustomerGroup.getId()));
>>>>>>> 48f766b... #Updated user and groups model to have only corresponding ids as a property instead of whole objects. Also, updated service and controller methods accordingly.
        return savedCustomerGroup;
    }

    @Override
    public void deleteCustomerGroup(CustomerGroupId customerGroupId) {
        log.trace("Executing deleteCustomerGroup [{}]", customerGroupId);
        Validator.validateId(customerGroupId, INCORRECT_CUSTOMER_GROUP_ID + customerGroupId);
        CustomerGroup customerGroup = findByCustomerGroupId(customerGroupId);
        if (customerGroup == null) {
            throw new IncorrectParameterException("Unable to delete non-existent customer group.");
        }
<<<<<<< HEAD
<<<<<<< HEAD
        deleteEntityRelations(customerGroupId);
        customerGroupDao.removeById(customerGroupId.getId());
        customerGroupDao.deleteUserIdsForCustomerGroupId(customerGroupId.getId());
=======
//        dashboardService.unassignCustomerDashboards(customerGroupId);
//        assetService.unassignCustomerAssets(customerGroup.getTenantId(), customerGroupId);
//        deviceService.unassignCustomerDevices(customerGroup.getTenantId(), customerGroupId);
//        userService.deleteCustomerUsers(customerGroup.getTenantId(), customerGroupId);  TODO : Need to confirm this implementation
=======
>>>>>>> ee50baf... added unassign user api to group service
        deleteEntityRelations(customerGroupId);
        customerGroupDao.removeById(customerGroupId.getId());
<<<<<<< HEAD
>>>>>>> c48afe8... Adding CustomerGroupService, need to check delete functionality
=======
        customerGroupDao.deleteUserIdsForCustomerGroupId(customerGroupId.getId());
>>>>>>> 48f766b... #Updated user and groups model to have only corresponding ids as a property instead of whole objects. Also, updated service and controller methods accordingly.
    }

    @Override
    public TextPageData<CustomerGroup> findCustomerGroupsByTenantIdAndCustomerId(TenantId tenantId , CustomerId customerId , TextPageLink pageLink) {
        log.trace("Executing findCustomerGroupsByTenantIdAndCustomerId, tenantId [{}], coustomerId [{}], pageLink [{}]", tenantId, customerId, pageLink);
        Validator.validateId(tenantId, INCORRECT_TENANT_ID + tenantId);
        Validator.validateId(customerId, INCORRECT_CUSTOMER_ID + customerId);
        Validator.validatePageLink(pageLink, "Incorrect page link " + pageLink);
        List<CustomerGroup> customerGroups = customerGroupDao.findCustomerGroupsByTenantIdAndCustomerId(tenantId.getId(), customerId.getId(), pageLink);
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 48f766b... #Updated user and groups model to have only corresponding ids as a property instead of whole objects. Also, updated service and controller methods accordingly.
        List<CustomerGroup> customerGroupsWithUserIds =
                customerGroups.stream()
                        .peek(customerGroup -> customerGroup.setUserIds(findUserIdsByCustomerGroupId(customerGroup.getId())))
                        .collect(Collectors.toList());

        return new TextPageData<>(customerGroupsWithUserIds, pageLink);
<<<<<<< HEAD
    }

    @Override
    public void deleteCustomerGroupsByTenantIdAndCustomerId(TenantId tenantId, CustomerId customerId) {
        log.trace("Executing deleteCustomerGroupsByCustomerId, tenantId [{}] and customerId [{}]", tenantId, customerId);
        Validator.validateId(tenantId, INCORRECT_TENANT_ID + tenantId);
        Validator.validateId(customerId, INCORRECT_CUSTOMER_ID + customerId);
        new CustomerGroupRemover(tenantId).removeEntities(customerId);
    }

    @Override
    public CustomerGroup assignUsers(CustomerGroupId customerGroupId, List<UserId> userIds) {
        log.trace("Executing assignUsers, CustomerGroupId [{}] and userIds [{}]", customerGroupId, userIds);
        Validator.validateId(customerGroupId, INCORRECT_CUSTOMER_GROUP_ID + customerGroupId);
        customerGroupDao.assignUsers(customerGroupId, userIds);
        CustomerGroup customerGroup = customerGroupDao.findById(customerGroupId.getId());
        customerGroup.setUserIds(userIds);
        return customerGroup;
    }

    @Override
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> ee50baf... added unassign user api to group service
    public CustomerGroup unassignUsers(CustomerGroupId customerGroupId , List<UserId> userIds) {
        log.trace("Executing unassignUsers, CustomerGroupId [{}] and userIds [{}]", customerGroupId, userIds);
        Validator.validateId(customerGroupId, INCORRECT_CUSTOMER_GROUP_ID + customerGroupId);
        customerGroupDao.unassignUsers(customerGroupId, userIds);
        return customerGroupDao.findById(customerGroupId.getId());
    }

    @Override
<<<<<<< HEAD
=======
>>>>>>> e715e4d... #Added provision to fetch policies for user
=======
>>>>>>> ee50baf... added unassign user api to group service
    public List<String> findGroupPoliciesForUser(UserId userId) {
        log.trace("Executing findGroupPoliciesForUser, userId [{}]", userId);
        Validator.validateId(userId, INCORRECT_USER_ID + userId);
        List<CustomerGroup> customerGroups = customerGroupDao.findByUserId(userId.getId(), new TextPageLink(Integer.MAX_VALUE));
        return customerGroups.stream().flatMap(customerGroup -> customerGroup.getPolicies().stream()).distinct().collect(Collectors.toList());
    }

    private class CustomerGroupRemover extends PaginatedRemover<CustomerId, CustomerGroup> {

        private TenantId tenantId;

        CustomerGroupRemover(TenantId tenantId) {
            this.tenantId = tenantId;
        }

        @Override
        protected List<CustomerGroup> findEntities(CustomerId customerId, TextPageLink pageLink) {
            return customerGroupDao.findCustomerGroupsByTenantIdAndCustomerId(tenantId.getId(), customerId.getId(), pageLink);
        }

        @Override
        protected void removeEntity(CustomerGroup entity) {
            deleteCustomerGroup(new CustomerGroupId(entity.getUuidId()));
        }

=======
        return new TextPageData<>(customerGroups, pageLink);
    }

    @Override
    public void deleteCustomerGroupsByTenantIdAndCustomerId(TenantId tenantId , CustomerId customerId) {
        // TODO : Need to check implementation
>>>>>>> c48afe8... Adding CustomerGroupService, need to check delete functionality
=======
    }

    @Override
    public void deleteCustomerGroupsByTenantIdAndCustomerId(TenantId tenantId, CustomerId customerId) {
        log.trace("Executing deleteCustomerGroupsByCustomerId, tenantId [{}] and customerId [{}]", tenantId, customerId);
        Validator.validateId(tenantId, INCORRECT_TENANT_ID + tenantId);
        Validator.validateId(customerId, INCORRECT_CUSTOMER_ID + customerId);
        new CustomerGroupRemover(tenantId).removeEntities(customerId);
    }

    @Override
    public CustomerGroup assignUsers(CustomerGroupId customerGroupId, List<UserId> userIds) {
        log.trace("Executing assignUsers, CustomerGroupId [{}] and userIds [{}]", customerGroupId, userIds);
        Validator.validateId(customerGroupId, INCORRECT_CUSTOMER_GROUP_ID + customerGroupId);
        customerGroupDao.assignUsers(customerGroupId, userIds);
        CustomerGroup customerGroup = customerGroupDao.findById(customerGroupId.getId());
        customerGroup.setUserIds(userIds);
        return customerGroup;
    }

    private class CustomerGroupRemover extends PaginatedRemover<CustomerId, CustomerGroup> {

        private TenantId tenantId;

        CustomerGroupRemover(TenantId tenantId) {
            this.tenantId = tenantId;
        }

        @Override
        protected List<CustomerGroup> findEntities(CustomerId customerId, TextPageLink pageLink) {
            return customerGroupDao.findCustomerGroupsByTenantIdAndCustomerId(tenantId.getId(), customerId.getId(), pageLink);
        }

        @Override
        protected void removeEntity(CustomerGroup entity) {
            deleteCustomerGroup(new CustomerGroupId(entity.getUuidId()));
        }

>>>>>>> 48f766b... #Updated user and groups model to have only corresponding ids as a property instead of whole objects. Also, updated service and controller methods accordingly.
    }

    private DataValidator<CustomerGroup> customerGroupValidator =
            new DataValidator<CustomerGroup>() {

                @Override
                protected void validateCreate(CustomerGroup customerGroup) {
                    customerGroupDao
                            .findCustomerGroupsByTenantIdAndCustomerIdAndTitle(
                                customerGroup.getTenantId().getId(), customerGroup.getCustomerId().getId(), customerGroup.getTitle()
                            )
                            .ifPresent(c -> {
                                    throw new DataValidationException("Customer Group with such title already exists!");
                            }
                    );
                }

                @Override
                protected void validateUpdate(CustomerGroup customerGroup) {
                    customerGroupDao
                            .findCustomerGroupsByTenantIdAndCustomerIdAndTitle(
                                    customerGroup.getTenantId().getId(), customerGroup.getCustomerId().getId(), customerGroup.getTitle()
                            )
                            .ifPresent(c -> {
                                if (!c.getId().equals(customerGroup.getId())) {
                                    throw new DataValidationException("Customer Group with such title already exists!");
                                }
                            }
                    );
                }

                @Override
                protected void validateDataImpl(CustomerGroup customerGroup) {
                    if (StringUtils.isEmpty(customerGroup.getTitle())) {
                        throw new DataValidationException("Customer Group title should be specified!");
                    }
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> b50cf49... Updating install service to load System Group and Tenant Group

                    TenantId tenantId = customerGroup.getTenantId();
                    if (tenantId == null) {
                        tenantId = new TenantId(ModelConstants.NULL_UUID);
                        customerGroup.setTenantId(tenantId);
<<<<<<< HEAD
=======
                    if (customerGroup.getTenantId() == null) {
                        throw new DataValidationException("Customer Group should be assigned to tenant!");
>>>>>>> c48afe8... Adding CustomerGroupService, need to check delete functionality
=======
>>>>>>> b50cf49... Updating install service to load System Group and Tenant Group
                    } else {
                        Tenant tenant = tenantDao.findById(customerGroup.getTenantId().getId());
                        if (tenant == null) {
                            throw new DataValidationException("Customer Group is referencing to non-existent tenant!");
                        }
                    }
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> b50cf49... Updating install service to load System Group and Tenant Group

                    CustomerId customerId = customerGroup.getCustomerId();
                    if (customerId == null) {
                        customerId = new CustomerId(ModelConstants.NULL_UUID);
                        customerGroup.setCustomerId(customerId);
<<<<<<< HEAD
=======
                    if (customerGroup.getCustomerId() == null) {
                        throw new DataValidationException("Customer Group should be assigned to Customer!");
>>>>>>> c48afe8... Adding CustomerGroupService, need to check delete functionality
=======
>>>>>>> b50cf49... Updating install service to load System Group and Tenant Group
                    } else {
                        Customer customer = customerDao.findById(customerGroup.getCustomerId().getId());
                        if (customer == null) {
                            throw new DataValidationException("Customer Group is referencing to non-existent Customer!");
<<<<<<< HEAD
<<<<<<< HEAD
                        } else if (!customer.getTenantId().getId().equals(customerGroup.getTenantId().getId())) {
                            throw new DataValidationException("Customer Group can't be assigned to customer from different tenant!");
=======
>>>>>>> c48afe8... Adding CustomerGroupService, need to check delete functionality
=======
                        } else if (!customer.getTenantId().getId().equals(customerGroup.getTenantId().getId())) {
                            throw new DataValidationException("Customer Group can't be assigned to customer from different tenant!");
>>>>>>> b50cf49... Updating install service to load System Group and Tenant Group
                        }
                    }
                }
            };
}
