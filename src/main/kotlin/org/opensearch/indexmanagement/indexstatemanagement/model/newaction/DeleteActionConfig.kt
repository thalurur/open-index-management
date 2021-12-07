/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 *
 */

package org.opensearch.indexmanagement.indexstatemanagement.model.newaction

import org.opensearch.indexmanagement.indexstatemanagement.step.newdelete.AttemptDeleteStep
import org.opensearch.indexmanagement.spi.indexstatemanagement.model.Action
import org.opensearch.indexmanagement.spi.indexstatemanagement.model.Step

class DeleteActionConfig(val index: Int) : Action(type, index) {

    private val attemptDeleteStep = AttemptDeleteStep()
    private val steps = listOf(attemptDeleteStep)

    override fun getStepToExecute(): Step = attemptDeleteStep

    override fun getSteps(): List<Step> = steps

    companion object {
        const val type = "delete"
    }
}
