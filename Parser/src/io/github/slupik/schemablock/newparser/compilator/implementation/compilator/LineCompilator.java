package io.github.slupik.schemablock.newparser.compilator.implementation.compilator;

import io.github.slupik.schemablock.newparser.bytecode.bytecommand.abstraction.ByteCommand;
import io.github.slupik.schemablock.newparser.bytecode.bytecommand.abstraction.ByteCommandHeapValue;
import io.github.slupik.schemablock.newparser.bytecode.bytecommand.implementation.ByteCommandExecuteImpl;
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
                BracketsData data = BracketsCompiler.compile(parts.subList(i, parts.size()));

                //Remove command HEAP_VAR arrayName
                compiled.remove(compiled.size()-1);

                compiled.addAll(data.cmds);

                compiled.add(new ByteCommandHeapVariableImpl(
                        token.getLine(),
                        token.getPos(),
                        lastVariableName,
                        data.dimensions));
                i+=data.elementsCount;

                continue;
            }

            /*
                    FUNCTION
             */
            if(token.isFunction()) {
                ByteCommand argumentsCount = compiled.get(compiled.size()-1);
                compiled.remove(compiled.size()-1);

                compiled.add(new ByteCommandExecuteImpl(
                        token.getLine(),
                        token.getPos(),
                        token.getData(),
                        Integer.parseInt(((ByteCommandHeapValue) argumentsCount).getRawValue())) {
                });
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
