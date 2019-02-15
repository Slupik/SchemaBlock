package io.github.slupik.schemablock.newparser.compilator.implementation;

import io.github.slupik.schemablock.newparser.bytecode.bytecommand.abstraction.ByteCommand;
import io.github.slupik.schemablock.newparser.compilator.exception.ComExIllegalEscapeChar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * All rights reserved & copyright ©
 */
class DefaultCompilatorTest {

    @Test
    void getCompiled() throws ComExIllegalEscapeChar, Exception {
        Assertions.assertEquals("DECLARE_VAR DOUBLE name 0",
                ((LinkedList<ByteCommand>) new DefaultCompilator().getCompiled("double name;")).get(0).toString());
        Assertions.assertEquals("DECLARE_VAR DOUBLE name 2",
                ((LinkedList<ByteCommand>) new DefaultCompilator().getCompiled("double name[b[5+3]+4][3+5];")).get(0).toString());
        Assertions.assertEquals("DECLARE_VAR DOUBLE name 2",
                ((LinkedList<ByteCommand>) new DefaultCompilator().getCompiled("double[b[5+3]+4][3+5] name;")).get(0).toString());
        Assertions.assertEquals("DECLARE_VAR DOUBLE name 2",
                ((LinkedList<ByteCommand>) new DefaultCompilator().getCompiled("double name[b[5+a[3*sqrt(25)]]+4][3+5];")).get(0).toString());
        Assertions.assertEquals("DECLARE_VAR DOUBLE name 2",
                ((LinkedList<ByteCommand>) new DefaultCompilator().getCompiled("double[b[5+a[3*sqrt(25)]]+4][3+5] name;")).get(0).toString());
        Assertions.assertEquals("DECLARE_VAR DOUBLE name 3",
                ((LinkedList<ByteCommand>) new DefaultCompilator().getCompiled("double name[b[5+a[3*sqrt(25)]]+4][][3+5];")).get(0).toString());
        Assertions.assertEquals("DECLARE_VAR DOUBLE name 3",
                ((LinkedList<ByteCommand>) new DefaultCompilator().getCompiled("double[b[5+a[3*sqrt(25)]]+4][][3+5] name;")).get(0).toString());
        Assertions.assertEquals("DECLARE_VAR DOUBLE name 1",
                ((LinkedList<ByteCommand>) new DefaultCompilator().getCompiled("double[a=5] name;")).get(0).toString());

        Assertions.assertEquals("DECLARE_VAR DOUBLE name 0",
                ((LinkedList<ByteCommand>) new DefaultCompilator().getCompiled("double name = a[8]+b;")).get(0).toString());
        Assertions.assertEquals("DECLARE_VAR DOUBLE name 1",
                ((LinkedList<ByteCommand>) new DefaultCompilator().getCompiled("double name[] = {a[8]+b};")).get(0).toString());
        Assertions.assertEquals("DECLARE_VAR DOUBLE name 1",
                ((LinkedList<ByteCommand>) new DefaultCompilator().getCompiled("double[] name = {a[8]+b};")).get(0).toString());
        Assertions.assertEquals("DECLARE_VAR DOUBLE name 2",
                ((LinkedList<ByteCommand>) new DefaultCompilator().getCompiled("double name[][] = b[c[4]+3]=3;")).get(0).toString());
        Assertions.assertEquals("DECLARE_VAR DOUBLE name 2",
                ((LinkedList<ByteCommand>) new DefaultCompilator().getCompiled("double[][] name = b[c[4]+3]=3;")).get(0).toString());

        check("double name;",
                new String[]{
                        "DECLARE_VAR DOUBLE name 0",
                        "HEAP_VAR name",
                        "CLEAR_EXEC_HEAP"
                });
        check("double name = 2;",
                new String[]{
                        "DECLARE_VAR DOUBLE name 0",
                        "HEAP_VAR name",
                        "HEAP_VALUE INTEGER 2",
                        "OPERATION =",
                        "CLEAR_EXEC_HEAP"
                });
        check("double name = 2d;",
                new String[]{
                        "DECLARE_VAR DOUBLE name 0",
                        "HEAP_VAR name",
                        "HEAP_VALUE DOUBLE 2",
                        "OPERATION =",
                        "CLEAR_EXEC_HEAP"
                });
        check("double name = a[8];",
                new String[]{
                        "DECLARE_VAR DOUBLE name 0",
                        "HEAP_VAR name",
                        "HEAP_VALUE INTEGER 8",
                        "HEAP_VAR a 1",
                        "OPERATION =",
                        "CLEAR_EXEC_HEAP"
                });
        check("double name = a[8][9];",
                new String[]{
                        "DECLARE_VAR DOUBLE name 0",
                        "HEAP_VAR name",
                        "HEAP_VALUE INTEGER 9",
                        "HEAP_VALUE INTEGER 8",
                        "HEAP_VAR a 2",
                        "OPERATION =",
                        "CLEAR_EXEC_HEAP"
                });

        //TODO make code below to work the same as above
        /*
        double name = a[b[8]];
        double name = sqrt(7);

        double[8] name;
        double[a=8] name;

        double[] name = new double[8];
        double[] name = new double[b[8]];
        double[][] name = new double[8][9];
        double[][] name = new double[b[8]][c[9]];

        double name[] = new double[8];
        double name[] = new double[b[8]];
        double name[][] = new double[8][8];
        double name[][] = new double[b[8]][c[9]];

        double[] name = {1, 2.0, 3};
        double[][] name = {{1}, {2.0}, {3}};

        double name[] = {1, 2.0, 3};
        double name[][] = {{1}, {2.0}, {3}};
         */

//        Queue<ByteCommand> queue = new DefaultCompilator().getCompiled("\"test a\\\"b\\tc\"");//"test a\"b\tc"
//        System.out.println("=======");
//        new DefaultCompilator().getCompiled("\"test//\"\"awdwa\"");
//        System.out.println("=======");
//        new DefaultCompilator().getCompiled("\"test\"/*\"awdwa\"*/");
    }

    @Test
    void checkError() throws ComExIllegalEscapeChar, Exception {
        //FIXME
        //returns:
        //should return:
        Queue<ByteCommand> queue = new DefaultCompilator().getCompiled("double name = a[b[8]];");

        for(ByteCommand bc:queue) {
            System.out.println("bc.getCommandType().toString() = " + bc.toString());
        }
    }

    private void check(String input, String[] answer) throws ComExIllegalEscapeChar, Exception {
        LinkedList<ByteCommand> output = (LinkedList<ByteCommand>) new DefaultCompilator().getCompiled(input);
        for(int i=0;i<output.size();i++) {
            System.out.println(output.get(i).toString());
        }

        Assertions.assertEquals(answer.length, output.size());
        for(int i=0;i<output.size();i++) {
            Assertions.assertEquals(answer[i], output.get(i).toString());
        }
    }

    @Test
    void toRpn() throws ComExIllegalEscapeChar {
        String equation = "double[a=5] name;";
//        String equation = "double name[a=5];";
        List<Token> tokens = new Tokenizer(equation).getTokenized();
        List<Token> cleared = new BracketsRemover().getCleared(tokens);
        LinkedList<Token> rpn = new LinkedList<>(ConvertInfixToRPN.convertInfixToRPN(cleared));

        for(Token token:rpn) {
            System.out.println("token.getData() = " + token.getData());
        }
    }
}