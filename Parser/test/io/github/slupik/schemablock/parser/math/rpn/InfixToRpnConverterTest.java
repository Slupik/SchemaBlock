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
//        String[] infixNotation = {"41", "+", "13"};
//        assertEquals("[41, 13, +]",
//                getAsString(new InfixToRpnConverter().convertInfixToRPN(infixNotation)));
//
//        String[] infixNotation2 = {"2", "*", "(", "3", "+", "4", "/", "2", ")"};
//        assertEquals("[2, 3, 4, 2, /, +, *]",
//                getAsString(new InfixToRpnConverter().convertInfixToRPN(infixNotation2)));
//
//        String infixNotation3 = "4 * 5 * ( 3 - 2 )";
//        assertEquals("[4, 5, *, 3, 2, -, *]",
//                getAsString(new InfixToRpnConverter().convertInfixToRPN(infixNotation3.split(" "))));

//        String infixNotation4 = "4 * sqrt(3) * ( 3 - 2 )";
//        assertEquals("[4, sqrt(3), *, 3, 2, -, *]",
//                getAsString(new InfixToRpnConverter().convertInfixToRPN(infixNotation4.split(" "))));

//        String infixNotation5 = "4 * sqrt(3.2) * ( 3 - 2 )";
//        assertEquals("[4, sqrt(3.2), *, 3, 2, -, *]",
//                getAsString(new InfixToRpnConverter().convertInfixToRPN(infixNotation5.split(" "))));



        String infixNotation6 = "4 * sqrt ( 3 ) + 3";// * ( 3 - 2 )
        assertEquals("[4, 3, sqrt;1, *, 3, +]",
                getAsString(InfixToRpnConverter.convertInfixToRPN(infixNotation6.split(" "))));
    }

    private String getAsString(Queue<String> convertInfixToRPN) {
        return getAsString(new ArrayList<>(convertInfixToRPN));
    }

    private static String getAsString(List<String> value) {
        return Arrays.toString(value.toArray(new String[0]));
    }
}