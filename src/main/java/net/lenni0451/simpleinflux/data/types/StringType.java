package net.lenni0451.simpleinflux.data.types;

import net.lenni0451.simpleinflux.data.DataType;
import net.lenni0451.simpleinflux.utils.LineProtocolEscaper;

/**
 * Represents a string value.<br>
 * The size limit is 64KB.
 *
 * @see <a href="https://docs.influxdata.com/influxdb/v2/reference/syntax/line-protocol/#string">InfluxDB Documentation</a>
 */
public class StringType implements DataType {

    private final String value;

    public StringType(final String value) {
        this.value = value;
    }

    @Override
    public String asString() {
        return '"' + LineProtocolEscaper.escapeFieldValue(this.value) + '"';
    }

    @Override
    public boolean equals(DataType other) {
        return other instanceof StringType && ((StringType) other).value.equals(this.value);
    }

}
