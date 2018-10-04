package io.github.slupik.schemablock.parser.math.rpn;

import io.github.slupik.schemablock.parser.math.rpn.pattern.InvalidArgumentsException;
import io.github.slupik.schemablock.parser.math.rpn.pattern.MathPattern;
import io.github.slupik.schemablock.parser.math.rpn.pattern.PatternFinder;
import io.github.slupik.schemablock.parser.math.rpn.pattern.UnsupportedValueException;
import io.github.slupik.schemablock.parser.math.rpn.pattern.specific.MathPatternSqrt;

import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public class RpnCalculation {

    private static final PatternFinder FUNCTIONS = new PatternFinder();

    static {
        FUNCTIONS.registerPattern(new MathPatternSqrt());
    }

    public static double calculate(List<String> rpnTokens) throws InvalidArgumentsException, UnsupportedValueException {
        List<Double> stack = new ArrayList<>();

        for(String token:rpnTokens) {
            token = token.trim();
            double value = 0;
            MathPattern function = FUNCTIONS.getForName(getFunctionName(token));
            if(function!=null) {
                value = ((Double) function.calculate(token));
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
                        case "/":
                            value = x / y;
                            break;
                        case "*":
                            value = x * y;
                            break;
                    }
                    stack.add(value);
                }
            }
        }
        return stack.get(0);
    }

    private static String getFunctionName(String token) {
        if(token.indexOf('(')==-1) {
            return null;
        }
        return token.substring(0, token.indexOf('(')).trim();
    }
}
