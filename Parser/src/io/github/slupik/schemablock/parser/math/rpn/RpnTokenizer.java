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
        boolean quotationMode = false;
        int arrayBracketsDeepness = 0;
        for(int i=0;i<equation.length();i++) {
            char c = equation.charAt(i);

            Character lastChar = null;
            if(tokenBuffer.length()>0) {
                lastChar = tokenBuffer.charAt(tokenBuffer.length()-1);
            }

            //String
            if(quotationMode) {
                tokenBuffer.append(c);
                if(c=='"') {
                    if(lastChar != null && lastChar!='\\') {
                        tokens.add(tokenBuffer.toString());
                        tokenBuffer = new StringBuilder();
                        quotationMode = false;
                    }
                }
                continue;
            } else {
                if(c=='"') {
                    tokenBuffer.append(c);
                    quotationMode = true;
                    continue;
                }
            }

            //Array index
            if(c==']') {
                arrayBracketsDeepness--;
                tokenBuffer.append(']');
                if(arrayBracketsDeepness==0) {
                    if(tokenBuffer.length()>0) {
                        tokens.add(tokenBuffer.toString());
                    }
                    tokenBuffer = new StringBuilder();
                }
                continue;
            }
            if(c=='[') {
                if(arrayBracketsDeepness==0) {
                    if(tokenBuffer.length()>0) {
                        tokens.add(tokenBuffer.toString());
                    }
                    tokenBuffer = new StringBuilder();
                }
                arrayBracketsDeepness++;
            }
            if(arrayBracketsDeepness>0) {
                tokenBuffer.append(c);
                continue;
            }

            //Normal Code
            if(isCharEndingToken(c)) {
                if(isGroupOperator(c)) {
                    if(isSingleOperator(c) && equation.length()>i+1 && (!isGroupOperator(equation.charAt(i+1)) || isSingleOperator(equation.charAt(i+1)))) {
                        if(tokenBuffer.length()>0) {
                            tokens.add(tokenBuffer.toString());
                            tokenBuffer = new StringBuilder();
                        }
                        tokens.add(String.valueOf(c));
                        continue;
                    }

                    if(lastChar != null && isGroupOperator(lastChar)) {
                        tokenBuffer.append(c);
                    } else {
                        if(tokenBuffer.length()>0) {
                            tokens.add(tokenBuffer.toString());
                            tokenBuffer = new StringBuilder();
                        }
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
                if(!Character.isWhitespace(c)) {
                    tokens.add(String.valueOf(c));
                }
            } else {
                if(lastChar != null && isGroupOperator(lastChar)) {
                    tokens.add(tokenBuffer.toString());
                    tokenBuffer = new StringBuilder();
                }
                if(!Character.isWhitespace(c)) {
                    tokenBuffer.append(c);
                }
            }
        }

        if(tokenBuffer.length()>0) {
            tokens.add(tokenBuffer.toString());
        }

        return tokens;
    }

    private static boolean isSingleOperator(char c) {
        return c=='!' || c=='~';
    }

    private static boolean isCharEndingToken(char c) {
        return c == '%' || c == '-' || c == '+' || c == '*' || c == '/' || c == '(' || c == ')' || c == ',' || c == '^' || c=='~' || isGroupOperator(c) || Character.isWhitespace(c);
    }

    private static boolean isGroupOperator(char c) {
        return c=='!' || c=='<' || c=='>' || c=='=' || c=='|' || c=='&';
    }
}
