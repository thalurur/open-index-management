/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 *
 * com.maddyhome.idea.copyright.pattern.CommentInfo@526579e5
 */

package org.opensearch.indexmanagement.indexstatemanagement.model.action

import org.opensearch.common.io.stream.StreamInput
import org.opensearch.common.xcontent.XContentParser
import org.opensearch.common.xcontent.XContentParserUtils
import org.opensearch.indexmanagement.indexstatemanagement.model.newaction.DeleteActionParser
import org.opensearch.indexmanagement.spi.indexstatemanagement.model.ActionConfig
import org.opensearch.indexmanagement.spi.indexstatemanagement.model.ActionParser
import org.opensearch.indexmanagement.spi.indexstatemanagement.model.ActionRetry
import org.opensearch.indexmanagement.spi.indexstatemanagement.model.ActionTimeout

class ISMActionsParser private constructor() {

    private object HOLDER {
        val instance = ISMActionsParser()
    }

    // TODO: Add other action parsers as they are implemented
    private val parsers = mutableListOf<ActionParser>(
        DeleteActionParser()
    )

    fun addParser(parser: ActionParser) {
        parsers.add(parser)
    }

    fun fromStreamInput(sin: StreamInput): ActionConfig {
        val action: String = sin.readString()
        val configTimeout = sin.readOptionalWriteable(::ActionTimeout)
        val configRetry = sin.readOptionalWriteable(::ActionRetry)
        val parser = parsers.firstOrNull { it.getActionType() == action }
        val actionConfig: ActionConfig = parser?.fromStreamInput(sin) ?: throw IllegalArgumentException("Invalid field: [$action] found in Actions.")

        actionConfig.configTimeout = configTimeout
        actionConfig.configRetry = configRetry

        return actionConfig
    }

    fun parse(xcp: XContentParser, totalActions: Int): ActionConfig {
        var actionConfig: ActionConfig? = null
        var timeout: ActionTimeout? = null
        var retry: ActionRetry? = null
        XContentParserUtils.ensureExpectedToken(XContentParser.Token.START_OBJECT, xcp.currentToken(), xcp)
        while (xcp.nextToken() != XContentParser.Token.END_OBJECT) {
            val action = xcp.currentName()
            xcp.nextToken()
            when (action) {
                ActionTimeout.TIMEOUT_FIELD -> timeout = ActionTimeout.parse(xcp)
                ActionRetry.RETRY_FIELD -> retry = ActionRetry.parse(xcp)
                else -> {
                    val parser = parsers.firstOrNull { it.getActionType() == action }
                    if (parser != null) {
                        actionConfig = parser.fromXContent(xcp, totalActions)
                    } else {
                        throw IllegalArgumentException("Invalid field: [$action] found in Actions.")
                    }
                }
            }
        }

        requireNotNull(actionConfig) { "ActionConfig inside state is null" }

        actionConfig.configTimeout = timeout
        actionConfig.configRetry = retry

        return actionConfig
    }

    companion object {
        val instance: ISMActionsParser by lazy { HOLDER.instance }
    }
}
