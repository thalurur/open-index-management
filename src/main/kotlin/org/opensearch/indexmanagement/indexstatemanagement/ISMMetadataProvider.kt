/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.opensearch.indexmanagement.indexstatemanagement

import org.opensearch.indexmanagement.spi.indexstatemanagement.IndexMetadataService

class ISMMetadataProvider {
    var providers = mutableMapOf<String, IndexMetadataService>()

    public fun addProviders(metadataProviders: Map<String, IndexMetadataService>) {
        providers.putAll(metadataProviders)
    }

    // TODO: implement read methods to get the metadata for an index
}
