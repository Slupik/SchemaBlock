package io.github.slupik.schemablock.newparser.memory.element;

/**
 * All rights reserved & copyright ©
 */
public class DefaultValueProvider {

    public static Object getDefaultValue(ValueType type) {
        switch (type) {
            case BOOLEAN:
                return false;
            case BYTE:
                return (byte) 0;
            case SHORT:
                return (short) 0;
            case INTEGER:
                return 0;
            case LONG:
                return 0L;
            case FLOAT:
                return 0.0F;
            case DOUBLE:
                return 0.0d;
            case STRING:
                return "";
            case VOID:
            case UNKNOWN:
            default:
                return null;
        }
    }

}
