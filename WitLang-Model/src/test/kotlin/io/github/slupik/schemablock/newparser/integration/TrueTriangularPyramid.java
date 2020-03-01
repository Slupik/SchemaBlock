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

import static java.lang.Math.sqrt;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * All rights reserved & copyright ©
 */
class TrueTriangularPyramid {

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
        double a = 7;

        String declare = "double a="+a+";";
        exe.execute(declare);

        String calculations =
                "double pp = a*a*a*sqrt(3)/4;"+
                        "double pc = 4*pp;"+
                        "double V = power(a, 3)*sqrt(2)/12;";
        exe.execute(calculations);

        Value value = memory.get("pc").getContent();
        Assertions.assertFalse(value.isArray());
        SimpleValue sValue = ((SimpleValue) value);
        assertEquals(
                String.valueOf(field(a)),
                sValue.getValue().toString()
        );
        value = memory.get("V").getContent();
        Assertions.assertFalse(value.isArray());
        sValue = ((SimpleValue) value);
        assertEquals(
                String.valueOf(volume(a)),
                sValue.getValue().toString()
        );
    }

    private double volume(double a) {
        return a*a*a*sqrt(2)/12;
    }

    private double field(double a) {
        double pp = a*a*a*sqrt(3)/4;
        return 4*pp;
    }

}