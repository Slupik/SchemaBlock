package io.github.slupik.schemablock.parser.math.rpn;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * All rights reserved & copyright ©
 */
class RpnTokenizerTest {

    @Test
    void getEquationAsTokens() {
        String equation1 = "1-2";
        assertEquals("[1, -, 2]",
                getAsString(RpnTokenizer.getEquationAsTokens(equation1)));

        String equation2 = "(1+(2*3))";
        assertEquals("[(, 1, +, (, 2, *, 3, ), )]",
                getAsString(RpnTokenizer.getEquationAsTokens(equation2)));

        String equation3 = "(1+(212*3))";
        assertEquals("[(, 1, +, (, 212, *, 3, ), )]",
                getAsString(RpnTokenizer.getEquationAsTokens(equation3)));

        String equation4 = "(1.45+(212.234*3.5))";
        assertEquals("[(, 1.45, +, (, 212.234, *, 3.5, ), )]",
                getAsString(RpnTokenizer.getEquationAsTokens(equation4)));
    }

    private static String getAsString(List<String> value) {
        return Arrays.toString(value.toArray(new String[0]));
    }
}