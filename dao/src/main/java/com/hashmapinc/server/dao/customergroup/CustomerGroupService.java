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
<<<<<<< HEAD
=======
>>>>>>> c48afe8... Adding CustomerGroupService, need to check delete functionality
=======
>>>>>>> 178530f... Adding license header to CustomerGroup Service
package com.hashmapinc.server.dao.customergroup;

import com.google.common.util.concurrent.ListenableFuture;
import com.hashmapinc.server.common.data.CustomerGroup;
import com.hashmapinc.server.common.data.id.CustomerGroupId;
import com.hashmapinc.server.common.data.id.CustomerId;
import com.hashmapinc.server.common.data.id.TenantId;
<<<<<<< HEAD
<<<<<<< HEAD
import com.hashmapinc.server.common.data.id.UserId;
import com.hashmapinc.server.common.data.page.TextPageData;
import com.hashmapinc.server.common.data.page.TextPageLink;

import java.util.List;
=======
=======
import com.hashmapinc.server.common.data.id.UserId;
>>>>>>> 48f766b... #Updated user and groups model to have only corresponding ids as a property instead of whole objects. Also, updated service and controller methods accordingly.
import com.hashmapinc.server.common.data.page.TextPageData;
import com.hashmapinc.server.common.data.page.TextPageLink;

<<<<<<< HEAD
>>>>>>> c48afe8... Adding CustomerGroupService, need to check delete functionality
=======
import java.util.List;
>>>>>>> c08cec8... #Added assingUsers to group support.
import java.util.Optional;

public interface CustomerGroupService {

    CustomerGroup findByCustomerGroupId(CustomerGroupId customerGroupId);

<<<<<<< HEAD
<<<<<<< HEAD
    TextPageData<CustomerGroup> findByUserId(UserId userId, TextPageLink pageLink);

=======
>>>>>>> c48afe8... Adding CustomerGroupService, need to check delete functionality
=======
    TextPageData<CustomerGroup> findByUserId(UserId userId, TextPageLink pageLink);

>>>>>>> 48f766b... #Updated user and groups model to have only corresponding ids as a property instead of whole objects. Also, updated service and controller methods accordingly.
    ListenableFuture<CustomerGroup> findCustomerGroupByIdAsync(CustomerGroupId customerGroupId);

    Optional<CustomerGroup> findCustomerByTenantIdAndCustomerIdAndTitle(TenantId tenantId, CustomerId customerId, String title);

    CustomerGroup saveCustomerGroup(CustomerGroup customerGroup);

    void deleteCustomerGroup(CustomerGroupId customerGroupId);

    TextPageData<CustomerGroup> findCustomerGroupsByTenantIdAndCustomerId(TenantId tenantId, CustomerId customerId, TextPageLink pageLink);

    void deleteCustomerGroupsByTenantIdAndCustomerId(TenantId tenantId, CustomerId customerId);

<<<<<<< HEAD
<<<<<<< HEAD
    CustomerGroup assignUsers(CustomerGroupId customerGroupId, List<UserId> userIds);

    CustomerGroup unassignUsers(CustomerGroupId customerGroupId, List<UserId> userIds);

    List<String> findGroupPoliciesForUser(UserId userId);
=======
>>>>>>> c48afe8... Adding CustomerGroupService, need to check delete functionality
=======
    CustomerGroup assignUsers(CustomerGroupId customerGroupId, List<UserId> userIds);
<<<<<<< HEAD
>>>>>>> c08cec8... #Added assingUsers to group support.
=======

    CustomerGroup unassignUsers(CustomerGroupId customerGroupId, List<UserId> userIds);

    List<String> findGroupPoliciesForUser(UserId userId);
>>>>>>> e715e4d... #Added provision to fetch policies for user
}
