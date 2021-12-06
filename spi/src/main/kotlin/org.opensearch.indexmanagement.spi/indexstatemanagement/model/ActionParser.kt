/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 *
 */

package org.opensearch.indexmanagement.spi.indexstatemanagement.model

import org.opensearch.common.io.stream.StreamInput
import org.opensearch.common.xcontent.XContentParser

abstract class ActionParser(customAction: Boolean = false) {

    /**
     * The action type parser will parse
     */
    abstract fun getActionType(): String

    /**
     * Populate ActionConfig from stream input
     */
    abstract fun fromStreamInput(sin: StreamInput): ActionConfig

    /**
     * Populate ActionConfig from xContent
     */
    abstract fun fromXContent(xcp: XContentParser, index: Int): ActionConfig
}