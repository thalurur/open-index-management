/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 *
 * $originalComment
 */

package org.opensearch.indexmanagement.sampleextension

import org.opensearch.client.node.NodeClient
import org.opensearch.rest.BaseRestHandler
import org.opensearch.rest.BytesRestResponse
import org.opensearch.rest.RestChannel
import org.opensearch.rest.RestHandler
import org.opensearch.rest.RestRequest
import org.opensearch.rest.RestStatus

class SampleExtensionRestHandler: BaseRestHandler() {
    companion object {
        const val SAMPLE_URI = "/_plugins/sample_extension"
    }

    override fun getName(): String {
        return "Sample IndexManagement extension handler"
    }

    override fun routes(): List<RestHandler.Route> {
        return listOf(RestHandler.Route(RestRequest.Method.POST, SAMPLE_URI))
    }

    override fun prepareRequest(request: RestRequest, client: NodeClient): RestChannelConsumer {
        if (request.method() == RestRequest.Method.POST) {
            return RestChannelConsumer { restChannel: RestChannel ->
                restChannel.sendResponse(
                    BytesRestResponse(
                        RestStatus.METHOD_NOT_ALLOWED,
                        request.method().toString() + " is not allowed cause..."
                    )
                )
            }
        } else {
            return RestChannelConsumer { restChannel: RestChannel ->
                restChannel.sendResponse(
                    BytesRestResponse(
                        RestStatus.METHOD_NOT_ALLOWED,
                        request.method().toString() + " is not allowed."
                    )
                )
            }
        }
    }
}