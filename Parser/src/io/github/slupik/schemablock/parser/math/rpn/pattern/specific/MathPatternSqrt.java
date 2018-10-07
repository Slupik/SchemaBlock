package io.github.slupik.schemablock.parser.math.rpn.pattern.specific;

import io.github.slupik.schemablock.parser.math.rpn.pattern.InvalidArgumentsException;
import io.github.slupik.schemablock.parser.math.rpn.pattern.MathPattern;
import io.github.slupik.schemablock.parser.math.rpn.pattern.UnsupportedValueException;
import io.github.slupik.schemablock.parser.math.rpn.variable.value.Value;

/**
 * All rights reserved & copyright ©
 */
public class MathPatternSqrt extends MathPattern {

    public MathPatternSqrt() {
        super("sqrt");
    }

    @Override
    public Object getResult(Value... args) throws InvalidArgumentsException, UnsupportedValueException {
        if(isValidArgs(args)) {
            Value value = args[0];
            if(value.getType().isNumber) {
                return Math.sqrt(value.getAsDouble());
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
            return arg.getType().isNumber;
        }
        return false;
    }

    @Override
    public int maxArgs() {
        return 1;
    }
}
