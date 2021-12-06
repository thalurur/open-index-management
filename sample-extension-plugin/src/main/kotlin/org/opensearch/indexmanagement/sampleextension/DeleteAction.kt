/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 *
 * $originalComment
 */

package org.opensearch.indexmanagement.sampleextension

import org.opensearch.indexmanagement.spi.indexstatemanagement.model.Action
import org.opensearch.indexmanagement.spi.indexstatemanagement.model.Step

class DeleteAction(config: DeleteActionConfig) : Action(config) {
    private val attemptDeleteStep = AttemptDeleteStep()
    private val steps = listOf(attemptDeleteStep)

    override fun getStepToExecute(): Step {
        return attemptDeleteStep
    }

    override fun getSteps(): List<Step> {
        return steps
    }
}