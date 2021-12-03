/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 *
 */

package org.opensearch.indexmanagement.spi.indexstatemanagement.model

import java.util.Locale
import org.opensearch.client.Client
import org.opensearch.cluster.service.ClusterService
import org.opensearch.common.io.stream.StreamInput
import org.opensearch.common.io.stream.StreamOutput
import org.opensearch.common.io.stream.Writeable

abstract class Step(val name: String, val isSafeToDisableOn: Boolean = true) {

    fun preExecute(): Step {
        return this
    }

    abstract suspend fun execute(clusterService: ClusterService, client: Client, context: StepContext): Step

    fun postExecute(): Step {
        return this
    }

    abstract fun getUpdatedManagedIndexMetadata(currentMetadata: ManagedIndexMetadata): ManagedIndexMetadata

    abstract fun isIdempotent(): Boolean

    enum class StepStatus(val status: String) : Writeable {
        STARTING("starting"),
        CONDITION_NOT_MET("condition_not_met"),
        FAILED("failed"),
        COMPLETED("completed");

        override fun toString(): String {
            return status
        }

        override fun writeTo(out: StreamOutput) {
            out.writeString(status)
        }

        companion object {
            fun read(streamInput: StreamInput): StepStatus {
                return valueOf(streamInput.readString().toUpperCase(Locale.ROOT))
            }
        }
    }
}