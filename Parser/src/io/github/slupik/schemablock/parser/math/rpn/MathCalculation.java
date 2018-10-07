package io.github.slupik.schemablock.parser.math.rpn;

import io.github.slupik.schemablock.parser.math.rpn.pattern.InvalidArgumentsException;
import io.github.slupik.schemablock.parser.math.rpn.pattern.MathPattern;
import io.github.slupik.schemablock.parser.math.rpn.pattern.PatternFinder;
import io.github.slupik.schemablock.parser.math.rpn.pattern.UnsupportedValueException;
import io.github.slupik.schemablock.parser.math.rpn.pattern.specific.MathPatternSqrt;
import io.github.slupik.schemablock.parser.math.rpn.pattern.specific.MathPatternSum;
import io.github.slupik.schemablock.parser.math.rpn.variable.Variable;
import io.github.slupik.schemablock.parser.math.rpn.variable.VariableHeap;
import io.github.slupik.schemablock.parser.math.rpn.variable.value.NotFoundTypeException;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public class MathCalculation {

    private static final PatternFinder FUNCTIONS = new PatternFinder();

    static {
        FUNCTIONS.registerPattern(new MathPatternSqrt());
        FUNCTIONS.registerPattern(new MathPatternSum());
    }

    public static Object getResult(String value) throws UnsupportedValueException, InvalidArgumentsException, NotFoundTypeException {
        return getResult(new VariableHeap(), value);
    }

    public static Object getResult(VariableHeap heap, String value) throws UnsupportedValueException, InvalidArgumentsException, NotFoundTypeException {
        List<String> rawTokens = RpnTokenizer.getEquationAsTokens(value);
        List<String> tokens = new ArrayList<>();
        for(String raw:rawTokens) {
            tokens.add(getParsedToken(heap, raw));
        }
        List<String> rpn = new ArrayList<>(InfixToRpnConverter.convertInfixToRPN(tokens.toArray(new String[0])));
        return RpnCalculation.calculate(rpn);
    }

    private static String getParsedToken(VariableHeap heap, String raw) throws UnsupportedValueException, InvalidArgumentsException, NotFoundTypeException {
        raw = raw.trim();
        if(raw.equals("+") || raw.equals("-") || raw.equals("/") || raw.equals("*") || raw.equals("(") || raw.equals(")")) {
            return raw;
        }
        if(isNumber(raw)) {
            return raw;
        }
        MathPattern function = FUNCTIONS.getForName(getFunctionName(raw));
        if(function!=null) {
            return String.valueOf(function.calculate(raw));
        }
        Variable variable = heap.getVariable(raw);
        if(variable!=null) {
            return variable.getValue();
        }
        return raw;
    }

    private static boolean isNumber(String raw) {
        if(raw.startsWith("0x")) {
            return NumberUtils.isParsable(raw);
        } else {
            for(int i=0;i<raw.length();i++) {
                char c = raw.charAt(i);
                if(!Character.isDigit(c)) {
                    return false;
                }
            }
            return true;
        }
    }

    private static String getFunctionName(String token) {
        if(token.indexOf('(')==-1) {
            return null;
        }
        return token.substring(0, token.indexOf('(')).trim();
    }
}
