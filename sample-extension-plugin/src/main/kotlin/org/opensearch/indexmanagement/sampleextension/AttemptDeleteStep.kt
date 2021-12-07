/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 *
 * $originalComment
 */

package org.opensearch.indexmanagement.sampleextension

import org.opensearch.indexmanagement.spi.indexstatemanagement.model.ManagedIndexMetaData
import org.opensearch.indexmanagement.spi.indexstatemanagement.Step

class AttemptDeleteStep: Step(name) {

    companion object {
        const val name = "attempt_delete"
    }

    override suspend fun execute(): Step {
        TODO("Not yet implemented")
    }

    override fun getUpdatedManagedIndexMetadata(currentMetadata: ManagedIndexMetaData): ManagedIndexMetaData {
        TODO("Not yet implemented")
    }

    override fun isIdempotent(): Boolean {
        TODO("Not yet implemented")
    }
}