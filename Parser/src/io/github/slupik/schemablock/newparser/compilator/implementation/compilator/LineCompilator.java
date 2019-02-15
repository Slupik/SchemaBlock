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
public class LineCompilator {

    public static List<ByteCommand> getCompiledLine(List<Token> parts) throws NameForDeclarationCannotBeFound {
        System.out.println("AGAIN");
        List<ByteCommand> compiled = new ArrayList<>();

        String lastVariableName = "";
        for(int i=0;i<parts.size();i++) {
            Token token = parts.get(i);

            /*
                    DECLARE VARIABLE
             */
            ValueType type = ValueType.getType(token.getData());
            if(type != ValueType.UNKNOWN) {
                ArrayList<Token> declarationArray = new ArrayList<>();

                int actualNestLvl = -1;
                for(i++;i<parts.size();i++) {
                    Token part = parts.get(i);

                    if(CodeUtils.isArrayStart(part)) {
                        actualNestLvl = CodeUtils.getArrayNestLvl(part);
                    } else if(CodeUtils.isArrayEnd(part)) {
                        actualNestLvl = CodeUtils.getArrayNestLvl(part)-1;
                    }

                    if("=".equals(part.getData()) && actualNestLvl == -1) {
                        break;
                    } else {
                        declarationArray.add(part);
                    }
                }
                compiled.addAll(Declaration.compile(type, declarationArray));
                continue;
            }

            if(token.isSpecialToken() && "=".equals(token.getData())) {
                continue;
            }

            /*
                    VALUE ex. 123, 2.4, 45d, "text"...
             */
            ValueType valueType = TypeParser.getType(token);
            if(valueType!= ValueType.UNKNOWN) {
                String parsedData = getParsedData(token.getData());
                compiled.add(new ByteCommandHeapValueImpl(
                        token.getLine(),
                        token.getPos(),
                        valueType,
                        parsedData));
                continue;
            }

            /*
                    OPERATION ex. = + - * ...
             */
            if(!token.isSpecialToken() && CodeUtils.isOperation(token.getData())) {
                compiled.add(new ByteCommandOperationImpl(
                        token.getLine(),
                        token.getPos(),
                        token.getData()));
                continue;
            }

            /*
                    MULTIPLE ARRAY BRACKETS ex. name[...][...]
             */
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
                compiled.remove(compiled.size()-1);

                for(int j=cmdsForIndexes.size()-1;j>=0;j--) {
                    compiled.addAll(cmdsForIndexes.get(j));
                }

                compiled.add(new ByteCommandHeapVariableImpl(
                        token.getLine(),
                        token.getPos(),
                        lastVariableName,
                        cmdsForIndexes.size()));

                continue;
            }

            /*
                    VARIABLE
             */
            if(!token.isSpecialToken()) {
                compiled.add(new ByteCommandHeapVariableImpl(
                        token.getLine(),
                        token.getPos(),
                        token.getData()));
                lastVariableName = token.getData();
            }
        }

        return compiled;
    }

    static String getParsedData(String data) {
        if(CodeUtils.isLetterForNumber(data.charAt(data.length()-1))) {
            return data.substring(0, data.length()-1);
        }
        return data;
    }
}
