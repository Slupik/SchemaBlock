package io.github.slupik.schemablock.parser.math.rpn;

import io.github.slupik.schemablock.parser.math.rpn.pattern.InvalidArgumentsException;
import io.github.slupik.schemablock.parser.math.rpn.pattern.MathPattern;
import io.github.slupik.schemablock.parser.math.rpn.pattern.PatternFinder;
import io.github.slupik.schemablock.parser.math.rpn.pattern.UnsupportedValueException;
import io.github.slupik.schemablock.parser.math.rpn.variable.value.NotFoundTypeException;
import io.github.slupik.schemablock.parser.math.rpn.variable.value.Value;
import io.github.slupik.schemablock.parser.math.rpn.variable.value.ValueType;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * All rights reserved & copyright ©
 */
class RpnCalculation {

    private static final PatternFinder FUNCTIONS = new PatternFinder();

    static {
        FUNCTIONS.registerDefaultPatterns();
    }

    static Object calculate(List<String> rpnTokens) throws InvalidArgumentsException, UnsupportedValueException, NotFoundTypeException {
        Stack<Value> stack = new Stack<>();

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
                try {
                    Value value = new Value(ValueType.getStandardizedType(token), token);
                    if(value.getType().isNumber || value.getType()==ValueType.STRING || value.getType() == ValueType.BOOLEAN || value.getType() == ValueType.ENUM) {
                        stack.add(value);
                        continue;
                    }
                } catch (NotFoundTypeException ignore){}

                if(isOperatorNeedOneValue(token)) {
                    Value valueX = stack.pop();
                    Value res = null;
                    switch (token) {
                        case "~": {
                            if(valueX.getType().isByteCompatible) {
                                res = new Value(~valueX.getAsLong());
                            } else {
                                throw new InvalidArgumentsException();
                            }
                            break;
                        }
                        case "!": {
                            if(valueX.getType()==ValueType.BOOLEAN) {
                                res = new Value(!valueX.getAsBoolean());
                            } else {
                                throw new InvalidArgumentsException();
                            }
                            break;
                        }
                    }
                    stack.add(res);
                } else {
                    Value valueY = stack.pop();
                    Value valueX = stack.pop();
                    if(token.equals("+") && (valueX.getType()==ValueType.STRING || valueY.getType()==ValueType.STRING)) {
                        String x;
                        String y;
                        if(valueX.getType()==ValueType.STRING) {
                            x = valueX.getAsString();
                        } else {
                            x = valueX.getValue();
                        }
                        if(valueY.getType()==ValueType.STRING) {
                            y = valueY.getAsString();
                        } else {
                            y = valueY.getValue();
                        }
                        stack.add(new Value(ValueType.STRING, "\"" + x + y + "\""));
                    } else if(isArithmeticOperator(token)) {
                        if(valueX.getType().isNumber && valueY.getType().isNumber) {
                            double x = valueX.getAsDouble();
                            double y = valueY.getAsDouble();

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
                        }  else {
                            throw new InvalidArgumentsException(token);
                        }
                    } else if(isByteOperator(token)) {
                        if(valueX.getType().isByteCompatible && valueY.getType().isByteCompatible) {
                            long x = valueX.getAsLong();
                            long y = valueY.getAsLong();

                            Value res = null;
                            switch (token) {
                                case "^":
                                    res = new Value(x ^ y);
                                    break;
                                case "<<":
                                    res = new Value(x << y);
                                    break;
                                case ">>":
                                    res = new Value(x >> y);
                                    break;
                                case "|":
                                    res = new Value(x | y);
                                    break;
                                case "&":
                                    res = new Value(x & y);
                                    break;
                            }
                            stack.add(res);
                        } else {
                            throw new InvalidArgumentsException();
                        }
                    } else if(isRelationalOperator(token) && valueX.getType().isNumber) {
                        if(valueX.getType().isNumber && valueY.getType().isNumber) {
                            double x = valueX.getAsDouble();
                            double y = valueY.getAsDouble();

                            Value res = null;
                            switch (token) {
                                case "==":
                                    res = new Value(x == y);
                                    break;
                                case "!=":
                                    res = new Value(x != y);
                                    break;
                                case ">=":
                                    res = new Value(x >= y);
                                    break;
                                case "<=":
                                    res = new Value(x <= y);
                                    break;
                                case "<":
                                    res = new Value(x < y);
                                    break;
                                case ">":
                                    res = new Value(x > y);
                                    break;
                            }
                            stack.add(res);
                        } else {
                            throw new InvalidArgumentsException();
                        }
                    } else if(isLogic(token) && valueX.getType() == ValueType.BOOLEAN) {
                        boolean x = valueX.getAsBoolean();
                        boolean y = valueY.getAsBoolean();

                        Value res = null;
                        switch (token) {
                            case "^":
                                res = new Value(x ^ y);
                                break;
                            case "&&":
                                res = new Value(x && y);
                                break;
                            case "||":
                                res = new Value(x || y);
                                break;
                            case "==":
                                res = new Value(x == y);
                                break;
                            case "!=":
                                res = new Value(x != y);
                                break;
                        }
                        stack.add(res);
                    }
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

    private static boolean isLogic(String token) {
        return token.equals("==") || token.equals("!=") || token.equals("&&") || token.equals("||");
    }

    private static boolean isRelationalOperator(String token) {
        return token.equals("==") || token.equals("!=") || token.equals("<=") || token.equals(">=") || token.equals("<") || token.equals(">");
    }

    private static boolean isByteOperator(String token) {
        return token.equals("^") || token.equals("&") || token.equals("|") || token.equals(">>") || token.equals("<<");
    }

    private static boolean isOperatorNeedOneValue(String token) {
        return token.equals("!") || token.equals("~");
    }

    private static boolean isArithmeticOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/") || token.equals("%");
    }

    private static int getArgsAmount(String functionToken) {
        return Integer.parseInt(functionToken.substring(functionToken.lastIndexOf(";")+1));
    }

    private static String getFunctionName(String functionToken) {
        return functionToken.substring(0, functionToken.lastIndexOf(";"));
    }
}
