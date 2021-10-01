/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 *
 * $originalComment
 */

package org.opensearch.indexmanagement.sampleextension

import org.opensearch.common.io.stream.StreamInput
import org.opensearch.common.xcontent.XContentParser
import org.opensearch.indexmanagement.spi.indexstatemanagement.model.Action
import org.opensearch.indexmanagement.spi.indexstatemanagement.model.ActionParser

class DeleteActionParser: ActionParser() {

    override fun fromStreamInput(sin: StreamInput): Action {
        TODO("Not yet implemented")
    }

    override fun parse(xcp: XContentParser, index: Int): Action {
        TODO("Not yet implemented")
    }
}