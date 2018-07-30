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

import com.hashmapinc.server.common.data.CustomerGroup;
import com.hashmapinc.server.common.data.UUIDConverter;
<<<<<<< HEAD
<<<<<<< HEAD
import com.hashmapinc.server.common.data.id.CustomerGroupId;
import com.hashmapinc.server.common.data.id.UserId;
=======
>>>>>>> e898424... #Updated method in DAO interface for customer group
=======
import com.hashmapinc.server.common.data.id.CustomerGroupId;
import com.hashmapinc.server.common.data.id.UserId;
>>>>>>> 48f766b... #Updated user and groups model to have only corresponding ids as a property instead of whole objects. Also, updated service and controller methods accordingly.
import com.hashmapinc.server.common.data.page.TextPageLink;
import com.hashmapinc.server.dao.DaoUtil;
import com.hashmapinc.server.dao.customergroup.CustomerGroupDao;
import com.hashmapinc.server.dao.model.ModelConstants;
<<<<<<< HEAD
<<<<<<< HEAD
import com.hashmapinc.server.dao.model.sql.CustomerGroupEntity;
import com.hashmapinc.server.dao.sql.JpaAbstractSearchTextDao;
import com.hashmapinc.server.dao.util.SqlDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
=======
import com.hashmapinc.server.dao.model.nosql.CustomerGroupEntity;
=======
import com.hashmapinc.server.dao.model.sql.CustomerGroupEntity;
>>>>>>> f78073d... Refactor group repository and jpa-dao
import com.hashmapinc.server.dao.sql.JpaAbstractSearchTextDao;
import com.hashmapinc.server.dao.util.SqlDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> e898424... #Updated method in DAO interface for customer group
=======
import java.sql.ResultSet;
>>>>>>> 48f766b... #Updated user and groups model to have only corresponding ids as a property instead of whole objects. Also, updated service and controller methods accordingly.
=======
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
>>>>>>> c08cec8... #Added assingUsers to group support.
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
<<<<<<< HEAD
<<<<<<< HEAD
import java.util.stream.Collectors;

import static com.hashmapinc.server.dao.model.ModelConstants.*;

@Component
@SqlDao
@Slf4j
<<<<<<< HEAD
public class JpaCustomerGroupDao extends JpaAbstractSearchTextDao<CustomerGroupEntity, CustomerGroup> implements CustomerGroupDao {

    private static final String QUERY_TO_FETCH_USER_ID_BY_GROUP_ID =
            String.format("SELECT DISTINCT %s FROM %s WHERE %s = ?", USER_ID_PROPERTY, USER_GROUP_TABLE_NAME, CUSTOMER_GROUP_ID_PROPERTY);
    private static final String DELETE_USER_ID_FROM_USER_GROUP = String.format("DELETE FROM %s WHERE %s = ?", USER_GROUP_TABLE_NAME, CUSTOMER_GROUP_ID_PROPERTY);
    private static final String DELETE_GROUP_IDS_FROM_USER_GROUP = String.format("DELETE FROM %s WHERE %s = ?", USER_GROUP_TABLE_NAME, USER_ID_PROPERTY);
    private static final String SELECT_GROUP_IDS_FOR_USER_ID = String.format("SELECT DISTINCT %s FROM %s WHERE %s = ?", CUSTOMER_GROUP_ID_PROPERTY, USER_GROUP_TABLE_NAME, USER_ID_PROPERTY);
    private static final String INSERT_USER_GROUPS = String.format("INSERT INTO %s  (%s, %s) VALUES (?, ?)", USER_GROUP_TABLE_NAME, USER_ID_PROPERTY, CUSTOMER_GROUP_ID_PROPERTY);
    private static final String DELETE_FROM_USER_GROUP = String.format("DELETE FROM %s WHERE %s = ? AND %s = ?", USER_GROUP_TABLE_NAME, USER_ID_PROPERTY,CUSTOMER_GROUP_ID_PROPERTY);

    @Autowired
    JdbcTemplate jdbcTemplate;

=======
=======
import java.util.stream.Collectors;

import static com.hashmapinc.server.dao.model.ModelConstants.*;
>>>>>>> 48f766b... #Updated user and groups model to have only corresponding ids as a property instead of whole objects. Also, updated service and controller methods accordingly.

@Component
@SqlDao
=======
>>>>>>> 27a2dbe... #On delete user, delete groups relationships as well.
public class JpaCustomerGroupDao extends JpaAbstractSearchTextDao<CustomerGroupEntity, CustomerGroup> implements CustomerGroupDao {

