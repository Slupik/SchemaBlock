package io.github.slupik.schemablock.parser.math.rpn.pattern.specific;

import io.github.slupik.schemablock.parser.math.rpn.pattern.InvalidArgumentsException;
import io.github.slupik.schemablock.parser.math.rpn.pattern.MathPattern;
import io.github.slupik.schemablock.parser.math.rpn.pattern.UnsupportedValueException;
import io.github.slupik.schemablock.parser.math.rpn.variable.value.Value;

/**
 * All rights reserved & copyright ©
 */
public class MathPatternPow extends MathPattern {

    public MathPatternPow() {
        super("pow");
    }

    @Override
    public Object getResult(Value... args) throws InvalidArgumentsException, UnsupportedValueException {
        if(isValidArgs(args)) {
            Value base = args[0];
            Value exponent = args[1];
            if(base.getType().isNumber) {
                if(exponent.getType().isNumber) {
                    return Math.pow(base.getAsDouble(), exponent.getAsDouble());
                } else {
                    throw new UnsupportedValueException(exponent.getValue());
                }
            } else {
                throw new UnsupportedValueException(base.getValue());
            }
        } else {
            throw new InvalidArgumentsException();
        }
    }

    @Override
    public boolean isValidArgs(Value... args) {
        if(args.length==2) {
            return args[0].getType().isNumber && args[1].getType().isNumber;
        }
        return false;
    }

    @Override
    public int maxArgs() {
        return 1;
    }
}
