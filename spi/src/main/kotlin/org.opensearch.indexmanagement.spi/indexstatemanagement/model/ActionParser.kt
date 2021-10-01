/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 *
 * $originalComment
 */

package org.opensearch.indexmanagement.spi.indexstatemanagement.model

import org.opensearch.common.io.stream.StreamInput
import org.opensearch.common.xcontent.XContentParser

interface ActionParser {
    fun parse(xcp: XContentParser, index: Int): Action

    fun fromStreamInput(sin: StreamInput): Action

    fun getSchema():
}