package io.github.slupik.schemablock.parser.math.rpn;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public class RpnTokenizer {

    public static List<String> getEquationAsTokens(String equation) {
        List<String> tokens = new ArrayList<>();

        StringBuilder tokenBuffer = new StringBuilder();
        int deepness = 0;
        for(int i=0;i<equation.length();i++) {
            char c = equation.charAt(i);

            if(deepness>0) {
                if(c == '(') {
                    deepness++;
                } else if(c == ')') {
                    deepness--;
                }
                tokenBuffer.append(c);
            } else {
                if(Character.isWhitespace(c)) {
                    continue;
                }
                if(isCharEndingToken(c)) {
                    if(tokenBuffer.length()>0) {
                        if(!tokenBuffer.toString().contains("(") && !NumberUtils.isParsable(tokenBuffer.toString())) {
                            deepness++;
                            tokenBuffer.append(c);
                        } else {
                            tokens.add(tokenBuffer.toString());
                            tokenBuffer = new StringBuilder();
                        }
                    }

                    if(deepness==0) {
                        tokens.add(String.valueOf(c));
                    }
                } else {
                    tokenBuffer.append(c);
                }
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
