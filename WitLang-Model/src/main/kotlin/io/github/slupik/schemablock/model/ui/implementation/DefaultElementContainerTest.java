package io.github.slupik.schemablock.model.ui.implementation;

/**
 * All rights reserved & copyright ©
 */
class DefaultElementContainerTest {
//TODO repair tests

//    private Register register = new RegisterImpl();
//    private HeapController heapController = new HeapController() {
//        @Override
//        public void setVariableValue(String name, Value value) {
//
//        }
//
//        @Override
//        public ValueType getVariableType(String name) throws VariableNotFound {
//            return null;
//        }
//    };
//    private Compilator compilator = new DefaultCompilator();
//    private Memory memory = new MemoryImpl();
//    private Executor executor = new ExecutorImpl(compilator, memory, register);
//    private ElementParser elementParser = new ElementParser(executor, heapController);
//
//    @Test
//    void basicRun() throws StartBlockNotFound, NotFoundTypeException, VariableNotFound, WrongArgumentException, UnsupportedValueException, NextElementNotFound, IncompatibleTypeException, VariableIsAlreadyDefinedException, InvalidArgumentsException {
//        double a = 43.23;
//        int b = 10;
//
//        DefaultElementContainer container = new DefaultElementContainer(register, elementParser);
//
//        StartElement start = new StartBlock();
//        OperationElement calc = new OperationBlock(executor);
//        calc.setContent("double a = " + a + ";" +
//                "int b = " + b + ";");
//        Element stop = new StopBlock();
//
//        start.setNextElement(calc.getId());
//        calc.setNextElement(stop.getId());
//
//        container.addElement(start);
//        container.addElement(calc);
//        container.addElement(stop);
//
//        container.run();
//
//        assertEquals(a, CodeParser.getHeap().getVariable("a").getAsDouble());
//        assertEquals(b, CodeParser.getHeap().getVariable("b").getAsInt());
//    }
//
//    @Test
//    void factorialRun() throws StartBlockNotFound, NotFoundTypeException, VariableNotFound, WrongArgumentException, UnsupportedValueException, NextElementNotFound, IncompatibleTypeException, VariableIsAlreadyDefinedException, InvalidArgumentsException {
//        int source = 5;
//
//        DefaultElementContainer container = new DefaultElementContainer(register, elementParser);
//
//        StartElement start = new StartBlock();
//
//        OperationElement init = new OperationBlock(executor);
//        init.setContent("long    value =     1;" +
//                "int     source     ="+source+";" +
//                "int i =    0;");
//
//        ConditionalElement zeroCheck = new ConditionBlock(executor);
//        zeroCheck.setContent("source!=0");
//
//        OperationElement calculateIfZero = new OperationBlock(executor);
//        calculateIfZero.setContent("value = 1;");
//
//        ConditionalElement loopCondition = new ConditionBlock(executor);
//        loopCondition.setContent("i<   source");
//
//        OperationElement calculateInLoop = new OperationBlock(executor);
//        calculateInLoop.setContent("i     = i + 1;"+
//                        "value = value * i;");
//
//        Element stop = new StopBlock();
//
//        start.setNextElement(init.getId());
//        init.setNextElement(zeroCheck.getId());
//        zeroCheck.setOnFalse(calculateIfZero.getId());
//        zeroCheck.setOnTrue(loopCondition.getId());
//        loopCondition.setOnTrue(calculateInLoop.getId());
//        calculateInLoop.setNextElement(loopCondition.getId());
//        loopCondition.setOnFalse(stop.getId());
//
//        container.addElement(start);
//        container.addElement(init);
//        container.addElement(zeroCheck);
//        container.addElement(calculateIfZero);
//        container.addElement(loopCondition);
//        container.addElement(calculateInLoop);
//        container.addElement(stop);
//
//        container.run();
//        assertEquals(factorial(source), CodeParser.getHeap().getVariable("value").getAsLong());
//    }
//
//    private static long factorial(long i) {
//        if (i < 1)
//            return 1;
//        else
//            return i * factorial(i - 1);
//    }
//
//    @Test
//    void runNWDandNWW() throws StartBlockNotFound, NotFoundTypeException, VariableNotFound, WrongArgumentException, UnsupportedValueException, NextElementNotFound, IncompatibleTypeException, VariableIsAlreadyDefinedException, InvalidArgumentsException {
//        int a = 172;
//        int b = 20;
//
//        DefaultElementContainer container = new DefaultElementContainer(register, elementParser);
//
//        StartElement start = new StartBlock();
//
//        OperationElement init = new OperationBlock(executor);
//        init.setContent("int a, b, nww, nwd, pom, ab;" +
//                "a="+a+";" +
//                "b="+b+";" +
//                "ab=a*b;");
//
//        ConditionalElement loopCondition = new ConditionBlock(executor);
//        loopCondition.setContent("b!=0");
//
//        OperationElement calculateInLoop = new OperationBlock(executor);
//        calculateInLoop.setContent(
//                "pom=b;" +
//                "b=a%b;" +
//                "a=pom;");
//
//        OperationElement calculateNWW = new OperationBlock(executor);
//        calculateNWW.setContent(
//                "nwd=a;" +
//                "nww=ab/nwd;"
//        );
//
//        Element stop = new StopBlock();
//
//        start.setNextElement(init.getId());
//        init.setNextElement(loopCondition.getId());
//        loopCondition.setOnTrue(calculateInLoop.getId());
//        calculateInLoop.setNextElement(loopCondition.getId());
//        loopCondition.setOnFalse(calculateNWW.getId());
//        calculateNWW.setNextElement(stop.getId());
//
//        container.addElement(start);
//        container.addElement(init);
//        container.addElement(loopCondition);
//        container.addElement(calculateInLoop);
//        container.addElement(calculateNWW);
//        container.addElement(stop);
//
//        container.run();
//
//        assertEquals(nwd(a, b), CodeParser.getHeap().getVariable("nwd").getAsInt());
//        assertEquals(nww(a, b), CodeParser.getHeap().getVariable("nww").getAsInt());
//    }
//
//    private static int nww(int a, int b) {
//        return a*b/nwd(a, b);
//    }
//
//    private static int nwd(int a, int b) {
//        while (a != b) {
//            if (a > b)
//                a -= b;
//            else
//                b -= a;
//        }
//        return a;
//    }
//
//    @Test
//    void runTrueTriangularPyramid() throws StartBlockNotFound, NotFoundTypeException, VariableNotFound, WrongArgumentException, UnsupportedValueException, NextElementNotFound, IncompatibleTypeException, VariableIsAlreadyDefinedException, InvalidArgumentsException {
//        double a = 7;
//        DefaultElementContainer container = new DefaultElementContainer(register, elementParser);
//
//        StartElement start = new StartBlock();
//
//        OperationElement init = new OperationBlock(executor);
//        init.setContent("double a="+a+";");
//
//        ConditionalElement validation = new ConditionBlock(executor);
//        validation.setContent("a>0");
//
//        OperationElement mainCalc = new OperationBlock(executor);
//        mainCalc.setContent(
//                "double pp = a*a*a*sqrt(3)/4;"+
//                        "double pc = 4*pp;"+
//                        "double V = pow(a, 3)*sqrt(2)/12;"
//        );
//
//        Element stop = new StopBlock();
//
//        start.setNextElement(init.getId());
//        init.setNextElement(validation.getId());
//        validation.setOnTrue(mainCalc.getId());
//        mainCalc.setNextElement(stop.getId());
//        validation.setOnFalse(stop.getId());
//
//        container.addElement(start);
//        container.addElement(init);
//        container.addElement(validation);
//        container.addElement(mainCalc);
//        container.addElement(stop);
//
//        container.run();
//
//        assertEquals(field(a), CodeParser.getHeap().getVariable("pc").getAsDouble());
//        assertEquals(volume(a), CodeParser.getHeap().getVariable("V").getAsDouble());
//    }
//
//    private double volume(double a) {
//        return a*a*a*sqrt(2)/12;
//    }
//
//    private double field(double a) {
//        double pp = a*a*a*sqrt(3)/4;
//        return 4*pp;
//    }    @Test
//
//    void checkStringify() throws StartBlockNotFound, NotFoundTypeException, VariableNotFound, WrongArgumentException, UnsupportedValueException, NextElementNotFound, IncompatibleTypeException, VariableIsAlreadyDefinedException, InvalidArgumentsException {
//        double a = 7;
//        DefaultElementContainer container = new DefaultElementContainer(register, elementParser);
//
//        StartElement start = new StartBlock();
//
//        OperationElement init = new OperationBlock(executor);
//        init.setContent("double a="+a+";");
//
//        ConditionalElement validation = new ConditionBlock(executor);
//        validation.setContent("a>0");
//
//        OperationElement mainCalc = new OperationBlock(executor);
//        mainCalc.setContent(
//                "double pp = a*a*a*sqrt(3)/4;"+
//                        "double pc = 4*pp;"+
//                        "double V = pow(a, 3)*sqrt(2)/12;"
//        );
//
//        Element stop = new StopBlock();
//
//        start.setNextElement(init.getId());
//        init.setNextElement(validation.getId());
//        validation.setOnTrue(mainCalc.getId());
//        mainCalc.setNextElement(stop.getId());
//        validation.setOnFalse(stop.getId());
//
//        container.addElement(start);
//        container.addElement(init);
//        container.addElement(validation);
//        container.addElement(mainCalc);
//        container.addElement(stop);
//
//        container.run();
//
//        assertEquals(field(a), CodeParser.getHeap().getVariable("pc").getAsDouble());
//        assertEquals(volume(a), CodeParser.getHeap().getVariable("V").getAsDouble());
//
//        //Restore
//
//        String saved = container.stringify();
//        DefaultElementContainer containerCopy = new DefaultElementContainer(register, elementParser);
//        containerCopy.restore(saved);
//
//        container.run();
//
//        assertEquals(field(a), CodeParser.getHeap().getVariable("pc").getAsDouble());
//        assertEquals(volume(a), CodeParser.getHeap().getVariable("V").getAsDouble());
//    }
}