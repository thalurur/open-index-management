/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 *
 * $originalComment
 */

package org.opensearch.indexmanagement.spi.indexstatemanagement

import org.opensearch.client.Client
import org.opensearch.cluster.ClusterChangedEvent
import org.opensearch.cluster.service.ClusterService
import org.opensearch.indexmanagement.spi.indexstatemanagement.model.Decision

interface ClusterEventHandler {

    fun processIndexCreateEvent(client: Client, clusterService: ClusterService, event: ClusterChangedEvent): Decision

    fun processIndexDeleteEvent(client: Client, clusterService: ClusterService, event: ClusterChangedEvent): Decision
}