package io.github.slupik.schemablock.parser.math.rpn.pattern;

import io.github.slupik.schemablock.parser.math.rpn.MathCalculation;
import io.github.slupik.schemablock.parser.math.rpn.variable.value.NotFoundTypeException;
import io.github.slupik.schemablock.parser.math.rpn.variable.value.Value;
import io.github.slupik.schemablock.parser.math.rpn.variable.value.ValueType;

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

    public Object calculate(Value... args) throws InvalidArgumentsException, UnsupportedValueException {
//        OldValue[] parsed = parseRawValues(args);
        return getResult(args);
    }
    protected abstract Object getResult(Value... parsed) throws UnsupportedValueException, InvalidArgumentsException;
    public abstract boolean isValidArgs(Value... args);
    public abstract int maxArgs();

    public Object calculate(String token) throws UnsupportedValueException, InvalidArgumentsException {
        String argumentsToken = token.substring(token.indexOf('(')+1, token.lastIndexOf(')'));

        String[] args = argumentsToken.split(",");
        List<Value> values = new ArrayList<>();
        for(String arg:args) {
            arg = arg.trim();
            try {
                values.add(new Value(ValueType.getStandardizedType(arg), arg));
            } catch (NotFoundTypeException e) {
                e.printStackTrace();
                throw new InvalidArgumentsException();
            }
        }
        return calculate(values.toArray(new Value[0]));
    }

    public String getName() {
        return name;
    }
}
