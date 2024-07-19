package net.lenni0451.simpleinflux.connection;

import net.lenni0451.commons.httpclient.HttpClient;
import net.lenni0451.commons.httpclient.HttpResponse;
import net.lenni0451.commons.httpclient.RetryHandler;
import net.lenni0451.commons.httpclient.constants.ContentTypes;
import net.lenni0451.commons.httpclient.constants.Headers;
import net.lenni0451.commons.httpclient.constants.StatusCodes;
import net.lenni0451.commons.httpclient.content.HttpContent;
import net.lenni0451.simpleinflux.data.DataBuilder;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class InfluxDBConnection {

    private final HttpClient httpClient;
    private final String url;

    public InfluxDBConnection(final ConnectionProperties connectionProperties) {
        this.httpClient = new HttpClient()
                .setHeader(Headers.AUTHORIZATION, "Token " + connectionProperties.getApiToken())
                .setConnectTimeout(connectionProperties.getConnectTimeout())
                .setReadTimeout(connectionProperties.getReadTimeout())
                .setRetryHandler(new RetryHandler(connectionProperties.getConnectRetries(), connectionProperties.getConnectRetryAfterTries()))
                .setFollowRedirects(connectionProperties.isFollowRedirects())
                .setCookieManager(null) //Cookies are not required
                .setHeader(Headers.ACCEPT, ContentTypes.APPLICATION_JSON.getMimeType()); //We only accept JSON responses

        String url = connectionProperties.getAddress();
        if (!url.endsWith("/")) url += "/";
        this.url = url + "api/v2/write?org=" + connectionProperties.getOrganization() + "&bucket=" + connectionProperties.getBucket() + "&precision=" + connectionProperties.getTimestampPrecision().getPrecision();
    }

    public void write(final DataBuilder... data) throws IOException {
        if (data.length == 0) return;
        this.write(Arrays.asList(data));
    }

    public void write(final Iterable<DataBuilder> data) throws IOException {
        String payload = StreamSupport.stream(data.spliterator(), false).map(DataBuilder::toLineProtocol).collect(Collectors.joining("\n"));
        if (payload.isEmpty()) return;
        this.write(payload);
    }

    private void write(final String payload) throws IOException {
        HttpResponse response = this.httpClient.post(this.url).setContent(HttpContent.string(payload)).execute();
        if (response.getStatusCode() != StatusCodes.NO_CONTENT) {
            //See here for more information: https://docs.influxdata.com/influxdb/v2/write-data/troubleshoot/#review-http-status-codes
            throw new IOException("Failed to write data to InfluxDB: " + response.getContentAsString());
        }
    }

}
