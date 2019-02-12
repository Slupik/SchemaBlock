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
        //FIXME
        check("double name;",
                new String[]{
                        "DECLARE_VAR DOUBLE name 0",
                        "HEAP_VAR name",
                        "CLEAR_EXEC_HEAP"
        });
        Assertions.assertEquals("DECLARE_VAR DOUBLE name 2",
                ((LinkedList<ByteCommand>) new DefaultCompilator().getCompiled("double[][] name = b[c[4]+3]=3;")).get(0).toString());
//        Queue<ByteCommand> queue = new DefaultCompilator().getCompiled("\"test a\\\"b\\tc\"");//"test a\"b\tc"
//        System.out.println("=======");
//        new DefaultCompilator().getCompiled("\"test//\"\"awdwa\"");
//        System.out.println("=======");
//        new DefaultCompilator().getCompiled("\"test\"/*\"awdwa\"*/");
    }

    @Test
    void checkError() throws ComExIllegalEscapeChar, Exception {
        //FIXME should return DECLARE_VAR DOUBLE name 2
        Queue<ByteCommand> queue = new DefaultCompilator().getCompiled("double[b[5+3]+4][3+5] name;");
        for(ByteCommand bc:queue) {
            System.out.println("bc.getCommandType().toString() = " + bc.toString());
        }
    }

    private void check(String input, String[] answer) throws ComExIllegalEscapeChar, Exception {
        LinkedList<ByteCommand> output = (LinkedList<ByteCommand>) new DefaultCompilator().getCompiled(input);
        Assertions.assertEquals(answer.length, output.size());
        for(int i=0;i<output.size();i++) {
            Assertions.assertEquals(answer[i], output.get(i).toString());
        }
    }

    @Test
    void toRpn() throws ComExIllegalEscapeChar {
        String equation = "type[size] value;";
        List<Token> tokens = new Tokenizer(equation).getTokenized();
        List<Token> cleared = new BracketsRemover().getCleared(tokens);
        LinkedList<Token> rpn = new LinkedList<>(ConvertInfixToRPN.convertInfixToRPN(cleared));

        for(Token token:rpn) {
            System.out.println("token.getData() = " + token.getData());
        }
    }
}