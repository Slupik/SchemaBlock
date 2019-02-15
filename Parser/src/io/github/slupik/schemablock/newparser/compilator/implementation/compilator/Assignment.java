package io.github.slupik.schemablock.newparser.compilator.implementation.compilator;

import io.github.slupik.schemablock.newparser.bytecode.bytecommand.abstraction.ByteCommand;
import io.github.slupik.schemablock.newparser.bytecode.bytecommand.implementation.ByteCommandHeapValueImpl;
import io.github.slupik.schemablock.newparser.bytecode.bytecommand.implementation.ByteCommandHeapVariableImpl;
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

    static List<ByteCommand> compile(List<Token> parts) throws NameForDeclarationCannotBeFound {
        List<ByteCommand> cmds = new ArrayList<>();

        for(int i=0;i<parts.size();i++) {
            Token token = parts.get(i);
            System.out.println("compile data = " + token.getData());
        }


        String lastVariableName = "";
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

            if(token.isSpecialToken() && CodeUtils.isArrayStart(token)) {
                List<List<ByteCommand>> cmdsForIndexes = new ArrayList<>();
                List<Token> toCompile = new ArrayList<>();

                final int nestLvl = CodeUtils.getArrayNestLvl(token);
                for(i++;i<parts.size();i++) {
                    token = parts.get(i);

                    if(token.isSpecialToken() && CodeUtils.isArrayEnd(token) && CodeUtils.getArrayNestLvl(token)==nestLvl) {
                        if(toCompile==null) {
                            toCompile = new ArrayList<>();
                        }

                        List<ByteCommand> cmdLine = new ArrayList<>(LineCompilator.getCompiledLine(toCompile));
                        cmdsForIndexes.add(cmdLine);
                        toCompile=null;
                        continue;
                    }

                    if(token.isSpecialToken() && CodeUtils.isArrayStart(token) && CodeUtils.getArrayNestLvl(token)==nestLvl) {
                        toCompile = new ArrayList<>();
                        continue;
                    }

                    if(toCompile==null) {
                        i--;
                        break;
                    } else {
                        toCompile.add(token);
                    }
                }

                //Remove command HEAP_VAR arrayName
                cmds.remove(cmds.size()-1);

                for(int j=cmdsForIndexes.size()-1;j>=0;j--) {
                    cmds.addAll(cmdsForIndexes.get(j));
                }

                cmds.add(new ByteCommandHeapVariableImpl(
                        token.getLine(),
                        token.getPos(),
                        lastVariableName,
                        cmdsForIndexes.size()));

                continue;
            }

            if(!token.isSpecialToken()) {
                cmds.add(new ByteCommandHeapVariableImpl(
                        token.getLine(),
                        token.getPos(),
                        token.getData()));
                lastVariableName = token.getData();
            }
        }

        return cmds;
    }

    static String getParsedData(String data) {
        if(CodeUtils.isLetterForNumber(data.charAt(data.length()-1))) {
            return data.substring(0, data.length()-1);
        }
        return data;
    }
}
