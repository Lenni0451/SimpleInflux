package net.lenni0451.simpleinflux.data.types;

import net.lenni0451.simpleinflux.data.DataType;

/**
 * Represents a 64-bit floating point number.
 *
 * @see <a href="https://docs.influxdata.com/influxdb/v2/reference/syntax/line-protocol/#float">InfluxDB Documentation</a>
 */
public class FloatType implements DataType {

    private final double value;

    public FloatType(final double value) {
        this.value = value;
    }

    @Override
    public String asString() {
        return String.valueOf(this.value);
    }

    @Override
    public boolean equals(DataType other) {
        return other instanceof FloatType && ((FloatType) other).value == this.value;
    }

}
