package io.github.slupik.schemablock.parser.code;

import io.github.slupik.schemablock.parser.math.rpn.pattern.InvalidArgumentsException;
import io.github.slupik.schemablock.parser.math.rpn.pattern.UnsupportedValueException;
import io.github.slupik.schemablock.parser.math.rpn.variable.VariableIsAlreadyDefinedException;
import io.github.slupik.schemablock.parser.math.rpn.variable.value.NotFoundTypeException;
import io.github.slupik.schemablock.parser.math.rpn.variable.value.ValueType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * All rights reserved & copyright ©
 */
class CodeParserTest {

    @Test
    void execute() throws InvalidArgumentsException, UnsupportedValueException, VariableIsAlreadyDefinedException, VariableNotFound, WrongArgumentException, NotFoundTypeException, IncompatibleTypeException {
//        CodeParser.execute("double a = 3;");
//        assertEquals("3", CodeParser.getHeap().getVariable("a").getValue());
//        assertEquals(ValueType.DOUBLE, CodeParser.getHeap().getVariable("a").getType());
//
//        CodeParser.clearHeap();
//        CodeParser.execute("double a = 3;" +
//                "double b = 3;");
//        assertEquals("3", CodeParser.getHeap().getVariable("a").getValue());
//        assertEquals("3", CodeParser.getHeap().getVariable("b").getValue());
//        assertEquals(ValueType.DOUBLE, CodeParser.getHeap().getVariable("a").getType());
//        assertEquals(ValueType.DOUBLE, CodeParser.getHeap().getVariable("b").getType());
//
//        CodeParser.clearHeap();
//        CodeParser.execute("double a = 3;" +
//                "double b = a;");
//        assertEquals("3", CodeParser.getHeap().getVariable("a").getValue());
//        assertEquals("3", CodeParser.getHeap().getVariable("b").getValue());
//        assertEquals(ValueType.DOUBLE, CodeParser.getHeap().getVariable("a").getType());
//        assertEquals(ValueType.DOUBLE, CodeParser.getHeap().getVariable("b").getType());
//
//        CodeParser.clearHeap();
//        CodeParser.execute("double a = 3;" +
//                "double b = a;");
//        assertEquals("3", CodeParser.getHeap().getVariable("a").getValue());
//        assertEquals("3", CodeParser.getHeap().getVariable("b").getValue());
//        assertEquals(ValueType.DOUBLE, CodeParser.getHeap().getVariable("a").getType());
//        assertEquals(ValueType.DOUBLE, CodeParser.getHeap().getVariable("b").getType());
//
//        CodeParser.clearHeap();
//        CodeParser.execute("double a = 5;" +
//                "double b = a = 3;");
//        assertEquals("3", CodeParser.getHeap().getVariable("a").getValue());
//        assertEquals("3", CodeParser.getHeap().getVariable("b").getValue());
//        assertEquals(ValueType.DOUBLE, CodeParser.getHeap().getVariable("a").getType());
//        assertEquals(ValueType.DOUBLE, CodeParser.getHeap().getVariable("b").getType());
//
//        CodeParser.clearHeap();
//        CodeParser.execute("double a, b;" +
//                "b = a = 3;");
//        assertEquals("3", CodeParser.getHeap().getVariable("a").getValue());
//        assertEquals("3", CodeParser.getHeap().getVariable("b").getValue());
//        assertEquals(ValueType.DOUBLE, CodeParser.getHeap().getVariable("a").getType());
//        assertEquals(ValueType.DOUBLE, CodeParser.getHeap().getVariable("b").getType());
//
//        CodeParser.clearHeap();
//        assertThrows(IncompatibleTypeException.class,
//                () -> CodeParser.execute("double a, b;" +
//            "b = a = true;"));
//        assertNull(CodeParser.getHeap().getVariable("a").getValue());
//        assertNull(CodeParser.getHeap().getVariable("b").getValue());
//        assertEquals(ValueType.DOUBLE, CodeParser.getHeap().getVariable("a").getType());
//        assertEquals(ValueType.DOUBLE, CodeParser.getHeap().getVariable("b").getType());
//
//        CodeParser.clearHeap();
//        CodeParser.execute("double[5] a;");
//        assertNull(CodeParser.getHeap().getVariable("a[0]").getValue());
//        assertNull(CodeParser.getHeap().getVariable("a[1]").getValue());
//        assertNull(CodeParser.getHeap().getVariable("a[2]").getValue());
//        assertNull( CodeParser.getHeap().getVariable("a[3]").getValue());
//        assertEquals(ValueType.DOUBLE, CodeParser.getHeap().getVariable("a[0]").getType());
//        assertEquals(ValueType.DOUBLE, CodeParser.getHeap().getVariable("a[1]").getType());
//        assertEquals(ValueType.DOUBLE, CodeParser.getHeap().getVariable("a[2]").getType());
//        assertEquals(ValueType.DOUBLE, CodeParser.getHeap().getVariable("a[3]").getType());
//        assertEquals(ValueType.DOUBLE, CodeParser.getHeap().getVariable("a[4]").getType());
//
//        CodeParser.clearHeap();
//        CodeParser.execute("double a[5];");
//        assertNull(CodeParser.getHeap().getVariable("a[0]").getValue());
//        assertNull(CodeParser.getHeap().getVariable("a[1]").getValue());
//        assertNull(CodeParser.getHeap().getVariable("a[2]").getValue());
//        assertNull( CodeParser.getHeap().getVariable("a[3]").getValue());
//        assertNull( CodeParser.getHeap().getVariable("a[4]").getValue());
//        assertEquals(ValueType.DOUBLE, CodeParser.getHeap().getVariable("a[0]").getType());
//        assertEquals(ValueType.DOUBLE, CodeParser.getHeap().getVariable("a[1]").getType());
//        assertEquals(ValueType.DOUBLE, CodeParser.getHeap().getVariable("a[2]").getType());
//        assertEquals(ValueType.DOUBLE, CodeParser.getHeap().getVariable("a[3]").getType());
//        assertEquals(ValueType.DOUBLE, CodeParser.getHeap().getVariable("a[4]").getType());
//
//        CodeParser.clearHeap();
//        CodeParser.execute("double a[3];" +
//                "a[1]=2;"+
//                "a[2]=3122;"
//        );
//        assertNull(CodeParser.getHeap().getVariable("a[0]").getValue());
//        assertEquals("2", CodeParser.getHeap().getVariable("a[1]").getValue());
//        assertEquals("3122", CodeParser.getHeap().getVariable("a[2]").getValue());
//        assertEquals(ValueType.DOUBLE, CodeParser.getHeap().getVariable("a[0]").getType());
//        assertEquals(ValueType.DOUBLE, CodeParser.getHeap().getVariable("a[1]").getType());
//        assertEquals(ValueType.DOUBLE, CodeParser.getHeap().getVariable("a[1]").getType());
//
//        CodeParser.clearHeap();
//        CodeParser.execute(
//                "int i = 2;" +
//                "double a[3];" +
//                "a[1]=2;"+
//                "a[i]=3122;"
//        );
//        assertNull(CodeParser.getHeap().getVariable("a[0]").getValue());
//        assertEquals("2", CodeParser.getHeap().getVariable("a[1]").getValue());
//        assertEquals("3122", CodeParser.getHeap().getVariable("a[2]").getValue());
//        assertEquals(ValueType.DOUBLE, CodeParser.getHeap().getVariable("a[0]").getType());
//        assertEquals(ValueType.DOUBLE, CodeParser.getHeap().getVariable("a[1]").getType());
//        assertEquals(ValueType.DOUBLE, CodeParser.getHeap().getVariable("a[1]").getType());

        //FIXME
        CodeParser.clearHeap();
        CodeParser.execute("double a[3];" +
                "a[1]=2;"+
                "a[2]=3122;" +
                "double b = a[2];"
        );
        assertNull(CodeParser.getHeap().getVariable("a[0]").getValue());
        assertEquals("2", CodeParser.getHeap().getVariable("a[1]").getValue());
        assertEquals("3122", CodeParser.getHeap().getVariable("a[2]").getValue());
        assertEquals("3122", CodeParser.getHeap().getVariable("b").getValue());
        assertEquals(ValueType.DOUBLE, CodeParser.getHeap().getVariable("a[0]").getType());
        assertEquals(ValueType.DOUBLE, CodeParser.getHeap().getVariable("a[1]").getType());
        assertEquals(ValueType.DOUBLE, CodeParser.getHeap().getVariable("a[1]").getType());
        assertEquals(ValueType.DOUBLE, CodeParser.getHeap().getVariable("b").getType());
    }

    private static void keepImports(){
        assertEquals(true, true);
        assertNull(null);
        assertThrows(Exception.class, () -> {});
    }
}