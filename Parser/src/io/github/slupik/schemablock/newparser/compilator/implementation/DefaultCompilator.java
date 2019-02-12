package io.github.slupik.schemablock.newparser.compilator.implementation;

import io.github.slupik.schemablock.newparser.bytecode.bytecommand.abstraction.ByteCommand;
import io.github.slupik.schemablock.newparser.bytecode.bytecommand.implementation.ByteCommandClearImpl;
import io.github.slupik.schemablock.newparser.compilator.Compilator;
import io.github.slupik.schemablock.newparser.compilator.exception.ComExIllegalEscapeChar;
import io.github.slupik.schemablock.newparser.compilator.implementation.compilator.LineCompilator;
import io.github.slupik.schemablock.newparser.compilator.implementation.compilator.NameForDeclarationCannotBeFound;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * All rights reserved & copyright ©
 */
public class DefaultCompilator implements Compilator {

    @Override
    public Queue<ByteCommand> getCompiled(String code) throws ComExIllegalEscapeChar, NameForDeclarationCannotBeFound {
        LinkedList<ByteCommand> commands = new LinkedList<>();

        List<Token> tokenized = new Tokenizer(code).getTokenized();
        List<Token> cleared = new BracketsRemover().getCleared(tokenized);

        List<Token> buffer = new ArrayList<>();
        for(Token token:cleared) {
            if(token.getData().equals(";")) {
                List<Token> rpn = ConvertInfixToRPN.convertInfixToRPN(buffer);
                for(Token rToken:rpn) {
                    //TODO remove debugging
                    System.out.println("rToken = " + rToken.getData());
                }
                commands.addAll(getCompiledLine(rpn, token));
                buffer.clear();
            } else {
                buffer.add(token);
            }
        }
        return commands;
    }

    private List<ByteCommand> getCompiledLine(List<Token> parts, Token end) throws NameForDeclarationCannotBeFound {

        List<ByteCommand> compiled = new ArrayList<>(new LineCompilator().getCompiledLine(parts));

        compiled.add(new ByteCommandClearImpl(end.getLine(), end.getPos(), false));
        return compiled;
    }

}
