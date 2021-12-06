/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 *
 */

package org.opensearch.indexmanagement.spi.indexstatemanagement.model

import org.apache.logging.log4j.Logger
import java.util.Locale
import org.opensearch.common.io.stream.StreamInput
import org.opensearch.common.io.stream.StreamOutput
import org.opensearch.common.io.stream.Writeable
import java.time.Instant

abstract class Step(val name: String, val isSafeToDisableOn: Boolean = true) {

    fun preExecute(logger: Logger, context: StepContext): Step {
        logger.info("Executing $name for ${context.metadata.index}")
        return this
    }

    abstract suspend fun execute(context: StepContext): Step

    fun postExecute(logger: Logger, context: StepContext): Step {
        logger.info("Finished executing $name for ${context.metadata.index}")
        return this
    }

    abstract fun getUpdatedManagedIndexMetadata(currentMetadata: ManagedIndexMetaData): ManagedIndexMetaData

    abstract fun isIdempotent(): Boolean

    fun getStepStartTime(metadata: ManagedIndexMetaData): Instant {
        return when {
            metadata.stepMetaData == null -> Instant.now()
            metadata.stepMetaData.name != this.name -> Instant.now()
            // The managed index metadata is a historical snapshot of the metadata and refers to what has happened from the previous
            // execution, so if we ever see it as COMPLETED it means we are always going to be in a new step, this specifically
            // helps with the Transition -> Transition (empty state) sequence which the above do not capture
            metadata.stepMetaData.stepStatus == StepStatus.COMPLETED -> Instant.now()
            else -> Instant.ofEpochMilli(metadata.stepMetaData.startTime)
        }
    }

    fun getStartingStepMetaData(metadata: ManagedIndexMetaData): StepMetaData = StepMetaData(name, getStepStartTime(metadata).toEpochMilli(), StepStatus.STARTING)

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