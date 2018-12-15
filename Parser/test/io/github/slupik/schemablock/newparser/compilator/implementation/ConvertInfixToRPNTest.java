package io.github.slupik.schemablock.newparser.compilator.implementation;

import io.github.slupik.schemablock.newparser.compilator.exception.ComExIllegalEscapeChar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Queue;

/**
 * All rights reserved & copyright ©
 */
class ConvertInfixToRPNTest {

    @Test
    void convertInfixToRPN() throws ComExIllegalEscapeChar {
        check("(2+3)*5", "2", "3", "+", "5", "*");
        check("((2+7)/3+(14-3)*4)/2", "2", "7", "+", "3", "/", "14", "3", "-", "4", "*", "+", "2", "/");
        check("(2+3)*a", "2", "3", "+", "a", "*");
        check("  ( 2  + \t 3) *  a  ", "2", "3", "+", "a", "*");
        check("(2+3)%5", "2", "3", "+", "5", "%");
        check("b=(2+3)*5", "b", "2", "3", "+", "5", "*", "=");
        check("b=(2+3)*5", "b", "2", "3", "+", "5", "*", "=");
        check("double b=(2+3)*5", "double", "b", "2", "3", "+", "5", "*", "=");
        check("double[][] b=(2+3)*5", "double", "[]", "[]", "b", "2", "3", "+", "5", "*", "=");
        check("double[][] b=(c=4)*5", "double", "[]", "[]", "b", "c", "4", "=", "5", "*", "=");
    }

    @Test
    void toRepair() throws ComExIllegalEscapeChar {

    }

    private void check(String equation, String... excepted) throws ComExIllegalEscapeChar {
        List<Token> tokens = new Tokenizer(equation).getTokenized();
        List<Token> cleared = new BracketsRemover().getCleared(tokens);
        String[] splited = new String[cleared.size()];
        for(int i=0;i<cleared.size();i++) {
            splited[i] = cleared.get(i).getData();
//            System.out.println("cleared = "+cleared.get(i).getData());
        }

        Queue<String> queue = ConvertInfixToRPN.convertInfixToRPN(splited);
        Assertions.assertEquals(excepted.length, queue.size());
        for(int i=0;i<queue.size();i++) {
//            System.out.println("peek = "+queue.peek());
            Assertions.assertEquals(excepted[i], queue.poll());
        }
    }
}