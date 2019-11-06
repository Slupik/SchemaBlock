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
class NWDandNWWTest {

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
        int a = 172;
        int b = 20;

        String declare = "int a, b, nww, nwd, pom;" +
                "a=" + a + ";" +
                "b=" + b + ";" +
                "int ab = a * b;";
        exe.execute(declare);

        String condition = "b!=0";
        while (((Boolean) exe.getResult(condition).getValue())) {
            String operation =
                    "pom=b;" +
                            "b=a%b;" +
                            "a=pom;";
            exe.execute(operation);
        }

        String calculations =
                "nwd=a;" +
                        "nww=ab/nwd;";
        exe.execute(calculations);

        Value value = memory.get("nwd").getContent();
        Assertions.assertFalse(value.isArray());
        SimpleValue sValue = ((SimpleValue) value);
        Assertions.assertEquals(
                String.valueOf(nwd(a, b)),
                sValue.getValue().toString()
        );

        value = memory.get("nww").getContent();
        Assertions.assertFalse(value.isArray());
        sValue = ((SimpleValue) value);
        Assertions.assertEquals(
                String.valueOf(nww(a, b)),
                sValue.getValue().toString()
        );
    }

    private static int nww(int a, int b) {
        return a * b / nwd(a, b);
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