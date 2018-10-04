package io.github.slupik.schemablock.parser.math.custom.value;

/**
 * All rights reserved & copyright ©
 */
public class Value {

    private final ValueType type;

    private Object value = null;

    public Value(ValueType type) {
        this.type = type;
    }

    public Value(ValueType type, Object value) {
        this.type = type;
        this.value = value;
    }

    public ValueType getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

}
