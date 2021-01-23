package io.github.slupik.schemablock.newparser.memory.element;

import org.jetbrains.annotations.NotNull;

/**
 * All rights reserved & copyright ©
 */
public enum ValueType {
    SHORT(true, true, 0),
    INTEGER(true, true, 0),
    LONG(true, true, 0),
    FLOAT(true, true, 0),
    DOUBLE(true, true, 0),

    BYTE(true, true, 0),

    BOOLEAN(false, false, false),

    STRING(false, false, ""),

    VOID(false, false, null),
    UNKNOWN(false, false, null);

    public final boolean IS_NUMBER;
    public final boolean IS_BYTE;
    public final Object DEFAULT_VALUE;

    ValueType(boolean isNumber, boolean isByte, Object DEFAULT_VALUE) {
        this.IS_NUMBER = isNumber;
        this.IS_BYTE = isByte;
        this.DEFAULT_VALUE = DEFAULT_VALUE;
    }

    public static boolean isCastable(ValueType type1, ValueType type2) {
        return ((type1.IS_NUMBER && type2.IS_NUMBER) ||
                (type1.IS_BYTE && type2.IS_BYTE)) ||
                (type1 == BOOLEAN && type2 == BOOLEAN);
    }

    public static boolean isCompatible(ValueType type1, ValueType type2) {
        if (isCastable(type1, type2)) {
            return true;
        }
        return type1 == STRING;
    }

    public static ValueType getType(String token) {
        if (token.equalsIgnoreCase("short")) {
            return SHORT;
        }
        if (token.equalsIgnoreCase("int") || token.equalsIgnoreCase("integer")) {
            return INTEGER;
        }
        if (token.equalsIgnoreCase("long")) {
            return LONG;
        }
        if (token.equalsIgnoreCase("float")) {
            return FLOAT;
        }
        if (token.equalsIgnoreCase("double")) {
            return DOUBLE;
        }
        if (token.equalsIgnoreCase("string") || token.equalsIgnoreCase("text")) {
            return STRING;
        }
        if (token.equalsIgnoreCase("byte")) {
            return BYTE;
        }
        if (token.equalsIgnoreCase("boolean") || token.equalsIgnoreCase("bool")) {
            return BOOLEAN;
        }
        if (token.equalsIgnoreCase("void")) {
            return VOID;
        }
        return UNKNOWN;
    }

    @NotNull
    public String getDisplayName() {
        return name().toLowerCase();
    }
}
