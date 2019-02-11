package io.github.slupik.schemablock.newparser.compilator.implementation;

import io.github.slupik.schemablock.newparser.compilator.exception.ComExIllegalEscapeChar;
import org.junit.jupiter.api.Assertions;

import java.util.List;

/**
 * All rights reserved & copyright ©
 */
class BracketsRemoverTest {

//    @Test
    void getCleared() throws ComExIllegalEscapeChar {
        //TODO check is this need
        checkOptimization("()", "()");
        checkOptimization("[]", "[]");


        checkOptimization("value", "value");
        checkOptimization("(value)", "value");
        checkOptimization("(v1+v2)", "(", "v1", "+", "v2", ")");
        checkOptimization("((v1+v2))", "(", "v1", "+", "v2", ")");
        checkOptimization("(((v1)+v2))", "(", "v1", "+", "v2", ")");
        checkOptimization("(((v1)+v2))+((v1+v2))", "(", "v1", "+", "v2", ")", "+", "(", "v1", "+", "v2", ")");
        checkOptimization("b+(((v1)+v2))+((v1+v2))", "b", "+", "(", "v1", "+", "v2", ")", "+", "(", "v1", "+", "v2", ")");
        checkOptimization("b+(((v1)+v2))+((v1+v2))+c", "b", "+", "(", "v1", "+", "v2", ")", "+", "(", "v1", "+", "v2", ")", "+", "c");
        checkOptimization("b+(((v1)+v2))+((v1+v2))+c", "b", "+", "(", "v1", "+", "v2", ")", "+", "(", "v1", "+", "v2", ")", "+", "c");


        checkOptimization("sqrt(value)", "sqrt", "(", "value", ")");
        checkOptimization("sqrt((((v1)+v2))+((v1+v2)))", "sqrt", "(", "(", "v1", "+", "v2", ")", "+", "(", "v1", "+", "v2", ")", ")");
        checkOptimization("sqrt(b+(((v1)+v2))+((v1+v2))+c)", "sqrt", "(", "b", "+", "(", "v1", "+", "v2", ")", "+", "(", "v1", "+", "v2", ")", "+", "c", ")");


        checkOptimization("(value[5])", "value", "[", "5", "]");
        checkOptimization("sqrt(value[5])", "sqrt", "(", "value", "[", "5", "]", ")");
        checkOptimization("sqrt((((value[5])+v2))+((value[5]+v2)))", "sqrt", "(", "(", "value", "[", "5", "]", "+", "v2", ")", "+", "(", "value", "[", "5", "]", "+", "v2", ")", ")");
    }

    private void checkOptimization(String data, String... excepted) throws ComExIllegalEscapeChar {
        checkOptimization(new Tokenizer(data).getTokenized(), excepted);
    }

    private void checkOptimization(List<Token> data, String... excepted) {
        List<Token> tokens = new BracketsRemover().getCleared(data);
//        for(Token token:tokens) {
//            System.out.println(token.getData());
//        }
        Assertions.assertEquals(excepted.length, tokens.size());
        for(int i=0;i<tokens.size();i++) {
            Assertions.assertEquals(excepted[i], tokens.get(i).getData());
        }
    }
}