package io.github.slupik.schemablock.parser.math.rpn.value;

import java.io.Serializable;

/**
 * All rights reserved & copyright ©
 */
public enum ValueType {
    BOOLEAN(Boolean.class, false, false),
    CHAR(Character.class,false, true),
    BYTE(Byte.class,false, true),
    SHORT(Short.class,true, true),
    INT(Integer.class,true, true),
    LONG(Long.class,true, true),
    FLOAT(Float.class,true, false),
    DOUBLE(Double.class,true, false),
    STRING(String.class,false, false);

    public final boolean isNumber;
    public final boolean isByteCompatible;
    public final Serializable javaType;

    ValueType(Class javaType, boolean isNumber, boolean isByteCompatible) {
        this.javaType = javaType;
        this.isNumber = isNumber;
        this.isByteCompatible = isByteCompatible;
    }

    public static ValueType getType(Object value) throws NotFoundTypeException {
        if(value==null) {
            throw new NotFoundTypeException(null);
        }
        if(value instanceof Short) return SHORT;
        if(value instanceof Integer) return INT;
        if(value instanceof Long) return LONG;
        if(value instanceof Float) return FLOAT;
        if(value instanceof Double) return DOUBLE;
        if(value instanceof String) return STRING;
        if(value instanceof Boolean) return BOOLEAN;
        if(value instanceof Character) return CHAR;
        if(value instanceof Byte) return BYTE;
        throw new NotFoundTypeException(value);
    }

    public static ValueType getType(String value) throws NotFoundTypeException {
        try {
            Short.parseShort(value);
            return SHORT;
        } catch (Exception ignore){}

        try {
            Integer.parseInt(value);
            return INT;
        } catch (Exception ignore){}

        try {
            Long.parseLong(value);
            return LONG;
        } catch (Exception ignore){}

        if(isFloat(value)) {
            return FLOAT;
        } else {
            try {
                Double.parseDouble(value);
                return DOUBLE;
            } catch (Exception ignore){}
        }

        if(isBoolean(value)) {
            return BOOLEAN;
        }

        if(value.length()==1) {
            return CHAR;
        }

        if(value.startsWith("\"") && value.endsWith("\"")) {
            return STRING;
        }

        throw new NotFoundTypeException(value);
    }

    private static boolean isBoolean(String value) {
        try {
            boolean parsed = Boolean.parseBoolean(value);
            return String.valueOf(parsed).equalsIgnoreCase(value);
        } catch (Exception ignore){}
        return false;
    }

    private static boolean isFloat(String value) {
        try {
            float parsed = Float.parseFloat(value);
            String stringify = String.valueOf(parsed);
            return !Float.isNaN(parsed) && !Float.isInfinite(parsed) && stringify.equalsIgnoreCase(value);
        } catch (Exception ignore){}
        return false;
    }

    /*
    byte (number, 1 byte)
    short (number, 2 bytes)
    int (number, 4 bytes)
    long (number, 8 bytes)
    float (float number, 4 bytes)
    double (float number, 8 bytes)
    char (a character, 2 bytes)
    boolean (true or false, 1 byte)
     */
}
