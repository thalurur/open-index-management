/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 *
 */

package org.opensearch.indexmanagement.indexstatemanagement.model.newaction

import org.opensearch.indexmanagement.indexstatemanagement.step.newdelete.AttemptDeleteStep
import org.opensearch.indexmanagement.spi.indexstatemanagement.Action
import org.opensearch.indexmanagement.spi.indexstatemanagement.Step
import org.opensearch.indexmanagement.spi.indexstatemanagement.model.StepContext

class DeleteActionConfig(val index: Int) : Action(type, index) {

    private val attemptDeleteStep = AttemptDeleteStep()
    private val steps = listOf(attemptDeleteStep)

    override fun getStepToExecute(context: StepContext): Step = attemptDeleteStep

    override fun getSteps(): List<Step> = steps

    companion object {
        const val type = "delete"
    }
}
