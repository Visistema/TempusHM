/*
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
/*@ngInject*/
export default function DeleteGroupsToUserController($scope, toast, userService,usergroupService, $mdDialog, userId, users, customerId, $translate) {

    var vm = this;

    vm.users = users;
    vm.users.selections = [];
    vm.searchText = '';
    vm.users.selectedCount =0;

    vm.unassign = unassign;
    vm.cancel = cancel;
    vm.hasData = hasData;
    vm.noData = noData;
    vm.searchUserTextUpdated = searchUserTextUpdated;
    vm.toggleUserSelection = toggleUserSelection;
    vm.customerId = customerId;
    vm.userId = userId;
    //vm.email =[];
    vm.theUsers = {
        getItemAtIndex: function (index) {
            if (index > vm.users.data.length) {
                vm.theUsers.fetchMoreItems_(index);
                return null;
            }
            var item = vm.users.data[index];
            if (item) {
                item.indexNumber = index + 1;
            }

            return item;
        },

        getLength: function () {
            if (vm.users.hasNext) {
                return vm.users.data.length + vm.users.nextPageLink.limit;
            } else {
                return vm.users.data.length;
            }
        },

        fetchMoreItems_: function () {
            if (vm.users.hasNext && !vm.users.pending) {
                vm.users.pending = true;

                usergroupService.assignedGroups(vm.userId,vm.users.nextPageLink).then(
                    function success(users) {
                        vm.users.data = vm.users.data.concat(users.data);
                        vm.users.nextPageLink = users.nextPageLink;
                        vm.users.hasNext = users.hasNext;
                        if (vm.users.hasNext) {
                            vm.users.nextPageLink.limit = vm.users.pageSize;
                        }
                        vm.users.pending = false;
                    },
                    function fail() {
                        vm.users.hasNext = false;
                        vm.users.pending = false;
                    });
            }
        }
    };

    function cancel () {
        $mdDialog.cancel();
    }

    function unassign() {
        usergroupService.unassignGroupToUser(vm.userId,vm.users.selections).then(

            function success() {

              $mdDialog.cancel();
              toast.showSuccess($translate.instant('user.user-unassigned-message'));

            },

            function fail() {
            });

    }


    function noData() {
        return vm.users.data.length == 0 && !vm.users.hasNext;
    }

    function hasData() {
        return vm.users.data.length > 0;
    }

    function toggleUserSelection($event, user) {
        $event.stopPropagation();
        var selected = angular.isDefined(user.selected) && user.selected;

        user.selected = !selected;
        if (user.selected) {
            vm.users.selections.push(user.id.id);
            vm.users.selectedCount++;
        } else {

            var index = vm.users.selections.indexOf(user.id.id)
            vm.users.selections.splice(index, 1);
            vm.users.selectedCount--;
        }
    }

    function searchUserTextUpdated() {
        vm.users = {
            pageSize: vm.users.pageSize,
            data: [],
            nextPageLink: {
                limit: vm.users.pageSize,
                textSearch: vm.searchText
            },
            selections: [],
            selectedCount: 0,
            hasNext: true,
            pending: false
        };
    }

}
