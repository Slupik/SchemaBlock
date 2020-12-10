package io.github.slupik.schemablock.newparser.memory.element;

import org.apache.commons.lang3.StringUtils;

/**
 * All rights reserved & copyright ©
 */
class ValueConverter {

    static Object castValueToType(ValueType type, Object value) {
        if (value == null) return null;

        value = value.toString();
        String parsed = ((String) value);
        switch (type) {
            case STRING: {
                if (StringUtils.isBlank(parsed)) return "";
                return value;
            }
            case DOUBLE: {
                if (StringUtils.isBlank(parsed)) return 0D;
                return Double.parseDouble(parsed);
            }
            case FLOAT: {
                if (StringUtils.isBlank(parsed)) return 0F;
                return ((float) Double.parseDouble(parsed));
            }
            case LONG: {
                if (StringUtils.isBlank(parsed)) return 0L;
                return ((long) Double.parseDouble(parsed));
            }
            case INTEGER: {
                if (StringUtils.isBlank(parsed)) return 0;
                return ((int) Double.parseDouble(parsed));
            }
            case SHORT: {
                if (StringUtils.isBlank(parsed)) return (short) 0;
                return ((short) Double.parseDouble(parsed));
            }
            case BYTE: {
                if (StringUtils.isBlank(parsed)) return (byte) 0;
                return ((byte) Double.parseDouble(parsed));
            }
            case BOOLEAN: {
                if (StringUtils.isBlank(parsed)) return false;
                return Boolean.parseBoolean(parsed);
            }
        }

        return null;
    }
}
