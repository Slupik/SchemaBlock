package io.github.slupik.schemablock.model.ui.implementation;

import io.github.slupik.schemablock.model.ui.abstraction.element.ConditionalElement;
import io.github.slupik.schemablock.model.ui.abstraction.element.Element;
import io.github.slupik.schemablock.model.ui.abstraction.element.OperationElement;
import io.github.slupik.schemablock.model.ui.abstraction.element.StartElement;
import io.github.slupik.schemablock.model.ui.implementation.container.NextElementNotFound;
import io.github.slupik.schemablock.model.ui.implementation.container.DefaultElementContainer;
import io.github.slupik.schemablock.model.ui.implementation.element.specific.OperationBlock;
import io.github.slupik.schemablock.model.ui.implementation.element.specific.ConditionBlock;
import io.github.slupik.schemablock.model.ui.implementation.element.specific.StartBlock;
import io.github.slupik.schemablock.model.ui.implementation.element.specific.StopBlock;
import io.github.slupik.schemablock.model.ui.implementation.container.StartBlockNotFound;
import io.github.slupik.schemablock.parser.code.CodeParser;
import io.github.slupik.schemablock.parser.code.IncompatibleTypeException;
import io.github.slupik.schemablock.parser.code.VariableNotFound;
import io.github.slupik.schemablock.parser.code.WrongArgumentException;
import io.github.slupik.schemablock.parser.math.rpn.pattern.InvalidArgumentsException;
import io.github.slupik.schemablock.parser.math.rpn.pattern.UnsupportedValueException;
import io.github.slupik.schemablock.parser.math.rpn.variable.VariableIsAlreadyDefinedException;
import io.github.slupik.schemablock.parser.math.rpn.variable.value.NotFoundTypeException;
import org.junit.jupiter.api.Test;

import static java.lang.Math.sqrt;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * All rights reserved & copyright ©
 */
class DefaultElementContainerTest {

    @Test
    void basicRun() throws StartBlockNotFound, NotFoundTypeException, VariableNotFound, WrongArgumentException, UnsupportedValueException, NextElementNotFound, IncompatibleTypeException, VariableIsAlreadyDefinedException, InvalidArgumentsException {
        double a = 43.23;
        int b = 10;

        DefaultElementContainer container = new DefaultElementContainer();

        StartElement start = new StartBlock();
        OperationElement calc = new OperationBlock();
        calc.setContent("double a = " + a + ";" +
                "int b = " + b + ";");
        Element stop = new StopBlock();

        start.setNextElement(calc.getId());
        calc.setNextElement(stop.getId());

        container.addElement(start);
        container.addElement(calc);
        container.addElement(stop);

        container.run();

        assertEquals(a, CodeParser.getHeap().getVariable("a").getAsDouble());
        assertEquals(b, CodeParser.getHeap().getVariable("b").getAsInt());
    }

    @Test
    void factorialRun() throws StartBlockNotFound, NotFoundTypeException, VariableNotFound, WrongArgumentException, UnsupportedValueException, NextElementNotFound, IncompatibleTypeException, VariableIsAlreadyDefinedException, InvalidArgumentsException {
        int source = 5;

        DefaultElementContainer container = new DefaultElementContainer();

        StartElement start = new StartBlock();

        OperationElement init = new OperationBlock();
        init.setContent("long    value =     1;" +
                "int     source     ="+source+";" +
                "int i =    0;");

        ConditionalElement zeroCheck = new ConditionBlock();
        zeroCheck.setContent("source!=0");

        OperationElement calculateIfZero = new OperationBlock();
        calculateIfZero.setContent("value = 1;");

        ConditionalElement loopCondition = new ConditionBlock();
        loopCondition.setContent("i<   source");

        OperationElement calculateInLoop = new OperationBlock();
        calculateInLoop.setContent("i     = i + 1;"+
                        "value = value * i;");

        Element stop = new StopBlock();

        start.setNextElement(init.getId());
        init.setNextElement(zeroCheck.getId());
        zeroCheck.setOnFalse(calculateIfZero.getId());
        zeroCheck.setOnTrue(loopCondition.getId());
        loopCondition.setOnTrue(calculateInLoop.getId());
        calculateInLoop.setNextElement(loopCondition.getId());
        loopCondition.setOnFalse(stop.getId());

        container.addElement(start);
        container.addElement(init);
        container.addElement(zeroCheck);
        container.addElement(calculateIfZero);
        container.addElement(loopCondition);
        container.addElement(calculateInLoop);
        container.addElement(stop);

        container.run();
        assertEquals(factorial(source), CodeParser.getHeap().getVariable("value").getAsLong());
    }

    private static long factorial(long i) {
        if (i < 1)
            return 1;
        else
            return i * factorial(i - 1);
    }

