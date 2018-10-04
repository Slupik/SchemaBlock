package io.github.slupik.schemablock.parser.math.custom;

import io.github.slupik.schemablock.parser.math.custom.value.Value;

/**
 * All rights reserved & copyright ©
 */
public class Variable {

    private final String name;
    private Value value;

    public Variable(String name, Value value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

}
