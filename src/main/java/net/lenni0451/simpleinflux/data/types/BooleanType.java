package net.lenni0451.simpleinflux.data.types;

import net.lenni0451.simpleinflux.data.DataType;

/**
 * Represents a boolean value.
 *
 * @see <a href="https://docs.influxdata.com/influxdb/v2/reference/syntax/line-protocol/#boolean">InfluxDB Documentation</a>
 */
public class BooleanType implements DataType {

    private final boolean value;

    public BooleanType(final boolean value) {
        this.value = value;
    }

    @Override
    public String asString() {
        return this.value ? "t" : "f";
    }

    @Override
    public boolean equals(DataType other) {
        return other instanceof BooleanType && ((BooleanType) other).value == this.value;
    }

}
