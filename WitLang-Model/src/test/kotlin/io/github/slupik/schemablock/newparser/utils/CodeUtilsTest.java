package io.github.slupik.schemablock.newparser.utils;

import io.github.slupik.schemablock.newparser.compilator.implementation.Token;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * All rights reserved & copyright ©
 */
class CodeUtilsTest {

    @Test
    void getArrayNestLvl() {
        Assertions.assertEquals(5, CodeUtils.getArrayNestLvl("[5]"));
        Assertions.assertEquals(0, CodeUtils.getArrayNestLvl("[0]"));
        Assertions.assertEquals(0, CodeUtils.getArrayNestLvl("0]"));
        Assertions.assertEquals(0, CodeUtils.getArrayNestLvl("[0"));
        Assertions.assertEquals(5423, CodeUtils.getArrayNestLvl("[5423]"));

        Assertions.assertEquals(5423, CodeUtils.getArrayNestLvl(new Token("[5423]", 0, 0)));
    }

    @Test
    void isArrayBrackets() {
        assertTrue(CodeUtils.isArrayBrackets("[]"));
        assertTrue(CodeUtils.isArrayBrackets("[5423]"));
        assertTrue(CodeUtils.isArrayBrackets(new Token("[]", 0, 0)));

        Assertions.assertFalse(CodeUtils.isArrayBrackets("5423"));
        Assertions.assertFalse(CodeUtils.isArrayBrackets("[c]"));
    }

    @Test
    void isSpecialText() {
        for(Map.Entry<String, Integer> entry: new CodeOperations().entrySet()) {
            assertTrue(CodeUtils.isSpecialText(entry.getKey()));
        }

        Assertions.assertFalse(CodeUtils.isSpecialText("!a"));
        Assertions.assertFalse(CodeUtils.isSpecialText("(a)"));
    }

    @Test
    void isOperation() {
        assertTrue(CodeUtils.isOperation("!="));
        assertTrue(CodeUtils.isOperation("=="));
        assertTrue(CodeUtils.isOperation("<="));
        assertTrue(CodeUtils.isOperation(">="));
        assertTrue(CodeUtils.isOperation("++"));
        assertTrue(CodeUtils.isOperation("+="));
        assertTrue(CodeUtils.isOperation("-="));
        assertTrue(CodeUtils.isOperation("!"));
        assertTrue(CodeUtils.isSpecialText("[]"));

        Assertions.assertFalse(CodeUtils.isOperation("!a"));
        Assertions.assertFalse(CodeUtils.isOperation("=1"));
    }

    @Test
    void isLetterForNumber() {
        assertTrue(CodeUtils.isLetterForNumber('s'));//short
        assertTrue(CodeUtils.isLetterForNumber('i'));//int
        assertTrue(CodeUtils.isLetterForNumber('l'));//long
        assertTrue(CodeUtils.isLetterForNumber('f'));//float
        assertTrue(CodeUtils.isLetterForNumber('d'));//double

        Assertions.assertFalse(CodeUtils.isLetterForNumber('e'));
    }

    @Test
    void isEmptyArrayBrackets() {
        assertTrue(CodeUtils.isEmptyArrayBrackets("[]"));
        assertTrue(CodeUtils.isEmptyArrayBrackets(new Token("[]", 0, 0)));

        Assertions.assertFalse(CodeUtils.isEmptyArrayBrackets("()"));
        Assertions.assertFalse(CodeUtils.isEmptyArrayBrackets("{}"));
        Assertions.assertFalse(CodeUtils.isEmptyArrayBrackets("[ ]"));
        Assertions.assertFalse(CodeUtils.isEmptyArrayBrackets("[a]"));
        Assertions.assertFalse(CodeUtils.isEmptyArrayBrackets("[a"));
        Assertions.assertFalse(CodeUtils.isEmptyArrayBrackets("a]"));
        Assertions.assertFalse(CodeUtils.isEmptyArrayBrackets("["));
        Assertions.assertFalse(CodeUtils.isEmptyArrayBrackets("]"));
    }

    @Test
    void isArrayStart() {
        assertTrue(CodeUtils.isArrayStart("[0"));
        assertTrue(CodeUtils.isArrayStart("[-1"));

        Assertions.assertFalse(CodeUtils.isArrayStart("[a"));
        Assertions.assertFalse(CodeUtils.isArrayStart("[3.2"));
        Assertions.assertFalse(CodeUtils.isArrayStart("[5.0"));

        Assertions.assertFalse(CodeUtils.isArrayStart("["));
        Assertions.assertFalse(CodeUtils.isArrayStart("0]"));
        Assertions.assertFalse(CodeUtils.isArrayStart("(0"));
        Assertions.assertFalse(CodeUtils.isArrayStart("{0"));
    }

    @Test
    void isArrayEnd() {
        assertTrue(CodeUtils.isArrayEnd("0]"));
        assertTrue(CodeUtils.isArrayEnd("-1]"));

        Assertions.assertFalse(CodeUtils.isArrayEnd("a]"));
        Assertions.assertFalse(CodeUtils.isArrayEnd("3.2]"));
        Assertions.assertFalse(CodeUtils.isArrayEnd("5.0]"));

        Assertions.assertFalse(CodeUtils.isArrayEnd("]"));
        Assertions.assertFalse(CodeUtils.isArrayEnd("[0"));
        Assertions.assertFalse(CodeUtils.isArrayEnd("0)"));
        Assertions.assertFalse(CodeUtils.isArrayEnd("0}"));
    }
}