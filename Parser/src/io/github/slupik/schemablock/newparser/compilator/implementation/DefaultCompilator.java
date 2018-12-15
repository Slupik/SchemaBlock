package io.github.slupik.schemablock.newparser.compilator.implementation;

import io.github.slupik.schemablock.newparser.bytecode.ByteCommand;
import io.github.slupik.schemablock.newparser.compilator.Compilator;
import io.github.slupik.schemablock.newparser.compilator.exception.ComExIllegalEscapeChar;

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
                commands.addAll(getCompiledLine(buffer));
                buffer.clear();
            } else {
                buffer.add(token);
            }
        }

        return commands;
    }

    private List<ByteCommand> getCompiledLine(List<Token> parts) {
        List<ByteCommand> compiled = new ArrayList<>();



        return compiled;
//                    sorted.add(new ByteCommandImpl(
//                            line,
//                            linePos,
//                            ByteCommandType.HEAP,
//                            VariableType.STRING,
//                            word.toString()
//                    ));
    }
}
