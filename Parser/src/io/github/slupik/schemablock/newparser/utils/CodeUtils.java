package io.github.slupik.schemablock.newparser.utils;

/**
 * All rights reserved & copyright ©
 */
public class CodeUtils {

    public static boolean isFunctionalSign(char token) {
        return isSignOfAction(token) || token=='(' || token==')' || token=='[' || token==']' || token==',' || token=='{' || token=='}' || token==';';
    }

    public static boolean isSignOfAction(char token) {
        return token=='+' || token=='-' || token=='*' || token=='/' || token=='\\' || token=='%' || token=='!' || token=='&' || token=='|' || token=='=';
    }

    public static boolean isLetterForNumber(char token) {
        return token=='l' || token=='s' || token=='i' || token=='d' || token=='f';
    }
}
