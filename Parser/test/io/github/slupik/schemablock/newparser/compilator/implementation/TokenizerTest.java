package io.github.slupik.schemablock.newparser.compilator.implementation;

import io.github.slupik.schemablock.newparser.compilator.exception.ComExIllegalEscapeChar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * All rights reserved & copyright ©
 */
class TokenizerTest {

    @Test
    void getTokenized() throws ComExIllegalEscapeChar {
        checkTokenization("double abc = 2343.2;", "double", "abc", "=", "2343.2", ";");
        checkTokenization("double abc = 2343.2d;", "double", "abc", "=", "2343.2d", ";");
        checkTokenization("double[][] abc = 2343.2;", "double", "[", "]", "[", "]", "abc", "=", "2343.2", ";");
        checkTokenization("double    []    []  \t   abc       =       2343.2;", "double", "[", "]", "[", "]", "abc", "=", "2343.2", ";");
        checkTokenization("String abc = \"test \\n fefw 4234.543 \\\" gerhr\";", "String", "abc", "=", "test \n fefw 4234.543 \" gerhr", ";");
        checkTokenization("double abc = Math.pow(2343.2, 3);", "double", "abc", "=", "Math.pow", "(", "2343.2", ",", "3", ")", ";");

        Assertions.assertThrows(ComExIllegalEscapeChar.class, () -> checkTokenization("String abc = \"aa\\a\"", ""));
    }

    private void checkTokenization(String data, String... excepted) throws ComExIllegalEscapeChar {
        List<Token> tokens = new Tokenizer(data).getTokenized();
//        for(Token token:tokens) {
//            System.out.println(token.getData());
//        }
        Assertions.assertEquals(excepted.length, tokens.size());
        for(int i=0;i<tokens.size();i++) {
            Assertions.assertEquals(excepted[i], tokens.get(i).getData());
        }
    }
}