    private static final String QUERY_TO_FETCH_USER_ID_BY_GROUP_ID =
            String.format("SELECT DISTINCT %s FROM %s WHERE %s = ?", USER_ID_PROPERTY, USER_GROUP_TABLE_NAME, CUSTOMER_GROUP_ID_PROPERTY);
    private static final String DELETE_USER_ID_FROM_USER_GROUP = String.format("DELETE FROM %s WHERE %s = ?", USER_GROUP_TABLE_NAME, CUSTOMER_GROUP_ID_PROPERTY);
    private static final String DELETE_GROUP_IDS_FROM_USER_GROUP = String.format("DELETE FROM %s WHERE %s = ?", USER_GROUP_TABLE_NAME, USER_ID_PROPERTY);
    private static final String SELECT_GROUP_IDS_FOR_USER_ID = String.format("SELECT DISTINCT %s FROM %s WHERE %s = ?", CUSTOMER_GROUP_ID_PROPERTY, USER_GROUP_TABLE_NAME, USER_ID_PROPERTY);
    private static final String INSERT_USER_GROUPS = String.format("INSERT INTO %s  (%s, %s) VALUES (?, ?)", USER_GROUP_TABLE_NAME, USER_ID_PROPERTY, CUSTOMER_GROUP_ID_PROPERTY);

    @Autowired
    JdbcTemplate jdbcTemplate;

>>>>>>> e898424... #Updated method in DAO interface for customer group
    @Autowired
    private CustomerGroupRepository customerGroupRepository;

    @Override
    public List<CustomerGroup> findCustomerGroupsByTenantIdAndCustomerId(UUID tenantId, UUID customerId, TextPageLink pageLink) {
        return DaoUtil.convertDataList(customerGroupRepository.findByTenantIdAndCustomerId(
                UUIDConverter.fromTimeUUID(tenantId),
                UUIDConverter.fromTimeUUID(customerId),
                Objects.toString(pageLink.getTextSearch(), ""),
                pageLink.getIdOffset() == null ? ModelConstants.NULL_UUID_STR : UUIDConverter.fromTimeUUID(pageLink.getIdOffset()),
                new PageRequest(0, pageLink.getLimit())));
    }

    @Override
    public Optional<CustomerGroup> findCustomerGroupsByTenantIdAndCustomerIdAndTitle(UUID tenantId, UUID customerId, String title) {

        CustomerGroup customerGroup = DaoUtil.getData(
                customerGroupRepository.findByTenantIdAndCustomerIdAndTitle(UUIDConverter.fromTimeUUID(tenantId), UUIDConverter.fromTimeUUID(customerId), title)
        );
        return Optional.ofNullable(customerGroup);
    }

    @Override
<<<<<<< HEAD
<<<<<<< HEAD
    public List<UserId> findUserIdsByCustomerGroupId(UUID customerGroupId) {
        return jdbcTemplate.query(QUERY_TO_FETCH_USER_ID_BY_GROUP_ID, new Object[]{UUIDConverter.fromTimeUUID(customerGroupId)},
                (ResultSet rs, int rowNum) -> new UserId(UUIDConverter.fromString(rs.getString(USER_ID_PROPERTY))));
=======
    public List<UserId> findUserIdsByCustomerGroupId(UUID customerGroupId) {
        return jdbcTemplate.query(QUERY_TO_FETCH_USER_ID_BY_GROUP_ID, new Object[]{UUIDConverter.fromTimeUUID(customerGroupId)},
<<<<<<< HEAD
                (ResultSet rs, int rowNum) -> UserId.fromString(rs.getString(USER_ID_PROPERTY)));
>>>>>>> 48f766b... #Updated user and groups model to have only corresponding ids as a property instead of whole objects. Also, updated service and controller methods accordingly.
=======
                (ResultSet rs, int rowNum) -> new UserId(UUIDConverter.fromString(rs.getString(USER_ID_PROPERTY))));
>>>>>>> 3b98754... Fixed of issues related to UserId for groups
    }

    @Override
    public void deleteUserIdsForCustomerGroupId(UUID customerGroupId) {
<<<<<<< HEAD
<<<<<<< HEAD
        jdbcTemplate.update(DELETE_USER_ID_FROM_USER_GROUP, UUIDConverter.fromTimeUUID(customerGroupId));
    }

