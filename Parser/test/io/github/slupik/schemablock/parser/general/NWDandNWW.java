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
public class NWDandNWW {

    @BeforeEach
    void restartEnvironment(){
        CodeParser.clearHeap();
    }

    @Test
    void checkStandard() throws IncompatibleTypeException, InvalidArgumentsException, UnsupportedValueException, VariableIsAlreadyDefinedException, VariableNotFound, WrongArgumentException, NotFoundTypeException {
        int a = 172;
        int b = 20;
        CodeParser.execute("int a, b, nww, nwd, pom, ab;" +
                "a="+a+";" +
                "b="+b+";" +
                "ab=a*b;"
        );
        assertEquals(a, CodeParser.getHeap().getVariable("a").getAsInt());
        assertEquals(b, CodeParser.getHeap().getVariable("b").getAsInt());

        Object result = MathCalculation.getResult(CodeParser.getHeap(), "b!=0");
        assertTrue(((Boolean) result));

        while(((Boolean) result)) {
            CodeParser.execute(
                    "pom=b;" +
                    "b=a%b;" +
                    "a=pom;"
            );
            result = MathCalculation.getResult(CodeParser.getHeap(), "b!=0");
        }

        CodeParser.execute(
                "nwd=a;" +
                        "nww=ab/nwd;"
        );
        assertEquals(nwd(a, b), CodeParser.getHeap().getVariable("nwd").getAsInt());
        assertEquals(nww(a, b), CodeParser.getHeap().getVariable("nww").getAsInt());
    }

    private static int nww(int a, int b) {
        return a*b/nwd(a, b);
    }

    private static int nwd(int a, int b) {
        while (a != b) {
            if (a > b)
                a -= b;
            else
                b -= a;
        }
        return a;
    }
}
