/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 *
 * $originalComment
 */

package org.opensearch.indexmanagement.spi.indexstatemanagement

import org.opensearch.client.Client
import org.opensearch.cluster.service.ClusterService
import org.opensearch.indexmanagement.spi.indexstatemanagement.model.IndexMetadata

interface IndexMetadataService {

    fun getMetadata(indexNames: List<String>, client: Client, clusterService: ClusterService): Map<String, IndexMetadata>
}
