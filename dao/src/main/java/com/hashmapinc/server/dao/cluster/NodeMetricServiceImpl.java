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
package com.hashmapinc.server.dao.cluster;

import com.hashmapinc.server.common.data.cluster.NodeMetric;
import com.hashmapinc.server.dao.entity.AbstractEntityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class NodeMetricServiceImpl extends AbstractEntityService implements NodeMetricService {

    @Autowired
    private NodeMetricDao nodeMetricDao;

    @Override
    public NodeMetric save(NodeMetric nodeMetric) {
        log.debug("Executing NodeMetricServiceImpl save NodeMetric [{}]", nodeMetric);
        return nodeMetricDao.save(nodeMetric);
    }

    @Override
    public Optional<NodeMetric> findNodeMetricByHostAndPort(String host, int port) {
        log.debug("Executing NodeMetricServiceImpl findNodeMetricByHostAndPort, host [{}], port [{}]", host, port);
        return nodeMetricDao.findNodeMetricByHostAndPort(host, port);
    }

    @Override
    public List<NodeMetric> findAll() {
        log.debug("Executing NodeMetricServiceImpl findAll NodeMetric");
        return nodeMetricDao.find();
    }

    @Override
    public NodeMetric incrementRpcSessionCount(String host, int port) {
        Optional<NodeMetric> nodeMetric = findNodeMetricByHostAndPort(host, port);
        if (nodeMetric.isPresent()) {
            int rpcSessionCount = nodeMetric.get().getRpcSessionCount();
            log.debug("NodeMetricServiceImpl rpcSessionCount [{}]", rpcSessionCount);
            nodeMetric.get().setRpcSessionCount(rpcSessionCount + 1);
            return nodeMetricDao.save(nodeMetric.get());
        }
        return nodeMetric.get();
    }

    @Override
    public NodeMetric decrementRpcSessionCount(String host, int port) {
        Optional<NodeMetric> nodeMetric = findNodeMetricByHostAndPort(host, port);
        if (nodeMetric.isPresent()) {
            int rpcSessionCount = nodeMetric.get().getRpcSessionCount();
            log.debug("NodeMetricServiceImpl rpcSessionCount [{}]", rpcSessionCount);
            rpcSessionCount--;
            nodeMetric.get().setRpcSessionCount(rpcSessionCount);
            /*if (rpcSessionCount < 0) {
                nodeMetric.get().setRpcSessionCount(0);
            } else {
                nodeMetric.get().setRpcSessionCount(rpcSessionCount);
            }*/
            return nodeMetricDao.save(nodeMetric.get());
        }
        return nodeMetric.get();
    }

    @Override
    public NodeMetric incrementDeviceSessionCount(String host, int port) {
        Optional<NodeMetric> nodeMetric = findNodeMetricByHostAndPort(host, port);
        if (nodeMetric.isPresent()) {
            int deviceSessionCount = nodeMetric.get().getDeviceSessionCount();
            log.debug("NodeMetricServiceImpl deviceSessionCount [{}]", deviceSessionCount);
            nodeMetric.get().setDeviceSessionCount(deviceSessionCount + 1);
            return nodeMetricDao.save(nodeMetric.get());
        }
        return nodeMetric.get();
    }

    @Override
    public NodeMetric decrementDeviceSessionCount(String host, int port) {
        Optional<NodeMetric> nodeMetric = findNodeMetricByHostAndPort(host, port);
        if (nodeMetric.isPresent()) {
            int deviceSessionCount = nodeMetric.get().getDeviceSessionCount();
            log.debug("NodeMetricServiceImpl deviceSessionCount [{}]", deviceSessionCount);
            deviceSessionCount--;
            nodeMetric.get().setDeviceSessionCount(deviceSessionCount);
            /*if (deviceSessionCount < 0) {
                nodeMetric.get().setDeviceSessionCount(0);
            } else {
                nodeMetric.get().setDeviceSessionCount(deviceSessionCount);
            }*/
            return nodeMetricDao.save(nodeMetric.get());
        }
        return nodeMetric.get();
    }
}
