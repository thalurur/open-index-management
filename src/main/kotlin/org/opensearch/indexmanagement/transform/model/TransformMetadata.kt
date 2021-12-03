/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.opensearch.indexmanagement.transform.model

import org.opensearch.common.io.stream.StreamInput
import org.opensearch.common.io.stream.StreamOutput
import org.opensearch.common.io.stream.Writeable
import org.opensearch.common.xcontent.ToXContent
import org.opensearch.common.xcontent.ToXContentObject
import org.opensearch.common.xcontent.XContentBuilder
import org.opensearch.common.xcontent.XContentParser
import org.opensearch.common.xcontent.XContentParserUtils.ensureExpectedToken
import org.opensearch.index.seqno.SequenceNumbers
import org.opensearch.indexmanagement.indexstatemanagement.util.WITH_TYPE
import org.opensearch.indexmanagement.opensearchapi.instant
import org.opensearch.indexmanagement.opensearchapi.optionalTimeField
import org.opensearch.script.Script
import org.opensearch.script.ScriptType
import java.io.IOException
import java.time.Instant
import java.util.Locale

data class TransformMetadata(
    val id: String,
    val seqNo: Long = SequenceNumbers.UNASSIGNED_SEQ_NO,
    val primaryTerm: Long = SequenceNumbers.UNASSIGNED_PRIMARY_TERM,
    val transformId: String,
    val afterKey: Map<String, Any>? = null,
    val lastUpdatedAt: Instant,
    val status: Status,
    val failureReason: String? = null,
    val stats: TransformStats,
    val script: Script = Script(ScriptType.INLINE, Script.DEFAULT_TEMPLATE_LANG, "{{}}", mapOf())
) : ToXContentObject, Writeable {

    enum class Status(val type: String) {
        INIT("init"),
        STARTED("started"),
        STOPPED("stopped"),
        FINISHED("finished"),
        FAILED("failed");

        override fun toString(): String {
            return type
        }
    }

    @Throws(IOException::class)
    constructor(sin: StreamInput) : this(
        id = sin.readString(),
        seqNo = sin.readLong(),
        primaryTerm = sin.readLong(),
        transformId = sin.readString(),
        afterKey = if (sin.readBoolean()) sin.readMap() else null,
        lastUpdatedAt = sin.readInstant(),
        status = sin.readEnum(Status::class.java),
        failureReason = sin.readOptionalString(),
        stats = TransformStats(sin)
    )

    override fun toXContent(builder: XContentBuilder, params: ToXContent.Params): XContentBuilder {
        builder.startObject()
        if (params.paramAsBoolean(WITH_TYPE, true)) builder.startObject(TRANSFORM_METADATA_TYPE)

        builder.field(TRANSFORM_ID_FIELD, transformId)
        if (afterKey != null) builder.field(AFTER_KEY_FIELD, afterKey)
        builder.optionalTimeField(LAST_UPDATED_AT_FIELD, lastUpdatedAt)
        builder.field(STATUS_FIELD, status.type)
        builder.field(FAILURE_REASON, failureReason)
        builder.field(STATS_FIELD, stats)

        if (params.paramAsBoolean(WITH_TYPE, true)) builder.endObject()
        return builder.endObject()
    }

    override fun writeTo(out: StreamOutput) {
        out.writeString(id)
        out.writeLong(seqNo)
        out.writeLong(primaryTerm)
        out.writeString(transformId)
        out.writeBoolean(afterKey != null)
        afterKey?.let { out.writeMap(it) }
        out.writeInstant(lastUpdatedAt)
        out.writeEnum(status)
        out.writeOptionalString(failureReason)
        stats.writeTo(out)
    }

    fun mergeStats(stats: TransformStats): TransformMetadata {
        return this.copy(
            stats = this.stats.copy(
                pagesProcessed = this.stats.pagesProcessed + stats.pagesProcessed,
                documentsIndexed = this.stats.documentsIndexed + stats.documentsIndexed,
                documentsProcessed = this.stats.documentsProcessed + stats.documentsProcessed,
                indexTimeInMillis = this.stats.indexTimeInMillis + stats.indexTimeInMillis,
                searchTimeInMillis = this.stats.searchTimeInMillis + stats.searchTimeInMillis
            )
        )
    }

    companion object {
        const val TRANSFORM_METADATA_TYPE = "transform_metadata"
        const val TRANSFORM_ID_FIELD = "transform_id"
        const val AFTER_KEY_FIELD = "after_key"
        const val LAST_UPDATED_AT_FIELD = "last_updated_at"
        const val STATUS_FIELD = "status"
        const val STATS_FIELD = "stats"
        const val FAILURE_REASON = "failure_reason"

        @Suppress("ComplexMethod", "LongMethod")
        @JvmStatic
        @Throws(IOException::class)
        fun parse(
            xcp: XContentParser,
            id: String,
            seqNo: Long = SequenceNumbers.UNASSIGNED_SEQ_NO,
            primaryTerm: Long = SequenceNumbers.UNASSIGNED_PRIMARY_TERM
        ): TransformMetadata {
            var transformId: String? = null
            var afterkey: Map<String, Any>? = null
            var lastUpdatedAt: Instant? = null
            var status: Status? = null
            var failureReason: String? = null
            var stats: TransformStats? = null

            ensureExpectedToken(XContentParser.Token.START_OBJECT, xcp.currentToken(), xcp)
            while (xcp.nextToken() != XContentParser.Token.END_OBJECT) {
                val fieldName = xcp.currentName()
                xcp.nextToken()

                when (fieldName) {
                    TRANSFORM_ID_FIELD -> transformId = xcp.text()
                    AFTER_KEY_FIELD -> afterkey = xcp.map()
                    LAST_UPDATED_AT_FIELD -> lastUpdatedAt = xcp.instant()
                    STATUS_FIELD -> status = Status.valueOf(xcp.text().toUpperCase(Locale.ROOT))
                    FAILURE_REASON -> failureReason = xcp.textOrNull()
                    STATS_FIELD -> stats = TransformStats.parse(xcp)
                }
            }

            return TransformMetadata(
                id,
                seqNo,
                primaryTerm,
                transformId = requireNotNull(transformId) { "TransformId must not be null" },
                afterKey = afterkey,
                lastUpdatedAt = requireNotNull(lastUpdatedAt) { "Last updated time must not be null" },
                status = requireNotNull(status) { "Status must not be null" },
                failureReason = failureReason,
                stats = requireNotNull(stats) { "Stats must not be null" }
            )
        }
    }
}
