package io.github.slupik.schemablock.newparser.utils;

import io.github.slupik.schemablock.newparser.compilator.implementation.Token;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.github.slupik.schemablock.newparser.memory.element.ValueType.*;

/**
 * All rights reserved & copyright ©
 */
class TypeParserTest {

    @Test
    void checkResults() throws ValueTooBig {
        Assertions.assertEquals(INTEGER, TypeParser.getType("1"));
        Assertions.assertEquals(LONG, TypeParser.getType("1542352525235523"));
        Assertions.assertEquals(DOUBLE, TypeParser.getType("1.0"));

        Assertions.assertEquals(INTEGER, TypeParser.getType("1i"));
        Assertions.assertEquals(INTEGER, TypeParser.getType("1.0i"));
        Assertions.assertEquals(SHORT, TypeParser.getType("1s"));
        Assertions.assertEquals(LONG, TypeParser.getType("1l"));
        Assertions.assertEquals(FLOAT, TypeParser.getType("1f"));
        Assertions.assertEquals(DOUBLE, TypeParser.getType("1d"));

        Assertions.assertEquals(BOOLEAN, TypeParser.getType("true"));
        Assertions.assertEquals(BOOLEAN, TypeParser.getType("false"));

        Assertions.assertEquals(STRING, TypeParser.getType("\"\""));
        Assertions.assertEquals(STRING, TypeParser.getType("\"aaaaa\""));
        Assertions.assertEquals(STRING, TypeParser.getType("\"5\""));

        Assertions.assertThrows(ValueTooBig.class, () -> TypeParser.getType("99999999999999999999999999999999999999999999999999999"));

        Assertions.assertEquals(UNKNOWN, TypeParser.getType("variable"));

        Assertions.assertEquals(INTEGER, TypeParser.getType(new Token("1", -1, -1)));
    }
}