    @Override
    public void deleteGroupIdsForUserId(UUID userId) {
        jdbcTemplate.update(DELETE_GROUP_IDS_FROM_USER_GROUP, UUIDConverter.fromTimeUUID(userId));
=======
        jdbcTemplate.update(DELETE_USER_ID_FROM_USER_GROUP);
>>>>>>> 48f766b... #Updated user and groups model to have only corresponding ids as a property instead of whole objects. Also, updated service and controller methods accordingly.
=======
        jdbcTemplate.update(DELETE_USER_ID_FROM_USER_GROUP, UUIDConverter.fromTimeUUID(customerGroupId));
>>>>>>> b4e8388... #Fixed JPA customer group DAO queries which was taking UUID objects instead of UUID in string format.
    }

    @Override
    public void deleteGroupIdsForUserId(UUID userId) {
        jdbcTemplate.update(DELETE_GROUP_IDS_FROM_USER_GROUP, UUIDConverter.fromTimeUUID(userId));
    }

    @Override
    public List<CustomerGroup> findByUserId(UUID userId, TextPageLink textPageLink) {
<<<<<<< HEAD
<<<<<<< HEAD
        List<CustomerGroupId> customerGroupIds = jdbcTemplate.query(SELECT_GROUP_IDS_FOR_USER_ID, new Object[]{UUIDConverter.fromTimeUUID(userId)},
                (ResultSet rs, int rowNum) -> new CustomerGroupId(UUIDConverter.fromString(rs.getString(CUSTOMER_GROUP_ID_PROPERTY))));
        List<String> customerGroupIdsStr = customerGroupIds.stream().map(id -> UUIDConverter.fromTimeUUID((id.getId()))).collect(Collectors.toList());
        List<CustomerGroupEntity> customerGroupEntities = customerGroupRepository.findByIdIn(customerGroupIdsStr , new PageRequest(0 , textPageLink.getLimit()));
        return customerGroupEntities.stream().map(CustomerGroupEntity::toData).collect(Collectors.toList());
    }

    @Override
    public void assignUsers(CustomerGroupId customerGroupId, List<UserId> userIds) {

        List<String> userIdsStr = userIds.stream().map(userId -> UUIDConverter.fromTimeUUID(userId.getId())).collect(Collectors.toList());

        jdbcTemplate.batchUpdate(INSERT_USER_GROUPS, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                String userIdStr = userIdsStr.get(i);
                ps.setString(1, userIdStr);
                ps.setString(2, UUIDConverter.fromTimeUUID(customerGroupId.getId()));
            }

            @Override
            public int getBatchSize() {
                return userIdsStr.size();
            }
        });
    }

    @Override
    public void unassignUsers(CustomerGroupId customerGroupId , List<UserId> userIds) {
        List<String> userIdsStr = userIds.stream().map(userId -> UUIDConverter.fromTimeUUID(userId.getId())).collect(Collectors.toList());
        userIdsStr.forEach(userId -> jdbcTemplate.update(DELETE_FROM_USER_GROUP, userId, UUIDConverter.fromTimeUUID(customerGroupId.getId())));
    }

    @Override
    public void assignGroups(UserId userId , List<CustomerGroupId> customerGroupIds) {
        List<String> groupIdsStr = customerGroupIds.stream().map(groupId -> UUIDConverter.fromTimeUUID(groupId.getId())).collect(Collectors.toList());

        jdbcTemplate.batchUpdate(INSERT_USER_GROUPS , new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps , int i) throws SQLException {
                String groupIdStr = groupIdsStr.get(i);
                ps.setString(1, UUIDConverter.fromTimeUUID(userId.getId()));
                ps.setString(2, groupIdStr);
            }

            @Override
            public int getBatchSize() {
                return customerGroupIds.size();
            }
        });
    }

    @Override
    public void unassignGroups(UserId userId , List<CustomerGroupId> customerGroupIds) {
        List<String> customerGroupIdsStr = customerGroupIds.stream().map(customerGroupId -> UUIDConverter.fromTimeUUID(customerGroupId.getId())).collect(Collectors.toList());
        customerGroupIdsStr.forEach(customerGroupId -> jdbcTemplate.update(DELETE_FROM_USER_GROUP, UUIDConverter.fromTimeUUID(userId.getId()), customerGroupId));
    }

    @Override
