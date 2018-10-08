package io.github.slupik.schemablock.parser.code;

import io.github.slupik.schemablock.parser.math.rpn.pattern.InvalidArgumentsException;
import io.github.slupik.schemablock.parser.math.rpn.pattern.UnsupportedValueException;
import io.github.slupik.schemablock.parser.math.rpn.variable.VariableIsAlreadyDefinedException;
import io.github.slupik.schemablock.parser.math.rpn.variable.value.NotFoundTypeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * All rights reserved & copyright ©
 */
class CodeParserTest {

    @Test
    void execute() throws InvalidArgumentsException, UnsupportedValueException, VariableIsAlreadyDefinedException, VariableNotFound, WrongArgumentException, NotFoundTypeException {
//        CodeParser.execute("double a = 3;");

//        CodeParser.execute("double a = 3;" +
//                "double b = 3;");

//        CodeParser.execute("double a = 3;" +
//                "double b = a;");

//        CodeParser.execute("double a = 3;" +
//                "double b = a;");

        //FIXME Priority: HIGH
        CodeParser.execute("double b = a = 3;");
    }

    private static void keepImports(){
        assertEquals(true, true);
    }
}