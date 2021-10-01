/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 *
 * $originalComment
 */

package org.opensearch.indexmanagement.sampleextension

import org.opensearch.indexmanagement.spi.indexstatemanagement.model.Step

class AttemptDeleteStep: Step(name) {

    override suspend fun execute(): AttemptDeleteStep {

        return this
    }

    companion object {
        const val name = "attempt_delete"
    }
}