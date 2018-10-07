package io.github.slupik.schemablock.parser.math.rpn;

import io.github.slupik.schemablock.parser.math.rpn.pattern.InvalidArgumentsException;
import io.github.slupik.schemablock.parser.math.rpn.pattern.UnsupportedValueException;
import io.github.slupik.schemablock.parser.math.rpn.value.NotFoundTypeException;
import io.github.slupik.schemablock.parser.math.rpn.value.Value;
import org.junit.jupiter.api.Test;

import static java.lang.Math.sqrt;
import static org.junit.jupiter.api.Assertions.*;

/**
 * All rights reserved & copyright ©
 */
class MathCalculationTest {

    @Test
    void getResult() throws InvalidArgumentsException, UnsupportedValueException, NotFoundTypeException {
        assertEquals(5, MathCalculation.getResult("5"));
        assertEquals(2+3, MathCalculation.getResult("2+3"));
        assertEquals(2+3, MathCalculation.getResult("2 + 3"));
        assertEquals(2%3, MathCalculation.getResult("2%3"));
        assertEquals(5.43, MathCalculation.getResult("2.43+3"));
        assertEquals(5.43, MathCalculation.getResult("   2.43  +   3  "));
        assertEquals(2.43+ sqrt(3), MathCalculation.getResult("2.43+sqrt(3)"));
        assertEquals(2.43+ sqrt(3), MathCalculation.getResult("  2.43  + sqrt( 3   )   "));
        assertEquals(2.43+ sqrt(sqrt(3)), MathCalculation.getResult("2.43+sqrt(sqrt(3))"));
        assertEquals(2.43+ sqrt(sqrt(3)), MathCalculation.getResult("2.43  +    sqrt  (  sqrt  (  3  )  )  "));
        assertEquals(2.43+ sqrt(sqrt(sqrt(sqrt(3)))), MathCalculation.getResult("2.43+sqrt(sqrt(sqrt(sqrt(3))))"));
        assertEquals(2.43+ sqrt(sqrt(sqrt(sqrt(3)))), MathCalculation.getResult("2.43   +  \t sqrt  ( sqrt (  sqrt (  sqrt  ( 3) ))  )"));
        assertEquals(2.43*(2-1)+ sqrt(sqrt(sqrt(sqrt((3*4-2))))), MathCalculation.getResult("2.43*(2-1)   +  \t sqrt  ( sqrt (  sqrt (  sqrt  ( 3*4-2) ))  )"));
        assertEquals(1+ sqrt(sqrt(sqrt(sqrt((3*4-2))))), MathCalculation.getResult("1+sqrt  ( sqrt (  sqrt (  sqrt  ( 3*4-2) ))  )"));
        assertEquals(sqrt(sqrt(sqrt(sqrt((3*4-2))))), MathCalculation.getResult("sqrt(sqrt(sqrt(sqrt(3*4-2))))"));
        assertEquals(sqrt(sqrt(sqrt(sqrt((3*4-2))))), MathCalculation.getResult("sqrt  ( sqrt (  sqrt (  sqrt  ( 3*4-2) ))  )"));

        assertEquals(2+3+4+10d, MathCalculation.getResult("sum(2, 3, 4, 10)"));
        assertEquals(sqrt(sqrt(3)), MathCalculation.getResult("sqrt(sqrt(3))"));
        assertEquals(sqrt(sqrt(3)), MathCalculation.getResult("sqrt(sqrt(1+2))"));
        assertEquals(2+ sqrt(sqrt(3))+4+10, MathCalculation.getResult("sum(2, sqrt(sqrt(1+2)), 4, 10)"));

        assertEquals(Math.sqrt(Math.sqrt(2+3+4+10)), MathCalculation.getResult("sqrt(sqrt(sum(2,3,4,10)))"));
        assertEquals(2.43+ sqrt(3), MathCalculation.getResult("2.43  +    sqrt  (3)  "));
        assertEquals(2.43+ sqrt(2+3+4+10), MathCalculation.getResult("2.43  +    sqrt  (  sum(2, 3, 4, 10)  )  "));

        assertEquals(sqrt(3), MathCalculation.getResult("sqrt(3)"));
        assertEquals(sqrt(2+3+4+10), MathCalculation.getResult("sqrt(sum(2,3,4,10))"));
        assertEquals(sqrt(2+3.53+4+10), MathCalculation.getResult("sqrt(sum(2,3.53,4,10))"));
        assertEquals(sqrt(2+3.53+sqrt(7)+10), MathCalculation.getResult("sqrt(sum(2,3.53,sqrt(7),10))"));
        assertEquals(sqrt(2+3.53+sqrt(4+3+1-2)+10), MathCalculation.getResult("sqrt(sum(2,3.53,sqrt(4+3+1-2),10))"));

        assertEquals(-3, MathCalculation.getResult("-3"));
        assertEquals(-3 + -5 + 3 + -45 + -4.32, MathCalculation.getResult("sum(-3, -5, 3, -45, -4.32)"));
        assertEquals(sqrt(-2+3.53+sqrt(4+3+1-2)+10), MathCalculation.getResult("sqrt(sum(-2,3.53,sqrt(4+3+1-2),10))"));

        assertEquals(4 * sqrt ( 3 ) + 3, MathCalculation.getResult("4 * sqrt ( 3 ) + 3"));
        assertEquals(0-1+(2*1)+3/(4-1), MathCalculation.getResult("0-1+(2*1)+3/(4-1)"));

        assertEquals(-3, MathCalculation.getResult("sum((-3))"));
        //FIXME priority: LOW
//        assertEquals(sqrt(3), MathCalculation.getResult("sqrt((3))"));


        assertEquals(3<<5, MathCalculation.getResult("3<<5"));
        assertEquals(3>>5, MathCalculation.getResult("3>>5"));
        assertEquals(3^5, MathCalculation.getResult("3^5"));
        assertEquals(3|5, MathCalculation.getResult("3|5"));
        assertEquals(3&5, MathCalculation.getResult("3&5"));
        assertEquals(~3, MathCalculation.getResult("~3"));
        assertEquals(~~3, MathCalculation.getResult("~~3"));
        assertEquals(~~~3, MathCalculation.getResult("~~~3"));

        assertTrue((Boolean) MathCalculation.getResult("3<5"));
        assertTrue((Boolean) MathCalculation.getResult("5>3"));
        assertTrue((Boolean) MathCalculation.getResult("3==3"));
        assertTrue((Boolean) MathCalculation.getResult("3!=5"));
        assertTrue((Boolean) MathCalculation.getResult("3<=5"));
        assertTrue((Boolean) MathCalculation.getResult("5>=3"));

        assertEquals("foo"+"bar", MathCalculation.getResult("\"foo\"+\"bar\""));
        assertEquals("foo"+"bar"+" Lorem", MathCalculation.getResult("\"foo\"+\"bar\"+\" Lorem\""));

        assertTrue((Boolean) MathCalculation.getResult("true&&true"));
        assertFalse((Boolean) MathCalculation.getResult("false&&false"));
        assertFalse((Boolean) MathCalculation.getResult("true&&false"));
        assertFalse((Boolean) MathCalculation.getResult("true==false"));
        assertTrue((Boolean) MathCalculation.getResult("true!=false"));
        assertTrue((Boolean) MathCalculation.getResult("true||false"));
        assertTrue((Boolean) MathCalculation.getResult("false||true"));
        assertEquals(true==false==true, MathCalculation.getResult("true==false==true"));
        assertEquals(true&&false&&true, MathCalculation.getResult("true&&false&&true"));

        assertTrue((Boolean) MathCalculation.getResult("!!!false"));

        assertTrue(((Boolean) MathCalculation.getResult("!false")));
    }

    private static void keepImports() throws NotFoundTypeException {
        double sqrt = sqrt(10);
        assertEquals(1, 1);
        assertTrue(true);
        new Value("");
    }
}