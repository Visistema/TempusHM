/**
<<<<<<< HEAD
<<<<<<< HEAD
 * Copyright © 2016-2018 The Thingsboard Authors
 * Modifications © 2017-2018 Hashmap, Inc
=======
 * Copyright © 2017-2018 Hashmap, Inc
>>>>>>> 1b6c3e4... #Added DAO interface for customer group
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

import com.hashmapinc.server.common.data.CustomerGroup;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> c08cec8... #Added assingUsers to group support.
import com.hashmapinc.server.common.data.id.CustomerGroupId;
import com.hashmapinc.server.common.data.id.UserId;
=======
>>>>>>> 1b6c3e4... #Added DAO interface for customer group
=======
import com.hashmapinc.server.common.data.id.UserId;
>>>>>>> 48f766b... #Updated user and groups model to have only corresponding ids as a property instead of whole objects. Also, updated service and controller methods accordingly.
import com.hashmapinc.server.common.data.page.TextPageLink;
import com.hashmapinc.server.dao.Dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * The Interface CustomerGroupDao.
 */
public interface CustomerGroupDao extends Dao<CustomerGroup> {
    /**
     * Save or update customerGroup object
     *
     * @param customerGroup the customerGroup object
     * @return saved customerGroup object
     */
    CustomerGroup save(CustomerGroup customerGroup);

    /**
<<<<<<< HEAD
<<<<<<< HEAD
     * Find customerGroups by tenantId, customerId and page link.
     *
     * @param tenantId the tenantId
     * @param customerId the customerId
     * @param pageLink the page link
     * @return the list of customerGroup objects
     */
    List<CustomerGroup> findCustomerGroupsByTenantIdAndCustomerId(UUID tenantId, UUID customerId, TextPageLink pageLink);

    /**
     * Find customerGroups by tenantId and customerGroup title.
     *
     * @param tenantId the tenantId
     * @param customerId the customerId
     * @param title the customerGroup title
     * @return the list of customerGroup objects
     */
    Optional<CustomerGroup> findCustomerGroupsByTenantIdAndCustomerIdAndTitle(UUID tenantId, UUID customerId, String title);

    /**
     * Find User IDs by customer group id.
     *
     * @param customerGroupId the customer group id
     * @return the list of UserId objects
     */
    List<UserId> findUserIdsByCustomerGroupId(UUID customerGroupId);

    /**
     * Delete User IDs by customer group id.
     *
     * @param customerGroupId the customer group id
     *
     */
    void deleteUserIdsForCustomerGroupId(UUID customerGroupId);

<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 27a2dbe... #On delete user, delete groups relationships as well.
    /**
     * Delete Group IDs by userId.
     *
     * @param userId the userId
     *
     */
    void deleteGroupIdsForUserId(UUID userId);

<<<<<<< HEAD
=======
>>>>>>> 48f766b... #Updated user and groups model to have only corresponding ids as a property instead of whole objects. Also, updated service and controller methods accordingly.
=======
>>>>>>> 27a2dbe... #On delete user, delete groups relationships as well.

    /**
     * Find Customer groups by user id.
     *
     * @param userId the user id
     * @param textPageLink the page link
     * @return the list of customer group objects
     */
    List<CustomerGroup> findByUserId(UUID userId, TextPageLink textPageLink);
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> c08cec8... #Added assingUsers to group support.

    /**
     * Assign Users to groups by id.
     *
     * @param customerGroupId the customerGroupId
     * @param userIds list of userId objects
     */
    void assignUsers(CustomerGroupId customerGroupId, List<UserId> userIds);
<<<<<<< HEAD
<<<<<<< HEAD

    /**
     * Unassign Users to groups by id.
     *
     * @param customerGroupId the customerGroupId
     * @param userIds list of userId objects
     */
    void unassignUsers(CustomerGroupId customerGroupId, List<UserId> userIds);
=======
>>>>>>> 1af01a8... Added assingGroups to user support.

    /**
     * Unassign Users to groups by id.
     *
     * @param customerGroupId the customerGroupId
     * @param userIds list of userId objects
     */
    void unassignUsers(CustomerGroupId customerGroupId, List<UserId> userIds);

    /**
     * Assign Groups to user by id.
     *
     * @param userId the userId
     * @param customerGroupIds list of customerGroupId objects
     */
    void assignGroups(UserId userId , List<CustomerGroupId> customerGroupIds);
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 29d4a4d... Added api to unassignUsers and unassignGroup

    /**
     * Unassign Groups to user by id.
     *
     * @param userId the userId
     * @param customerGroupIds list of customerGroupId objects
     */
    void unassignGroups(UserId userId , List<CustomerGroupId> customerGroupIds);
<<<<<<< HEAD
=======
     * Find customerGroups by tenant id and page link.
     *
     * @param tenantId the tenant id
     * @param pageLink the page link
     * @return the list of customerGroup objects
     */
    List<CustomerGroup> findCustomerGroupsByTenantId(UUID tenantId, TextPageLink pageLink);

    /**
=======
>>>>>>> e898424... #Updated method in DAO interface for customer group
     * Find customerGroups by tenantId, customerId and page link.
     *
     * @param tenantId the tenantId
     * @param customerId the customerId
     * @param pageLink the page link
     * @return the list of customerGroup objects
     */
    List<CustomerGroup> findCustomerGroupsByTenantIdAndCustomerId(UUID tenantId, UUID customerId, TextPageLink pageLink);

    /**
     * Find customerGroups by tenantId and customerGroup title.
     *
     * @param tenantId the tenantId
     * @param customerId the customerId
     * @param title the customerGroup title
     * @return the list of customerGroup objects
     */
<<<<<<< HEAD
    Optional<CustomerGroup> findCustomerGroupsByTenantIdAndTitle(UUID tenantId, String title);
>>>>>>> 1b6c3e4... #Added DAO interface for customer group
=======
    Optional<CustomerGroup> findCustomerGroupsByTenantIdAndCustomerIdAndTitle(UUID tenantId, UUID customerId, String title);
>>>>>>> e898424... #Updated method in DAO interface for customer group
=======
>>>>>>> 48f766b... #Updated user and groups model to have only corresponding ids as a property instead of whole objects. Also, updated service and controller methods accordingly.
=======
>>>>>>> c08cec8... #Added assingUsers to group support.
=======
>>>>>>> 1af01a8... Added assingGroups to user support.
=======
>>>>>>> 29d4a4d... Added api to unassignUsers and unassignGroup
}
