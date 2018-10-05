package io.github.slupik.schemablock.parser.math.rpn;

import io.github.slupik.schemablock.parser.math.rpn.pattern.InvalidArgumentsException;
import io.github.slupik.schemablock.parser.math.rpn.pattern.MathPattern;
import io.github.slupik.schemablock.parser.math.rpn.pattern.PatternFinder;
import io.github.slupik.schemablock.parser.math.rpn.pattern.UnsupportedValueException;
import io.github.slupik.schemablock.parser.math.rpn.pattern.specific.MathPatternSum;
import io.github.slupik.schemablock.parser.math.rpn.pattern.specific.MathPatternSqrt;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
//TODO cleanup code
public class MathCalculation {

    private static final PatternFinder FUNCTIONS = new PatternFinder();

    static {
        FUNCTIONS.registerPattern(new MathPatternSqrt());
        FUNCTIONS.registerPattern(new MathPatternSum());
    }

    public static double getResult(String value) throws UnsupportedValueException, InvalidArgumentsException {

        List<String> rawTokens = RpnTokenizer.getEquationAsTokens(value);
        List<String> tokens = new ArrayList<>();
        for(String raw:rawTokens) {
            tokens.add(getParsedToken(raw));
        }
        List<String> rpn = new ArrayList<>(InfixToRpnConverter.convertInfixToRPN(tokens.toArray(new String[0])));
        return RpnCalculation.calculate(rpn);
    }

    public static String getParsedToken(String raw) throws UnsupportedValueException, InvalidArgumentsException {
        raw = raw.trim();
        if(raw.equals("+") || raw.equals("-") || raw.equals("/") || raw.equals("*") || raw.equals("(") || raw.equals(")")) {
            return raw;
        }
        if(NumberUtils.isParsable(raw)) {
            return raw;
        }
        MathPattern function = FUNCTIONS.getForName(getFunctionName(raw));
        if(function!=null) {
            return String.valueOf(function.calculate(raw));
        }
        return raw;
    }

    private static String getFunctionName(String token) {
        if(token.indexOf('(')==-1) {
            return null;
        }
        return token.substring(0, token.indexOf('(')).trim();
    }
}
