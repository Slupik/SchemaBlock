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
        //FIXME
//        check("double a = 5 * (-9);", -45);
        check("double a = 5 * 9+3;", 48);
    }

    private void check(String code, Object result) throws Throwable {
        exe.execute(code);
        Assertions.assertEquals(result, memory.get("a").getValue().getValue());
    }
}