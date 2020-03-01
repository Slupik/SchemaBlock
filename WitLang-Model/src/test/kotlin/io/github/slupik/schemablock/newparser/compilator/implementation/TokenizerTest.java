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
        checkTokenization("double abc=2343.2d;", "double", "abc", "=", "2343.2d", ";");
        checkTokenization("double abc=1==2;", "double", "abc", "=", "1", "==", "2", ";");
        checkTokenization("double abc=1!=2;", "double", "abc", "=", "1", "!=", "2", ";");
        checkTokenization("double abc=1&&2;", "double", "abc", "=", "1", "&&", "2", ";");
        checkTokenization("double[][] abc = 2343.2;", "double", "[", "]", "[", "]", "abc", "=", "2343.2", ";");
        checkTokenization("double    []    []  \t   abc       =       2343.2;", "double", "[", "]", "[", "]", "abc", "=", "2343.2", ";");
        checkTokenization("String abc = \"test \\n fefw 4234.543 \\\" gerhr\";", "String", "abc", "=", "\"test \n fefw 4234.543 \" gerhr\"", ";");
        checkTokenization("String abc = \"test \\t \\b \\n \\r \\f \\' \\\" \\\\ \";", "String", "abc", "=", "\"test \t \b \n \r \f \' \" \\ \"", ";");
        checkTokenization("double abc = Math.pow(2343.2, 3);", "double", "abc", "=", "Math.pow", "(", "2343.2", ",", "3", ")", ";");

        checkTokenization("\ndouble abc\n \n \n= 2343.2;", "double", "abc", "=", "2343.2", ";");

        Assertions.assertThrows(ComExIllegalEscapeChar.class, () -> checkTokenization("String abc = \"aa\\a\"", ""));
    }

    @Test
    void checkPosition() throws Throwable {
        List<Token> tokens = new Tokenizer("\n\ndouble \na = \n2343.2;").getTokenized();
        Assertions.assertEquals(3, tokens.get(0).getLine());
        Assertions.assertEquals(4, tokens.get(1).getLine());
        Assertions.assertEquals(4, tokens.get(2).getLine());
        Assertions.assertEquals(5, tokens.get(3).getLine());

        Assertions.assertEquals(0, tokens.get(0).getPos());
        Assertions.assertEquals(0, tokens.get(1).getPos());
        Assertions.assertEquals(2, tokens.get(2).getPos());
        Assertions.assertEquals(0, tokens.get(3).getPos());

        tokens = new Tokenizer("\n\ndouble \na != \n2343.2;").getTokenized();
        Assertions.assertEquals(3, tokens.get(0).getLine());
        Assertions.assertEquals(4, tokens.get(1).getLine());
        Assertions.assertEquals(4, tokens.get(2).getLine());
        Assertions.assertEquals(5, tokens.get(3).getLine());

        Assertions.assertEquals(0, tokens.get(0).getPos());
        Assertions.assertEquals(0, tokens.get(1).getPos());
        Assertions.assertEquals(2, tokens.get(2).getPos());
        Assertions.assertEquals(0, tokens.get(3).getPos());

        tokens = new Tokenizer("\n\ndouble \na abc \n2343.2;").getTokenized();
        Assertions.assertEquals(3, tokens.get(0).getLine());
        Assertions.assertEquals(4, tokens.get(1).getLine());
        Assertions.assertEquals(4, tokens.get(2).getLine());
        Assertions.assertEquals(5, tokens.get(3).getLine());

        Assertions.assertEquals(0, tokens.get(0).getPos());
        Assertions.assertEquals(0, tokens.get(1).getPos());
        Assertions.assertEquals(2, tokens.get(2).getPos());
        Assertions.assertEquals(0, tokens.get(3).getPos());
    }

    @Test
    void toRepair() throws ComExIllegalEscapeChar {
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