package io.github.slupik.schemablock.parser.math.rpn.value;

import org.apache.commons.lang3.math.NumberUtils;

/**
 * All rights reserved & copyright ©
 */
public enum ValueType {
    SHORT,
    INT,
    DOUBLE,
    STRING;

    public static boolean isNumber(ValueType type) {
        return type == INT || type == DOUBLE || type == SHORT;
    }

    public static boolean isValidValue(String value) {
        return NumberUtils.isParsable(value) || isText(value);
    }

    public static boolean isText(String value) {
        return value.startsWith("\"") && value.endsWith("\"");
    }

    public static Object parse(String valueText) {
        if(isValidValue(valueText)) {
            try {
                return Double.parseDouble(valueText);
            } catch (Exception e) {
                try {
                    return Integer.parseInt(valueText);
                } catch (Exception e1) {
                    if(isText(valueText)) {
                        return valueText;
                    }
                }
            }
        }
        return null;
    }

    public static ValueType getType(Object value) {
        if(value instanceof Double) {
            return DOUBLE;
        }
        if(value instanceof Integer) {
            return INT;
        }
        if(value instanceof Short) {
            return SHORT;
        }
        if(value instanceof String) {
            return STRING;
        }
        return null;
    }
}
