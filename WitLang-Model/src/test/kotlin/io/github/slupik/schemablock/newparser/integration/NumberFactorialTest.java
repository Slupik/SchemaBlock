package io.github.slupik.schemablock.newparser.integration;

import io.github.slupik.schemablock.newparser.compilator.Compilator;
import io.github.slupik.schemablock.newparser.compilator.implementation.DefaultCompilator;
import io.github.slupik.schemablock.newparser.executor.Executor;
import io.github.slupik.schemablock.newparser.executor.implementation.ExecutorImpl;
import io.github.slupik.schemablock.newparser.memory.Memory;
import io.github.slupik.schemablock.newparser.memory.MemoryImpl;
import io.github.slupik.schemablock.newparser.memory.Register;
import io.github.slupik.schemablock.newparser.memory.RegisterImpl;
import io.github.slupik.schemablock.newparser.memory.element.SimpleValue;
import io.github.slupik.schemablock.newparser.memory.element.Value;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * All rights reserved & copyright ©
 */
class NumberFactorialTest {

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
        int input = 10;

        String declare = "long result = 1;" +
                "int i = 1;" +
                "int input = "+input+";";
        exe.execute(declare);

        String condition = "i < input";
        while(((Boolean) exe.getResult(condition).getValue())) {
            String operation = "i = i + 1;" +
                    "result = result * i;";
            exe.execute(operation);
        }

        Value value = memory.get("result").getContent();
        Assertions.assertFalse(value.isArray());
        SimpleValue sValue = ((SimpleValue) value);
        Assertions.assertEquals(
                String.valueOf(factorial(input)),
                sValue.getValue().toString()
        );
    }

    private static long factorial(int i) {
        if (i < 1)
            return 1;
        else
            return i * factorial(i - 1);
    }

}