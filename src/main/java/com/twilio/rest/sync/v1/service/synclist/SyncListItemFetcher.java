/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.sync.v1.service.synclist;

import com.twilio.base.Fetcher;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

/**
 * PLEASE NOTE that this class contains beta products that are subject to
 * change. Use them with caution.
 */
public class SyncListItemFetcher extends Fetcher<SyncListItem> {
    private final String pathServiceSid;
    private final String pathListSid;
    private final Integer pathIndex;

    /**
     * Construct a new SyncListItemFetcher.
     * 
     * @param pathServiceSid The service_sid
     * @param pathListSid The list_sid
     * @param pathIndex The index
     */
    public SyncListItemFetcher(final String pathServiceSid, 
                               final String pathListSid, 
                               final Integer pathIndex) {
        this.pathServiceSid = pathServiceSid;
        this.pathListSid = pathListSid;
        this.pathIndex = pathIndex;
    }

    /**
     * Make the request to the Twilio API to perform the fetch.
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Fetched SyncListItem
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public SyncListItem fetch(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            Domains.SYNC.toString(),
            "/v1/Services/" + this.pathServiceSid + "/Lists/" + this.pathListSid + "/Items/" + this.pathIndex + "",
            client.getRegion()
        );

        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("SyncListItem fetch failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.apply(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }

            throw new ApiException(
                restException.getMessage(),
                restException.getCode(),
                restException.getMoreInfo(),
                restException.getStatus(),
                null
            );
        }

        return SyncListItem.fromJson(response.getStream(), client.getObjectMapper());
    }
}