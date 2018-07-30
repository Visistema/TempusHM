/**
<<<<<<< HEAD
<<<<<<< HEAD
 * Copyright © 2016-2018 The Thingsboard Authors
 * Modifications © 2017-2018 Hashmap, Inc
=======
 * Copyright © 2017-2018 Hashmap, Inc
>>>>>>> 2a1e3c7... #Added missing headers.
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
package com.hashmapinc.server.common.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
<<<<<<< HEAD
<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonProperty;
=======
>>>>>>> a9f7a78... #Updated SQL entity and data models for User and group to support group feature with multiple group constraint.
=======
import com.fasterxml.jackson.annotation.JsonProperty;
>>>>>>> ac321dd... Adding json property to read only for name
import com.hashmapinc.server.common.data.id.CustomerGroupId;
import com.hashmapinc.server.common.data.id.CustomerId;
import com.hashmapinc.server.common.data.id.TenantId;
import com.hashmapinc.server.common.data.id.UserId;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class CustomerGroup extends SearchTextBasedWithAdditionalInfo<CustomerGroupId> implements HasName {
    private static final long serialVersionUID = -5520737431477399572L;
    private String title;
    private TenantId tenantId;
    private CustomerId customerId;
    private List<String> policies;
    @JsonIgnore
<<<<<<< HEAD
<<<<<<< HEAD
    private List<UserId> userIds;
=======
    private List<User> users;
>>>>>>> a9f7a78... #Updated SQL entity and data models for User and group to support group feature with multiple group constraint.
=======
    private List<UserId> userIds;
>>>>>>> 494385c... #Updated model and entities for user and group. To have only ids instead of objects for many to many relationship.

    public CustomerGroup(CustomerGroupId id) {
        super(id);
    }

    public  CustomerGroup(CustomerGroup customerGroup){
        super(customerGroup);
        this.title = customerGroup.title;
        this.tenantId = customerGroup.tenantId;
        this.customerId = customerGroup.customerId;
        this.policies = customerGroup.policies;
<<<<<<< HEAD
<<<<<<< HEAD
        this.userIds = customerGroup.userIds;
=======
        this.users = customerGroup.users;
>>>>>>> a9f7a78... #Updated SQL entity and data models for User and group to support group feature with multiple group constraint.
=======
        this.userIds = customerGroup.userIds;
>>>>>>> 494385c... #Updated model and entities for user and group. To have only ids instead of objects for many to many relationship.
    }

    @Override
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public String getName() {
        return title;
    }

    @Override
    public String getSearchText() {
        return getTitle();
    }
}