    @Test
    void runNWDandNWW() throws StartBlockNotFound, NotFoundTypeException, VariableNotFound, WrongArgumentException, UnsupportedValueException, NextElementNotFound, IncompatibleTypeException, VariableIsAlreadyDefinedException, InvalidArgumentsException {
        int a = 172;
        int b = 20;

        DefaultElementContainer container = new DefaultElementContainer();

        StartElement start = new StartBlock();

        OperationElement init = new OperationBlock();
        init.setContent("int a, b, nww, nwd, pom, ab;" +
                "a="+a+";" +
                "b="+b+";" +
                "ab=a*b;");

        ConditionalElement loopCondition = new ConditionBlock();
        loopCondition.setContent("b!=0");

        OperationElement calculateInLoop = new OperationBlock();
        calculateInLoop.setContent(
                "pom=b;" +
                "b=a%b;" +
                "a=pom;");

        OperationElement calculateNWW = new OperationBlock();
        calculateNWW.setContent(
                "nwd=a;" +
                "nww=ab/nwd;"
        );

        Element stop = new StopBlock();

        start.setNextElement(init.getId());
        init.setNextElement(loopCondition.getId());
        loopCondition.setOnTrue(calculateInLoop.getId());
        calculateInLoop.setNextElement(loopCondition.getId());
        loopCondition.setOnFalse(calculateNWW.getId());
        calculateNWW.setNextElement(stop.getId());

        container.addElement(start);
        container.addElement(init);
        container.addElement(loopCondition);
        container.addElement(calculateInLoop);
        container.addElement(calculateNWW);
        container.addElement(stop);

        container.run();

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

    @Test
    void runTrueTriangularPyramid() throws StartBlockNotFound, NotFoundTypeException, VariableNotFound, WrongArgumentException, UnsupportedValueException, NextElementNotFound, IncompatibleTypeException, VariableIsAlreadyDefinedException, InvalidArgumentsException {
        double a = 7;
        DefaultElementContainer container = new DefaultElementContainer();

        StartElement start = new StartBlock();

        OperationElement init = new OperationBlock();
        init.setContent("double a="+a+";");

        ConditionalElement validation = new ConditionBlock();
        validation.setContent("a>0");

        OperationElement mainCalc = new OperationBlock();
        mainCalc.setContent(
                "double pp = a*a*a*sqrt(3)/4;"+
                        "double pc = 4*pp;"+
                        "double V = pow(a, 3)*sqrt(2)/12;"
        );

        Element stop = new StopBlock();

        start.setNextElement(init.getId());
        init.setNextElement(validation.getId());
        validation.setOnTrue(mainCalc.getId());
        mainCalc.setNextElement(stop.getId());
        validation.setOnFalse(stop.getId());

        container.addElement(start);
        container.addElement(init);
        container.addElement(validation);
        container.addElement(mainCalc);
        container.addElement(stop);

        container.run();

        assertEquals(field(a), CodeParser.getHeap().getVariable("pc").getAsDouble());
        assertEquals(volume(a), CodeParser.getHeap().getVariable("V").getAsDouble());
    }

    private double volume(double a) {
        return a*a*a*sqrt(2)/12;
    }

    private double field(double a) {
        double pp = a*a*a*sqrt(3)/4;
        return 4*pp;
    }    @Test

    void checkStringify() throws StartBlockNotFound, NotFoundTypeException, VariableNotFound, WrongArgumentException, UnsupportedValueException, NextElementNotFound, IncompatibleTypeException, VariableIsAlreadyDefinedException, InvalidArgumentsException {
        double a = 7;
        DefaultElementContainer container = new DefaultElementContainer();

        StartElement start = new StartBlock();

        OperationElement init = new OperationBlock();
        init.setContent("double a="+a+";");

        ConditionalElement validation = new ConditionBlock();
        validation.setContent("a>0");

        OperationElement mainCalc = new OperationBlock();
        mainCalc.setContent(
                "double pp = a*a*a*sqrt(3)/4;"+
                        "double pc = 4*pp;"+
                        "double V = pow(a, 3)*sqrt(2)/12;"
        );

        Element stop = new StopBlock();

        start.setNextElement(init.getId());
        init.setNextElement(validation.getId());
        validation.setOnTrue(mainCalc.getId());
        mainCalc.setNextElement(stop.getId());
        validation.setOnFalse(stop.getId());

        container.addElement(start);
        container.addElement(init);
        container.addElement(validation);
        container.addElement(mainCalc);
        container.addElement(stop);

        container.run();

        assertEquals(field(a), CodeParser.getHeap().getVariable("pc").getAsDouble());
        assertEquals(volume(a), CodeParser.getHeap().getVariable("V").getAsDouble());

        //Restore

        String saved = container.stringify();
        DefaultElementContainer containerCopy = new DefaultElementContainer();
        containerCopy.load(saved);

        container.run();

        assertEquals(field(a), CodeParser.getHeap().getVariable("pc").getAsDouble());
        assertEquals(volume(a), CodeParser.getHeap().getVariable("V").getAsDouble());
    }
}