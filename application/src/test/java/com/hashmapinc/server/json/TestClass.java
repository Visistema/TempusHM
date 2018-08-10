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
package com.hashmapinc.server.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class TestClass {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        KafkaFunction kafka = new KafkaFunction();
        kafka.setLabel("hello-function");
        kafka.setTopic("telemetry");
        kafka.setConf(mapper.readTree("{\"a\":\"some a\", \"b\": \"some b\"}"));

        ParentInterface cast = kafka;

        String json = mapper.writeValueAsString(cast);

        System.out.println(json);

        System.out.println(mapper.readValue(json, ParentInterface.class));
    }
}
