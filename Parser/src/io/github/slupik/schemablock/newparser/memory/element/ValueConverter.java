package io.github.slupik.schemablock.newparser.memory.element;

/**
 * All rights reserved & copyright ©
 */
public class ValueConverter {

    public static Object castValueToType(ValueType type, Object value) {
        if(value==null) return null;

        value = value.toString();
        String parsed = ((String) value);
        switch (type) {
            case STRING: {
                return value;
            }
            case DOUBLE: {
                return Double.parseDouble(parsed);
            }
            case FLOAT: {
                return ((float) Double.parseDouble(parsed));
            }
            case LONG: {
                return ((long) Double.parseDouble(parsed));
            }
            case INTEGER: {
                return ((int) Double.parseDouble(parsed));
            }
            case SHORT: {
                return ((short) Double.parseDouble(parsed));
            }
            case BYTE: {
                return ((byte) Double.parseDouble(parsed));
            }
            case BOOLEAN: {
                return Boolean.parseBoolean(parsed);
            }
        }

        return null;
    }
}
