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

package org.opensearch.indexmanagement.spi

import org.opensearch.indexmanagement.spi.indexstatemanagement.model.Action
import org.opensearch.indexmanagement.spi.indexstatemanagement.model.Decision
import org.opensearch.indexmanagement.spi.indexstatemanagement.model.IndexMetadataProvider

/**
 * SPI for IndexManagement
 */
interface IndexManagementExtension {

    abstract fun getISMActions(): List<Action>

    abstract fun getIndexMetadataProvider(): IndexMetadataProvider

    abstract fun getIndexType(): String

    abstract fun processIndexCreateEvent(): Decision

    abstract fun processIndexDeleteEvent(): Decision
}