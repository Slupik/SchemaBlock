package io.github.slupik.schemablock.newparser.compilator.implementation.compilator;

import io.github.slupik.schemablock.newparser.bytecode.bytecommand.abstraction.ByteCommand;
import io.github.slupik.schemablock.newparser.bytecode.bytecommand.implementation.ByteCommandHeapValueImpl;
import io.github.slupik.schemablock.newparser.compilator.implementation.Token;
import io.github.slupik.schemablock.newparser.memory.element.ValueType;
import io.github.slupik.schemablock.newparser.utils.CodeUtils;
import io.github.slupik.schemablock.newparser.utils.TypeParser;

import java.util.ArrayList;
import java.util.List;

import static io.github.slupik.schemablock.newparser.compilator.implementation.compilator.Assignment.getParsedData;

/**
 * All rights reserved & copyright ©
 */
public class LineCompilator {

    public static List<ByteCommand> getCompiledLine(List<Token> parts) throws NameForDeclarationCannotBeFound {
        System.out.println("AGAIN");
        List<ByteCommand> compiled = new ArrayList<>();

        for(int i=0;i<parts.size();i++) {
            Token token = parts.get(i);

            //Declare variable
            ValueType type = ValueType.getType(token.getData());
            if(type != ValueType.UNKNOWN) {
                ArrayList<Token> declarationArray = new ArrayList<>();

                int actualNestLvl = -1;
                for(int j=i+1;j<parts.size();j++) {
                    Token part = parts.get(j);

                    if(CodeUtils.isArrayStart(part)) {
                        actualNestLvl = CodeUtils.getArrayNestLvl(part);
                    } else if(CodeUtils.isArrayEnd(part)) {
                        actualNestLvl = CodeUtils.getArrayNestLvl(part)-1;
                    }

                    if("=".equals(part.getData()) && actualNestLvl == -1) {
                        i=j;
                        token=part;
                        break;
                    } else {
                        declarationArray.add(part);
                    }
                }
                compiled.addAll(Declaration.compile(type, declarationArray));
            }

            if(token.isSpecialToken() && "=".equals(token.getData())) {
                compiled.addAll(Assignment.compile(parts.subList(i+1, parts.size())));
                break;
            }


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
        }

        return compiled;
    }
}
