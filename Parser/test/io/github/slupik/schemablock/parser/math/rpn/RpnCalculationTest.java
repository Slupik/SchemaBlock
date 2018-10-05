package io.github.slupik.schemablock.parser.math.rpn;

import io.github.slupik.schemablock.parser.math.rpn.pattern.InvalidArgumentsException;
import io.github.slupik.schemablock.parser.math.rpn.pattern.UnsupportedValueException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * All rights reserved & copyright ©
 */
class RpnCalculationTest {

    @Test
    void calculate() throws InvalidArgumentsException, UnsupportedValueException {
        checkCase(1.7320508075688772, "3", "sqrt;1");
        checkCase(0.7320508075688772, "3", "sqrt;1", "1", "-");
    }

    private void checkCase(double expected, String... tokens) throws UnsupportedValueException, InvalidArgumentsException {
        checkCase(expected, new ArrayList<>(Arrays.asList(tokens)));
    }

    private void checkCase(double expected, List<String> tokens) throws UnsupportedValueException, InvalidArgumentsException {
        assertEquals(expected, RpnCalculation.calculate(tokens));
    }
}