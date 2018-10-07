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
        checkCase("[1, -, 2]", "1-2");
        checkCase("[1, %, 2]", "1%2");
        checkCase("[(, 1, +, (, 2, *, 3, ), )]", "(1+(2*3))");
        checkCase("[(, 1, +, (, 212, *, 3, ), )]", "(1+(212*3))");
        checkCase("[(, 1.45, +, (, 212.234, *, 3.5, ), )]", "(1.45+(212.234*3.5))");
        checkCase("[(, 1.45, +, (, sqrt, (, 3, ), *, 3.5, ), )]", "(1.45+(sqrt(3)*3.5))");
        checkCase("[(, 1.45, +, (, add, (, 3, ,, 2, ), *, 3.5, ), )]", "(1.45+(add(3,2)*3.5))");
        checkCase("[(, 1.45, +, (, add, (, 3.423, ,, 2.6534, ), *, 3.5, ), )]", "(1.45+(add(3.423,2.6534)*3.5))");
        checkCase("[sum, (, 2, ,, 3, ,, 4, ,, 10, )]", "sum(2,3,4,10)");
        checkCase("[sqrt, (, sum, (, 2, ,, 3, ,, 4, ,, 10, ), )]", "sqrt  (  sum(2, 3, 4, 10)  )");
        checkCase("[(, (, sqrt, (, 3, ), ), )]", "((sqrt(3)))");
        checkCase("[-3]", "-3");
        checkCase("[-3, -, 2]", "-3-2");
        checkCase("[sum, (, -3, ,, -5, ,, 3, ,, -45, ,, -4.32, )]", "sum(-3, -5, 3, -45, -4.32)");

        checkCase("[1, ^, 3]", "1^3");
        checkCase("[1, <, 3]", "1<3");
        checkCase("[1, >, 3]", "1>3");
        checkCase("[1, !, 3]", "1!3");
        checkCase("[1, ==, 3]", "1==3");
        checkCase("[1, !=, 3]", "1!=3");
        checkCase("[1, <=, 3]", "1<=3");
        checkCase("[1, >=, 3]", "1>=3");
        checkCase("[1, |, 3]", "1|3");
        checkCase("[1, &, 3]", "1&3");
        checkCase("[1, ||, 3]", "1||3");
        checkCase("[1, &&, 3]", "1&&3");
        checkCase("[1, <<, 3]", "1<<3");
        checkCase("[1, >>, 3]", "1>>3");
        checkCase("[1, ===, 3]", "1===3");
        checkCase("[1, =====, 3]", "1=====3");

        checkCase("[!, false]", "!false");
        checkCase("[!, true]", "!true");
        checkCase("[!, !, true]", "!!true");
        checkCase("[!, !, !, false]", "!!!false");
        checkCase("[!, !, !, !, !, true]", "!!!!!true");
        checkCase("[~, 1]", "~1");
        checkCase("[~, ~, 1]", "~~1");
        checkCase("[~, ~, ~, 1]", "~~~1");
        checkCase("[~, !, ~, 1]", "~!~1");

        checkCase("[write, (, \"Test\", ,, 0]", "write(\"Test\", 0");
        checkCase("[write, (, \"Te,st\", ,, 0]", "write(\"Te,st\", 0");
        checkCase("[write, (, \"T\te,st;\", ,, 0]", "write(\"T\te,st;\", 0");

        checkCase("[write, (, \"T\te,st;\", ,, testValue, ,, 0]", "write(\"T\te,st;\", testValue, 0");
    }

    private void checkCase(String expected, String input) {
        assertEquals(expected, getAsString(RpnTokenizer.getEquationAsTokens(input)));
    }

    private static String getAsString(List<String> value) {
        return Arrays.toString(value.toArray(new String[0]));
    }
}