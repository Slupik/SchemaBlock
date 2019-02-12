package io.github.slupik.schemablock.newparser.compilator.implementation.compilator;

import io.github.slupik.schemablock.newparser.bytecode.bytecommand.abstraction.ByteCommand;
import io.github.slupik.schemablock.newparser.compilator.implementation.Token;
import io.github.slupik.schemablock.newparser.memory.element.ValueType;

import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public class LineCompilator {

    public List<ByteCommand> getCompiledLine(List<Token> parts) throws NameForDeclarationCannotBeFound {
        List<ByteCommand> compiled = new ArrayList<>();

        for(int i=0;i<parts.size();i++) {
            Token token = parts.get(i);

            //Declare variable
            ValueType type = ValueType.getType(token.getData());
            if(type != ValueType.UNKNOWN) {
                ArrayList<Token> declarationArray = new ArrayList<>();

                for(int j=i+1;j<parts.size();j++) {
                    Token part = parts.get(j);
                    if("=".equals(part.getData())) {
                        i=j;
                        break;
                    } else {
                        //TODO remove debugging
                        System.out.println("decl part.getData() = " + part.getData());
                        declarationArray.add(part);
                    }
                }
                compiled.addAll(Declaration.compile(type, declarationArray));
            }
        }

        return compiled;
    }
}
