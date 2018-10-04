package io.github.slupik.schemablock.parser.math.custom.pattern.specific;

import io.github.slupik.schemablock.parser.math.custom.pattern.InvalidArgumentsException;
import io.github.slupik.schemablock.parser.math.custom.pattern.MathPattern;
import io.github.slupik.schemablock.parser.math.custom.pattern.UnsupportedValueException;
import io.github.slupik.schemablock.parser.math.custom.value.Value;
import io.github.slupik.schemablock.parser.math.custom.value.ValueType;

import static io.github.slupik.schemablock.parser.math.custom.value.ValueType.DOUBLE;
import static io.github.slupik.schemablock.parser.math.custom.value.ValueType.INT;
import static io.github.slupik.schemablock.parser.math.custom.value.ValueType.SHORT;

/**
 * All rights reserved & copyright ©
 */
public class MathPatternAdd extends MathPattern {

    public MathPatternAdd() {
        super("add");
    }

    @Override
    public Object calculate(Value... args) throws InvalidArgumentsException, UnsupportedValueException {
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
