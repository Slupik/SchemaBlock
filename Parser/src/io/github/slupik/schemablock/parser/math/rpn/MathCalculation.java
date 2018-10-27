package io.github.slupik.schemablock.parser.math.rpn;

import io.github.slupik.schemablock.parser.math.rpn.pattern.InvalidArgumentsException;
import io.github.slupik.schemablock.parser.math.rpn.pattern.MathPattern;
import io.github.slupik.schemablock.parser.math.rpn.pattern.PatternFinder;
import io.github.slupik.schemablock.parser.math.rpn.pattern.UnsupportedValueException;
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
        FUNCTIONS.registerDefaultPatterns();
    }

    public static Object getResult(String value) throws UnsupportedValueException, InvalidArgumentsException, NotFoundTypeException {
        return getResult(new VariableHeap(), value);
    }

    public static Object getResult(VariableHeap heap, String value) throws UnsupportedValueException, InvalidArgumentsException, NotFoundTypeException {
        List<String> rawTokens = RpnTokenizer.getEquationAsTokens(value);
        List<String> tokens = getParsedTokens(heap, rawTokens);
        List<String> rpn = new ArrayList<>(InfixToRpnConverter.convertInfixToRPN(tokens.toArray(new String[0])));
        return RpnCalculation.calculate(rpn);
    }

    private static List<String> getParsedTokens(VariableHeap heap, List<String> rawTokens) throws InvalidArgumentsException, UnsupportedValueException, NotFoundTypeException {
        List<String> parsedTokens = new ArrayList<>();
        for(int i=0;i<rawTokens.size();i++) {
            String raw = rawTokens.get(i);
            raw = raw.trim();
            if(raw.equals("+") || raw.equals("-") || raw.equals("/") || raw.equals("*") || raw.equals("(") || raw.equals(")")) {
                parsedTokens.add(raw);
                continue;
            }
            if(isNumber(raw)) {
                parsedTokens.add(raw);
                continue;
            }
            MathPattern function = FUNCTIONS.getForName(getFunctionName(raw));
            if(function!=null) {
                parsedTokens.add(String.valueOf(function.calculate(raw)));
                continue;
            }
            if(i+1<rawTokens.size()) {
                String brackets = rawTokens.get(i+1);
                if(isBrackets(brackets)) {
                    String arrayLength = brackets.substring(1, brackets.length()-1);
                    Object result = MathCalculation.getResult(heap, arrayLength);
                    raw = raw+"["+result+"]";
                    i++;
                }
            }
            Variable variable = heap.getVariable(raw);
            if(variable!=null) {
                parsedTokens.add(variable.getValue());
                continue;
            }
            parsedTokens.add(raw);
        }
        return parsedTokens;
    }

    private static boolean isBrackets(String toCheck) {
        return toCheck.startsWith("[") && toCheck.endsWith("]");
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
