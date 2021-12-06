/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 *
 * com.maddyhome.idea.copyright.pattern.CommentInfo@83e4912
 */

package org.opensearch.indexmanagement.indexstatemanagement.newaction

import org.opensearch.indexmanagement.indexstatemanagement.model.newaction.TransitionsActionConfig
import org.opensearch.indexmanagement.indexstatemanagement.step.newtransition.AttemptTransitionStep
import org.opensearch.indexmanagement.spi.indexstatemanagement.model.Action
import org.opensearch.indexmanagement.spi.indexstatemanagement.model.Step

class TransitionsAction(config: TransitionsActionConfig) : Action(config) {

    private val attemptTransitionStep = AttemptTransitionStep(config)
    private val steps = listOf(attemptTransitionStep)

    override fun getStepToExecute(): Step = attemptTransitionStep

    override fun getSteps(): List<Step> = steps
}
