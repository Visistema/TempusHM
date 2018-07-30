/**
<<<<<<< HEAD
<<<<<<< HEAD
 * Copyright © 2016-2018 The Thingsboard Authors
 * Modifications © 2017-2018 Hashmap, Inc
=======
 * Copyright © 2017-2018 Hashmap, Inc
>>>>>>> e898424... #Updated method in DAO interface for customer group
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
package com.hashmapinc.server.dao.sql.customergroup;

import org.springframework.data.domain.Pageable;
<<<<<<< HEAD
<<<<<<< HEAD
import com.hashmapinc.server.dao.model.sql.CustomerGroupEntity;
=======
import com.hashmapinc.server.dao.model.nosql.CustomerGroupEntity;
>>>>>>> e898424... #Updated method in DAO interface for customer group
=======
import com.hashmapinc.server.dao.model.sql.CustomerGroupEntity;
>>>>>>> f78073d... Refactor group repository and jpa-dao
import com.hashmapinc.server.dao.util.SqlDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

@SqlDao
public interface CustomerGroupRepository  extends CrudRepository<CustomerGroupEntity, String> {
    @Query("SELECT c FROM CustomerGroupEntity c WHERE c.tenantId = :tenantId " +
            "AND c.customerId = :customerId " +
            "AND LOWER(c.searchText) LIKE LOWER(CONCAT(:textSearch, '%')) " +
            "AND c.id > :idOffset ORDER BY c.id")
    List<CustomerGroupEntity> findByTenantIdAndCustomerId(@Param("tenantId") String tenantId,
                                                          @Param("customerId") String customerId,
                                                          @Param("textSearch") String textSearch,
                                                          @Param("idOffset") String idOffset,
                                                          Pageable pageable);

<<<<<<< HEAD
<<<<<<< HEAD
    CustomerGroupEntity findByTenantIdAndCustomerIdAndTitle(String tenantId , String customerId , String title);

    List<CustomerGroupEntity> findByIdIn(List<String> id, Pageable pageable);
=======
    CustomerGroupEntity findByTenantIdAndCustomerIdAndTitle(String tenantId, String customerId, String title);
<<<<<<< HEAD
>>>>>>> e898424... #Updated method in DAO interface for customer group
=======

    List<CustomerGroup> findByIdIn(List<String> id, PageRequest pageRequest);
>>>>>>> 48f766b... #Updated user and groups model to have only corresponding ids as a property instead of whole objects. Also, updated service and controller methods accordingly.
=======
    CustomerGroupEntity findByTenantIdAndCustomerIdAndTitle(String tenantId , String customerId , String title);

    List<CustomerGroupEntity> findByIdIn(List<String> id, Pageable pageable);
>>>>>>> f78073d... Refactor group repository and jpa-dao
}
