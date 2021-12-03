/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 *
 */

package org.opensearch.indexmanagement.spi.indexstatemanagement.model

import org.opensearch.client.Client
import org.opensearch.cluster.service.ClusterService
import org.opensearch.common.io.stream.StreamInput
import org.opensearch.common.io.stream.Writeable
import org.opensearch.common.settings.Settings
import org.opensearch.common.xcontent.ToXContent
import org.opensearch.common.xcontent.ToXContentFragment
import org.opensearch.common.xcontent.XContentBuilder
import org.opensearch.common.xcontent.XContentParser
import org.opensearch.script.ScriptService

abstract class ActionConfig(
    val type: String,
    val actionIndex: Int
) : ToXContentFragment, Writeable {

    var configTimeout: ActionTimeout? = null
        private set
    var configRetry: ActionRetry? = null
        private set

    override fun toXContent(builder: XContentBuilder, params: ToXContent.Params): XContentBuilder {
        configTimeout?.toXContent(builder, params)
        configRetry?.toXContent(builder, params)
        return builder
    }

    abstract fun toAction(
        clusterService: ClusterService,
        scriptService: ScriptService,
        client: Client,
        settings: Settings,
        managedIndexMetadata: ManagedIndexMetadata
    ): Action

    abstract fun parse(xcp: XContentParser, index: Int): ActionConfig

    abstract fun fromStreamInput(sin: StreamInput): ActionConfig
}