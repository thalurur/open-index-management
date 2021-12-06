/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 *
 * com.maddyhome.idea.copyright.pattern.CommentInfo@7b45ccfc
 */

package org.opensearch.indexmanagement.indexstatemanagement.newaction

import org.opensearch.indexmanagement.indexstatemanagement.step.newdelete.AttemptDeleteStep
import org.opensearch.indexmanagement.spi.indexstatemanagement.model.Action
import org.opensearch.indexmanagement.spi.indexstatemanagement.model.ActionConfig
import org.opensearch.indexmanagement.spi.indexstatemanagement.model.Step

class DeleteAction(config: ActionConfig) : Action(config) {

    private val attemptDeleteStep = AttemptDeleteStep()
    private val steps = listOf(attemptDeleteStep)

    override fun getStepToExecute(): Step = attemptDeleteStep

    override fun getSteps(): List<Step> = steps
}
