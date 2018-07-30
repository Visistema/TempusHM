/**
<<<<<<< HEAD
<<<<<<< HEAD
 * Copyright © 2016-2018 The Thingsboard Authors
 * Modifications © 2017-2018 Hashmap, Inc
=======
 * Copyright © 2017-2018 Hashmap, Inc
>>>>>>> 1ce43ab... #Added customer group controller.
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
package com.hashmapinc.server.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hashmapinc.server.common.data.CustomerGroup;
import com.hashmapinc.server.common.data.EntityType;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
import com.hashmapinc.server.common.data.UUIDConverter;
>>>>>>> f41810e... #Fixed customer group controller methods which was taking customerId from currentUser context instead of taking from url.
=======
>>>>>>> 895869b... adding changes in group controller
import com.hashmapinc.server.common.data.User;
=======
>>>>>>> 1ce43ab... #Added customer group controller.
=======
import com.hashmapinc.server.common.data.User;
>>>>>>> 43ee32c... #Added controller changes for group support for users
import com.hashmapinc.server.common.data.audit.ActionType;
import com.hashmapinc.server.common.data.id.CustomerGroupId;
import com.hashmapinc.server.common.data.id.CustomerId;
import com.hashmapinc.server.common.data.id.TenantId;
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> c08cec8... #Added assingUsers to group support.
import com.hashmapinc.server.common.data.id.UserId;
import com.hashmapinc.server.common.data.page.TextPageData;
import com.hashmapinc.server.common.data.page.TextPageLink;
import com.hashmapinc.server.common.data.security.Authority;
import com.hashmapinc.server.exception.TempusErrorCode;
import com.hashmapinc.server.exception.TempusException;
import com.hashmapinc.server.service.security.model.SecurityUser;
<<<<<<< HEAD
<<<<<<< HEAD
import lombok.extern.slf4j.Slf4j;
=======
import com.hashmapinc.server.common.data.page.TextPageData;
import com.hashmapinc.server.common.data.page.TextPageLink;
import com.hashmapinc.server.exception.TempusException;
>>>>>>> 1ce43ab... #Added customer group controller.
=======
>>>>>>> 43ee32c... #Added controller changes for group support for users
=======
import lombok.extern.slf4j.Slf4j;
>>>>>>> f41810e... #Fixed customer group controller methods which was taking customerId from currentUser context instead of taking from url.
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> c08cec8... #Added assingUsers to group support.
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customer")
@Slf4j
public class CustomerGroupController extends BaseController {

    public static final String CUSTOMER_GROUP_ID = "customerGroupId";
    public static final String USER_ID = "userId";
<<<<<<< HEAD
=======
@RestController
@RequestMapping("/api/customer")
public class CustomerGroupController extends BaseController {

    public static final String CUSTOMER_GROUP_ID = "customerGroupId";
>>>>>>> 1ce43ab... #Added customer group controller.
=======
>>>>>>> e715e4d... #Added provision to fetch policies for user

    @PreAuthorize("hasAuthority('TENANT_ADMIN')")
    @GetMapping(value = "/group/{customerGroupId}")
    @ResponseBody
    public CustomerGroup getCustomerGroupById(@PathVariable(CUSTOMER_GROUP_ID) String strCustomerGroupId) throws TempusException {
        checkParameter(CUSTOMER_GROUP_ID, strCustomerGroupId);
        try {
            CustomerGroupId customerGroupId = new CustomerGroupId(toUUID(strCustomerGroupId));
            return checkCustomerGroupId(customerGroupId);
        } catch (Exception e) {
            throw handleException(e);
        }
    }


    @PreAuthorize("hasAuthority('TENANT_ADMIN')")
    @GetMapping(value = "/group/{customerGroupId}/shortInfo")
    @ResponseBody
    public JsonNode getShortCustomerGroupInfoById(@PathVariable(CUSTOMER_GROUP_ID) String strCustomerGroupId) throws TempusException {
        checkParameter(CUSTOMER_GROUP_ID, strCustomerGroupId);
        try {
            CustomerGroupId customerGroupId = new CustomerGroupId(toUUID(strCustomerGroupId));
            CustomerGroup customerGroup = checkCustomerGroupId(customerGroupId);
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode infoObject = objectMapper.createObjectNode();
            infoObject.put("title", customerGroup.getTitle());
            ArrayNode policies = objectMapper.valueToTree(customerGroup.getPolicies());
            infoObject.putArray("policies").add(policies);
            return infoObject;
        } catch (Exception e) {
            throw handleException(e);
        }
    }

    @PreAuthorize("hasAuthority('TENANT_ADMIN')")
    @GetMapping(value = "/group/{customerGroupId}/title", produces = "application/text")
    @ResponseBody
    public String getCustomerTitleById(@PathVariable(CUSTOMER_GROUP_ID) String strCustomerGroupId) throws TempusException {
        checkParameter(CUSTOMER_GROUP_ID, strCustomerGroupId);
        try {
            CustomerGroupId customerGroupId = new CustomerGroupId(toUUID(strCustomerGroupId));
            CustomerGroup customerGroup = checkCustomerGroupId(customerGroupId);
            return customerGroup.getTitle();
        } catch (Exception e) {
            throw handleException(e);
        }
    }

    @PreAuthorize("hasAuthority('TENANT_ADMIN')")
    @PostMapping(value = "/group")
    @ResponseBody
    public CustomerGroup saveCustomerGroup(@RequestBody CustomerGroup customerGroup) throws TempusException {
        try {
            customerGroup.setTenantId(getCurrentUser().getTenantId());
<<<<<<< HEAD
<<<<<<< HEAD
            customerGroup.setCustomerId(customerGroup.getCustomerId());
=======
            customerGroup.setCustomerId(getCurrentUser().getCustomerId());
>>>>>>> 1ce43ab... #Added customer group controller.
=======
            customerGroup.setCustomerId(customerGroup.getCustomerId());
>>>>>>> f41810e... #Fixed customer group controller methods which was taking customerId from currentUser context instead of taking from url.
            CustomerGroup savedCustomerGroup = checkNotNull(customerGroupService.saveCustomerGroup(customerGroup));

            logEntityAction(savedCustomerGroup.getId(), savedCustomerGroup,
                    savedCustomerGroup.getCustomerId(),
                    customerGroup.getId() == null ? ActionType.ADDED : ActionType.UPDATED, null);

            return savedCustomerGroup;
        } catch (Exception e) {

            logEntityAction(emptyId(EntityType.CUSTOMER_GROUP), customerGroup,
                    null, customerGroup.getId() == null ? ActionType.ADDED : ActionType.UPDATED, e);

            throw handleException(e);
        }
    }

    @PreAuthorize("hasAuthority('TENANT_ADMIN')")
    @DeleteMapping(value = "/group/{customerGroupId}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteCustomerGroup(@PathVariable(CUSTOMER_GROUP_ID) String strCustomerGroupId) throws TempusException {
        checkParameter(CUSTOMER_GROUP_ID, strCustomerGroupId);
        try {
            CustomerGroupId customerGroupId = new CustomerGroupId(toUUID(strCustomerGroupId));
            CustomerGroup customerGroup = checkCustomerGroupId(customerGroupId);
            customerGroupService.deleteCustomerGroup(customerGroupId);

            logEntityAction(customerGroupId, customerGroup,
                    customerGroup.getCustomerId(),
                    ActionType.DELETED, null, strCustomerGroupId);

        } catch (Exception e) {

            logEntityAction(emptyId(EntityType.CUSTOMER_GROUP),
                    null,
                    null,
                    ActionType.DELETED, e, strCustomerGroupId);

            throw handleException(e);
        }
    }

    @PreAuthorize("hasAuthority('TENANT_ADMIN')")
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
    @GetMapping(value = "/{customerId}/groups", params = {"limit"})
    @ResponseBody
    public TextPageData<CustomerGroup> getCustomerGroups(@PathVariable("customerId") String strCustomerId,
                                               @RequestParam int limit,
                                               @RequestParam(required = false) String textSearch,
                                               @RequestParam(required = false) String idOffset,
                                               @RequestParam(required = false) String textOffset) throws TempusException {
        checkParameter("customerId", strCustomerId);
        try {
            TextPageLink pageLink = createPageLink(limit, textSearch, idOffset, textOffset);
            TenantId tenantId = getCurrentUser().getTenantId();
            CustomerId customerId = new CustomerId(toUUID(strCustomerId));
            checkCustomerId(customerId);
=======
    @GetMapping(value = "/groups", params = {"limit"})
=======
    @GetMapping(value = "/{customerId}/group", params = {"limit"})
>>>>>>> f41810e... #Fixed customer group controller methods which was taking customerId from currentUser context instead of taking from url.
=======
    @GetMapping(value = "/{customerId}/groups", params = {"limit"})
>>>>>>> 895869b... adding changes in group controller
    @ResponseBody
    public TextPageData<CustomerGroup> getCustomerGroups(@PathVariable("customerId") String strCustomerId,
                                               @RequestParam int limit,
                                               @RequestParam(required = false) String textSearch,
                                               @RequestParam(required = false) String idOffset,
                                               @RequestParam(required = false) String textOffset) throws TempusException {
        checkParameter("customerId", strCustomerId);
        try {
            TextPageLink pageLink = createPageLink(limit, textSearch, idOffset, textOffset);
            TenantId tenantId = getCurrentUser().getTenantId();
<<<<<<< HEAD
            CustomerId customerId = getCurrentUser().getCustomerId();
>>>>>>> 1ce43ab... #Added customer group controller.
=======
            CustomerId customerId = new CustomerId(toUUID(strCustomerId));
            checkCustomerId(customerId);
>>>>>>> f41810e... #Fixed customer group controller methods which was taking customerId from currentUser context instead of taking from url.
            return checkNotNull(customerGroupService.findCustomerGroupsByTenantIdAndCustomerId(tenantId, customerId, pageLink));
        } catch (Exception e) {
            throw handleException(e);
        }
    }

    @PreAuthorize("hasAuthority('TENANT_ADMIN')")
<<<<<<< HEAD
<<<<<<< HEAD
    @GetMapping(value = "/{customerId}/group/{customerGroupTitle}")
    @ResponseBody
    public CustomerGroup getCustomerGroupByTitle(
            @PathVariable("customerId") String strCustomerId,
            @PathVariable("customerGroupTitle") String customerGroupTitle) throws TempusException {
        checkParameter("customerId", strCustomerId);
        try {
            TenantId tenantId = getCurrentUser().getTenantId();
            CustomerId customerId = new CustomerId(toUUID(strCustomerId));
            checkCustomerId(customerId);
=======
    @GetMapping(value = "/group", params = {"customerGroupTitle"})
=======
    @GetMapping(value = "/{customerId}/group/{customerGroupTitle}")
>>>>>>> f41810e... #Fixed customer group controller methods which was taking customerId from currentUser context instead of taking from url.
    @ResponseBody
    public CustomerGroup getCustomerGroupByTitle(
            @PathVariable("customerId") String strCustomerId,
            @PathVariable("customerGroupTitle") String customerGroupTitle) throws TempusException {
        checkParameter("customerId", strCustomerId);
        try {
            TenantId tenantId = getCurrentUser().getTenantId();
<<<<<<< HEAD
            CustomerId customerId = getCurrentUser().getCustomerId();
>>>>>>> 1ce43ab... #Added customer group controller.
=======
            CustomerId customerId = new CustomerId(toUUID(strCustomerId));
            checkCustomerId(customerId);
>>>>>>> f41810e... #Fixed customer group controller methods which was taking customerId from currentUser context instead of taking from url.
            return checkNotNull(customerGroupService.findCustomerByTenantIdAndCustomerIdAndTitle(tenantId, customerId, customerGroupTitle));
        } catch (Exception e) {
            throw handleException(e);
        }
    }
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 43ee32c... #Added controller changes for group support for users

    @PreAuthorize("hasAuthority('TENANT_ADMIN')")
    @GetMapping(value = "/group/{customerGroupId}/users", params = { "limit" })
    @ResponseBody
    public TextPageData<User> getUsersByGropuId(
            @PathVariable(CUSTOMER_GROUP_ID) String strCustomerGroupId,
            @RequestParam int limit,
            @RequestParam(required = false) String textSearch,
            @RequestParam(required = false) String idOffset,
            @RequestParam(required = false) String textOffset) throws TempusException {
        checkParameter(CUSTOMER_GROUP_ID, strCustomerGroupId);
        try {
            CustomerGroupId customerGroupId = new CustomerGroupId(toUUID(strCustomerGroupId));
            SecurityUser authUser = getCurrentUser();
            if (authUser.getAuthority() == Authority.CUSTOMER_USER && !authUser.getId().equals(customerGroupId)) {
                throw new TempusException(YOU_DON_T_HAVE_PERMISSION_TO_PERFORM_THIS_OPERATION,
                        TempusErrorCode.PERMISSION_DENIED);
            }
            final CustomerGroup customerGroup = checkNotNull(checkCustomerGroupId(customerGroupId));
            TextPageLink pageLink = createPageLink(limit, textSearch, idOffset, textOffset);
<<<<<<< HEAD
<<<<<<< HEAD
            return userService.findUsersByIds(customerGroup.getUserIds(), pageLink);
=======
            return new TextPageData<>(customerGroup.getUsers(), pageLink);
>>>>>>> 43ee32c... #Added controller changes for group support for users
=======
            return userService.findUsersByIds(customerGroup.getUserIds(), pageLink);
>>>>>>> 48f766b... #Updated user and groups model to have only corresponding ids as a property instead of whole objects. Also, updated service and controller methods accordingly.
        } catch (Exception e) {
            throw handleException(e);
        }
    }
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> c08cec8... #Added assingUsers to group support.

    @PreAuthorize("hasAuthority('TENANT_ADMIN')")
    @PostMapping(value = "/group/{customerGroupId}/users")
    @ResponseBody
    public CustomerGroup assignUsersToGroup(
            @PathVariable(CUSTOMER_GROUP_ID) String strCustomerGroupId,
            @RequestBody List<UUID> userUuids) throws TempusException {
        checkParameter(CUSTOMER_GROUP_ID, strCustomerGroupId);
        try{
            CustomerGroupId customerGroupId = new CustomerGroupId(toUUID(strCustomerGroupId));
            List<UserId> userIds = userUuids.stream().map(UserId::new).collect(Collectors.toList());
            for (UserId userId: userIds) {
                checkUserId(userId);
            }
            return checkNotNull(customerGroupService.assignUsers(customerGroupId, userIds));
        } catch (Exception e){
            throw handleException(e);
        }
    }
<<<<<<< HEAD
<<<<<<< HEAD

    @PreAuthorize("hasAuthority('TENANT_ADMIN')")
    @PutMapping(value = "/group/{customerGroupId}/users")
    @ResponseBody
    public CustomerGroup unassignUsersFromGroup(
            @PathVariable(CUSTOMER_GROUP_ID) String strCustomerGroupId,
            @RequestBody List<UUID> userUuids) throws TempusException {
        checkParameter(CUSTOMER_GROUP_ID, strCustomerGroupId);
        try{
            CustomerGroupId customerGroupId = new CustomerGroupId(toUUID(strCustomerGroupId));
            List<UserId> userIds = userUuids.stream().map(UserId::new).collect(Collectors.toList());
            for (UserId userId: userIds) {
                checkUserId(userId);
            }
            return checkNotNull(customerGroupService.unassignUsers(customerGroupId, userIds));
        } catch (Exception e){
            throw handleException(e);
        }
    }
=======
>>>>>>> e715e4d... #Added provision to fetch policies for user

    @PreAuthorize("hasAuthority('TENANT_ADMIN')")
    @PutMapping(value = "/group/{customerGroupId}/users")
    @ResponseBody
    public CustomerGroup unassignUsersFromGroup(
            @PathVariable(CUSTOMER_GROUP_ID) String strCustomerGroupId,
            @RequestBody List<UUID> userUuids) throws TempusException {
        checkParameter(CUSTOMER_GROUP_ID, strCustomerGroupId);
        try{
            CustomerGroupId customerGroupId = new CustomerGroupId(toUUID(strCustomerGroupId));
            List<UserId> userIds = userUuids.stream().map(UserId::new).collect(Collectors.toList());
            for (UserId userId: userIds) {
                checkUserId(userId);
            }
            return checkNotNull(customerGroupService.unassignUsers(customerGroupId, userIds));
        } catch (Exception e){
            throw handleException(e);
        }
    }

    @PreAuthorize("hasAuthority('TENANT_ADMIN')")
    @GetMapping(value = "/group/policy/{userId}")
    @ResponseBody
    public List<String> getPoliciesForUser(
            @PathVariable(USER_ID) String strUserId) throws TempusException {
        checkParameter(USER_ID, strUserId);
        try{

            UserId userId = new UserId(toUUID(strUserId));
            checkUserId(userId);
            return checkNotNull(customerGroupService.findGroupPoliciesForUser(userId));
        } catch (Exception e){
            throw handleException(e);
        }
    }
<<<<<<< HEAD
=======
>>>>>>> 1ce43ab... #Added customer group controller.
=======
>>>>>>> 43ee32c... #Added controller changes for group support for users
=======
>>>>>>> c08cec8... #Added assingUsers to group support.
=======
>>>>>>> e715e4d... #Added provision to fetch policies for user
}
