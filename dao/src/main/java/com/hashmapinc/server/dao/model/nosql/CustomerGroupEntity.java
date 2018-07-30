/**
<<<<<<< HEAD
<<<<<<< HEAD
 * Copyright © 2016-2018 The Thingsboard Authors
 * Modifications © 2017-2018 Hashmap, Inc
=======
 * Copyright © 2017-2018 Hashmap, Inc
>>>>>>> 605c899... adding NoSql Entity for CustomerGroup
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
package com.hashmapinc.server.dao.model.nosql;

import com.datastax.driver.core.utils.UUIDs;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import com.fasterxml.jackson.databind.JsonNode;
import com.hashmapinc.server.common.data.CustomerGroup;
import com.hashmapinc.server.common.data.id.CustomerGroupId;
import com.hashmapinc.server.common.data.id.CustomerId;
import com.hashmapinc.server.common.data.id.TenantId;
import com.hashmapinc.server.dao.model.ModelConstants;
import com.hashmapinc.server.dao.model.SearchTextEntity;
import com.hashmapinc.server.dao.model.type.JsonCodec;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

<<<<<<< HEAD
<<<<<<< HEAD
=======
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.JoinColumn;
<<<<<<< HEAD
>>>>>>> 605c899... adding NoSql Entity for CustomerGroup
=======
import javax.persistence.ManyToMany;
>>>>>>> 494385c... #Updated model and entities for user and group. To have only ids instead of objects for many to many relationship.
=======
>>>>>>> dae8593... Changed nosql group entity
import java.util.List;
import java.util.UUID;

import static com.hashmapinc.server.dao.model.ModelConstants.*;

@NoArgsConstructor
@Data
@Table(name = CUSTOMER_GROUP_COLUMN_FAMILY_NAME)
@EqualsAndHashCode
@ToString
public class CustomerGroupEntity implements SearchTextEntity<CustomerGroup> {

    @PartitionKey(value = 0)
    @Column(name = ID_PROPERTY)
    private UUID id;

    @PartitionKey(value = 1)
    @Column(name = CUSTOMER_GROUP_TENANT_ID_PROPERTY)
    private UUID tenantId;

<<<<<<< HEAD
<<<<<<< HEAD
    @PartitionKey(value = 2)
    @Column(name = CUSTOMER_GROUP_CUSTOMER_ID_PROPERTY)
    private UUID customerId;

    @Column(name = CUSTOMER_GROUP_TITLE_PROPERTY)
    private String title;

=======
    @Column(name = CUSTOMER_GROUP_TITLE_PROPERTY)
    private String title;

    @Column(name = CUSTOMER_GROUP_CUSTOMER_ID_PROPERTY)
    private UUID customerId;

    @ElementCollection()
    @CollectionTable(name = ModelConstants.CUSTOMER_GROUP_POLICY_COLUMN_FAMILY_NAME, joinColumns = @JoinColumn(name = ModelConstants.CUSTOMER_GROUP_POLICY_ID_PROPERTY))
>>>>>>> 605c899... adding NoSql Entity for CustomerGroup
=======
    @PartitionKey(value = 2)
    @Column(name = CUSTOMER_GROUP_CUSTOMER_ID_PROPERTY)
    private UUID customerId;

    @Column(name = CUSTOMER_GROUP_TITLE_PROPERTY)
    private String title;

>>>>>>> dae8593... Changed nosql group entity
    @Column(name = ModelConstants.CUSTOMER_GROUP_POLICY_PROPERTY)
    private List<String> policies;

    @Column(name = SEARCH_TEXT_PROPERTY)
    private String searchText;

    @Column(name = ADDITIONAL_INFO_PROPERTY, codec = JsonCodec.class)
    private JsonNode additionalInfo;

    public CustomerGroupEntity(CustomerGroup customerGroup) {
        if (customerGroup.getId() != null) {
            this.setId(customerGroup.getId().getId());
        }
        this.tenantId = customerGroup.getTenantId().getId();
        this.customerId = customerGroup.getCustomerId().getId();
        this.title = customerGroup.getTitle();
<<<<<<< HEAD
<<<<<<< HEAD
        if (customerGroup.getPolicies() != null && !customerGroup.getPolicies().isEmpty()) {
            this.policies = customerGroup.getPolicies();
        }
=======
        this.policies = customerGroup.getPolicies();
>>>>>>> 605c899... adding NoSql Entity for CustomerGroup
=======
        if (customerGroup.getPolicies() != null && !customerGroup.getPolicies().isEmpty()) {
            this.policies = customerGroup.getPolicies();
        }
>>>>>>> cff5b04... Adding changes in customer group entity
        this.additionalInfo = customerGroup.getAdditionalInfo();
    }

    @Override
    public String getSearchTextSource() {
        return title;
    }

    @Override
    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    @Override
    public CustomerGroup toData() {
        CustomerGroup customerGroup = new CustomerGroup(new CustomerGroupId(getId()));
        customerGroup.setCreatedTime(UUIDs.unixTimestamp(getId()));
        customerGroup.setTenantId(new TenantId(tenantId));
        customerGroup.setCustomerId(new CustomerId(customerId));
        customerGroup.setTitle(title);
        customerGroup.setAdditionalInfo(additionalInfo);
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
        if (policies != null && !policies.isEmpty()) {
            customerGroup.setPolicies(policies);
        }
=======
>>>>>>> 605c899... adding NoSql Entity for CustomerGroup
=======
        customerGroup.setPolicies(policies);
>>>>>>> afd3d24... #Fixed Issue: Policies are not coming in CustomerGroup model.
=======
        if (policies != null && !policies.isEmpty()) {
            customerGroup.setPolicies(policies);
        }
>>>>>>> cff5b04... Adding changes in customer group entity
        return customerGroup;
    }
}
