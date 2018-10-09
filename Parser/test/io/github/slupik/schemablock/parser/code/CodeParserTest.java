package io.github.slupik.schemablock.parser.code;

import io.github.slupik.schemablock.parser.math.rpn.pattern.InvalidArgumentsException;
import io.github.slupik.schemablock.parser.math.rpn.pattern.UnsupportedValueException;
import io.github.slupik.schemablock.parser.math.rpn.variable.VariableIsAlreadyDefinedException;
import io.github.slupik.schemablock.parser.math.rpn.variable.value.NotFoundTypeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * All rights reserved & copyright ©
 */
class CodeParserTest {

    @Test
    void execute() throws InvalidArgumentsException, UnsupportedValueException, VariableIsAlreadyDefinedException, VariableNotFound, WrongArgumentException, NotFoundTypeException, IncompatibleTypeException {
        CodeParser.execute("double a = 3;");

        CodeParser.clearHeap();
        CodeParser.execute("double a = 3;" +
                "double b = 3;");

        CodeParser.clearHeap();
        CodeParser.execute("double a = 3;" +
                "double b = a;");

        CodeParser.clearHeap();
        CodeParser.execute("double a = 3;" +
                "double b = a;");

        CodeParser.clearHeap();
        CodeParser.execute("double a = 5;" +
                "double b = a = 3;");

        CodeParser.clearHeap();
        CodeParser.execute("double a, b;" +
                "b = a = 3;");

        CodeParser.clearHeap();
        assertThrows(IncompatibleTypeException.class,
                () -> CodeParser.execute("double a, b;" +
            "b = a = true;"));
    }

    private static void keepImports(){
        assertEquals(true, true);
        assertThrows(Exception.class, () -> {});
    }
}