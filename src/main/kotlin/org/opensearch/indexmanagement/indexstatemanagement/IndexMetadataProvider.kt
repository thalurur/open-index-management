/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 *
 * $originalComment
 */

package org.opensearch.indexmanagement.indexstatemanagement

import org.apache.logging.log4j.LogManager
import org.opensearch.client.Client
import org.opensearch.cluster.service.ClusterService
import org.opensearch.indexmanagement.spi.indexstatemanagement.IndexMetadataService
import org.opensearch.indexmanagement.spi.indexstatemanagement.model.IndexMetadata

/**
 * Provides index metadata to classes that need to know the index metadata
 */
class IndexMetadataProvider(
    val client: Client,
    val clusterService: ClusterService,
    val services: Map<String, IndexMetadataService>
) {

    val logger = LogManager.getLogger(javaClass)

    fun getIndexMetadata(type: String, indexNames: List<String>): Map<String, IndexMetadata>? {
        logger.info("I am initialized and being called. Providers are ${services.keys}")
        if (type in services && IndexType.values().map { it.name }.contains(type)) {
            val service = services[type]

            val metadata = service?.getMetadata(indexNames, client, clusterService)
        }
        return null
    }

    enum class IndexType(val type: String) {
        COLD("cold"),
        WARM("warm")
    }
}
