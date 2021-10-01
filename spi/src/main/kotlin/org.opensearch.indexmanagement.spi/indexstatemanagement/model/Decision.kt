/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 *
 * $originalComment
 */

package org.opensearch.indexmanagement.spi.indexstatemanagement.model

// TODO: Probably need an override or priority to clear clashes if there are multiple decisions with conflicting index metadata
data class Decision (val shouldProcess: Boolean, val indexMetadata: IndexMetadata) {
}