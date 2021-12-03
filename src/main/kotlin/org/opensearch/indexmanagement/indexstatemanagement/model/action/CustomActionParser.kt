/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 *
 * com.maddyhome.idea.copyright.pattern.CommentInfo@526579e5
 */

package org.opensearch.indexmanagement.indexstatemanagement.model.action

import org.opensearch.common.io.stream.StreamInput
import org.opensearch.common.xcontent.XContentParser
import org.opensearch.indexmanagement.spi.indexstatemanagement.model.ActionConfig

class CustomActionParser private constructor() {

    private object HOLDER {
        val instance = CustomActionParser()
    }

    private val parsers = mutableListOf<ActionConfig>()

    fun addParser(parser: ActionConfig) {
        parsers.add(parser)
    }

    fun writeTo(action: String, sin: StreamInput): ActionConfig {
        val parser = parsers.firstOrNull { it.type == action }
        if (parser != null) {
            return parser.fromStreamInput(sin)
        } else {
            throw IllegalArgumentException("Invalid field: [$action] found in Action.")
        }
    }

    fun parse(xcp: XContentParser, action: String, totalActions: Int): ActionConfig {
        val parser = parsers.firstOrNull { it.type == action }
        if (parser != null) {
            return parser.parse(xcp, totalActions)
        } else {
            throw IllegalArgumentException("Invalid field: [$action] found in Action.")
        }
    }

    companion object {
        val instance: CustomActionParser by lazy { HOLDER.instance }
    }
}
