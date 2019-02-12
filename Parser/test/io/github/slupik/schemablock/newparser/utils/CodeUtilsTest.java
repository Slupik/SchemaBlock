package io.github.slupik.schemablock.newparser.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * All rights reserved & copyright ©
 */
class CodeUtilsTest {

    @Test
    void getArrayNestLvl() {
        Assertions.assertEquals(5, CodeUtils.getArrayNestLvl("[5]"));
        Assertions.assertEquals(0, CodeUtils.getArrayNestLvl("[0]"));
        Assertions.assertEquals(5423, CodeUtils.getArrayNestLvl("[5423]"));
    }

    @Test
    void isArrayBrackets() {
        Assertions.assertTrue(CodeUtils.isArrayBrackets("[]"));
        Assertions.assertTrue(CodeUtils.isArrayBrackets("[5423]"));
        Assertions.assertFalse(CodeUtils.isArrayBrackets("5423"));
    }
}