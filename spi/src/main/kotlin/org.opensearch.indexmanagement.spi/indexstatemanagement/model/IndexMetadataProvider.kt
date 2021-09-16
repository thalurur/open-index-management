/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 *
 * $originalComment
 */

package org.opensearch.indexmanagement.spi.indexstatemanagement.model

abstract class IndexMetadataProvider {

    abstract fun getMetadata(indexNames: List<String>): Map<String, IndexMetadata>
}