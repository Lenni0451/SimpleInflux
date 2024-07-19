package net.lenni0451.simpleinflux.data;

public enum TimestampPrecision {

    MILLISECONDS("ms"),
    SECONDS("s"),
    MICROSECONDS("u"),
    NANOSECONDS("ns");

    private final String precision;

    TimestampPrecision(final String precision) {
        this.precision = precision;
    }

    public String getPrecision() {
        return this.precision;
    }

}
