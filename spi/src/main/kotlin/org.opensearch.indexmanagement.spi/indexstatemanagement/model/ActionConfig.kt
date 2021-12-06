/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 *
 */

package org.opensearch.indexmanagement.spi.indexstatemanagement.model

import org.opensearch.common.io.stream.Writeable
import org.opensearch.common.xcontent.ToXContent
import org.opensearch.common.xcontent.ToXContentFragment
import org.opensearch.common.xcontent.XContentBuilder

abstract class ActionConfig(
    val type: String,
    val actionIndex: Int
) : ToXContentFragment, Writeable {

    var configTimeout: ActionTimeout? = null
    var configRetry: ActionRetry? = null

    override fun toXContent(builder: XContentBuilder, params: ToXContent.Params): XContentBuilder {
        configTimeout?.toXContent(builder, params)
        configRetry?.toXContent(builder, params)
        return builder
    }

    abstract fun toAction(): Action
}