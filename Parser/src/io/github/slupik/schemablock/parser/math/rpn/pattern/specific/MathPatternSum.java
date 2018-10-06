package io.github.slupik.schemablock.parser.math.rpn.pattern.specific;

import io.github.slupik.schemablock.parser.math.rpn.pattern.InvalidArgumentsException;
import io.github.slupik.schemablock.parser.math.rpn.pattern.MathPattern;
import io.github.slupik.schemablock.parser.math.rpn.pattern.UnsupportedValueException;
import io.github.slupik.schemablock.parser.math.rpn.value.NotFoundTypeException;
import io.github.slupik.schemablock.parser.math.rpn.value.Value;
import io.github.slupik.schemablock.parser.math.rpn.value.ValueType;

/**
 * All rights reserved & copyright ©
 */
public class MathPatternSum extends MathPattern {

    public MathPatternSum() {
        super("sum");
    }

    @Override
    public Object getResult(Value... args) throws UnsupportedValueException, InvalidArgumentsException {
        if(isValidArgs(args)) {
            double result = 0;
            for(int i=0;i<args.length;i++) {
                Double val = args[i].getAsDouble();
                ValueType type = ValueType.STRING;
                try {
                    type = ValueType.getType(val);
                } catch (NotFoundTypeException e) {
                    e.printStackTrace();
                }
                if(type.isNumber) {
                    result += val;
                } else {
                    throw new UnsupportedValueException();
                }
            }
            return result;
        } else {
            throw new InvalidArgumentsException();
        }
    }

    @Override
    public boolean isValidArgs(Value... args) {
        for(int i=0;i<args.length;i++) {
            Value val = args[i];
            if(!val.getType().isNumber) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int maxArgs() {
        return -1;
    }
}