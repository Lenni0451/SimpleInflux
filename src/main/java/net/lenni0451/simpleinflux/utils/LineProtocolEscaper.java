package net.lenni0451.simpleinflux.utils;

public class LineProtocolEscaper {

    private static final char[] MEASUREMENT_ESCAPE = new char[]{',', ' ', '\t'};
    private static final char[] TAG_KEY_ESCAPE = new char[]{',', ' ', '\t', '='};
    private static final char[] TAG_VALUE_ESCAPE = TAG_KEY_ESCAPE;
    private static final char[] FIELD_KEY_ESCAPE = TAG_KEY_ESCAPE;
    private static final char[] FIELD_VALUE_ESCAPE = new char[]{'"', '\\'};

    public static String escapeMeasurement(final String measurement) {
        return escape(measurement, MEASUREMENT_ESCAPE);
    }

    public static String escapeTagKey(final String tagKey) {
        return escape(tagKey, TAG_KEY_ESCAPE);
    }

    public static String escapeTagValue(final String tagValue) {
        return escape(tagValue, TAG_VALUE_ESCAPE);
    }

    public static String escapeFieldKey(final String fieldKey) {
        return escape(fieldKey, FIELD_KEY_ESCAPE);
    }

    public static String escapeFieldValue(final String fieldValue) {
        return escape(fieldValue, FIELD_VALUE_ESCAPE);
    }

    private static String escape(final String s, final char[] toEscape) {
        StringBuilder out = new StringBuilder();
        char[] chars = s.toCharArray();
        for (char c : chars) {
            for (char e : toEscape) {
                if (c == e) {
                    out.append('\\');
                    break;
                }
            }
            out.append(c);
        }
        return out.toString();
    }

}
