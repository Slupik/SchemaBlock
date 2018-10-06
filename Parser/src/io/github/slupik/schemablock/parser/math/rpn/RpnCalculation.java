package io.github.slupik.schemablock.parser.math.rpn;

import io.github.slupik.schemablock.parser.math.rpn.pattern.InvalidArgumentsException;
import io.github.slupik.schemablock.parser.math.rpn.pattern.MathPattern;
import io.github.slupik.schemablock.parser.math.rpn.pattern.PatternFinder;
import io.github.slupik.schemablock.parser.math.rpn.pattern.UnsupportedValueException;
import io.github.slupik.schemablock.parser.math.rpn.pattern.specific.MathPatternSqrt;
import io.github.slupik.schemablock.parser.math.rpn.pattern.specific.MathPatternSum;
import io.github.slupik.schemablock.parser.math.rpn.value.NotFoundTypeException;
import io.github.slupik.schemablock.parser.math.rpn.value.Value;
import io.github.slupik.schemablock.parser.math.rpn.value.ValueType;

import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
class RpnCalculation {

    private static final PatternFinder FUNCTIONS = new PatternFinder();

    static {
        FUNCTIONS.registerPattern(new MathPatternSqrt());
        FUNCTIONS.registerPattern(new MathPatternSum());
    }

    static Object calculate(List<String> rpnTokens) throws InvalidArgumentsException, UnsupportedValueException, NotFoundTypeException {
        List<Value> stack = new ArrayList<>();

        for(String token:rpnTokens) {
            token = token.trim();

            if(token.contains(";")) {
                MathPattern function = FUNCTIONS.getForName(getFunctionName(token));
                List<Value> args = new ArrayList<>();
                int argsAmount = getArgsAmount(token);
                for(;argsAmount>0;argsAmount--) {
                    Value parsedArg = stack.get(stack.size()-argsAmount);
                    args.add(parsedArg);
                    stack.remove(stack.size()-argsAmount);
                }
                Object result = function.calculate(args.toArray(new Value[0]));
                Value value = new Value(ValueType.getStandardizedType(result), result);
                stack.add(value);
            } else {
                Value value = new Value(ValueType.getStandardizedType(token), token);
                if(value.getType().isNumber) {
                    stack.add(value);
                } else {
                    double x = stack.get(stack.size()-2).getAsDouble();
                    stack.remove(stack.size()-2);
                    double y = stack.get(stack.size()-1).getAsDouble();
                    stack.remove(stack.size()-1);

                    Value res = null;

                    switch (token) {
                        case "+":
                            res = new Value(x + y);
                            break;
                        case "-":
                            res = new Value(x - y);
                            break;
                        case "*":
                            res = new Value(x * y);
                            break;
                        case "/":
                            res = new Value(x / y);
                            break;
                        case "%":
                            res = new Value(x % y);
                            break;
                    }
                    stack.add(res);
                }
            }
        }
        switch (stack.get(0).getType()) {
            case SHORT: {
                return stack.get(0).getAsShort();
            }
            case INT: {
                return stack.get(0).getAsInt();
            }
            case LONG: {
                return stack.get(0).getAsLong();
            }
            case FLOAT: {
                return stack.get(0).getAsFloat();
            }
            case DOUBLE: {
                return stack.get(0).getAsDouble();
            }
            case STRING: {
                return stack.get(0).getAsString();
            }
            case CHAR: {
                return stack.get(0).getAsChar();
            }
            case BYTE: {
                return stack.get(0).getAsByte();
            }
            case BOOLEAN: {
                return stack.get(0).getAsBoolean();
            }
        }
        return stack.get(0).getValue();
    }

    private static int getArgsAmount(String functionToken) {
        return Integer.parseInt(functionToken.substring(functionToken.lastIndexOf(";")+1));
    }

    private static String getFunctionName(String functionToken) {
        return functionToken.substring(0, functionToken.lastIndexOf(";"));
    }
}
