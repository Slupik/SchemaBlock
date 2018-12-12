package io.github.slupik.schemablock.newparser.bytecode;

/**
 * All rights reserved & copyright ©
 */
public enum VariableType {
    SHORT(true, true),
    INTEGER(true, true),
    LONG(true, true),
    FLOAT(true, true),
    DOUBLE(true, true),

    BYTE(true, true),

    BOOLEAN(false, false),

    STRING(false, false),

    VOID(false, false);

    private final boolean IS_NUMBER;
    private final boolean IS_BYTE;

    VariableType(boolean isNumber, boolean isByte){
        this.IS_NUMBER = isNumber;
        this.IS_BYTE = isByte;
    }

    public static boolean isCastable(VariableType type1, VariableType type2){
        return ((type1.IS_NUMBER && type2.IS_NUMBER) ||
                (type1.IS_BYTE && type2.IS_BYTE));
    }

    public static boolean isCompatible(VariableType type1, VariableType type2){
        if(isCastable(type1, type2)) {
            return true;
        }
        return type1 == STRING;
    }
}
