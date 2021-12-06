/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 *
 */

package org.opensearch.indexmanagement.indexstatemanagement.model.newaction

import org.opensearch.common.io.stream.StreamOutput
import org.opensearch.common.xcontent.ToXContent
import org.opensearch.common.xcontent.ToXContentObject
import org.opensearch.common.xcontent.XContentBuilder
import org.opensearch.indexmanagement.indexstatemanagement.newaction.DeleteAction
import org.opensearch.indexmanagement.spi.indexstatemanagement.model.Action
import org.opensearch.indexmanagement.spi.indexstatemanagement.model.ActionConfig

class DeleteActionConfig(val index: Int) : ToXContentObject, ActionConfig(type, index) {

    override fun toXContent(builder: XContentBuilder, params: ToXContent.Params): XContentBuilder {
        builder.startObject()
        super.toXContent(builder, params)
            .startObject(type)
        return builder.endObject().endObject()
    }

    override fun toAction(): Action {
        return DeleteAction(this)
    }

    override fun writeTo(out: StreamOutput) {
        out.writeInt(index)
    }

    override fun isFragment(): Boolean = super<ToXContentObject>.isFragment()

    companion object {
        const val type = "delete"
    }
}
