/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 *
 * $originalComment
 */

package org.opensearch.indexmanagement.indexstatemanagement

import org.opensearch.indexmanagement.spi.indexstatemanagement.model.Action
import org.opensearch.indexmanagement.spi.indexstatemanagement.model.ActionParser

class ActionService(val customActions: Map<ActionParser, Action>) {

    companion object {

    }
}