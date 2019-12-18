/*
 * Copyright 2018 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kiegroup.zenithr.drools.model;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;

/**
 * {@link Output#getValue()} should be a JsonObject. Then this wrapper can be removed
 */
@Deprecated
public class RuleResult {

    private String name;
    private String path;
    private JsonObject value;

    public RuleResult(Output output) {
        this.name = output.getName();
        this.path = output.getPath();
        this.value = Json.createReader(new StringReader(output.getValue())).readObject();
    }

    public void setValue(JsonObject value) {
        this.value = value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public JsonObject getValue() {
        return value;
    }

    public String getPath() {
        return path;
    }
}
