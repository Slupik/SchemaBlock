package io.github.slupik.schemablock.parser.math.pattern;

import io.github.slupik.schemablock.parser.math.value.Value;

/**
 * All rights reserved & copyright ©
 */
public abstract class MathPattern {

    private final String name;

    public MathPattern(String name) {
        this.name = name;
    }

    public abstract Object calculate(Value... args) throws InvalidArgumentsException, UnsupportedValueException;
    public abstract boolean isValidArgs(Value... args);
    public abstract int maxArgs();

    public String getName() {
        return name;
    }
}
