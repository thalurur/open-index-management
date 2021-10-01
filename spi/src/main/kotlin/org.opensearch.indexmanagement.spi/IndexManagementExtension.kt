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
import org.opensearch.indexmanagement.spi.indexstatemanagement.IndexMetadataService
import org.opensearch.indexmanagement.spi.indexstatemanagement.ClusterEventHandler

/**
 * SPI for IndexManagement
 */
interface IndexManagementExtension {

    abstract fun getISMActions(): List<Action>

    abstract fun getProvider(): IndexMetadataService

    abstract fun getIndexType(): String

    abstract fun getClusterEventHandlers(): List<ClusterEventHandler>
}