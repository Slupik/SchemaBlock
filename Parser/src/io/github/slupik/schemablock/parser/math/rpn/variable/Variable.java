package io.github.slupik.schemablock.parser.math.rpn.variable;

import io.github.slupik.schemablock.parser.math.rpn.variable.value.NotFoundTypeException;
import io.github.slupik.schemablock.parser.math.rpn.variable.value.Value;
import io.github.slupik.schemablock.parser.math.rpn.variable.value.ValueType;

/**
 * All rights reserved & copyright ©
 */
public class Variable extends Value {

    private final String name;

    public Variable(String name, Object value) throws NotFoundTypeException {
        super(value);
        this.name = name;
    }

    public Variable(String name, ValueType type, Object value) {
        super(type, value);
        this.name = name;
    }

    public Variable(String name, ValueType type, String value) {
        super(type, value);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getType()+" "+getName()+" = "+getValue()+";";
    }
}
