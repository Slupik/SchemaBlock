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

import static java.lang.Math.sqrt;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * All rights reserved & copyright ©
 */
public class TrueTriangularPyramid {

    @BeforeEach
    void restartEnvironment(){
        CodeParser.clearHeap();
    }

    @Test
    void checkStandard() throws IncompatibleTypeException, InvalidArgumentsException, UnsupportedValueException, VariableIsAlreadyDefinedException, VariableNotFound, WrongArgumentException, NotFoundTypeException {
        double a = 7;

        CodeParser.execute(
                "double a="+a+";"
        );
        assertEquals(a, CodeParser.getHeap().getVariable("a").getAsDouble());

        Object isValidSideLength = MathCalculation.getResult(CodeParser.getHeap(), "a>0");
        assertEquals(a>0, isValidSideLength);


        CodeParser.execute(
                "double pp = a*a*a*sqrt(3)/4;"+
                        "double pc = 4*pp;"+
                        "double V = pow(a, 3)*sqrt(2)/12;"
        );
        assertEquals(field(a), CodeParser.getHeap().getVariable("pc").getAsDouble());
        assertEquals(volume(a), CodeParser.getHeap().getVariable("V").getAsDouble());
    }

    private double volume(double a) {
        return a*a*a*sqrt(2)/12;
    }

    private double field(double a) {
        double pp = a*a*a*sqrt(3)/4;
        return 4*pp;
    }
}
