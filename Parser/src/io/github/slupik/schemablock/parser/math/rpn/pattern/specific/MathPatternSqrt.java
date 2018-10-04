package io.github.slupik.schemablock.parser.math.rpn.pattern.specific;

import io.github.slupik.schemablock.parser.math.rpn.pattern.InvalidArgumentsException;
import io.github.slupik.schemablock.parser.math.rpn.pattern.MathPattern;
import io.github.slupik.schemablock.parser.math.rpn.pattern.UnsupportedValueException;
import io.github.slupik.schemablock.parser.math.rpn.value.Value;
import io.github.slupik.schemablock.parser.math.rpn.value.ValueType;

/**
 * All rights reserved & copyright ©
 */
public class MathPatternSqrt extends MathPattern {

    public MathPatternSqrt() {
        super("sqrt");
    }

    @Override
    public Object calculate(Value... args) throws InvalidArgumentsException, UnsupportedValueException {
        if(isValidArgs(args)) {
            Value value = args[0];
            if(value.getType()== ValueType.DOUBLE) {
                return Math.sqrt(((Double) value.getValue()));
            } else if (value.getType()== ValueType.INT) {
                return Math.sqrt(((Double) value.getValue()));
            }
            throw new UnsupportedValueException();
        } else {
            throw new InvalidArgumentsException();
        }
    }

    @Override
    public boolean isValidArgs(Value... args) {
        if(args.length==1) {
            Value arg = args[0];
            return ValueType.isNumber(arg.getType());
        }
        return false;
    }

    @Override
    public int maxArgs() {
        return 1;
    }
}
