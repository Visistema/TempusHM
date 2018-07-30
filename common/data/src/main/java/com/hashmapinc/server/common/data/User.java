/**
 * Copyright © 2016-2018 The Thingsboard Authors
 * Modifications © 2017-2018 Hashmap, Inc
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
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hashmapinc.server.common.data.id.CustomerGroupId;
import com.hashmapinc.server.common.data.id.CustomerId;
import com.hashmapinc.server.common.data.id.TenantId;
import com.hashmapinc.server.common.data.id.UserId;
import com.hashmapinc.server.common.data.security.Authority;
import lombok.EqualsAndHashCode;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
public class User extends SearchTextBasedWithAdditionalInfo<UserId> implements HasName {

    private static final long serialVersionUID = 8250339805336035966L;

    private TenantId tenantId;
    private CustomerId customerId;
    private String email;
    private Authority authority;
    @JsonIgnore
    private Collection<String> permissions = Collections.emptyList();
    private String firstName;
    private String lastName;
    @JsonIgnore
<<<<<<< HEAD
<<<<<<< HEAD
    private List<CustomerGroupId> groupIds;
=======
    private List<CustomerGroup> groups;
>>>>>>> a9f7a78... #Updated SQL entity and data models for User and group to support group feature with multiple group constraint.
=======
    private List<CustomerGroupId> groupIds;
>>>>>>> 494385c... #Updated model and entities for user and group. To have only ids instead of objects for many to many relationship.

    public User() {
        super();
    }

    public User(UserId id) {
        super(id);
    }

    public User(User user) {
        super(user);
        this.tenantId = user.getTenantId();
        this.customerId = user.getCustomerId();
        this.email = user.getEmail();
        this.authority = user.getAuthority();
        this.permissions = user.getPermissions();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
<<<<<<< HEAD
<<<<<<< HEAD
        this.groupIds = user.getGroupIds();
=======
        this.groups = user.getGroups();
>>>>>>> a9f7a78... #Updated SQL entity and data models for User and group to support group feature with multiple group constraint.
=======
        this.groupIds = user.getGroupIds();
>>>>>>> 494385c... #Updated model and entities for user and group. To have only ids instead of objects for many to many relationship.
    }

    public TenantId getTenantId() {
        return tenantId;
    }

    public void setTenantId(TenantId tenantId) {
        this.tenantId = tenantId;
    }

    public CustomerId getCustomerId() {
        return customerId;
    }

    public void setCustomerId(CustomerId customerId) {
        this.customerId = customerId;
    }

    public String getEmail() {
        return (email!= null?email.toLowerCase():email);
    }

    public void setEmail(String email) {
        this.email = (email!= null?email.toLowerCase():email);
    }

    @Override
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public String getName() {
        return email;
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }

    public Collection<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Collection<String> permissions) {
        this.permissions = permissions;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

<<<<<<< HEAD
<<<<<<< HEAD
    public List<CustomerGroupId> getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(List<CustomerGroupId> groupIds) {
        this.groupIds = groupIds;
=======
    public List<CustomerGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<CustomerGroup> groups) {
        this.groups = groups;
>>>>>>> a9f7a78... #Updated SQL entity and data models for User and group to support group feature with multiple group constraint.
=======
    public List<CustomerGroupId> getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(List<CustomerGroupId> groupIds) {
        this.groupIds = groupIds;
>>>>>>> 494385c... #Updated model and entities for user and group. To have only ids instead of objects for many to many relationship.
    }

    @Override
    public String getSearchText() {
        return getEmail();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("User [tenantId=");
        builder.append(tenantId);
        builder.append(", customerId=");
        builder.append(customerId);
        builder.append(", email=");
        builder.append(email);
        builder.append(", authority=");
        builder.append(authority);
        builder.append(", permissions=");
        builder.append(permissions);
        builder.append(", firstName=");
        builder.append(firstName);
        builder.append(", lastName=");
        builder.append(lastName);
        builder.append(", additionalInfo=");
        builder.append(getAdditionalInfo());
        builder.append(", createdTime=");
        builder.append(createdTime);
        builder.append(", id=");
        builder.append(id);
        builder.append("]");
        return builder.toString();
    }

}
