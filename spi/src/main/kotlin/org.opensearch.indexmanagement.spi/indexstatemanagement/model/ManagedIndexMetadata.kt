/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 *
 */

package org.opensearch.indexmanagement.spi.indexstatemanagement.model

data class ManagedIndexMetadata(
    val index: String,
    val policyId: String
) {
}