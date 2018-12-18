package io.github.slupik.schemablock.newparser.bytecode;

/**
 * All rights reserved & copyright ©
 */
public enum ValueType {
    SHORT(true, true),
    INTEGER(true, true),
    LONG(true, true),
    FLOAT(true, true),
    DOUBLE(true, true),

    BYTE(true, true),

    BOOLEAN(false, false),

    STRING(false, false),

    VOID(false, false),
    UNKNOWN(false, false);

    private final boolean IS_NUMBER;
    private final boolean IS_BYTE;

    ValueType(boolean isNumber, boolean isByte){
        this.IS_NUMBER = isNumber;
        this.IS_BYTE = isByte;
    }

    public static boolean isCastable(ValueType type1, ValueType type2){
        return ((type1.IS_NUMBER && type2.IS_NUMBER) ||
                (type1.IS_BYTE && type2.IS_BYTE));
    }

    public static boolean isCompatible(ValueType type1, ValueType type2){
        if(isCastable(type1, type2)) {
            return true;
        }
        return type1 == STRING;
    }

    public static ValueType getType(String token){
        if(token.equals("short")) {
            return SHORT;
        }
        if(token.equals("int")) {
            return INTEGER;
        }
        if(token.equals("long")) {
            return LONG;
        }
        if(token.equals("float")) {
            return FLOAT;
        }
        if(token.equals("double")) {
            return DOUBLE;
        }
        if(token.equals("string")) {
            return STRING;
        }
        if(token.equals("byte")) {
            return BYTE;
        }
        if(token.equals("boolean") || token.equals("bool")) {
            return BOOLEAN;
        }
        if(token.equals("void")) {
            return VOID;
        }
        return UNKNOWN;
    }
}
