package io.github.slupik.schemablock.newparser.executor.implementation;

import io.github.slupik.schemablock.newparser.compilator.Compilator;
import io.github.slupik.schemablock.newparser.compilator.implementation.DefaultCompilator;
import io.github.slupik.schemablock.newparser.executor.Executor;
import io.github.slupik.schemablock.newparser.memory.Memory;
import io.github.slupik.schemablock.newparser.memory.MemoryImpl;
import io.github.slupik.schemablock.newparser.memory.Register;
import io.github.slupik.schemablock.newparser.memory.RegisterImpl;
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
        memory = new MemoryImpl();
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
        check("double[] b = {1, 2};" +
                "double a = b[1];", 2);
    }

    private void check(String code, Object result) throws Throwable {
        exe.execute(code);
        Assertions.assertEquals(result, memory.get("a").getContent().getValue());
        memory.clear();
        register.clear();
    }
}