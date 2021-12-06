/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 *
 */

package org.opensearch.indexmanagement.sampleextension

import org.opensearch.common.io.stream.StreamOutput
import org.opensearch.common.xcontent.ToXContent
import org.opensearch.common.xcontent.ToXContentObject
import org.opensearch.common.xcontent.XContentBuilder
import org.opensearch.indexmanagement.spi.indexstatemanagement.model.Action
import org.opensearch.indexmanagement.spi.indexstatemanagement.model.ActionConfig

class DeleteActionConfig(actionIndex: Int) : ToXContentObject, ActionConfig(DELETE, actionIndex) {

    override fun toAction(): Action {
        return DeleteAction(this)
    }

    override fun isFragment(): Boolean = false

    override fun toXContent(builder: XContentBuilder, params: ToXContent.Params): XContentBuilder {
        builder.startObject()
        super.toXContent(builder, params)
                .startObject(DELETE)
        return builder.endObject().endObject()
    }

    override fun writeTo(out: StreamOutput) {
        out.writeInt(actionIndex)
    }

    companion object {
        const val DELETE = "delete"
    }
}