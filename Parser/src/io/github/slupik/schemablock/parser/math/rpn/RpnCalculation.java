package io.github.slupik.schemablock.parser.math.rpn;

import io.github.slupik.schemablock.parser.math.rpn.pattern.InvalidArgumentsException;
import io.github.slupik.schemablock.parser.math.rpn.pattern.MathPattern;
import io.github.slupik.schemablock.parser.math.rpn.pattern.PatternFinder;
import io.github.slupik.schemablock.parser.math.rpn.pattern.UnsupportedValueException;
import io.github.slupik.schemablock.parser.math.rpn.pattern.specific.MathPatternSqrt;
import io.github.slupik.schemablock.parser.math.rpn.pattern.specific.MathPatternSum;
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

    static double calculate(List<String> rpnTokens) throws InvalidArgumentsException, UnsupportedValueException {
        List<Double> stack = new ArrayList<>();

        for(String token:rpnTokens) {
            token = token.trim();
            double value = 0;

            if(token.contains(";")) {
                MathPattern function = FUNCTIONS.getForName(getFunctionName(token));
                List<Value> args = new ArrayList<>();
                int argsAmount = getArgsAmount(token);
                for(;argsAmount>0;argsAmount--) {
                    double parsedArg = stack.get(stack.size()-argsAmount);
                    args.add(new Value(ValueType.getType(parsedArg), parsedArg));
                    stack.remove(stack.size()-argsAmount);
                }
                value = new Double(function.calculate(args.toArray(new Value[0])).toString());
                stack.add(value);
            } else {
                try {
                    value = Double.parseDouble(token);
                    stack.add(value);
                } catch (Exception e) {
                    double x = stack.get(stack.size()-2);
                    stack.remove(stack.size()-2);
                    double y = stack.get(stack.size()-1);
                    stack.remove(stack.size()-1);

                    switch (token) {
                        case "+":
                            value = x + y;
                            break;
                        case "-":
                            value = x - y;
                            break;
                        case "*":
                            value = x * y;
                            break;
                        case "/":
                            value = x / y;
                            break;
                        case "%":
                            value = x % y;
                            break;
                    }
                    stack.add(value);
                }
            }
        }
        return stack.get(0);
    }

    private static int getArgsAmount(String functionToken) {
        return Integer.parseInt(functionToken.substring(functionToken.lastIndexOf(";")+1));
    }

    private static String getFunctionName(String functionToken) {
        return functionToken.substring(0, functionToken.lastIndexOf(";"));
    }
}
