/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 *
 * com.maddyhome.idea.copyright.pattern.CommentInfo@26c2dd54
 */

package org.opensearch.indexmanagement.sampleextension

import org.opensearch.common.io.stream.StreamInput
import org.opensearch.common.xcontent.XContentParser
import org.opensearch.common.xcontent.XContentParserUtils
import org.opensearch.indexmanagement.spi.indexstatemanagement.model.Action
import org.opensearch.indexmanagement.spi.indexstatemanagement.model.ActionParser

class DeleteActionParser: ActionParser() {

    override fun fromStreamInput(sin: StreamInput): Action {
        val index = sin.readInt()
        return DeleteActionConfig(index)
    }

    override fun fromXContent(xcp: XContentParser, index: Int): Action {
        XContentParserUtils.ensureExpectedToken(XContentParser.Token.START_OBJECT, xcp.currentToken(), xcp)
        XContentParserUtils.ensureExpectedToken(XContentParser.Token.END_OBJECT, xcp.nextToken(), xcp)

        return DeleteActionConfig(index)
    }

    override fun getActionType(): String {
        return DeleteActionConfig.DELETE
    }
}