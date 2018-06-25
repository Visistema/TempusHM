/**
 * Copyright © 2017-2018 Hashmap, Inc
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
package com.hashmapinc.server.requests;

import com.hashmapinc.server.common.data.UUIDConverter;
import com.hashmapinc.server.common.data.User;
import com.hashmapinc.server.common.data.id.CustomerId;
import com.hashmapinc.server.common.data.id.TenantId;
import com.hashmapinc.server.common.data.id.UserId;
import com.hashmapinc.server.common.data.security.Authority;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
public class IdentityUser {
    private UUID id;
    private String userName;
    private String tenantId;
    private String customerId;
    private String firstName;
    private String lastName;
    private List<String> authorities;
    private String clientId;
    private Map<String, String> additionalDetails;
    private boolean enabled;

    public IdentityUser(){}

    public IdentityUser(User user){
        this.id = user.getUuidId();
        this.userName = user.getEmail();
        this.authorities = Arrays.asList(user.getAuthority().name());
        this.tenantId = UUIDConverter.fromTimeUUID(user.getTenantId().getId());
        this.customerId = UUIDConverter.fromTimeUUID(user.getCustomerId().getId());
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
    }

    public User toUser(){
        User user = new User();
        user.setId(new UserId(id));
        user.setEmail(userName);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setTenantId(new TenantId(UUIDConverter.fromString(tenantId)));
        user.setCustomerId(new CustomerId(UUIDConverter.fromString(customerId)));
        user.setAuthority(Authority.parse(authorities.get(0)));
        return user;
    }
}
