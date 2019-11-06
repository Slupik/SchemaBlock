package io.github.slupik.schemablock.newparser.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * All rights reserved & copyright ©
 */
class TextUtilsTest {

    @Test
    void isWhitespace() {
        assertTrue(TextUtils.isWhitespace('\n'));//enter
        assertTrue(TextUtils.isWhitespace('\u0009'));//character tabulation	
        assertTrue(TextUtils.isWhitespace('\u000B'));//line tabulation
        assertTrue(TextUtils.isWhitespace('\u000C'));//form feed
        assertTrue(TextUtils.isWhitespace('\u0020'));//space
        assertTrue(TextUtils.isWhitespace('\u1680'));//ogham space mark
        assertTrue(TextUtils.isWhitespace('\u2000'));//en quad
        assertTrue(TextUtils.isWhitespace('\u2001'));//em quad
        assertTrue(TextUtils.isWhitespace('\u2002'));//en space
        assertTrue(TextUtils.isWhitespace('\u2003'));//em space
        assertTrue(TextUtils.isWhitespace('\u2004'));//three-per-em space
        assertTrue(TextUtils.isWhitespace('\u2005'));//four-per-em space
        assertTrue(TextUtils.isWhitespace('\u2006'));//six-per-em space
        assertTrue(TextUtils.isWhitespace('\u2008'));//punctuation space
        assertTrue(TextUtils.isWhitespace('\u2009'));//thin space
        assertTrue(TextUtils.isWhitespace('\u200A'));//hair space
        assertTrue(TextUtils.isWhitespace('\u2028'));//line separator
        assertTrue(TextUtils.isWhitespace('\u2029'));//paragraph separator
        assertTrue(TextUtils.isWhitespace('\u205F'));//medium mathematical space
        assertTrue(TextUtils.isWhitespace('\u3000'));//ideographic space

        assertFalse(TextUtils.isWhitespace('a'));

        //false?
//        assertTrue(TextUtils.isWhitespace('\u0085'));//next line
//        assertTrue(TextUtils.isWhitespace('\u00A0'));//no-break space
//        assertTrue(TextUtils.isWhitespace('\u2007'));//figure space
//        assertTrue(TextUtils.isWhitespace('\u202F'));//narrow no-break space
    }

    @Test
    void isDigit() {
        for(int i=0;i<10;i++) {
            assertTrue(TextUtils.isDigit(Integer.toString(i).charAt(0)));
        }
        assertFalse(TextUtils.isDigit('a'));
    }

    @Test
    void isNumber() {
        assertTrue(TextUtils.isNumber("1"));
        assertTrue(TextUtils.isNumber("41252352342342342432423"));
        assertTrue(TextUtils.isNumber("1.0"));
        assertTrue(TextUtils.isNumber("41252352342342342432423.0"));

        assertTrue(TextUtils.isNumber("-1"));
        assertTrue(TextUtils.isNumber("-41252352342342342432423"));
        assertTrue(TextUtils.isNumber("-1.0"));
        assertTrue(TextUtils.isNumber("-41252352342342342432423.0"));

        assertTrue(TextUtils.isNumber("1s"));
        assertTrue(TextUtils.isNumber("1i"));
        assertTrue(TextUtils.isNumber("1l"));
        assertTrue(TextUtils.isNumber("1f"));
        assertTrue(TextUtils.isNumber("1d"));

        assertFalse(TextUtils.isNumber("a"));
        assertFalse(TextUtils.isNumber("-5a"));
        assertFalse(TextUtils.isNumber("-2is"));
    }
}