package io.github.slupik.schemablock.newparser.executor.implementation;

import io.github.slupik.schemablock.newparser.compilator.Compilator;
import io.github.slupik.schemablock.newparser.compilator.implementation.DefaultCompilator;
import io.github.slupik.schemablock.newparser.executor.Executor;
import io.github.slupik.schemablock.newparser.memory.*;
import io.github.slupik.schemablock.newparser.memory.element.SimpleValue;
import io.github.slupik.schemablock.newparser.memory.element.Value;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * All rights reserved & copyright ©
 */
class ExecutorImplTest {

    private Compilator compilator;
    private Memory memory;
    private Register register;
    private Executor exe;

    @BeforeEach
    void setup() {
        compilator = new DefaultCompilator();
        memory = new MemoryImpl(new TokenizingIndexesExtractor(exe));
        register = new RegisterImpl();
        exe = new ExecutorImpl(compilator, memory, register);
    }

    @Test
    void execute() throws Throwable {
        check("double a = 5 + 3;", 8);

        check("double a = 5 - 3;", 2);
        check("double a = 5 - 9;", -4);

        check("double a = 5 * 9;", 45);
        check("double a = 5 * -9;", 5 * -9);
        check("double a = 5 * (-9);", 5 * (-9));
        check("double a = 5 * (+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+9);", 5 * (+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+9));
        check("double a = +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+5 * (+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+9);", +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+5 * (+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+9));
        check("double a = 5 * 9+3;", 48);

        check("String a = \"test \"+\"aaa\";",
                "test aaa");

        check("int b = 5 * 9+3;" +
                        "double c = 5/2;" +
                        "String a = \"text\"+b+c;",
                "text482");

        check("double[] b = {1, 2};" +
                "double a = b[1];", 2);
        check("double[4] b;" +
                "b[1] = 2;" +
                "double a = b[1];", 2);
        check("double[][] b = {{1}};" +
                "double a = b[0][0];", 1);
        check("double[][][] b = {{{1}}};" +
                "double a = b[0][0][0];", 1);
        check("double[][] b = {{1}, {2}};" +
                "double a = b[0][0];", 1);
        check("double[][] b = {{1}, {2}};" +
                "double a = b[1][0];", 2);
        check("double[][] b = {{1, 4}, {3, 2}};" +
                "double a = b[1][1];", 2);
        check("double[][] b = new double[8][9];" +
                "b[0][0] = 5;" +
                "double a = b[0][0];", 5);
        check("double[8][9] b;" +
                "b[0][0] = 5;" +
                "double a = b[0][0];", 5);

        check("double a = 0;" +
                "a = sqrt(4);",2.0);
        check("double a = sqrt(4);",2.0);
        check("int b = 9;" +
                        "double a = sqrt(b);",
                3.0);
        check("double b = sqrt(81);" +
                        "double a = sqrt(b);",
                3.0);
        check("double a = sqrt(sqrt(81));",
                3.0);
        check("double[] b = {3, 9};" +
                        "double a = sqrt(b[1]);",
                3.0);
        check("double[] b = {3, 9, 81};" +
                        "double a = sqrt(sqrt(b[2]));",
                3.0);
        check("double[] b = new double[2];" +
                        "b[1] = 9;" +
                        "double a = sqrt(b[1]);",
                3.0);
        check("double[] b = new double[3];" +
                        "b[2] = 81;" +
                        "double a = sqrt(sqrt(b[2]));",
                3.0);
        check("double a = sqrt(sqrt(40+41));",
                3.0);
        check("double a = 0;" +
                "a = sqrt(4);",
                2.0);
        check("double a = 0;" +
                "a = min(-2, 9);",
                -2.0);
        check("double a = 0;" +
                "a = power(2, 3);",
                Math.pow(2, 3));
        check("double a = 0;" +
                "a = power(2, sqrt(9));",
                Math.pow(2, Math.sqrt(9)));
        check("double[] array = {9, 81};" +
                        "double a = 0;" +
                        "a = power(2, sqrt(array[0]));",
                Math.pow(2, Math.sqrt(9)));
    }

    @Test
    void executeForValue() throws Throwable {
        checkForValue("5 * (+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+9);", 5 * (+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+9));
        checkForValue("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+5 * (+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+9);", +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+5 * (+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+9));
    }

    private void checkForValue(String code, Object result) throws Throwable {
        Assertions.assertEquals(result, exe.getResult(code).getValue());
        memory.clear();
        register.clear();
    }

    @Test
    void repair() throws Throwable {
//        String code = "";
//        check(code, 0);
    }

    @Test
    void handRepair() throws Throwable {
//        exe.execute("");
//
//
//
//        Value value = memory.get("a").getContent();
//        Assertions.assertTrue(value.isArray());
//        Array array = ((Array) value);
//        Assertions.assertEquals(2, array.getDimensionsCount());
//        Assertions.assertEquals(1, ((int) ((SimpleValue) array.getElement(new int[]{0})).getCastedValue()));
//
//
//
//        memory.clear();
//        register.clear();
    }

    private void check(String code, Object result) throws Throwable {
        exe.execute(code);
        Value value = memory.get("a").getContent();
        Assertions.assertFalse(value.isArray());
        SimpleValue sValue = ((SimpleValue) value);
        Assertions.assertEquals(result, sValue.getValue());
        memory.clear();
        register.clear();
    }
}