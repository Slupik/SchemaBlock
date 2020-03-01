package io.github.slupik.schemablock.newparser.compilator.implementation.compilator;

import io.github.slupik.schemablock.newparser.bytecode.bytecommand.abstraction.ByteCommand;
import io.github.slupik.schemablock.newparser.bytecode.bytecommand.implementation.ByteCommandHeapVirArrImpl;
import io.github.slupik.schemablock.newparser.compilator.implementation.Token;
import io.github.slupik.schemablock.newparser.memory.element.ValueType;
import io.github.slupik.schemablock.newparser.utils.ValueTooBig;

import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
class ElementsArrayCompiler {

    static ElementsArrayData compile(List<Token> parts, ValueType arrayType) throws NameForDeclarationCannotBeFound, ExceptedTypeOfArray, ValueTooBig {
        List<ByteCommand> compiled = new ArrayList<>();
        List<List<ByteCommand>> cmdsForIndexes = new ArrayList<>();
        List<Token> toCompile = new ArrayList<>();

        int nestLvl = 0;
        int i=1;
        for(;i<parts.size();i++) {
            Token token = parts.get(i);

            if("{".equals(token.getData())) {
                nestLvl++;
            } else if("}".equals(token.getData())) {
                nestLvl--;
            }

            if(nestLvl>=1) {
                if(",".equals(token.getData())) {
                    List<ByteCommand> cmdLine = new ArrayList<>(LineCompilator.getCompiledLine(toCompile, arrayType));
                    cmdsForIndexes.add(cmdLine);
                    toCompile = new ArrayList<>();
                } else {
                    if(nestLvl>1 || !("{".equals(token.getData()) || "}".equals(token.getData()))) {
                        toCompile.add(token);
                    }
                }
            } else {
                break;
            }
        }
        List<ByteCommand> cmdLine = new ArrayList<>(LineCompilator.getCompiledLine(toCompile, arrayType));
        cmdsForIndexes.add(cmdLine);

        for(int j=cmdsForIndexes.size()-1;j>=0;j--) {
            compiled.addAll(cmdsForIndexes.get(j));
        }

        int arraySize = Integer.parseInt(parts.get(0).getData());
        compiled.add(new ByteCommandHeapVirArrImpl(
                parts.get(1).getLine(),
                parts.get(1).getPos(),
                arrayType,
                arraySize,
                false));

        ElementsArrayData result = new ElementsArrayData();
        result.cmds = compiled;
        result.elementsCount = arraySize;
        result.elementsToOmitted = i-1;

        return result;
    }

    private static boolean isInteger(Token token) {
        try {
            Integer.parseInt(token.getData());
            return true;
        } catch (Exception ignore) {}
        return true;
    }
}
