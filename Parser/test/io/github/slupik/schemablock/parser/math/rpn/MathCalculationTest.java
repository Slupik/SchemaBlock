package io.github.slupik.schemablock.parser.math.rpn;

import io.github.slupik.schemablock.parser.math.rpn.pattern.InvalidArgumentsException;
import io.github.slupik.schemablock.parser.math.rpn.pattern.UnsupportedValueException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * All rights reserved & copyright ©
 */
class MathCalculationTest {

    @Test
    void getResult() throws InvalidArgumentsException, UnsupportedValueException {
        assertEquals(5, MathCalculation.getResult("2+3"));
        assertEquals(5, MathCalculation.getResult("2 + 3"));
        assertEquals(5.43, MathCalculation.getResult("2.43+3"));
        assertEquals(5.43, MathCalculation.getResult("   2.43  +   3  "));
        assertEquals(2.43+Math.sqrt(3), MathCalculation.getResult("2.43+sqrt(3)"));
        assertEquals(2.43+Math.sqrt(3), MathCalculation.getResult("  2.43  + sqrt( 3   )   "));
        assertEquals(2.43+Math.sqrt(Math.sqrt(3)), MathCalculation.getResult("2.43+sqrt(sqrt(3))"));
        assertEquals(2.43+Math.sqrt(Math.sqrt(3)), MathCalculation.getResult("2.43  +    sqrt  (  sqrt  (  3  )  )  "));
        assertEquals(2.43+Math.sqrt(Math.sqrt(Math.sqrt(Math.sqrt(3)))), MathCalculation.getResult("2.43+sqrt(sqrt(sqrt(sqrt(3))))"));
        assertEquals(2.43+Math.sqrt(Math.sqrt(Math.sqrt(Math.sqrt(3)))), MathCalculation.getResult("2.43   +  \t sqrt  ( sqrt (  sqrt (  sqrt  ( 3) ))  )"));
        assertEquals(2.43*(2-1)+Math.sqrt(Math.sqrt(Math.sqrt(Math.sqrt((3*4-2))))), MathCalculation.getResult("2.43*(2-1)   +  \t sqrt  ( sqrt (  sqrt (  sqrt  ( (3*4-2)) ))  )"));
    }
}