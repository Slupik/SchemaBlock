package io.github.slupik.schemablock.parser.math.rpn;

import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public class RpnTokenizer {

    public static List<String> getEquationAsTokens(String equation) {
        List<String> tokens = new ArrayList<>();

        StringBuilder tokenBuffer = new StringBuilder();
        for(int i=0;i<equation.length();i++) {
            char c = equation.charAt(i);

            if(isCharEndingToken(c)) {
                if(tokenBuffer.length()>0) {
                    tokens.add(tokenBuffer.toString());
                    tokenBuffer = new StringBuilder();
                }

                tokens.add(String.valueOf(c));
            } else {
                tokenBuffer.append(c);
            }
        }

        if(tokenBuffer.length()>0) {
            tokens.add(tokenBuffer.toString());
        }

        return tokens;
    }

    private static boolean isCharEndingToken(char c) {
        return c == '-' || c == '+' || c == '*' || c == '/' || c == '(' || c == ')';
    }
}
