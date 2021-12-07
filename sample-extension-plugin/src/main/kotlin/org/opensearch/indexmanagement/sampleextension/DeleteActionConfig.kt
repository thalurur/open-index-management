/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 *
 */

package org.opensearch.indexmanagement.sampleextension

import org.opensearch.indexmanagement.spi.indexstatemanagement.Action
import org.opensearch.indexmanagement.spi.indexstatemanagement.Step
import org.opensearch.indexmanagement.spi.indexstatemanagement.model.StepContext

class DeleteActionConfig(actionIndex: Int): Action(DELETE, actionIndex) {

    private val attemptDeleteStep = AttemptDeleteStep()
    private val steps = listOf(attemptDeleteStep)

    override fun getStepToExecute(context: StepContext): Step {
        return attemptDeleteStep
    }

    override fun getSteps(): List<Step> {
        return steps
    }

    companion object {
        const val DELETE = "delete"
    }
}