=======
>>>>>>> e898424... #Updated method in DAO interface for customer group
=======
        List<CustomerGroupId> customerGroupIds = jdbcTemplate.query(SELECT_GROUP_IDS_FOR_USER_ID, new Object[]{userId},
=======
        List<CustomerGroupId> customerGroupIds = jdbcTemplate.query(SELECT_GROUP_IDS_FOR_USER_ID, new Object[]{UUIDConverter.fromTimeUUID(userId)},
<<<<<<< HEAD
>>>>>>> b4e8388... #Fixed JPA customer group DAO queries which was taking UUID objects instead of UUID in string format.
                (ResultSet rs, int rowNum) -> CustomerGroupId.fromString(rs.getString(CUSTOMER_GROUP_ID_PROPERTY)));
        List<String> customerGroupIdsStr = customerGroupIds.stream().map(id -> id.getId().toString()).collect(Collectors.toList());
=======
                (ResultSet rs, int rowNum) -> new CustomerGroupId(UUIDConverter.fromString(rs.getString(CUSTOMER_GROUP_ID_PROPERTY))));
        List<String> customerGroupIdsStr = customerGroupIds.stream().map(id -> UUIDConverter.fromTimeUUID((id.getId()))).collect(Collectors.toList());
>>>>>>> 27a2dbe... #On delete user, delete groups relationships as well.
        List<CustomerGroupEntity> customerGroupEntities = customerGroupRepository.findByIdIn(customerGroupIdsStr , new PageRequest(0 , textPageLink.getLimit()));
        return customerGroupEntities.stream().map(CustomerGroupEntity::toData).collect(Collectors.toList());
    }

    @Override
<<<<<<< HEAD
>>>>>>> 48f766b... #Updated user and groups model to have only corresponding ids as a property instead of whole objects. Also, updated service and controller methods accordingly.
=======
    public void assignUsers(CustomerGroupId customerGroupId, List<UserId> userIds) {

        List<String> userIdsStr = userIds.stream().map(userId -> UUIDConverter.fromTimeUUID(userId.getId())).collect(Collectors.toList());

        jdbcTemplate.batchUpdate(INSERT_USER_GROUPS, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                String userIdStr = userIdsStr.get(i);
                ps.setString(1, userIdStr);
                ps.setString(2, UUIDConverter.fromTimeUUID(customerGroupId.getId()));
            }

            @Override
            public int getBatchSize() {
                return userIdsStr.size();
            }
        });
    }

    @Override
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> c08cec8... #Added assingUsers to group support.
=======
=======
    public void unassignUsers(CustomerGroupId customerGroupId , List<UserId> userIds) {
        List<String> userIdsStr = userIds.stream().map(userId -> UUIDConverter.fromTimeUUID(userId.getId())).collect(Collectors.toList());
        userIdsStr.forEach(userId -> jdbcTemplate.update(DELETE_FROM_USER_GROUP, userId, UUIDConverter.fromTimeUUID(customerGroupId.getId())));
    }

    @Override
>>>>>>> 880eca9... Added changes to jpa and cassandra dao implementaion for unassign users and groups
    public void assignGroups(UserId userId , List<CustomerGroupId> customerGroupIds) {
        List<String> groupIdsStr = customerGroupIds.stream().map(groupId -> UUIDConverter.fromTimeUUID(groupId.getId())).collect(Collectors.toList());

        jdbcTemplate.batchUpdate(INSERT_USER_GROUPS , new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps , int i) throws SQLException {
                String groupIdStr = groupIdsStr.get(i);
                ps.setString(1, UUIDConverter.fromTimeUUID(userId.getId()));
                ps.setString(2, groupIdStr);
            }

            @Override
            public int getBatchSize() {
                return customerGroupIds.size();
            }
        });
    }

    @Override
<<<<<<< HEAD
>>>>>>> 1af01a8... Added assingGroups to user support.
=======
    public void unassignGroups(UserId userId , List<CustomerGroupId> customerGroupIds) {
        List<String> customerGroupIdsStr = customerGroupIds.stream().map(customerGroupId -> UUIDConverter.fromTimeUUID(customerGroupId.getId())).collect(Collectors.toList());
        customerGroupIdsStr.forEach(customerGroupId -> jdbcTemplate.update(DELETE_FROM_USER_GROUP, UUIDConverter.fromTimeUUID(userId.getId()), customerGroupId));
    }

    @Override
>>>>>>> 880eca9... Added changes to jpa and cassandra dao implementaion for unassign users and groups
    protected Class<CustomerGroupEntity> getEntityClass() {
        return CustomerGroupEntity.class;
    }

    @Override
    protected CrudRepository<CustomerGroupEntity, String> getCrudRepository() {
        return customerGroupRepository;
    }
<<<<<<< HEAD
<<<<<<< HEAD


=======
>>>>>>> e898424... #Updated method in DAO interface for customer group
=======


>>>>>>> 48f766b... #Updated user and groups model to have only corresponding ids as a property instead of whole objects. Also, updated service and controller methods accordingly.
}
