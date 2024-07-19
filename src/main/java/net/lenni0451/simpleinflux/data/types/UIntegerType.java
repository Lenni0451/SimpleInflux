package net.lenni0451.simpleinflux.data.types;

import net.lenni0451.simpleinflux.data.DataType;

/**
 * Represents a 64-bit unsigned integer.
 *
 * @see <a href="https://docs.influxdata.com/influxdb/v2/reference/syntax/line-protocol/#integer">InfluxDB Documentation</a>
 */
public class UIntegerType implements DataType {

    private final long value;

    public UIntegerType(final long value) {
        this.value = value;
    }

    @Override
    public String asString() {
        return this.value + "u";
    }

    @Override
    public boolean equals(DataType other) {
        return other instanceof UIntegerType && ((UIntegerType) other).value == this.value;
    }

}
