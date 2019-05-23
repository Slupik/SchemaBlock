package io.github.slupik.schemablock.parser.code;

import io.github.slupik.schemablock.parser.math.rpn.pattern.InvalidArgumentsException;
import io.github.slupik.schemablock.parser.math.rpn.pattern.UnsupportedValueException;
import io.github.slupik.schemablock.parser.math.rpn.pattern.specific.special.IOproxy;
import io.github.slupik.schemablock.parser.math.rpn.pattern.specific.special.ProgramPrint;
import io.github.slupik.schemablock.parser.math.rpn.pattern.specific.special.ProgramPrintln;
import io.github.slupik.schemablock.parser.math.rpn.pattern.specific.special.ProgramRead;
import io.github.slupik.schemablock.parser.math.rpn.variable.VariableIsAlreadyDefinedException;
import io.github.slupik.schemablock.parser.math.rpn.variable.value.NotFoundTypeException;
import io.github.slupik.schemablock.parser.math.rpn.variable.value.ValueType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * All rights reserved & copyright ©
 */
class CodeParserTest {

    @Test
    void execute() throws InvalidArgumentsException, UnsupportedValueException, VariableIsAlreadyDefinedException, VariableNotFound, WrongArgumentException, NotFoundTypeException, IncompatibleTypeException {
        CodeParser.execute("double a = 3;");
        assertEquals("3", CodeParser.getHeap().getVariable("a").getValue());
        assertEquals(ValueType.DOUBLE, CodeParser.getHeap().getVariable("a").getType());

        CodeParser.clearHeap();
        CodeParser.execute("double a = 3;" +
                "double b = 3;");
        assertEquals("3", CodeParser.getHeap().getVariable("a").getValue());
        assertEquals("3", CodeParser.getHeap().getVariable("b").getValue());
        assertEquals(ValueType.DOUBLE, CodeParser.getHeap().getVariable("a").getType());
        assertEquals(ValueType.DOUBLE, CodeParser.getHeap().getVariable("b").getType());

        CodeParser.clearHeap();
        CodeParser.execute("double a = 3;" +
                "double b = a;");
        assertEquals("3", CodeParser.getHeap().getVariable("a").getValue());
        assertEquals("3", CodeParser.getHeap().getVariable("b").getValue());
        assertEquals(ValueType.DOUBLE, CodeParser.getHeap().getVariable("a").getType());
        assertEquals(ValueType.DOUBLE, CodeParser.getHeap().getVariable("b").getType());

        CodeParser.clearHeap();
        CodeParser.execute("double a = 3;" +
                "double b = a;");
        assertEquals("3", CodeParser.getHeap().getVariable("a").getValue());
        assertEquals("3", CodeParser.getHeap().getVariable("b").getValue());
        assertEquals(ValueType.DOUBLE, CodeParser.getHeap().getVariable("a").getType());
        assertEquals(ValueType.DOUBLE, CodeParser.getHeap().getVariable("b").getType());

        CodeParser.clearHeap();
        CodeParser.execute("double a = 5;" +
                "double b = a = 3;");
        assertEquals("3", CodeParser.getHeap().getVariable("a").getValue());
        assertEquals("3", CodeParser.getHeap().getVariable("b").getValue());
        assertEquals(ValueType.DOUBLE, CodeParser.getHeap().getVariable("a").getType());
        assertEquals(ValueType.DOUBLE, CodeParser.getHeap().getVariable("b").getType());

        CodeParser.clearHeap();
        CodeParser.execute("double a, b;" +
                "b = a = 3;");
        assertEquals("3", CodeParser.getHeap().getVariable("a").getValue());
        assertEquals("3", CodeParser.getHeap().getVariable("b").getValue());
        assertEquals(ValueType.DOUBLE, CodeParser.getHeap().getVariable("a").getType());
        assertEquals(ValueType.DOUBLE, CodeParser.getHeap().getVariable("b").getType());

        CodeParser.clearHeap();
        assertThrows(IncompatibleTypeException.class,
                () -> CodeParser.execute("double a, b;" +
            "b = a = true;"));
        assertNull(CodeParser.getHeap().getVariable("a").getValue());
        assertNull(CodeParser.getHeap().getVariable("b").getValue());
        assertEquals(ValueType.DOUBLE, CodeParser.getHeap().getVariable("a").getType());
        assertEquals(ValueType.DOUBLE, CodeParser.getHeap().getVariable("b").getType());

        CodeParser.clearHeap();
        CodeParser.execute("double[5] a;");
        assertNull(CodeParser.getHeap().getVariable("a[0]").getValue());
        assertNull(CodeParser.getHeap().getVariable("a[1]").getValue());
        assertNull(CodeParser.getHeap().getVariable("a[2]").getValue());
        assertNull( CodeParser.getHeap().getVariable("a[3]").getValue());
        assertEquals(ValueType.DOUBLE, CodeParser.getHeap().getVariable("a[0]").getType());
        assertEquals(ValueType.DOUBLE, CodeParser.getHeap().getVariable("a[1]").getType());
        assertEquals(ValueType.DOUBLE, CodeParser.getHeap().getVariable("a[2]").getType());
        assertEquals(ValueType.DOUBLE, CodeParser.getHeap().getVariable("a[3]").getType());
        assertEquals(ValueType.DOUBLE, CodeParser.getHeap().getVariable("a[4]").getType());

        CodeParser.clearHeap();
        CodeParser.execute("double a[5];");
        assertNull(CodeParser.getHeap().getVariable("a[0]").getValue());
        assertNull(CodeParser.getHeap().getVariable("a[1]").getValue());
        assertNull(CodeParser.getHeap().getVariable("a[2]").getValue());
        assertNull( CodeParser.getHeap().getVariable("a[3]").getValue());
        assertNull( CodeParser.getHeap().getVariable("a[4]").getValue());
        assertEquals(ValueType.DOUBLE, CodeParser.getHeap().getVariable("a[0]").getType());
        assertEquals(ValueType.DOUBLE, CodeParser.getHeap().getVariable("a[1]").getType());
        assertEquals(ValueType.DOUBLE, CodeParser.getHeap().getVariable("a[2]").getType());
        assertEquals(ValueType.DOUBLE, CodeParser.getHeap().getVariable("a[3]").getType());
        assertEquals(ValueType.DOUBLE, CodeParser.getHeap().getVariable("a[4]").getType());

        CodeParser.clearHeap();
        CodeParser.execute("double a[3];" +
                "a[1]=2;"+
                "a[2]=3122;"
        );
        assertNull(CodeParser.getHeap().getVariable("a[0]").getValue());
        assertEquals("2", CodeParser.getHeap().getVariable("a[1]").getValue());
        assertEquals("3122", CodeParser.getHeap().getVariable("a[2]").getValue());
        assertEquals(ValueType.DOUBLE, CodeParser.getHeap().getVariable("a[0]").getType());
        assertEquals(ValueType.DOUBLE, CodeParser.getHeap().getVariable("a[1]").getType());
        assertEquals(ValueType.DOUBLE, CodeParser.getHeap().getVariable("a[1]").getType());

        CodeParser.clearHeap();
        CodeParser.execute(
                "int i = 2;" +
                "double a[3];" +
                "a[1]=2;"+
                "a[i]=3122;"
        );
        assertNull(CodeParser.getHeap().getVariable("a[0]").getValue());
        assertEquals("2", CodeParser.getHeap().getVariable("a[1]").getValue());
        assertEquals("3122", CodeParser.getHeap().getVariable("a[2]").getValue());
        assertEquals(ValueType.DOUBLE, CodeParser.getHeap().getVariable("a[0]").getType());
        assertEquals(ValueType.DOUBLE, CodeParser.getHeap().getVariable("a[1]").getType());
        assertEquals(ValueType.DOUBLE, CodeParser.getHeap().getVariable("a[1]").getType());

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

    @Test
    void checkReadFunction() throws IncompatibleTypeException, InvalidArgumentsException, UnsupportedValueException, VariableIsAlreadyDefinedException, VariableNotFound, WrongArgumentException, NotFoundTypeException {
        String val1 = "23.5";
        String val2 = "Cool test";
        String valDefault = "0xDEADBEEF";

        IOproxy io = new IOproxy() {
            private int loop = 0;

            @Override
            public String readLine() {
                loop++;
                if(loop==1) {
                    return val1;
                }
                if(loop==2) {
                    return val2;
                }
                return valDefault;
            }

            @Override
            public void print(String print) {

            }
        };
        ProgramRead.setIo(io);

        CodeParser.clearHeap();
        CodeParser.execute("double number;" +
                "String value;" +
                "number = read(DOUBLE);"+
                "value = read(STRING);"
        );
        assertEquals(ValueType.DOUBLE, CodeParser.getHeap().getVariable("number").getType());
        assertEquals(ValueType.STRING, CodeParser.getHeap().getVariable("value").getType());
        assertEquals(val1, CodeParser.getHeap().getVariable("number").getValue());
        assertEquals(val2, CodeParser.getHeap().getVariable("value").getValue());
    }

    @Test
    void checkPrintFunction() throws IncompatibleTypeException, InvalidArgumentsException, UnsupportedValueException, VariableIsAlreadyDefinedException, VariableNotFound, WrongArgumentException, NotFoundTypeException {
        String val1 = "23.5";
        String val2 = "\"Cool test\"";

        IOproxy io = new IOproxy() {
            private int loop = 0;

            @Override
            public String readLine() {
                return "";
            }

            @Override
            public void print(String print) {
                loop++;
                if(loop==1) {
                    assertEquals(val1, print);
                }
                if(loop==2) {
                    String excepted = val2.substring(1, val2.length()-1);
                    assertEquals(excepted, print);
                }
            }
        };
        ProgramPrint.setIo(io);
        ProgramPrintln.setIo(io);

        CodeParser.clearHeap();
        CodeParser.execute("double number = " + val1 + ";" +
                "String value = " + val2 + ";"
        );
        assertEquals(ValueType.DOUBLE, CodeParser.getHeap().getVariable("number").getType());
        assertEquals(ValueType.STRING, CodeParser.getHeap().getVariable("value").getType());
        assertEquals(val1, CodeParser.getHeap().getVariable("number").getValue());
        assertEquals(val2.substring(1, val2.length()-1), CodeParser.getHeap().getVariable("value").getValue());
        CodeParser.execute(
                "getValueToPrint(number);"+
                        "getValueToPrint(value);");
    }

    private static void keepImports(){
        assertEquals(true, true);
        assertNull(null);
        assertThrows(Exception.class, () -> {});
    }

    @Test
    void getValueToPrint() throws IncompatibleTypeException, InvalidArgumentsException, UnsupportedValueException, VariableIsAlreadyDefinedException, VariableNotFound, WrongArgumentException, NotFoundTypeException {
        double val1 = 4231423;
        String val2 = "fewfwefw";

        CodeParser.clearHeap();
        CodeParser.execute("double number = " + val1 + ";" +
                "String value = \"" + val2 + "\";"
        );
        assertEquals(ValueType.DOUBLE, CodeParser.getHeap().getVariable("number").getType());
        assertEquals(ValueType.STRING, CodeParser.getHeap().getVariable("value").getType());

        assertEquals("This is a number: 4231423.0 and \\\"(test)\\\" this is a string: fewfwefw",
                CodeParser.getValueToPrint("\"This is a number: \"+number+\" and \\\"(test)\\\" this is a string: \"+value"));
    }
}