package io.github.slupik.schemablock.newparser.utils;

import io.github.slupik.schemablock.newparser.compilator.implementation.Token;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.github.slupik.schemablock.newparser.executor.implementation.ByteCommandOperationMocker.mockByteCommandOperation;
import static io.github.slupik.schemablock.newparser.memory.element.ValueType.*;

/**
 * All rights reserved & copyright ©
 */
class TypeParserTest {

    @Test
    void checkResults() throws ValueTooBig {
        Assertions.assertEquals(INTEGER, TypeParser.getType("1", mockByteCommandOperation()));
        Assertions.assertEquals(LONG, TypeParser.getType("1542352525235523", mockByteCommandOperation()));
        Assertions.assertEquals(DOUBLE, TypeParser.getType("1.0", mockByteCommandOperation()));

        Assertions.assertEquals(INTEGER, TypeParser.getType("1i", mockByteCommandOperation()));
        Assertions.assertEquals(INTEGER, TypeParser.getType("1.0i", mockByteCommandOperation()));
        Assertions.assertEquals(SHORT, TypeParser.getType("1s", mockByteCommandOperation()));
        Assertions.assertEquals(LONG, TypeParser.getType("1l", mockByteCommandOperation()));
        Assertions.assertEquals(FLOAT, TypeParser.getType("1f", mockByteCommandOperation()));
        Assertions.assertEquals(DOUBLE, TypeParser.getType("1d", mockByteCommandOperation()));

        Assertions.assertEquals(BOOLEAN, TypeParser.getType("true", mockByteCommandOperation()));
        Assertions.assertEquals(BOOLEAN, TypeParser.getType("false", mockByteCommandOperation()));

        Assertions.assertEquals(STRING, TypeParser.getType("\"\"", mockByteCommandOperation()));
        Assertions.assertEquals(STRING, TypeParser.getType("\"aaaaa\"", mockByteCommandOperation()));
        Assertions.assertEquals(STRING, TypeParser.getType("\"5\"", mockByteCommandOperation()));

        Assertions.assertThrows(ValueTooBig.class, () -> TypeParser.getType("99999999999999999999999999999999999999999999999999999", mockByteCommandOperation()));

        Assertions.assertEquals(UNKNOWN, TypeParser.getType("variable", mockByteCommandOperation()));

        Assertions.assertEquals(INTEGER, TypeParser.getType(new Token("1", -1, -1)));
    }
}