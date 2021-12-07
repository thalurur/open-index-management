/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 *
 */

package org.opensearch.indexmanagement.sampleextension

import org.opensearch.indexmanagement.spi.indexstatemanagement.model.Action
import org.opensearch.indexmanagement.spi.indexstatemanagement.model.Step

class DeleteActionConfig(actionIndex: Int): Action(DELETE, actionIndex) {

    private val attemptDeleteStep = AttemptDeleteStep()
    private val steps = listOf(attemptDeleteStep)

    override fun getStepToExecute(): Step {
        return attemptDeleteStep
    }

    override fun getSteps(): List<Step> {
        return steps
    }

    override fun isFragment(): Boolean = false

    companion object {
        const val DELETE = "delete"
    }
}