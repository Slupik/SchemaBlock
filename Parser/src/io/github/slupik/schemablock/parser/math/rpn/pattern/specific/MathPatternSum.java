package io.github.slupik.schemablock.parser.math.rpn.pattern.specific;

import io.github.slupik.schemablock.parser.math.rpn.pattern.InvalidArgumentsException;
import io.github.slupik.schemablock.parser.math.rpn.pattern.MathPattern;
import io.github.slupik.schemablock.parser.math.rpn.pattern.UnsupportedValueException;
import io.github.slupik.schemablock.parser.math.rpn.value.Value;
import io.github.slupik.schemablock.parser.math.rpn.value.ValueType;

import static io.github.slupik.schemablock.parser.math.rpn.value.ValueType.*;

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
            ValueType biggestType = ValueType.SHORT;
            for(int i=0;i<args.length;i++) {
                Object val = args[i].getValue();
                ValueType type = ValueType.getType(val);
                switch (type) {
                    case DOUBLE: {
                        biggestType = DOUBLE;
                        result += ((Double) val);
                        break;
                    }
                    case INT: {
                        if(biggestType==SHORT) {
                            biggestType = INT;
                        }
                        result += ((Integer) val);
                        break;
                    }
                    case SHORT: {
                        result += ((Short) val);
                        break;
                    }
                    default: {
                        throw new UnsupportedValueException();
                    }
                }
            }
            switch (biggestType) {
                case DOUBLE: {
                    return ((double) result);
                }
                case INT: {
                    return ((int) result);
                }
                case SHORT: {
                    return ((short) result);
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
            if(!ValueType.isNumber(val.getType())) {
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