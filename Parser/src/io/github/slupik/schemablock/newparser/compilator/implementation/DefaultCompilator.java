package io.github.slupik.schemablock.newparser.compilator.implementation;

import io.github.slupik.schemablock.newparser.bytecode.bytecommand.abstraction.ByteCommand;
import io.github.slupik.schemablock.newparser.bytecode.bytecommand.implementation.ByteCommandClearImpl;
import io.github.slupik.schemablock.newparser.bytecode.bytecommand.implementation.ByteCommandDeclareVarImpl;
import io.github.slupik.schemablock.newparser.compilator.Compilator;
import io.github.slupik.schemablock.newparser.compilator.exception.ComExIllegalEscapeChar;
import io.github.slupik.schemablock.newparser.memory.element.ValueType;
import io.github.slupik.schemablock.newparser.utils.CodeUtils;
import io.github.slupik.schemablock.newparser.utils.TextUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * All rights reserved & copyright ©
 */
public class DefaultCompilator implements Compilator {

    @Override
    public Queue<ByteCommand> getCompiled(String code) throws ComExIllegalEscapeChar {
        LinkedList<ByteCommand> commands = new LinkedList<>();

        List<Token> tokenized = new Tokenizer(code).getTokenized();
        List<Token> cleared = new BracketsRemover().getCleared(tokenized);

        List<Token> buffer = new ArrayList<>();
        for(Token token:cleared) {
            if(token.getData().equals(";")) {
                List<Token> rpn = ConvertInfixToRPN.convertInfixToRPN(buffer);
                commands.addAll(getCompiledLine(rpn, token));
                buffer.clear();
            } else {
                buffer.add(token);
            }
        }
        return commands;
    }

    private List<ByteCommand> getCompiledLine(List<Token> parts, Token end) {
        List<ByteCommand> compiled = new ArrayList<>();

        compiled.addAll(getCompiledLine(parts));

        compiled.add(new ByteCommandClearImpl(end.getLine(), end.getPos(), false));
        return compiled;
//                    sorted.add(new ByteCommandBase(
//                            line,
//                            linePos,
//                            ByteCommandType.HEAP_VALUE,
//                            ValueType.STRING,
//                            word.toString()
//                    ));
    }

    private List<ByteCommand> getCompiledLine(List<Token> parts) {
        List<ByteCommand> compiled = new ArrayList<>();

        for(int i=0;i<parts.size();i++) {
            Token token = parts.get(i);

            //Declare variable
            ValueType type = ValueType.getType(token.getData());
            if(type != ValueType.UNKNOWN) {
                List<List<Token>> dimensions = new ArrayList<>();
                List<Token> actDimension = new ArrayList<>();
                for(int j=i+1;j<parts.size();j++) {
                    Token checked = parts.get(j);
                    if(checked.getData().equals("[]")) {
                        dimensions.add(actDimension);
                        actDimension = new ArrayList<>();
                        continue;
                    }
                    if(CodeUtils.isOperation(token.getData()) || TextUtils.isNumber(token.getData())) {
                        actDimension.add(token);
                        continue;
                    }
                    if(!(j+1<parts.size() && parts.get(j).getData().equals("("))) {
                        i=j;
                    }
                }
                token = parts.get(i);//var name

                compiled.add(new ByteCommandDeclareVarImpl(
                        token.getLine(),
                        token.getPos(),
                        type,
                        token.getData(),
                        dimensions.size()
                ));
            }
        }

        return compiled;
    }
}
