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
package com.hashmapinc.server.dao.sql.cluster;

import com.hashmapinc.server.common.data.cluster.ClusterMetric;
import com.hashmapinc.server.dao.DaoUtil;
import com.hashmapinc.server.dao.cluster.ClusterMetricDao;
import com.hashmapinc.server.dao.model.sql.ClusterMetricEntity;
import com.hashmapinc.server.dao.sql.JpaAbstractSearchTextDao;
import com.hashmapinc.server.dao.util.SqlDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@SqlDao
@Slf4j
public class JpaClusterMetricDao extends JpaAbstractSearchTextDao<ClusterMetricEntity, ClusterMetric> implements ClusterMetricDao {

    @Autowired
    private ClusterMetricRepository clusterMetricRepository;

    @Override
    protected Class<ClusterMetricEntity> getEntityClass() {
        return ClusterMetricEntity.class;
    }

    @Override
    protected CrudRepository<ClusterMetricEntity, String> getCrudRepository() {
        return clusterMetricRepository;
    }

    @Override
    public Optional<ClusterMetric> findClusterMetricByNodeIpAndNodePort(String nodeIp, int nodePort) {
        ClusterMetric clusterMetric = DaoUtil.getData(clusterMetricRepository.findClusterMetricByNodeIpAndNodePort(nodeIp, nodePort));
        return Optional.of(clusterMetric);
    }
}
