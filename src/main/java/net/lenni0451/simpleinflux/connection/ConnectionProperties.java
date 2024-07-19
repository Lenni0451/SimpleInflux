package net.lenni0451.simpleinflux.connection;

import net.lenni0451.simpleinflux.data.TimestampPrecision;

public class ConnectionProperties {

    public static Builder builder() {
        return new Builder();
    }


    private String address;
    private String apiToken;
    private String organization;
    private String bucket;
    private TimestampPrecision timestampPrecision = TimestampPrecision.MILLISECONDS;
    private int connectTimeout = 2000;
    private int readTimeout = 2000;
    private int connectRetries = 3;
    private int connectRetryAfterTries = 3;
    private boolean followRedirects = false;

    private ConnectionProperties() {
    }

    public String getAddress() {
        return this.address;
    }

    public String getApiToken() {
        return this.apiToken;
    }

    public String getOrganization() {
        return this.organization;
    }

    public String getBucket() {
        return this.bucket;
    }

    public TimestampPrecision getTimestampPrecision() {
        return this.timestampPrecision;
    }

    public int getConnectTimeout() {
        return this.connectTimeout;
    }

    public int getReadTimeout() {
        return this.readTimeout;
    }

    public int getConnectRetries() {
        return this.connectRetries;
    }

    public int getConnectRetryAfterTries() {
        return this.connectRetryAfterTries;
    }

    public boolean isFollowRedirects() {
        return this.followRedirects;
    }

    public InfluxDBConnection createConnection() {
        return new InfluxDBConnection(this);
    }


    public static class Builder {
        private final ConnectionProperties connectionProperties = new ConnectionProperties();

        public Builder address(final String address) {
            this.connectionProperties.address = address;
            return this;
        }

        public Builder apiToken(final String apiToken) {
            this.connectionProperties.apiToken = apiToken;
            return this;
        }

        public Builder organization(final String organization) {
            this.connectionProperties.organization = organization;
            return this;
        }

        public Builder bucket(final String bucket) {
            this.connectionProperties.bucket = bucket;
            return this;
        }

        public Builder timestampPrecision(final TimestampPrecision timestampPrecision) {
            this.connectionProperties.timestampPrecision = timestampPrecision;
            return this;
        }

        public Builder connectTimeout(final int connectTimeout) {
            this.connectionProperties.connectTimeout = connectTimeout;
            return this;
        }

        public Builder readTimeout(final int readTimeout) {
            this.connectionProperties.readTimeout = readTimeout;
            return this;
        }

        public Builder connectRetries(final int connectRetries) {
            this.connectionProperties.connectRetries = connectRetries;
            return this;
        }

        public Builder connectRetryAfterTries(final int connectRetryAfterTries) {
            this.connectionProperties.connectRetryAfterTries = connectRetryAfterTries;
            return this;
        }

        public Builder followRedirects(final boolean followRedirects) {
            this.connectionProperties.followRedirects = followRedirects;
            return this;
        }

        public ConnectionProperties build() {
            if (this.connectionProperties.address == null) throw new IllegalArgumentException("Address cannot be null");
            if (this.connectionProperties.apiToken == null) throw new IllegalArgumentException("ApiToken cannot be null");
            if (this.connectionProperties.organization == null) throw new IllegalArgumentException("Organization cannot be null");
            if (this.connectionProperties.bucket == null) throw new IllegalArgumentException("Bucket cannot be null");
            return this.connectionProperties;
        }
    }

}
