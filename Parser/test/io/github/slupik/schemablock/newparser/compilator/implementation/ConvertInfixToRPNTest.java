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

        check("sqrt(3)", "3", "sqrt");
        check("sqrt(3, 2)", "3", "2", "sqrt");
        check("(2+sqrt(3, 2))*5", "2", "3", "2", "sqrt", "+", "5", "*");
        check("(2+sqrt(3, 2))*5", "2", "3", "2", "sqrt", "+", "5", "*");
        check("(2+sqrt(7+8, 2))*5", "2", "7", "8", "+", "2", "sqrt", "+", "5", "*");
        check("(2+sqrt((7+8)*9, 2))*5", "2", "7", "8", "+", "9", "*", "2", "sqrt", "+", "5", "*");
        check("double[32] b=6", "double", "32", "[]", "b", "6", "=");
        check("double[8+11] b=6", "double", "8", "11", "+", "[]", "b", "6", "=");
        check("double[32][47] b=6", "double", "32", "[]", "47", "[]", "b", "6", "=");
        check("double[8+11][98+34] b=6", "double", "8", "11", "+", "[]", "98", "34", "+", "[]", "b", "6", "=");
        check("double[][] b=new double[5][3]", "double", "[]", "[]", "b", "new", "double", "5", "[]", "3", "[]", "=");
        check("double[] b = {1.3}", "double", "[]", "b", "1", "{", "1.3", "}", "=");
        check("{1.3}", "1", "{", "1.3", "}");
        check("double[] b = {1.3, 23.4, 54.2}", "double", "[]", "b", "3", "{", "1.3", ",", "23.4", ",", "54.2", "}", "=");
        check("double[] b = {{1.3}, 23.4}", "double", "[]", "b", "2", "{", "1", "{", "1.3", "}", ",", "23.4", "}", "=");
    }

    @Test
    void toRepair() throws ComExIllegalEscapeChar {
    }

    private void check(String equation, String... excepted) throws ComExIllegalEscapeChar {
        List<Token> tokens = new Tokenizer(equation).getTokenized();
        List<Token> cleared = new BracketsRemover().getCleared(tokens);

//        for(Token token:cleared) {
//            System.out.println("cleared = " + token.getData());
//        }

        Queue<Token> queue = ConvertInfixToRPN.convertInfixToRPN(cleared);
        for(int i=0;queue.size()>0;i++) {
//            System.out.println("peek = "+queue.peek().getData());
            Assertions.assertEquals(excepted[i], queue.poll().getData());
        }
        Assertions.assertEquals(0, queue.size());
    }
}