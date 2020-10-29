package io.github.slupik.schemablock.newparser.compilator.implementation.compilator;

import io.github.slupik.schemablock.newparser.bytecode.bytecommand.ByteCommandType;
import io.github.slupik.schemablock.newparser.bytecode.bytecommand.abstraction.ByteCommand;
import io.github.slupik.schemablock.newparser.bytecode.bytecommand.abstraction.ByteCommandDeclareVar;
import io.github.slupik.schemablock.newparser.bytecode.bytecommand.abstraction.ByteCommandHeapVariable;
import io.github.slupik.schemablock.newparser.compilator.implementation.Token;
import io.github.slupik.schemablock.newparser.memory.element.ValueType;
import io.github.slupik.schemablock.newparser.utils.ValueTooBig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
class DeclarationTest {

    @Test
    void singleDeclaration() throws NameForDeclarationCannotBeFound, ExceptedTypeOfArray, ValueTooBig {
        List<ByteCommand> compiled = Declaration.compile(ValueType.DOUBLE,
                listOfTokens(
                        "name",
                        ";"
                ));

        Assertions.assertEquals(2, compiled.size());

        Assertions.assertEquals(ByteCommandType.DECLARE_VAR, compiled.get(0).getCommandType());
        Assertions.assertEquals("name", ((ByteCommandDeclareVar) compiled.get(0)).getName());
        Assertions.assertEquals(ValueType.DOUBLE, ((ByteCommandDeclareVar) compiled.get(0)).getType());

        Assertions.assertEquals(ByteCommandType.HEAP_VAR, compiled.get(1).getCommandType());
        Assertions.assertEquals("name", ((ByteCommandHeapVariable) compiled.get(1)).getName());
    }

    @Test
    void multipleDeclaration() throws NameForDeclarationCannotBeFound, ExceptedTypeOfArray, ValueTooBig {
        List<ByteCommand> compiled = Declaration.compile(ValueType.DOUBLE,
                listOfTokens(
                        "firstName",
                        ",",
                        "secondName",
                        ";"
                ));

        Assertions.assertEquals(4, compiled.size());

        Assertions.assertEquals(ByteCommandType.DECLARE_VAR, compiled.get(0).getCommandType());
        Assertions.assertEquals("firstName", ((ByteCommandDeclareVar) compiled.get(0)).getName());
        Assertions.assertEquals(ValueType.DOUBLE, ((ByteCommandDeclareVar) compiled.get(0)).getType());

        Assertions.assertEquals(ByteCommandType.HEAP_VAR, compiled.get(1).getCommandType());
        Assertions.assertEquals("firstName", ((ByteCommandHeapVariable) compiled.get(1)).getName());

        Assertions.assertEquals(ByteCommandType.DECLARE_VAR, compiled.get(2).getCommandType());
        Assertions.assertEquals("secondName", ((ByteCommandDeclareVar) compiled.get(2)).getName());
        Assertions.assertEquals(ValueType.DOUBLE, ((ByteCommandDeclareVar) compiled.get(2)).getType());

        Assertions.assertEquals(ByteCommandType.HEAP_VAR, compiled.get(3).getCommandType());
        Assertions.assertEquals("secondName", ((ByteCommandHeapVariable) compiled.get(3)).getName());
    }

    private List<Token> listOfTokens(String... values) {
        List<Token> result = new ArrayList<>();
        for(String value:values) {
            result.add(
                    new Token(
                            value,
                            1,
                            result.size()
                    )
            );
        }
        return result;
    }

}