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

        String equation5 = "(1.45+(sqrt(3)*3.5))";
//        assertEquals("[(, 1.45, +, (, sqrt(3), *, 3.5, ), )]",
//                getAsString(RpnTokenizer.getEquationAsTokens(equation5)));

        String equation6 = "(1.45+(add(3,2)*3.5))";
//        assertEquals("[(, 1.45, +, (, add(3,2), *, 3.5, ), )]",
//                getAsString(RpnTokenizer.getEquationAsTokens(equation6)));

        String equation7 = "(1.45+(add(3.423,2.6534)*3.5))";
//        assertEquals("[(, 1.45, +, (, add(3.423,2.6534), *, 3.5, ), )]",
//                getAsString(RpnTokenizer.getEquationAsTokens(equation7)));

        String equation8 = "sum(2,3,4,10)";
//        assertEquals("[sum(2,3,4,10)]",
//                getAsString(RpnTokenizer.getEquationAsTokens(equation8)));

        String equation9 = "sqrt  (  sum(2, 3, 4, 10)  )";
//        assertEquals("[sqrt(  sum(2, 3, 4, 10)  )]",
//                getAsString(RpnTokenizer.getEquationAsTokens(equation9)));

        String equation10 = "((sqrt(3)))";
        assertEquals("[(, (, sqrt, (, 3, ), ), )]",
                getAsString(RpnTokenizer.getEquationAsTokens(equation10)));

        String equation11 = "-3";
        assertEquals("[-3]",
                getAsString(RpnTokenizer.getEquationAsTokens(equation11)));

        String equation12 = "-3-2";
        assertEquals("[-3, -, 2]",
                getAsString(RpnTokenizer.getEquationAsTokens(equation12)));
    }

    private static String getAsString(List<String> value) {
        return Arrays.toString(value.toArray(new String[0]));
    }
}