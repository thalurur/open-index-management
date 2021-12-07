/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 *
 */

package org.opensearch.indexmanagement.spi.indexstatemanagement.model

import org.opensearch.common.io.stream.StreamOutput
import org.opensearch.common.io.stream.Writeable
import org.opensearch.common.xcontent.ToXContent
import org.opensearch.common.xcontent.ToXContentObject
import org.opensearch.common.xcontent.XContentBuilder

abstract class Action(
    val type: String,
    val actionIndex: Int
) : ToXContentObject, Writeable {

    var configTimeout: ActionTimeout? = null
    var configRetry: ActionRetry? = null

    final override fun toXContent(builder: XContentBuilder, params: ToXContent.Params): XContentBuilder {
        builder.startObject()
        configTimeout?.toXContent(builder, params)
        configRetry?.toXContent(builder, params)
        populateAction(builder, params)
        return builder.endObject()
    }

    open fun populateAction(builder: XContentBuilder, params: ToXContent.Params) {
        builder.startObject(type).endObject()
    }

    final override fun writeTo(out: StreamOutput) {
        out.writeString(type)
        out.writeOptionalWriteable(configTimeout)
        out.writeOptionalWriteable(configRetry)
        populateAction(out)
    }

    open fun populateAction(out: StreamOutput) {
        out.writeInt(actionIndex)
    }

    abstract fun getSteps(): List<Step>

    abstract fun getStepToExecute(): Step

    final fun isLastStep(stepName: String): Boolean = getSteps().last().name == stepName

    final fun isFirstStep(stepName: String): Boolean = getSteps().first().name == stepName
}