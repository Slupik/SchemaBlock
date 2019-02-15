package io.github.slupik.schemablock.newparser.compilator.implementation.compilator;

import io.github.slupik.schemablock.newparser.bytecode.bytecommand.abstraction.ByteCommand;
import io.github.slupik.schemablock.newparser.compilator.implementation.Token;
import io.github.slupik.schemablock.newparser.utils.CodeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
class BracketsCompiler {

    static BracketsData compile(List<Token> parts) throws NameForDeclarationCannotBeFound {
        List<ByteCommand> compiled = new ArrayList<>();
        List<List<ByteCommand>> cmdsForIndexes = new ArrayList<>();
        List<Token> toCompile = new ArrayList<>();

        final int nestLvl = CodeUtils.getArrayNestLvl(parts.get(0));
        int i=1;
        for(;i<parts.size();i++) {
            Token token = parts.get(i);

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

        for(int j=cmdsForIndexes.size()-1;j>=0;j--) {
            compiled.addAll(cmdsForIndexes.get(j));
        }

        BracketsData result = new BracketsData();
        result.cmds = compiled;
        result.dimensions = cmdsForIndexes.size();
        result.elementsCount = i-1;

        return result;
    }
}
