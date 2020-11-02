package io.github.slupik.schemablock.newparser.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * All rights reserved & copyright ©
 */
public class TextUtils {

    public static boolean isWhitespace(char token) {
        return StringUtils.isWhitespace(token + "");
    }

    public static boolean isDigit(char token) {
        return token == '0' || token == '1' || token == '2' || token == '3' || token == '4' || token == '5' || token == '6' || token == '7' || token == '8' || token == '9';
    }

    public static boolean isNumber(String text) {
        try {
            Double.parseDouble(text);
            return true;
        } catch (Exception ignore) {
        }
        text = text.toLowerCase();

        if (CodeUtils.isLetterForNumber(text.charAt(text.length() - 1))) {
            try {
                Double.parseDouble(text.substring(0, text.length() - 1));
                return true;
            } catch (Exception ignore) {
            }
        }

        return false;
    }

}
