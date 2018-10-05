package io.github.slupik.schemablock.parser.math.rpn;

import io.github.slupik.schemablock.parser.math.rpn.pattern.InvalidArgumentsException;
import io.github.slupik.schemablock.parser.math.rpn.pattern.UnsupportedValueException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * All rights reserved & copyright ©
 */
class RpnCalculationTest {

    @Test
    void calculate() {
        try {
            List<String> tokens1 = new ArrayList<>();
            tokens1.add("3");
            tokens1.add("sqrt;1");
//            assertEquals(1.7320508075688772, RpnCalculation.calculate(tokens1));


            List<String> tokens2 = new ArrayList<>();
            tokens2.add("3");
            tokens2.add("sqrt;1");
            tokens2.add("1");
            tokens2.add("-");
            assertEquals(0.7320508075688772, RpnCalculation.calculate(tokens2));
        } catch (Exception | InvalidArgumentsException | UnsupportedValueException e) {
            e.printStackTrace();
        }
    }
}