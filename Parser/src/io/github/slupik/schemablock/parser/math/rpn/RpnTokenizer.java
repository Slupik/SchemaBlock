package io.github.slupik.schemablock.parser.math.rpn;

import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
class RpnTokenizer {

    static List<String> getEquationAsTokens(String equation) {
        List<String> tokens = new ArrayList<>();

        StringBuilder tokenBuffer = new StringBuilder();
        for(int i=0;i<equation.length();i++) {
            char c = equation.charAt(i);

            if(Character.isWhitespace(c)) {
                continue;
            }
            if(isCharEndingToken(c)) {
                if(isCharForLogic(c)) {
                    Character lastChar = null;
                    if(tokenBuffer.length()>0) {
                        lastChar = tokenBuffer.charAt(tokenBuffer.length()-1);
                    }
                    if(lastChar != null && isCharForLogic(lastChar)) {
                        tokenBuffer.append(c);
                    } else {
                        tokens.add(tokenBuffer.toString());
                        tokenBuffer = new StringBuilder();
                        tokenBuffer.append(c);
                    }
                    continue;
                }

                if(tokenBuffer.length()>0) {
                    tokens.add(tokenBuffer.toString());
                    tokenBuffer = new StringBuilder();
                }

                if(c == '-' || c == '+') {
                    String lastValue = "";
                    if(tokens.size()>0) {
                        lastValue = tokens.get(tokens.size()-1);
                    }
                    if(lastValue.length()==0 ||
                            lastValue.endsWith("(") ||
                            lastValue.endsWith(",")) {
                        tokenBuffer.append(c);
                        continue;
                    }
                }
                tokens.add(String.valueOf(c));
            } else {
                Character lastChar = null;
                if(tokenBuffer.length()>0) {
                    lastChar = tokenBuffer.charAt(tokenBuffer.length()-1);
                }
                if(lastChar != null && isCharForLogic(lastChar)) {
                    tokens.add(tokenBuffer.toString());
                    tokenBuffer = new StringBuilder();
                }
                tokenBuffer.append(c);
            }
        }

        if(tokenBuffer.length()>0) {
            tokens.add(tokenBuffer.toString());
        }

        return tokens;
    }

    private static boolean isCharEndingToken(char c) {
        return c == '-' || c == '+' || c == '*' || c == '/' || c == '(' || c == ')' || c == ',' || isCharForLogic(c);
    }

    private static boolean isCharForLogic(char c) {
        return c=='!' || c=='<' || c=='>' || c=='=';
    }
}
