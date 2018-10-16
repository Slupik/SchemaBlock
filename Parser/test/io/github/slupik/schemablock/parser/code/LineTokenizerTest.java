package io.github.slupik.schemablock.parser.code;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * All rights reserved & copyright ©
 */
class LineTokenizerTest {

    @Test
    void tokenize() {
        assertEquals("[double, a, =, 3]", checkLine("double a = 3"));
        assertEquals("[double, a, ,, b]", checkLine("double a, b"));
        assertEquals("[double, a, ,, b, =, 3]", checkLine("double a, b = 3"));
        assertEquals("[double, a, =, 3+2*(4-3)]", checkLine("double a = 3+2*(4-3)"));
        assertEquals("[double, a, =, (3+2*(4-3))]", checkLine("double a = (3+2*(4-3))"));
        assertEquals("[double, a, =, -(3+2*(4-3))]", checkLine("double a = -(3+2*(4-3))"));
        assertEquals("[write, (, a, )]", checkLine("write(a)"));
        assertEquals("[double, a, []]", checkLine("double a[]"));
        assertEquals("[double, [], a]", checkLine("double[] a"));
        assertEquals("[write, (, a, [5], )]", checkLine("write(a[5])"));
        assertEquals("[write, (, a, [3+2], )]", checkLine("write(a[3+2])"));
        assertEquals("[write, (, a, [b[3+2]], )]", checkLine("write(a[b[3+2]])"));
        assertEquals("[double, a, ,, b]", checkLine("double a, b"));
    }

    private String checkLine(String line) {
        return stringify(LineTokenizer.tokenize(line));
    }

    private String stringify(List<String> list) {
        return Arrays.toString(list.toArray(new String[0]));
    }
}