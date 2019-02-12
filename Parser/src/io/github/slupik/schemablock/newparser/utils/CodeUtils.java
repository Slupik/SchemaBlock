package io.github.slupik.schemablock.newparser.utils;

import io.github.slupik.schemablock.newparser.compilator.implementation.Token;

/**
 * All rights reserved & copyright ©
 */
public class CodeUtils {

    public static boolean isOperation(String token) {
        for(int i=0;i<token.length();i++) {
            if(!isFunctionalSign(token.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isFunctionalSign(char token) {
        return isSignOfAction(token) || token=='(' || token==')' || token=='[' || token==']' || token==',' || token=='{' || token=='}' || token==';';
    }

    public static boolean isSignOfAction(char token) {
        return token=='+' || token=='-' || token=='*' || token=='/' || token=='\\' || token=='%' || token=='!' || token=='&' || token=='|' || token=='=';
    }

    public static boolean isLetterForNumber(char token) {
        return token=='l' || token=='s' || token=='i' || token=='d' || token=='f';
    }

    public static int getArrayNestLvl(Token token) {
        return getArrayNestLvl(token.getData());
    }
    public static int getArrayNestLvl(String data) {
        return Integer.parseInt(data.substring(1, data.length()-1));
    }

    public static boolean isArrayBrackets(Token token) {
        return isArrayBrackets(token.getData());
    }
    public static boolean isArrayBrackets(String data) {
        if(isEmptyArrayBrackets(data)) {
            return true;
        } else if(data.startsWith("[") && data.endsWith("]")) {
            try {
                getArrayNestLvl(data);
                return true;
            } catch (Throwable ignore) {
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean isEmptyArrayBrackets(Token token) {
        return isEmptyArrayBrackets(token.getData());
    }
    private static boolean isEmptyArrayBrackets(String data) {
        return "[]".equals(data);
    }
}
