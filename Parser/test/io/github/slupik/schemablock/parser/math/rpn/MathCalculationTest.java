package io.github.slupik.schemablock.parser.math.rpn;

import io.github.slupik.schemablock.parser.math.rpn.pattern.InvalidArgumentsException;
import io.github.slupik.schemablock.parser.math.rpn.pattern.UnsupportedValueException;
import org.junit.jupiter.api.Test;

import static java.lang.Math.sqrt;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * All rights reserved & copyright ©
 */
class MathCalculationTest {

    @Test
    void getResult() throws InvalidArgumentsException, UnsupportedValueException {
//        assertEquals(5, MathCalculation.getResult("2+3"));
//        assertEquals(5, MathCalculation.getResult("2 + 3"));
//        assertEquals(5.43, MathCalculation.getResult("2.43+3"));
//        assertEquals(5.43, MathCalculation.getResult("   2.43  +   3  "));
//        assertEquals(2.43+ sqrt(3), MathCalculation.getResult("2.43+sqrt(3)"));
//        assertEquals(2.43+ sqrt(3), MathCalculation.getResult("  2.43  + sqrt( 3   )   "));
//        assertEquals(2.43+ sqrt(sqrt(3)), MathCalculation.getResult("2.43+sqrt(sqrt(3))"));
//        assertEquals(2.43+ sqrt(sqrt(3)), MathCalculation.getResult("2.43  +    sqrt  (  sqrt  (  3  )  )  "));
//        assertEquals(2.43+ sqrt(sqrt(sqrt(sqrt(3)))), MathCalculation.getResult("2.43+sqrt(sqrt(sqrt(sqrt(3))))"));
//        assertEquals(2.43+ sqrt(sqrt(sqrt(sqrt(3)))), MathCalculation.getResult("2.43   +  \t sqrt  ( sqrt (  sqrt (  sqrt  ( 3) ))  )"));
//        assertEquals(2.43*(2-1)+ sqrt(sqrt(sqrt(sqrt((3*4-2))))), MathCalculation.getResult("2.43*(2-1)   +  \t sqrt  ( sqrt (  sqrt (  sqrt  ( 3*4-2) ))  )"));
//        assertEquals(1+ sqrt(sqrt(sqrt(sqrt((3*4-2))))), MathCalculation.getResult("1+sqrt  ( sqrt (  sqrt (  sqrt  ( 3*4-2) ))  )"));
//        assertEquals(sqrt(sqrt(sqrt(sqrt((3*4-2))))), MathCalculation.getResult("sqrt(sqrt(sqrt(sqrt(3*4-2))))"));
//        assertEquals(sqrt(sqrt(sqrt(sqrt((3*4-2))))), MathCalculation.getResult("sqrt  ( sqrt (  sqrt (  sqrt  ( 3*4-2) ))  )"));
//
//        assertEquals(2+3+4+10, MathCalculation.getResult("sum(2, 3, 4, 10)"));
//        assertEquals(2+ sqrt(sqrt(3))+4+10, MathCalculation.getResult("sum(2, sqrt(sqrt(1+2)), 4, 10)"));

        //FIXME bug with nested functions with multiple arguments
        assertEquals(Math.sqrt(Math.sqrt(2+3+4+10)), MathCalculation.getResult("sqrt(sum(2,3,4,10))"));
//        assertEquals(2.43+ sqrt(sqrt(2+3+4+10)), MathCalculation.getResult("2.43  +    sqrt  (  sum(2, 3, 4, 10)  )  "));
    }

    private static void keepInports(){
        double sqrt = sqrt(10);
        assertEquals(1, 1);
    }
}