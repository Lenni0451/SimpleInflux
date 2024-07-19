package net.lenni0451.simpleinflux.data;

import net.lenni0451.simpleinflux.data.types.BooleanType;
import net.lenni0451.simpleinflux.data.types.FloatType;
import net.lenni0451.simpleinflux.data.types.IntegerType;
import net.lenni0451.simpleinflux.data.types.StringType;
import net.lenni0451.simpleinflux.utils.LineProtocolEscaper;

import java.util.HashMap;
import java.util.Map;

public class DataBuilder {

    public DataBuilder create(final String measurement) {
        return new DataBuilder(measurement);
    }


    private final String measurement;
    private final Map<String, String> tags = new HashMap<>();
    private final Map<String, DataType> fields = new HashMap<>();
    private long timestamp = -1;

    public DataBuilder(final String measurement) {
        if (measurement.startsWith("_")) throw new IllegalArgumentException("Measurement cannot start with an underscore");
        this.measurement = measurement;
    }

    public DataBuilder tag(final String key, final String value) {
        if (key.startsWith("_")) throw new IllegalArgumentException("Tag key cannot start with an underscore");
        this.tags.put(key, value);
        return this;
    }

    public DataBuilder field(final String key, final DataType value) {
        if (key.startsWith("_")) throw new IllegalArgumentException("Field key cannot start with an underscore");
        this.fields.put(key, value);
        return this;
    }

    public DataBuilder field(final String key, final boolean value) {
        return this.field(key, new BooleanType(value));
    }

    public DataBuilder field(final String key, final int value) {
        return this.field(key, new IntegerType(value));
    }

    public DataBuilder field(final String key, final long value) {
        return this.field(key, new IntegerType(value));
    }

    public DataBuilder field(final String key, final float value) {
        return this.field(key, new FloatType(value));
    }

    public DataBuilder field(final String key, final double value) {
        return this.field(key, new FloatType(value));
    }

    public DataBuilder field(final String key, final String value) {
        return this.field(key, new StringType(value));
    }

    public DataBuilder timestamp(final long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public String toLineProtocol() {
        if (this.measurement == null) throw new IllegalStateException("Measurement cannot be null");
        if (this.fields.isEmpty()) throw new IllegalStateException("At least one field is required");

        StringBuilder builder = new StringBuilder();
        builder.append(LineProtocolEscaper.escapeMeasurement(this.measurement));
        for (Map.Entry<String, String> entry : this.tags.entrySet()) {
            builder.append(",").append(LineProtocolEscaper.escapeTagKey(entry.getKey())).append("=").append(LineProtocolEscaper.escapeTagValue(entry.getValue()));
        }
        builder.append(" ");
        boolean first = true;
        for (Map.Entry<String, DataType> entry : this.fields.entrySet()) {
            if (!first) builder.append(",");
            builder.append(LineProtocolEscaper.escapeFieldKey(entry.getKey())).append("=").append(entry.getValue().asString());
            first = false;
        }
        if (this.timestamp != -1) {
            builder.append(" ").append(this.timestamp);
        }
        return builder.toString();
    }

}
