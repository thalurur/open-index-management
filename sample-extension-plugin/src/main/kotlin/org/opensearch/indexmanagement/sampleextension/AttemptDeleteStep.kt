/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 *
 * $originalComment
 */

package org.opensearch.indexmanagement.sampleextension

import org.opensearch.client.Client
import org.opensearch.cluster.service.ClusterService
import org.opensearch.indexmanagement.spi.indexstatemanagement.model.ManagedIndexMetadata
import org.opensearch.indexmanagement.spi.indexstatemanagement.model.Step
import org.opensearch.indexmanagement.spi.indexstatemanagement.model.StepContext

class AttemptDeleteStep: Step(name) {

    companion object {
        const val name = "attempt_delete"
    }

    override suspend fun execute(clusterService: ClusterService, client: Client, context: StepContext): Step {
        TODO("Not yet implemented")
    }

    override fun getUpdatedManagedIndexMetadata(currentMetadata: ManagedIndexMetadata): ManagedIndexMetadata {
        TODO("Not yet implemented")
    }

    override fun isIdempotent(): Boolean {
        TODO("Not yet implemented")
    }
}