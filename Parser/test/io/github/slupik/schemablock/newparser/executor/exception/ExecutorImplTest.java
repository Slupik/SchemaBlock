package io.github.slupik.schemablock.newparser.executor.exception;

import io.github.slupik.schemablock.newparser.compilator.Compilator;
import io.github.slupik.schemablock.newparser.compilator.implementation.DefaultCompilator;
import io.github.slupik.schemablock.newparser.executor.Executor;
import io.github.slupik.schemablock.newparser.memory.Memory;
import io.github.slupik.schemablock.newparser.memory.MemoryImpl;
import io.github.slupik.schemablock.newparser.memory.Register;
import io.github.slupik.schemablock.newparser.memory.RegisterImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * All rights reserved & copyright ©
 */
class ExecutorImplTest {

    @Test
    void execute() throws Throwable {
        //TODO improve testing
        String code = "double a = 5 + 3;";

        Compilator compilator = new DefaultCompilator();
        Memory memory = new MemoryImpl();
        Register register = new RegisterImpl();
        Executor exe = new ExecutorImpl(compilator, memory, register);

        exe.execute(code);

        Assertions.assertEquals(8, memory.get("a").getValue().getValue());
    }
}