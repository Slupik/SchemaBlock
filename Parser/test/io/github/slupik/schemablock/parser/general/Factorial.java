package io.github.slupik.schemablock.parser.general;

import io.github.slupik.schemablock.parser.code.CodeParser;
import io.github.slupik.schemablock.parser.code.IncompatibleTypeException;
import io.github.slupik.schemablock.parser.code.VariableNotFound;
import io.github.slupik.schemablock.parser.code.WrongArgumentException;
import io.github.slupik.schemablock.parser.math.rpn.MathCalculation;
import io.github.slupik.schemablock.parser.math.rpn.pattern.InvalidArgumentsException;
import io.github.slupik.schemablock.parser.math.rpn.pattern.UnsupportedValueException;
import io.github.slupik.schemablock.parser.math.rpn.variable.VariableIsAlreadyDefinedException;
import io.github.slupik.schemablock.parser.math.rpn.variable.value.NotFoundTypeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * All rights reserved & copyright ©
 */
public class Factorial {

    @BeforeEach
    void restartEnvironment(){
        CodeParser.clearHeap();
    }

    @Test
    void checkStandard() throws IncompatibleTypeException, InvalidArgumentsException, UnsupportedValueException, VariableIsAlreadyDefinedException, VariableNotFound, WrongArgumentException, NotFoundTypeException {
        int source = 5;
        assertTrue(source>=0);

        CodeParser.execute("long value = 1;" +
                "int source="+source+";" +
                "int i = 0;"
        );
        assertEquals(source, CodeParser.getHeap().getVariable("source").getAsInt());

        Object isNotZero = MathCalculation.getResult(CodeParser.getHeap(), "source!=0");
        assertEquals(source!=0, isNotZero);

        if(((Boolean) isNotZero)) {
            Object result;
            do {
                CodeParser.execute(
                        "i=i+1;"+
                                "value = value*i;"
                );
                result = MathCalculation.getResult(CodeParser.getHeap(), "i<source");
            }while(((Boolean) result));
        }
        assertEquals(factorial(source), CodeParser.getHeap().getVariable("value").getAsLong());
    }

    private static long factorial(long i) {
        if (i < 1)
            return 1;
        else
            return i * factorial(i - 1);
    }
}
