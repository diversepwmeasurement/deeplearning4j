/*
 *  ******************************************************************************
 *  *
 *  *
 *  * This program and the accompanying materials are made available under the
 *  * terms of the Apache License, Version 2.0 which is available at
 *  * https://www.apache.org/licenses/LICENSE-2.0.
 *  *
 *  *  See the NOTICE file distributed with this work for additional
 *  *  information regarding copyright ownership.
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 *  * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 *  * License for the specific language governing permissions and limitations
 *  * under the License.
 *  *
 *  * SPDX-License-Identifier: Apache-2.0
 *  *****************************************************************************
 */
package org.eclipse.deeplearning4j.frameworkimport.keras.layers.advanced.activation;

import org.deeplearning4j.nn.conf.layers.ActivationLayer;
import org.deeplearning4j.BaseDL4JTest;
import org.deeplearning4j.frameworkimport.keras.keras.config.Keras1LayerConfiguration;
import org.deeplearning4j.frameworkimport.keras.keras.config.Keras2LayerConfiguration;
import org.deeplearning4j.frameworkimport.keras.keras.config.KerasLayerConfiguration;
import org.deeplearning4j.frameworkimport.keras.keras.layers.advanced.activations.KerasThresholdedReLU;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.nd4j.common.tests.tags.NativeTag;
import org.nd4j.common.tests.tags.TagNames;

/**
 * @author Max Pumperla
 */
@DisplayName("Keras Thresholded Re LU Test")
@Tag(TagNames.FILE_IO)
@Tag(TagNames.KERAS)
@NativeTag
class KerasThresholdedReLUTest extends BaseDL4JTest {

    private Keras1LayerConfiguration conf1 = new Keras1LayerConfiguration();

    private Keras2LayerConfiguration conf2 = new Keras2LayerConfiguration();

    @Test
    @DisplayName("Test Thresholded Re LU Layer")
    void testThresholdedReLULayer() throws Exception {
        Integer keras1 = 1;
        buildThresholdedReLULayer(conf1, keras1);
        Integer keras2 = 2;
        buildThresholdedReLULayer(conf2, keras2);
    }

    private void buildThresholdedReLULayer(KerasLayerConfiguration conf, Integer kerasVersion) throws Exception {
        double theta = 0.5;
        Map<String, Object> layerConfig = new HashMap<>();
        layerConfig.put(conf.getLAYER_FIELD_CLASS_NAME(), conf.getLAYER_CLASS_NAME_THRESHOLDED_RELU());
        Map<String, Object> config = new HashMap<>();
        String LAYER_FIELD_THRESHOLD = "theta";
        config.put(LAYER_FIELD_THRESHOLD, theta);
        String layerName = "thresholded_relu";
        config.put(conf.getLAYER_FIELD_NAME(), layerName);
        layerConfig.put(conf.getLAYER_FIELD_CONFIG(), config);
        layerConfig.put(conf.getLAYER_FIELD_KERAS_VERSION(), kerasVersion);
        ActivationLayer layer = new KerasThresholdedReLU(layerConfig).getActivationLayer();
        assertEquals(layer.getActivationFn().toString(), "thresholdedrelu(theta=0.5)");
        assertEquals(layerName, layer.getLayerName());
    }
}
