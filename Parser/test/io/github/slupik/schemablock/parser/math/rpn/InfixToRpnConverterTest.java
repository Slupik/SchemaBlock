package io.github.slupik.schemablock.parser.math.rpn;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * All rights reserved & copyright ©
 */
class InfixToRpnConverterTest {

    @Test
    void convertInfixToRPN() {
        checkCase("[41, 13, +]", new String[]{"41", "+", "13"});
        checkCase("[2, 3, 4, 2, /, +, *]", new String[]{"2", "*", "(", "3", "+", "4", "/", "2", ")"});
        checkCase("[4, 5, *, 3, 2, -, *]", "4 * 5 * ( 3 - 2 )".split(" "));
        checkCase("[4, 3, sqrt;1, *, 3, 2, -, *]", "4 * sqrt ( 3 ) * ( 3 - 2 )".split(" "));
        checkCase("[4, 3.2, sqrt;1, *, 3, 2, -, *]", "4 * sqrt ( 3.2 ) * ( 3 - 2 )".split(" "));
        checkCase("[4, 3, sqrt;1, *, 3, 3, 2, -, *, +]", "4 * sqrt ( 3 ) + 3 * ( 3 - 2 )".split(" "));
    }

    private void checkCase(String expected, String[] input) {
        assertEquals(expected, getAsString(InfixToRpnConverter.convertInfixToRPN(input)));
    }

    private String getAsString(Queue<String> convertInfixToRPN) {
        return getAsString(new ArrayList<>(convertInfixToRPN));
    }

    private static String getAsString(List<String> value) {
        return Arrays.toString(value.toArray(new String[0]));
    }
}