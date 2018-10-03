package io.github.slupik.schemablock.parser.math.parser;

import io.github.slupik.schemablock.parser.math.pattern.PatternFinder;
import io.github.slupik.schemablock.parser.math.pattern.specific.MathPatternSqrt;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * All rights reserved & copyright ©
 */
class MathParserTest {

    @Test
    void testSqrt(){
        PatternFinder finder = new PatternFinder();
        finder.registerPattern(new MathPatternSqrt());

        assertEquals(1.7320508075688772, new MathParser(finder, "sqrt(3)").getValue());
        assertEquals(1.3160740129524924, new MathParser(finder, "sqrt(1.7320508075688772)").getValue());
        assertEquals(1.3160740129524924, new MathParser(finder, "sqrt(sqrt(3))").getValue());
        assertEquals(1.147202690439877, new MathParser(finder, "sqrt(sqrt("+new MathParser(finder, "sqrt(3)").getValue()+"))").getValue());

        //TODO addition
//        assertEquals(5, new MathParser(finder, "add(3, 2)").getValue());
    }

    @Test
    void getName() {
        assertEquals("sqrt", MathParser.getName("sqrt(3)"));
        assertEquals("sqrt", MathParser.getName("sqrt (3)"));
        assertEquals("sqrt", MathParser.getName("sq rt (3)"));
        assertEquals("sqrt", MathParser.getName("s\tq rt(3)"));
        assertEquals("sqrt", MathParser.getName("s\tq rt (3)"));
    }

}