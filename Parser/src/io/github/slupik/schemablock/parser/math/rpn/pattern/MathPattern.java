package io.github.slupik.schemablock.parser.math.rpn.pattern;

import io.github.slupik.schemablock.parser.math.rpn.value.Value;
import io.github.slupik.schemablock.parser.math.rpn.value.ValueType;

import java.util.ArrayList;
import java.util.List;

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

    public Object calculate(String token) throws UnsupportedValueException, InvalidArgumentsException {
        String argumentsToken = token.substring(token.indexOf('(')+1, token.lastIndexOf(')'));
        String[] args = argumentsToken.split(",");
        List<Value> values = new ArrayList<>();
        for(String arg:args) {
            arg = arg.trim();
            Object parsedArg = ValueType.parse(arg);
            values.add(new Value(ValueType.getType(parsedArg), parsedArg));
        }
        return calculate(values.toArray(new Value[0]));
    }

    public String getName() {
        return name;
    }
}
