/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 *
 * $originalComment
 */

package org.opensearch.indexmanagement.spi.indexstatemanagement.model

data class Decision (val shouldProcess: Boolean, val indexMetadata: IndexMetadata) {
}