/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * The OpenSearch Contributors require contributions made to
 * this file be licensed under the Apache-2.0 license or a
 * compatible open source license.
 *
 * Modifications Copyright OpenSearch Contributors. See
 * GitHub history for details.
 */

package org.opensearch.indexmanagement.spi.indexstatemanagement.model

import org.opensearch.common.io.stream.Writeable
import org.opensearch.common.xcontent.ToXContentFragment

abstract class Action(
    val name: String
): Writeable, ToXContentFragment {

    abstract fun getSteps(): List<Step>

    abstract fun getStepToExecute(): Step

    fun isLastStep(stepName: String): Boolean = getSteps().last().name == stepName

    fun isFirstStep(stepName: String): Boolean = getSteps().first().name == stepName
}