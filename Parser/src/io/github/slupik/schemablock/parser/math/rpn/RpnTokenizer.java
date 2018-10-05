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
        Character bufferToAdd = null;
        for(int i=0;i<equation.length();i++) {
            char c = equation.charAt(i);

            if(Character.isWhitespace(c)) {
                continue;
            }
            if(bufferToAdd!=null) {
                tokenBuffer.append(bufferToAdd);
                bufferToAdd = null;
            }
            if(isCharEndingToken(c)) {
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
                        bufferToAdd = c;
                        continue;
                    }
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
        return c == '-' || c == '+' || c == '*' || c == '/' || c == '(' || c == ')' || c == ',';
    }
}
