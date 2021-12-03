/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 *
 */

package org.opensearch.indexmanagement.spi.indexstatemanagement.model

import org.opensearch.common.io.stream.StreamInput
import org.opensearch.common.xcontent.XContentParser
import org.opensearch.common.xcontent.XContentParserUtils.ensureExpectedToken

abstract class ActionParser {

    abstract fun getActionName(): String

    abstract fun parseAction(sin: StreamInput): Action

    abstract fun parseAction(xcp: XContentParser): Action

    fun fromStreamInput(sin: StreamInput): ActionConfig {

    }

    fun fromXContent(xcp: XContentParser, index: Int): ActionConfig {
        var timeout: ActionTimeout? = null
        var retry: ActionRetry? = null
        var action: Action? = null

        ensureExpectedToken(XContentParser.Token.START_OBJECT, xcp.currentToken(), xcp)
        while (xcp.nextToken() != XContentParser.Token.END_OBJECT) {
            val fieldName = xcp.currentName()
            xcp.nextToken()

            when (fieldName) {
                TIMEOUT_FIELD -> timeout = ActionTimeout.parse(xcp)
                RETRY_FIELD -> retry = ActionRetry.parse(xcp)
                getActionName() -> action = parseAction(xcp)
            }
        }
    }

    companion object {
        const val TIMEOUT_FIELD = "timeout"
        const val RETRY_FIELD = "retry"
    }
}