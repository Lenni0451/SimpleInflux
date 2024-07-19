package net.lenni0451.simpleinflux.data.types;

import net.lenni0451.simpleinflux.data.DataType;

/**
 * Represents a 64-bit signed integer.
 *
 * @see <a href="https://docs.influxdata.com/influxdb/v2/reference/syntax/line-protocol/#integer">InfluxDB Documentation</a>
 */
public class IntegerType implements DataType {

    private final long value;

    public IntegerType(final long value) {
        this.value = value;
    }

    @Override
    public String asString() {
        return this.value + "i";
    }

    @Override
    public boolean equals(DataType other) {
        return other instanceof IntegerType && ((IntegerType) other).value == this.value;
    }

}
