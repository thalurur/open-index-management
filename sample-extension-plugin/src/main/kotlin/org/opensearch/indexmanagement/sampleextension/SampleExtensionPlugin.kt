/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 *
 * $originalComment
 */

package org.opensearch.indexmanagement.sampleextension

import java.util.function.Supplier
import org.opensearch.client.Client
import org.opensearch.cluster.metadata.IndexNameExpressionResolver
import org.opensearch.cluster.node.DiscoveryNodes
import org.opensearch.cluster.service.ClusterService
import org.opensearch.common.io.stream.NamedWriteableRegistry
import org.opensearch.common.settings.ClusterSettings
import org.opensearch.common.settings.IndexScopedSettings
import org.opensearch.common.settings.Settings
import org.opensearch.common.settings.SettingsFilter
import org.opensearch.common.xcontent.NamedXContentRegistry
import org.opensearch.env.Environment
import org.opensearch.env.NodeEnvironment
import org.opensearch.indexmanagement.spi.ClusterEventType
import org.opensearch.indexmanagement.spi.IndexManagementExtension
import org.opensearch.indexmanagement.spi.indexstatemanagement.ClusterEventHandler
import org.opensearch.indexmanagement.spi.indexstatemanagement.IndexMetadataService
import org.opensearch.indexmanagement.spi.indexstatemanagement.model.Action
import org.opensearch.indexmanagement.spi.indexstatemanagement.model.ActionParser
import org.opensearch.plugins.ActionPlugin
import org.opensearch.plugins.Plugin
import org.opensearch.repositories.RepositoriesService
import org.opensearch.rest.RestController
import org.opensearch.rest.RestHandler
import org.opensearch.script.ScriptService
import org.opensearch.threadpool.ThreadPool
import org.opensearch.watcher.ResourceWatcherService

class SampleExtensionPlugin: ActionPlugin, IndexManagementExtension, Plugin() {

    override fun createComponents(
        client: Client?,
        clusterService: ClusterService?,
        threadPool: ThreadPool?,
        resourceWatcherService: ResourceWatcherService?,
        scriptService: ScriptService?,
        xContentRegistry: NamedXContentRegistry?,
        environment: Environment?,
        nodeEnvironment: NodeEnvironment?,
        namedWriteableRegistry: NamedWriteableRegistry?,
        indexNameExpressionResolver: IndexNameExpressionResolver?,
        repositoriesServiceSupplier: Supplier<RepositoriesService>?
    ): MutableCollection<Any> {
        return super.createComponents(
            client,
            clusterService,
            threadPool,
            resourceWatcherService,
            scriptService,
            xContentRegistry,
            environment,
            nodeEnvironment,
            namedWriteableRegistry,
            indexNameExpressionResolver,
            repositoriesServiceSupplier
        )
    }

    override fun getRestHandlers(
        settings: Settings?,
        restController: RestController?,
        clusterSettings: ClusterSettings?,
        indexScopedSettings: IndexScopedSettings?,
        settingsFilter: SettingsFilter?,
        indexNameExpressionResolver: IndexNameExpressionResolver?,
        nodesInCluster: Supplier<DiscoveryNodes>?
    ): List<RestHandler> {
        return listOf(SampleExtensionRestHandler())
    }

    override fun getClusterEventHandlers(): Map<ClusterEventType, ClusterEventHandler> {
        return mutableMapOf()
    }

    override fun getISMActionParsers(): List<ActionParser> {
        return listOf(DeleteActionParser())
    }

    override fun getIndexMetadataService(): Map<String, IndexMetadataService> {
        return mapOf()
    }
}