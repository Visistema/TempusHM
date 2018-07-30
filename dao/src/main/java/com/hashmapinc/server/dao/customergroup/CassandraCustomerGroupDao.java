/**
<<<<<<< HEAD
<<<<<<< HEAD
 * Copyright © 2016-2018 The Thingsboard Authors
 * Modifications © 2017-2018 Hashmap, Inc
=======
 * Copyright © 2017-2018 Hashmap, Inc
>>>>>>> 08741e7... #Added Cassandra DAO implementation for customer group
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

<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 588f07a... Adding cassandra dao changes for group support
import com.datastax.driver.core.*;
import com.datastax.driver.core.querybuilder.Delete;
import com.datastax.driver.core.querybuilder.Insert;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.hashmapinc.server.common.data.CustomerGroup;
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> c08cec8... #Added assingUsers to group support.
import com.hashmapinc.server.common.data.id.CustomerGroupId;
import com.hashmapinc.server.common.data.id.UserId;
=======
import com.datastax.driver.core.querybuilder.Select;
import com.hashmapinc.server.common.data.CustomerGroup;
>>>>>>> 08741e7... #Added Cassandra DAO implementation for customer group
=======
import com.hashmapinc.server.common.data.id.UserId;
>>>>>>> 48f766b... #Updated user and groups model to have only corresponding ids as a property instead of whole objects. Also, updated service and controller methods accordingly.
import com.hashmapinc.server.common.data.page.TextPageLink;
import com.hashmapinc.server.dao.DaoUtil;
import com.hashmapinc.server.dao.model.ModelConstants;
import com.hashmapinc.server.dao.model.nosql.CustomerGroupEntity;
import com.hashmapinc.server.dao.nosql.CassandraAbstractSearchTextDao;
import com.hashmapinc.server.dao.util.NoSqlDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

<<<<<<< HEAD
<<<<<<< HEAD
import java.util.*;
import java.util.stream.Collectors;

import static com.datastax.driver.core.querybuilder.QueryBuilder.eq;
import static com.datastax.driver.core.querybuilder.QueryBuilder.select;
import static com.hashmapinc.server.dao.model.ModelConstants.CUSTOMER_GROUP_ID_PROPERTY;
import static com.hashmapinc.server.dao.model.ModelConstants.USER_GROUP_COLUMN_FAMILY_NAME;
import static com.hashmapinc.server.dao.model.ModelConstants.USER_ID_PROPERTY;
=======
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.datastax.driver.core.querybuilder.QueryBuilder.eq;
import static com.datastax.driver.core.querybuilder.QueryBuilder.select;
>>>>>>> 08741e7... #Added Cassandra DAO implementation for customer group
=======
import java.util.*;
import java.util.stream.Collectors;

import static com.datastax.driver.core.querybuilder.QueryBuilder.eq;
import static com.datastax.driver.core.querybuilder.QueryBuilder.select;
import static com.hashmapinc.server.dao.model.ModelConstants.CUSTOMER_GROUP_ID_PROPERTY;
import static com.hashmapinc.server.dao.model.ModelConstants.USER_GROUP_COLUMN_FAMILY_NAME;
import static com.hashmapinc.server.dao.model.ModelConstants.USER_ID_PROPERTY;
>>>>>>> 588f07a... Adding cassandra dao changes for group support

@Component
@Slf4j
@NoSqlDao
public class CassandraCustomerGroupDao extends CassandraAbstractSearchTextDao<CustomerGroupEntity, CustomerGroup> implements CustomerGroupDao {
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 588f07a... Adding cassandra dao changes for group support

    private static final String SELECT_PREFIX = "SELECT ";
    private static final String EQUALS_PARAM = " = ? ";
    private static final String ALLOW_FILTERING = "ALLOW FILTERING ";

    private PreparedStatement groupIdByUserIdStmt;
    private PreparedStatement userIdByGroupIdStmt;


<<<<<<< HEAD
    @Override
    public List<CustomerGroup> findCustomerGroupsByTenantIdAndCustomerId(UUID tenantId, UUID customerId, TextPageLink pageLink) {
        log.trace("Try to find customer groups by tenantId [{}] and customerId [{}] and pageLink [{}]", tenantId, customerId, pageLink);
=======
    @Override
    public List<CustomerGroup> findCustomerGroupsByTenantIdAndCustomerId(UUID tenantId, UUID customerId, TextPageLink pageLink) {
        log.debug("Try to find customer groups by tenantId [{}] and customerId [{}] and pageLink [{}]", tenantId, customerId, pageLink);
>>>>>>> 08741e7... #Added Cassandra DAO implementation for customer group
=======
    @Override
    public List<CustomerGroup> findCustomerGroupsByTenantIdAndCustomerId(UUID tenantId, UUID customerId, TextPageLink pageLink) {
        log.trace("Try to find customer groups by tenantId [{}] and customerId [{}] and pageLink [{}]", tenantId, customerId, pageLink);
>>>>>>> 588f07a... Adding cassandra dao changes for group support
        List<CustomerGroupEntity> customerGroupEntities = findPageWithTextSearch(ModelConstants.CUSTOMER_GROUP_BY_TENANT_AND_CUSTOMER_SEARCH_TEXT_COLUMN_FAMILY_NAME,
                Arrays.asList(
                        eq(ModelConstants.CUSTOMER_GROUP_TENANT_ID_PROPERTY, tenantId),
                        eq(ModelConstants.CUSTOMER_GROUP_CUSTOMER_ID_PROPERTY, customerId)
                ),
                pageLink);
        log.trace("Found customer groups [{}] by tenantId [{}] and customerId [{}] and pageLink [{}]", customerGroupEntities, tenantId, customerId, pageLink);
        return DaoUtil.convertDataList(customerGroupEntities);
    }

    @Override
    public Optional<CustomerGroup> findCustomerGroupsByTenantIdAndCustomerIdAndTitle(UUID tenantId, UUID customerId, String title) {
<<<<<<< HEAD
<<<<<<< HEAD
        log.trace("Find CustomerGroup By tenantId [{}] customerId [{}] and title [{}]", tenantId, customerId, title);
=======
>>>>>>> 08741e7... #Added Cassandra DAO implementation for customer group
=======
        log.trace("Find CustomerGroup By tenantId [{}] customerId [{}] and title [{}]", tenantId, customerId, title);
>>>>>>> 588f07a... Adding cassandra dao changes for group support
        Select select = select().from(ModelConstants.CUSTOMER_GROUP_BY_TENANT_AND_CUSTOMER_AND_TITLE_VIEW_NAME);
        Select.Where query = select.where();
        query.and(eq(ModelConstants.CUSTOMER_GROUP_TENANT_ID_PROPERTY, tenantId));
        query.and(eq(ModelConstants.CUSTOMER_GROUP_CUSTOMER_ID_PROPERTY, customerId));
        query.and(eq(ModelConstants.CUSTOMER_GROUP_TITLE_PROPERTY, title));
<<<<<<< HEAD
<<<<<<< HEAD

=======
>>>>>>> 08741e7... #Added Cassandra DAO implementation for customer group
=======

>>>>>>> 588f07a... Adding cassandra dao changes for group support
        CustomerGroupEntity customerGroupEntity = findOneByStatement(query);
        CustomerGroup customerGroup = DaoUtil.getData(customerGroupEntity);
        return Optional.ofNullable(customerGroup);
    }

    @Override
<<<<<<< HEAD
<<<<<<< HEAD
    public List<UserId> findUserIdsByCustomerGroupId(UUID customerGroupId) {
        log.trace("find Users By CustomerGroupId [{}]", customerGroupId);
        List<UUID> list = new ArrayList<>();
        BoundStatement statement = createUserIdByGroupIdStmt().bind();
        statement.setUUID(0, customerGroupId);

        ResultSet resultSet = executeRead(statement);
        List<Row> rows = resultSet.all();
        for (Row row: rows) {
            list.add(row.getUUID(0));
        }
        return list.stream().map(UserId::new).collect(Collectors.toList());
    }

    private PreparedStatement createUserIdByGroupIdStmt() {
        if (userIdByGroupIdStmt == null) {
            userIdByGroupIdStmt = getSession().prepare(SELECT_PREFIX +
                    USER_ID_PROPERTY + " " +
                    "FROM " + USER_GROUP_COLUMN_FAMILY_NAME + " " +
                    "WHERE " + CUSTOMER_GROUP_ID_PROPERTY + EQUALS_PARAM + ALLOW_FILTERING);
        }
        return userIdByGroupIdStmt;
=======
    public List<UserId> findUserIdsByCustomerGroupId(UUID customerGroupId) {
<<<<<<< HEAD
        return null;
>>>>>>> 48f766b... #Updated user and groups model to have only corresponding ids as a property instead of whole objects. Also, updated service and controller methods accordingly.
=======
        log.trace("find Users By CustomerGroupId [{}]", customerGroupId);
        List<UUID> list = new ArrayList<>();
        BoundStatement statement = createUserIdByGroupIdStmt().bind();
        statement.setUUID(0, customerGroupId);

        ResultSet resultSet = executeRead(statement);
        List<Row> rows = resultSet.all();
        for (Row row: rows) {
            list.add(row.getUUID(0));
        }
        return list.stream().map(UserId::new).collect(Collectors.toList());
    }

    private PreparedStatement createUserIdByGroupIdStmt() {
        if (userIdByGroupIdStmt == null) {
            userIdByGroupIdStmt = getSession().prepare(SELECT_PREFIX +
                    USER_ID_PROPERTY + " " +
                    "FROM " + USER_GROUP_COLUMN_FAMILY_NAME + " " +
                    "WHERE " + CUSTOMER_GROUP_ID_PROPERTY + EQUALS_PARAM + ALLOW_FILTERING);
        }
        return userIdByGroupIdStmt;
>>>>>>> 588f07a... Adding cassandra dao changes for group support
    }

    @Override
    public void deleteUserIdsForCustomerGroupId(UUID customerGroupId) {
<<<<<<< HEAD
<<<<<<< HEAD
        log.trace("delete UserIds for CustomerGroupId [{}]", customerGroupId);
        List<UserId> userIds = findUserIdsByCustomerGroupId(customerGroupId);
        userIds.forEach(userId -> executeDeleteUserForGroup(customerGroupId, userId.getId()));
    }

    @Override
    public void deleteGroupIdsForUserId(UUID userId) {
        log.trace("delete GroupIds for UserId [{}]", userId);
        Delete.Where delete = QueryBuilder.delete().all().from(USER_GROUP_COLUMN_FAMILY_NAME).where(eq(USER_ID_PROPERTY, userId));
        getSession().execute(delete);
=======
        //TODO
>>>>>>> 48f766b... #Updated user and groups model to have only corresponding ids as a property instead of whole objects. Also, updated service and controller methods accordingly.
=======
        log.trace("delete UserIds for CustomerGroupId [{}]", customerGroupId);
        List<UserId> userIds = findUserIdsByCustomerGroupId(customerGroupId);
<<<<<<< HEAD

        for (UserId userId: userIds) {
            Delete.Where delete = QueryBuilder.delete().all()
                    .from(USER_GROUP_COLUMN_FAMILY_NAME)
                    .where(eq(USER_ID_PROPERTY, userId.getId()))
                    .and(eq(CUSTOMER_GROUP_ID_PROPERTY, customerGroupId));
            getSession().execute(delete);
        }
>>>>>>> 588f07a... Adding cassandra dao changes for group support
=======
        userIds.forEach(userId -> executeDeleteUserForGroup(customerGroupId, userId.getId()));
>>>>>>> 880eca9... Added changes to jpa and cassandra dao implementaion for unassign users and groups
    }

    @Override
    public void deleteGroupIdsForUserId(UUID userId) {
        log.trace("delete GroupIds for UserId [{}]", userId);
        Delete.Where delete = QueryBuilder.delete().all().from(USER_GROUP_COLUMN_FAMILY_NAME).where(eq(USER_ID_PROPERTY, userId));
        getSession().execute(delete);
    }

    @Override
    public List<CustomerGroup> findByUserId(UUID userId, TextPageLink textPageLink) {
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 588f07a... Adding cassandra dao changes for group support
        log.trace("find customer groups by UserId [{}]", userId);
        List<UUID> customerGroupIds;
        BoundStatement statement = createGroupIdByUserIdStmt().bind();
        statement.setUUID(0, userId);

        ResultSet resultSet = executeRead(statement);
        List<Row> rows = resultSet.all();
        customerGroupIds = rows.stream().map(row -> row.getUUID(0)).collect(Collectors.toList());

        List<CustomerGroup> customerGroups = new ArrayList<>();
        customerGroupIds.forEach(id -> {
            CustomerGroup customerGroup = findById(id);
            if (customerGroup != null) {
                customerGroups.add(customerGroup);
            }
        });
        return customerGroups;
    }

    private PreparedStatement createGroupIdByUserIdStmt() {
        if (groupIdByUserIdStmt == null) {
            groupIdByUserIdStmt = getSession().prepare(SELECT_PREFIX +
                    CUSTOMER_GROUP_ID_PROPERTY + " " +
                    "FROM " + USER_GROUP_COLUMN_FAMILY_NAME + " " +
                    "WHERE " + USER_ID_PROPERTY + EQUALS_PARAM + ALLOW_FILTERING);
        }
        return groupIdByUserIdStmt;
<<<<<<< HEAD
    }

    @Override
    public void assignUsers(CustomerGroupId customerGroupId, List<UserId> userIds) {
        log.trace("assign users [{}] to group [{}]", userIds, customerGroupId);
        userIds.forEach(userId -> {
            Insert insert = QueryBuilder.insertInto(USER_GROUP_COLUMN_FAMILY_NAME)
                    .value(USER_ID_PROPERTY, userId.getId())
                    .value(CUSTOMER_GROUP_ID_PROPERTY, customerGroupId.getId());
            getSession().execute(insert.toString());
        });

    }

    @Override
    public void unassignUsers(CustomerGroupId customerGroupId , List<UserId> userIds) {
        log.trace("unassign users [{}] from groups [{}]", userIds, customerGroupId);
        userIds.forEach(userId -> executeDeleteUserForGroup(customerGroupId.getId(), userId.getId()));
    }

    @Override
    public void assignGroups(UserId userId , List<CustomerGroupId> customerGroupIds) {
        log.trace("assign groups [{}] to user [{}]", customerGroupIds, userId);
        customerGroupIds.forEach(customerGroupId -> {
            Insert insert = QueryBuilder.insertInto(USER_GROUP_COLUMN_FAMILY_NAME)
                    .value(USER_ID_PROPERTY, userId.getId())
                    .value(CUSTOMER_GROUP_ID_PROPERTY, customerGroupId.getId());
            getSession().execute(insert.toString());
        });
    }

    @Override
    public void unassignGroups(UserId userId , List<CustomerGroupId> customerGroupIds) {
        log.trace("unassign groups [{}] from user [{}]", customerGroupIds, userId);
        customerGroupIds.forEach(customerGroupId -> executeDeleteUserForGroup(customerGroupId.getId(), userId.getId()));
    }

    private void executeDeleteUserForGroup(UUID customerGroupId, UUID userId) {
        Delete.Where delete = QueryBuilder.delete().all()
                .from(USER_GROUP_COLUMN_FAMILY_NAME)
                .where(eq(USER_ID_PROPERTY, userId))
                .and(eq(CUSTOMER_GROUP_ID_PROPERTY, customerGroupId));
        getSession().execute(delete);
    }

    @Override
=======
>>>>>>> 08741e7... #Added Cassandra DAO implementation for customer group
=======
        return null;
=======
>>>>>>> 588f07a... Adding cassandra dao changes for group support
    }

    @Override
<<<<<<< HEAD
>>>>>>> 48f766b... #Updated user and groups model to have only corresponding ids as a property instead of whole objects. Also, updated service and controller methods accordingly.
=======
    public void assignUsers(CustomerGroupId customerGroupId, List<UserId> userIds) {
        log.trace("assign users [{}] to group [{}]", userIds, customerGroupId);
        userIds.forEach(userId -> {
            Insert insert = QueryBuilder.insertInto(USER_GROUP_COLUMN_FAMILY_NAME)
                    .value(USER_ID_PROPERTY, userId.getId())
                    .value(CUSTOMER_GROUP_ID_PROPERTY, customerGroupId.getId());
            getSession().execute(insert.toString());
        });

    }

    @Override
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> c08cec8... #Added assingUsers to group support.
=======
=======
    public void unassignUsers(CustomerGroupId customerGroupId , List<UserId> userIds) {
        log.trace("unassign users [{}] from groups [{}]", userIds, customerGroupId);
        userIds.forEach(userId -> executeDeleteUserForGroup(customerGroupId.getId(), userId.getId()));
    }

    @Override
>>>>>>> 880eca9... Added changes to jpa and cassandra dao implementaion for unassign users and groups
    public void assignGroups(UserId userId , List<CustomerGroupId> customerGroupIds) {
        log.trace("assign groups [{}] to user [{}]", customerGroupIds, userId);
        customerGroupIds.forEach(customerGroupId -> {
            Insert insert = QueryBuilder.insertInto(USER_GROUP_COLUMN_FAMILY_NAME)
                    .value(USER_ID_PROPERTY, userId.getId())
                    .value(CUSTOMER_GROUP_ID_PROPERTY, customerGroupId.getId());
            getSession().execute(insert.toString());
        });
    }

    @Override
<<<<<<< HEAD
>>>>>>> 1af01a8... Added assingGroups to user support.
=======
    public void unassignGroups(UserId userId , List<CustomerGroupId> customerGroupIds) {
        log.trace("unassign groups [{}] from user [{}]", customerGroupIds, userId);
        customerGroupIds.forEach(customerGroupId -> executeDeleteUserForGroup(customerGroupId.getId(), userId.getId()));
    }

    private void executeDeleteUserForGroup(UUID customerGroupId, UUID userId) {
        Delete.Where delete = QueryBuilder.delete().all()
                .from(USER_GROUP_COLUMN_FAMILY_NAME)
                .where(eq(USER_ID_PROPERTY, userId))
                .and(eq(CUSTOMER_GROUP_ID_PROPERTY, customerGroupId));
        getSession().execute(delete);
    }

    @Override
>>>>>>> 880eca9... Added changes to jpa and cassandra dao implementaion for unassign users and groups
    protected Class<CustomerGroupEntity> getColumnFamilyClass() {
        return CustomerGroupEntity.class;
    }

    @Override
    protected String getColumnFamilyName() {
        return ModelConstants.CUSTOMER_GROUP_COLUMN_FAMILY_NAME;
    }
}
