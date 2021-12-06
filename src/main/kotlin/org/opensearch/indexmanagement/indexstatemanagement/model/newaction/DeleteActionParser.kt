/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 *
 * com.maddyhome.idea.copyright.pattern.CommentInfo@4f4fbcbe
 */

package org.opensearch.indexmanagement.indexstatemanagement.model.newaction

import org.opensearch.common.io.stream.StreamInput
import org.opensearch.common.xcontent.XContentParser
import org.opensearch.common.xcontent.XContentParserUtils
import org.opensearch.indexmanagement.spi.indexstatemanagement.model.ActionConfig
import org.opensearch.indexmanagement.spi.indexstatemanagement.model.ActionParser

class DeleteActionParser : ActionParser() {

    override fun fromStreamInput(sin: StreamInput): ActionConfig {
        val index = sin.readInt()
        return DeleteActionConfig(index)
    }

    override fun fromXContent(xcp: XContentParser, index: Int): ActionConfig {
        XContentParserUtils.ensureExpectedToken(XContentParser.Token.START_OBJECT, xcp.currentToken(), xcp)
        XContentParserUtils.ensureExpectedToken(XContentParser.Token.END_OBJECT, xcp.nextToken(), xcp)

        return DeleteActionConfig(index)
    }

    override fun getActionType(): String {
        return DeleteActionConfig.type
    }
}
