/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * The OpenSearch Contributors require contributions made to
 * this file be licensed under the Apache-2.0 license or a
 * compatible open source license.
 *
 * Modifications Copyright OpenSearch Contributors. See
 * GitHub history for details.
 */

package org.opensearch.indexmanagement.sampleextension

import org.junit.Assert
import org.opensearch.action.admin.cluster.node.info.NodesInfoRequest
import org.opensearch.action.admin.cluster.node.info.PluginsAndModules
import org.opensearch.test.OpenSearchIntegTestCase

class SampleExtensionPluginIT: OpenSearchIntegTestCase() {

    fun testPluginsAreInstalled() {
        val nodesInfoRequest = NodesInfoRequest()
        nodesInfoRequest.addMetric(NodesInfoRequest.Metric.PLUGINS.metricName())
        val nodesInfoResponse = client().admin().cluster().nodesInfo(nodesInfoRequest).actionGet()
        val pluginInfo = nodesInfoResponse.nodes[0].getInfo(PluginsAndModules::class.java).pluginInfos
        Assert.assertTrue(pluginInfo.map { info -> info.name }.equals("opensearch-index-management"))
        Assert.assertTrue(pluginInfo.map { info -> info.name }.equals("opensearch-index-management-sample-extension"))
    }
}