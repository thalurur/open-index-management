/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 *
 */

package org.opensearch.indexmanagement.sampleextension

import org.opensearch.client.Client
import org.opensearch.cluster.service.ClusterService
import org.opensearch.common.io.stream.StreamInput
import org.opensearch.common.io.stream.StreamOutput
import org.opensearch.common.settings.Settings
import org.opensearch.common.xcontent.XContentParser
import org.opensearch.indexmanagement.spi.indexstatemanagement.model.Action
import org.opensearch.indexmanagement.spi.indexstatemanagement.model.ActionConfig
import org.opensearch.indexmanagement.spi.indexstatemanagement.model.ManagedIndexMetadata
import org.opensearch.script.ScriptService

class DeleteActionConfig(type: String, actionIndex: Int) : ActionConfig(type, actionIndex) {

    override fun fromStreamInput(sin: StreamInput): ActionConfig {
        TODO("Not yet implemented")
    }

    override fun parse(xcp: XContentParser, index: Int): ActionConfig {
        TODO("Not yet implemented")
    }

    override fun toAction(clusterService: ClusterService, scriptService: ScriptService, client: Client, settings: Settings, managedIndexMetadata: ManagedIndexMetadata): Action {
        TODO("Not yet implemented")
    }

    override fun writeTo(out: StreamOutput) {
        TODO("Not yet implemented")
    }
}