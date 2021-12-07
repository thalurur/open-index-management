/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 *
 */

package org.opensearch.indexmanagement.indexstatemanagement.model.newaction

import org.opensearch.indexmanagement.indexstatemanagement.model.Transition
import org.opensearch.indexmanagement.indexstatemanagement.step.newtransition.AttemptTransitionStep
import org.opensearch.indexmanagement.spi.indexstatemanagement.Action
import org.opensearch.indexmanagement.spi.indexstatemanagement.Step
import org.opensearch.indexmanagement.spi.indexstatemanagement.model.StepContext

class TransitionsActionConfig(val transitions: List<Transition>) : Action(type, -1) {

    companion object {
        const val type = "transition"
    }

    private val attemptTransitionStep = AttemptTransitionStep(this)
    private val steps = listOf(attemptTransitionStep)

    override fun getStepToExecute(context: StepContext): Step = attemptTransitionStep

    override fun getSteps(): List<Step> = steps
}
