/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 *
 */

package org.opensearch.indexmanagement.indexstatemanagement.model.newaction

import org.opensearch.common.io.stream.StreamOutput
import org.opensearch.indexmanagement.indexstatemanagement.model.Transition
import org.opensearch.indexmanagement.indexstatemanagement.newaction.TransitionsAction
import org.opensearch.indexmanagement.spi.indexstatemanagement.model.Action
import org.opensearch.indexmanagement.spi.indexstatemanagement.model.ActionConfig

class TransitionsActionConfig(val transitions: List<Transition>) : ActionConfig(type, -1) {

    companion object {
        const val type = "transition"
    }

    override fun toAction(): Action {
        return TransitionsAction(this)
    }

    override fun writeTo(out: StreamOutput) {
    }
}
