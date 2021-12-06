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

import org.opensearch.indexmanagement.spi.indexstatemanagement.IndexMetadataService
import org.opensearch.indexmanagement.spi.indexstatemanagement.ClusterEventHandler
import org.opensearch.indexmanagement.spi.indexstatemanagement.model.ActionParser

/**
 * SPI for IndexManagement
 */
interface IndexManagementExtension {

    /**
     * List of action parsers that are supported by the extension, each of the action parser will parse the policy action into the defined action.
     * The ActionParser provides the ability to parse the action
     */
    abstract fun getISMActionParsers(): List<ActionParser>

    abstract fun getIndexMetadataService(): Map<String, IndexMetadataService>

    abstract fun getClusterEventHandlers(): Map<ClusterEventType, ClusterEventHandler>
}

enum class ClusterEventType(val type: String) {
    CREATE("create"),
    DELETE("delete");

    override fun toString(): String {
        return type
    }
}