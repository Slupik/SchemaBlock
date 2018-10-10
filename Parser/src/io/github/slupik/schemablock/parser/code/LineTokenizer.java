package io.github.slupik.schemablock.parser.code;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public class LineTokenizer {

    public static List<String> tokenize(String line) {
        List<String> tokens = new ArrayList<>();

        boolean autoAppendMode = false;
        boolean isProbablyEquation = false;
        int arrayBracketsDeepness = 0;
        StringBuilder tokenBuilder = new StringBuilder();
        for(int i=0;i<line.length();i++) {
            char c = line.charAt(i);

            if(autoAppendMode) {
                tokenBuilder.append(c);
                continue;
            }

            //Array index
            if(c==']') {
                arrayBracketsDeepness--;
                tokenBuilder.append(']');
                if(arrayBracketsDeepness==0) {
                    if(tokenBuilder.length()>0) {
                        tokens.add(tokenBuilder.toString());
                    }
                    tokenBuilder = new StringBuilder();
                }
                continue;
            }
            if(c=='[') {
                if(arrayBracketsDeepness==0) {
                    if(tokenBuilder.length()>0) {
                        tokens.add(tokenBuilder.toString());
                    }
                    tokenBuilder = new StringBuilder();
                }
                arrayBracketsDeepness++;
            }
            if(arrayBracketsDeepness>0) {
                tokenBuilder.append(c);
                continue;
            }

            //Equation
            if(c=='(' || c==')') {
                if(isProbablyEquation) {
                    autoAppendMode = true;
                    flushBuffer(tokens, tokenBuilder);
                    tokenBuilder = new StringBuilder();
                    tokenBuilder.append(c);
                    continue;
                }
            }

            //Normal code
            if(isNumber(tokenBuilder, c)) {
                if(isProbablyEquation) {
                    autoAppendMode = true;
                    flushBuffer(tokens, tokenBuilder);
                    tokenBuilder = new StringBuilder();
                    tokenBuilder.append(c);
                } else {
                    tokenBuilder.append(c);
                }
                continue;
            } else if(isProbablyEquation && (c=='+' || c=='-')) {
                autoAppendMode = true;
            }

            if (!Character.isWhitespace(c) && c!='+' && c!='-') {
                isProbablyEquation = false;
            }

            if(isCharEndingToken(c)) {
                if(c=='=') {
                    isProbablyEquation = true;
                }

                flushBuffer(tokens, tokenBuilder);
                tokenBuilder = new StringBuilder();

                if(!Character.isWhitespace(c)) {
                    tokenBuilder.append(c);
                }
                flushBuffer(tokens, tokenBuilder);
                tokenBuilder = new StringBuilder();
                continue;
            }

            if(Character.isWhitespace(c)) {
                continue;
            }
            tokenBuilder.append(c);
        }
        flushBuffer(tokens, tokenBuilder);

        return tokens;
    }

    private static boolean isNumber(StringBuilder tokenBuilder, char c) {
        if(NumberUtils.isParsable(String.valueOf(c))) {
            if(Character.isLetter(c)) {
                return tokenBuilder.toString().startsWith("0x");
            } else {
                return true;
            }
        }
        return false;
    }

    private static void flushBuffer(List<String> stack, StringBuilder buffer) {
        if(buffer.length() > 0) {
            stack.add(buffer.toString());
        }
    }

    private static boolean isCharEndingToken(char c) {
        return c=='=' || c=='(' || c==')' || c==',' || Character.isWhitespace(c);
    }
}
