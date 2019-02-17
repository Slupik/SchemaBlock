package io.github.slupik.schemablock.newparser.compilator.implementation;

import io.github.slupik.schemablock.newparser.bytecode.bytecommand.abstraction.ByteCommand;
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
    void getCompiled() throws Throwable {
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
        check("double name = a[b[8]];",
                new String[]{
                        "DECLARE_VAR DOUBLE name 0",
                        "HEAP_VAR name",
                        "HEAP_VALUE INTEGER 8",
                        "HEAP_VAR b 1",
                        "HEAP_VAR a 1",
                        "OPERATION =",
                        "CLEAR_EXEC_HEAP"
                });
        check("double name = a[b[8]+3];",
                new String[]{
                        "DECLARE_VAR DOUBLE name 0",
                        "HEAP_VAR name",
                        "HEAP_VALUE INTEGER 8",
                        "HEAP_VAR b 1",
                        "HEAP_VALUE INTEGER 3",
                        "OPERATION +",
                        "HEAP_VAR a 1",
                        "OPERATION =",
                        "CLEAR_EXEC_HEAP"
                });
        check("double name = sqrt(7);",
                new String[]{
                        "DECLARE_VAR DOUBLE name 0",
                        "HEAP_VAR name",
                        "HEAP_VALUE INTEGER 7",
                        "EXECUTE sqrt 1",
                        "OPERATION =",
                        "CLEAR_EXEC_HEAP"
                });
        check("double name = root(7, 2);",
                new String[]{
                        "DECLARE_VAR DOUBLE name 0",
                        "HEAP_VAR name",
                        "HEAP_VALUE INTEGER 2",
                        "HEAP_VALUE INTEGER 7",
                        "EXECUTE root 2",
                        "OPERATION =",
                        "CLEAR_EXEC_HEAP"
                });


        check("double[8] name;",
                new String[]{
                        "DECLARE_VAR DOUBLE name 1",
                        "HEAP_VAR name",
                        "HEAP_VALUE INTEGER 8",
                        "HEAP_VIRTUAL_ARRAY DOUBLE 1",
                        "OPERATION =",
                        "CLEAR_EXEC_HEAP"
                });
        check("double[a=8] name;",
                new String[]{
                        "DECLARE_VAR DOUBLE name 1",
                        "HEAP_VAR name",
                        "HEAP_VAR a",
                        "HEAP_VALUE INTEGER 8",
                        "OPERATION =",
                        "HEAP_VIRTUAL_ARRAY DOUBLE 1",
                        "OPERATION =",
                        "CLEAR_EXEC_HEAP"
                });


        check("double[] name = new double[8];",
                new String[]{
                        "DECLARE_VAR DOUBLE name 1",
                        "HEAP_VAR name",
                        "HEAP_VALUE INTEGER 8",
                        "HEAP_VIRTUAL_ARRAY DOUBLE 1",
                        "OPERATION =",
                        "CLEAR_EXEC_HEAP"
                });
        check("double[] name = new double[b[8]];",
                new String[]{
                        "DECLARE_VAR DOUBLE name 1",
                        "HEAP_VAR name",
                        "HEAP_VALUE INTEGER 8",
                        "HEAP_VAR b 1",
                        "HEAP_VIRTUAL_ARRAY DOUBLE 1",
                        "OPERATION =",
                        "CLEAR_EXEC_HEAP"
                });


        check("double[][] name = new double[8][9];",
                new String[]{
                        "DECLARE_VAR DOUBLE name 2",
                        "HEAP_VAR name",
                        "HEAP_VALUE INTEGER 9",
                        "HEAP_VALUE INTEGER 8",
                        "HEAP_VIRTUAL_ARRAY DOUBLE 2",
                        "OPERATION =",
                        "CLEAR_EXEC_HEAP"
                });
        check("double[][] name = new double[b[8]][c[9]];",
                new String[]{
                        "DECLARE_VAR DOUBLE name 2",
                        "HEAP_VAR name",
                        "HEAP_VALUE INTEGER 9",
                        "HEAP_VAR c 1",
                        "HEAP_VALUE INTEGER 8",
                        "HEAP_VAR b 1",
                        "HEAP_VIRTUAL_ARRAY DOUBLE 2",
                        "OPERATION =",
                        "CLEAR_EXEC_HEAP"
                });


        check("double name[] = new double[8];",
                new String[]{
                        "DECLARE_VAR DOUBLE name 1",
                        "HEAP_VAR name",
                        "HEAP_VALUE INTEGER 8",
                        "HEAP_VIRTUAL_ARRAY DOUBLE 1",
                        "OPERATION =",
                        "CLEAR_EXEC_HEAP"
                });
        check("double name[] = new double[b[8]];",
                new String[]{
                        "DECLARE_VAR DOUBLE name 1",
                        "HEAP_VAR name",
                        "HEAP_VALUE INTEGER 8",
                        "HEAP_VAR b 1",
                        "HEAP_VIRTUAL_ARRAY DOUBLE 1",
                        "OPERATION =",
                        "CLEAR_EXEC_HEAP"
                });
        check("double name[][] = new double[8][9];",
                new String[]{
                        "DECLARE_VAR DOUBLE name 2",
                        "HEAP_VAR name",
                        "HEAP_VALUE INTEGER 9",
                        "HEAP_VALUE INTEGER 8",
                        "HEAP_VIRTUAL_ARRAY DOUBLE 2",
                        "OPERATION =",
                        "CLEAR_EXEC_HEAP"
                });
        check("double name[][] = new double[b[8]][c[9]];",
                new String[]{
                        "DECLARE_VAR DOUBLE name 2",
                        "HEAP_VAR name",
                        "HEAP_VALUE INTEGER 9",
                        "HEAP_VAR c 1",
                        "HEAP_VALUE INTEGER 8",
                        "HEAP_VAR b 1",
                        "HEAP_VIRTUAL_ARRAY DOUBLE 2",
                        "OPERATION =",
                        "CLEAR_EXEC_HEAP"
                });


        check("double[] name = {1, 2.0, 3};",
                new String[]{
                        "DECLARE_VAR DOUBLE name 1",
                        "HEAP_VAR name",
                        "HEAP_VALUE INTEGER 3",
                        "HEAP_VALUE DOUBLE 2.0",
                        "HEAP_VALUE INTEGER 1",
                        "HEAP_VIRTUAL_ARRAY DOUBLE 3",
                        "OPERATION =",
                        "CLEAR_EXEC_HEAP"
                });
        check("double[][] name = {{1}, {2.0}, {3}};",
                new String[]{
                        "DECLARE_VAR DOUBLE name 2",
                        "HEAP_VAR name",
                        "HEAP_VALUE INTEGER 3",
                        "HEAP_VIRTUAL_ARRAY DOUBLE 1",
                        "HEAP_VALUE DOUBLE 2.0",
                        "HEAP_VIRTUAL_ARRAY DOUBLE 1",
                        "HEAP_VALUE INTEGER 1",
                        "HEAP_VIRTUAL_ARRAY DOUBLE 1",
                        "HEAP_VIRTUAL_ARRAY DOUBLE 3",
                        "OPERATION =",
                        "CLEAR_EXEC_HEAP"
                });
        check("double[] name = {{1, 4}, 2.0, 3};",
                new String[]{
                        "DECLARE_VAR DOUBLE name 1",
                        "HEAP_VAR name",
                        "HEAP_VALUE INTEGER 3",
                        "HEAP_VALUE DOUBLE 2.0",
                        "HEAP_VALUE INTEGER 4",
                        "HEAP_VALUE INTEGER 1",
                        "HEAP_VIRTUAL_ARRAY DOUBLE 2",
                        "HEAP_VIRTUAL_ARRAY DOUBLE 3",
                        "OPERATION =",
                        "CLEAR_EXEC_HEAP"
                });
        check("double[][][] name = {{{1}}, {{2.0}}, {{3}}};",
                new String[]{
                        "DECLARE_VAR DOUBLE name 3",
                        "HEAP_VAR name",
                        "HEAP_VALUE INTEGER 3",
                        "HEAP_VIRTUAL_ARRAY DOUBLE 1",
                        "HEAP_VIRTUAL_ARRAY DOUBLE 1",
                        "HEAP_VALUE DOUBLE 2.0",
                        "HEAP_VIRTUAL_ARRAY DOUBLE 1",
                        "HEAP_VIRTUAL_ARRAY DOUBLE 1",
                        "HEAP_VALUE INTEGER 1",
                        "HEAP_VIRTUAL_ARRAY DOUBLE 1",
                        "HEAP_VIRTUAL_ARRAY DOUBLE 1",
                        "HEAP_VIRTUAL_ARRAY DOUBLE 3",
                        "OPERATION =",
                        "CLEAR_EXEC_HEAP"
                });


        check("double name[] = {1, 2.0, 3};",
                new String[]{
                        "DECLARE_VAR DOUBLE name 1",
                        "HEAP_VAR name",
                        "HEAP_VALUE INTEGER 3",
                        "HEAP_VALUE DOUBLE 2.0",
                        "HEAP_VALUE INTEGER 1",
                        "HEAP_VIRTUAL_ARRAY DOUBLE 3",
                        "OPERATION =",
                        "CLEAR_EXEC_HEAP"
                });
        check("double name[][] = {{1}, {2.0}, {3}};",
                new String[]{
                        "DECLARE_VAR DOUBLE name 2",
                        "HEAP_VAR name",
                        "HEAP_VALUE INTEGER 3",
                        "HEAP_VIRTUAL_ARRAY DOUBLE 1",
                        "HEAP_VALUE DOUBLE 2.0",
                        "HEAP_VIRTUAL_ARRAY DOUBLE 1",
                        "HEAP_VALUE INTEGER 1",
                        "HEAP_VIRTUAL_ARRAY DOUBLE 1",
                        "HEAP_VIRTUAL_ARRAY DOUBLE 3",
                        "OPERATION =",
                        "CLEAR_EXEC_HEAP"
                });
        check("double name[] = {{1, 4}, 2.0, 3};",
                new String[]{
                        "DECLARE_VAR DOUBLE name 1",
                        "HEAP_VAR name",
                        "HEAP_VALUE INTEGER 3",
                        "HEAP_VALUE DOUBLE 2.0",
                        "HEAP_VALUE INTEGER 4",
                        "HEAP_VALUE INTEGER 1",
                        "HEAP_VIRTUAL_ARRAY DOUBLE 2",
                        "HEAP_VIRTUAL_ARRAY DOUBLE 3",
                        "OPERATION =",
                        "CLEAR_EXEC_HEAP"
                });
        check("double name[][][] = {{{1}}, {{2.0}}, {{3}}};",
                new String[]{
                        "DECLARE_VAR DOUBLE name 3",
                        "HEAP_VAR name",
                        "HEAP_VALUE INTEGER 3",
                        "HEAP_VIRTUAL_ARRAY DOUBLE 1",
                        "HEAP_VIRTUAL_ARRAY DOUBLE 1",
                        "HEAP_VALUE DOUBLE 2.0",
                        "HEAP_VIRTUAL_ARRAY DOUBLE 1",
                        "HEAP_VIRTUAL_ARRAY DOUBLE 1",
                        "HEAP_VALUE INTEGER 1",
                        "HEAP_VIRTUAL_ARRAY DOUBLE 1",
                        "HEAP_VIRTUAL_ARRAY DOUBLE 1",
                        "HEAP_VIRTUAL_ARRAY DOUBLE 3",
                        "OPERATION =",
                        "CLEAR_EXEC_HEAP"
                });


        check("\"test a\\\"b\\tc\";",
                new String[]{
                        "HEAP_VALUE STRING test a\"b\tc",
                        "CLEAR_EXEC_HEAP"
                });
        check("\"test\";//\"awdwa\"",
                new String[]{
                        "HEAP_VALUE STRING test",
                        "CLEAR_EXEC_HEAP"
                });
        check("\"test\"/*\"awdwa\"*/;",
                new String[]{
                        "HEAP_VALUE STRING test",
                        "CLEAR_EXEC_HEAP"
                });


        check("4==5 && 3-1>0;",
                new String[]{
                        "HEAP_VALUE INTEGER 4",
                        "HEAP_VALUE INTEGER 5",
                        "OPERATION ==",
                        "HEAP_VALUE INTEGER 3",
                        "HEAP_VALUE INTEGER 1",
                        "OPERATION -",
                        "HEAP_VALUE INTEGER 0",
                        "OPERATION >",
                        "OPERATION &&",
                        "CLEAR_EXEC_HEAP"
                });


        check("sqrt(a[8]+6*root(b[2+1], 9), 2);",
                new String[]{
                        "HEAP_VALUE INTEGER 2",
                        "HEAP_VALUE INTEGER 8",
                        "HEAP_VAR a 1",
                        "HEAP_VALUE INTEGER 6",
                        "HEAP_VALUE INTEGER 9",
                        "HEAP_VALUE INTEGER 2",
                        "HEAP_VALUE INTEGER 1",
                        "OPERATION +",
                        "HEAP_VAR b 1",
                        "EXECUTE root 2",
                        "OPERATION *",
                        "OPERATION +",
                        "EXECUTE sqrt 2",
                        "CLEAR_EXEC_HEAP"
                });
        check("sqrt(a[8]+6*root(b[2+(1)], ((9))), 2);",
                new String[]{
                        "HEAP_VALUE INTEGER 2",
                        "HEAP_VALUE INTEGER 8",
                        "HEAP_VAR a 1",
                        "HEAP_VALUE INTEGER 6",
                        "HEAP_VALUE INTEGER 9",
                        "HEAP_VALUE INTEGER 2",
                        "HEAP_VALUE INTEGER 1",
                        "OPERATION +",
                        "HEAP_VAR b 1",
                        "EXECUTE root 2",
                        "OPERATION *",
                        "OPERATION +",
                        "EXECUTE sqrt 2",
                        "CLEAR_EXEC_HEAP"
                });
    }

    @Test
    void checkError() throws Throwable {
        //returns:
        //should return:
        Queue<ByteCommand> queue = new DefaultCompilator().getCompiled("");

        for(ByteCommand bc:queue) {
            System.out.println("bc.getCommandType().toString() = " + bc.toString());
        }
    }

    private void check(String input, String[] answer) throws Throwable {
        LinkedList<ByteCommand> output = (LinkedList<ByteCommand>) new DefaultCompilator().getCompiled(input);
//        for(int i=0;i<output.size();i++) {
//            System.out.println(output.get(i).toString());
//        }

        Assertions.assertEquals(answer.length, output.size());
        for(int i=0;i<output.size();i++) {
            Assertions.assertEquals(answer[i], output.get(i).toString());
        }
    }

    @Test
    void toRpn() throws Throwable {
        String equation = "";
//        String equation = "double name[a=5];";

        List<Token> tokens = new Tokenizer(equation).getTokenized();
        for(Token token:tokens) {
            System.out.println("raw token = " + token.getData());
        }

        List<Token> cleared = new BracketsRemover().getCleared(tokens);
        LinkedList<Token> rpn = new LinkedList<>(ConvertInfixToRPN.convertInfixToRPN(cleared));

        for(Token token:rpn) {
            System.out.println("rpn token = " + token.getData());
        }
    }
}