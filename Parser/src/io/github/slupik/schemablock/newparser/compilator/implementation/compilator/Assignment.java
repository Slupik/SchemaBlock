package io.github.slupik.schemablock.newparser.compilator.implementation.compilator;

import io.github.slupik.schemablock.newparser.bytecode.bytecommand.abstraction.ByteCommand;
import io.github.slupik.schemablock.newparser.bytecode.bytecommand.implementation.ByteCommandHeapValueImpl;
import io.github.slupik.schemablock.newparser.bytecode.bytecommand.implementation.ByteCommandOperationImpl;
import io.github.slupik.schemablock.newparser.compilator.implementation.Token;
import io.github.slupik.schemablock.newparser.memory.element.ValueType;
import io.github.slupik.schemablock.newparser.utils.CodeUtils;
import io.github.slupik.schemablock.newparser.utils.TypeParser;

import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public class Assignment {

    static List<ByteCommand> compile(List<Token> parts) {
        List<ByteCommand> cmds = new ArrayList<>();

        for(int i=0;i<parts.size();i++) {
            Token token = parts.get(i);
            System.out.println("ass: token.getData() = " + token.getData());

            ValueType valueType = TypeParser.getType(token);
            if(valueType!= ValueType.UNKNOWN) {
                String parsedData = getParsedData(token.getData());
                cmds.add(new ByteCommandHeapValueImpl(
                        token.getLine(),
                        token.getPos(),
                        valueType,
                        parsedData));
                continue;
            }

            if(!token.isSpecialToken() && CodeUtils.isOperation(token.getData())) {
                cmds.add(new ByteCommandOperationImpl(
                        token.getLine(),
                        token.getPos(),
                        token.getData()));
                continue;
            }
        }

        return cmds;
    }

    private static String getParsedData(String data) {
        if(CodeUtils.isLetterForNumber(data.charAt(data.length()-1))) {
            return data.substring(0, data.length()-1);
        }
        return data;
    }
}
