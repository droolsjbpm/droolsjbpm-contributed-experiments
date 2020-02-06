/*
 * Copyright 2020 Red Hat, Inc. and/or its affiliates.
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

package org.jbpm.prediction.service.seldon;

import org.jbpm.prediction.service.seldon.payload.PredictionMetadata;
import org.jbpm.prediction.service.seldon.payload.PredictionResponse;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FeatureDeserializerTest {

    @Test
    public void testSingleFeatureDeserialization() throws IOException {

        final String JSON = TestUtils.readJSONAsString("responses/ndarray.json");

        final PredictionResponse response = PredictionResponse.parse(JSON);

        assertEquals(1, response.getData().getArray().size());
        assertEquals(2, response.getData().getNames().size());
        List<Double> firstOutcome = response.getData().getArray().get(0);
        assertEquals(2, firstOutcome.size());
        assertEquals(0.71, firstOutcome.get(0), 0.0);
        assertEquals(0.29, firstOutcome.get(1), 0.0);
    }

    @Test
    public void testTagsDeserialization() throws IOException {
        final String JSON = TestUtils.readJSONAsString("responses/metadata.json");

        final PredictionResponse response = PredictionResponse.parse(JSON);

        final PredictionMetadata metadata = response.getMetadata();

        assertEquals(2, metadata.getTags().keySet().size());
        assertEquals(1.0, metadata.getTags().get("version"));
        assertEquals("localhost", metadata.getTags().get("namespace"));
    }
}