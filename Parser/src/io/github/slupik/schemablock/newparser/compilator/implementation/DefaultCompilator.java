package io.github.slupik.schemablock.newparser.compilator.implementation;

import io.github.slupik.schemablock.newparser.bytecode.bytecommand.abstraction.ByteCommand;
import io.github.slupik.schemablock.newparser.bytecode.bytecommand.implementation.ByteCommandClearImpl;
import io.github.slupik.schemablock.newparser.compilator.Compilator;
import io.github.slupik.schemablock.newparser.compilator.exception.ComExIllegalEscapeChar;
import io.github.slupik.schemablock.newparser.compilator.exception.MissingSemicolon;
import io.github.slupik.schemablock.newparser.compilator.exception.SemicolonNotFound;
import io.github.slupik.schemablock.newparser.compilator.implementation.compilator.ExceptedTypeOfArray;
import io.github.slupik.schemablock.newparser.compilator.implementation.compilator.LineCompilator;
import io.github.slupik.schemablock.newparser.compilator.implementation.compilator.NameForDeclarationCannotBeFound;
import io.github.slupik.schemablock.newparser.utils.ValueTooBig;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * All rights reserved & copyright ©
 */
public class DefaultCompilator implements Compilator {

    @Override
    public Queue<ByteCommand> getCompiled(String code) throws ComExIllegalEscapeChar, NameForDeclarationCannotBeFound, ExceptedTypeOfArray, ValueTooBig, SemicolonNotFound, MissingSemicolon {
        return getCompiled(code, false);
    }

    @Override
    public Queue<ByteCommand> getCompiled(String code, boolean forResult) throws ComExIllegalEscapeChar, NameForDeclarationCannotBeFound, ExceptedTypeOfArray, ValueTooBig, SemicolonNotFound, MissingSemicolon {
        LinkedList<ByteCommand> commands = new LinkedList<>();

        List<Token> tokenized = new Tokenizer(code).getTokenized();
        List<Token> cleared = new BracketsRemover().getCleared(tokenized);

        if(forResult) {
            for(int i=cleared.size()-1;i>=0;i--) {
                if(cleared.get(i).getData().equals(";")) {
                    cleared.remove(i);
                } else {
                    break;
                }
            }
            List<Token> rpn = ConvertInfixToRPN.convertInfixToRPN(cleared);
            commands.addAll(getCompiledLine(rpn, cleared.get(cleared.size()-1), false));
        } else {
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
            if(buffer.size()>0) {
                Token lastToken = buffer.get(buffer.size()-1);
                throw new SemicolonNotFound(lastToken.getLine(), lastToken.getPos()+1);
            }
        }
        return commands;
    }

    private List<ByteCommand> getCompiledLine(List<Token> parts, Token end) throws NameForDeclarationCannotBeFound, ExceptedTypeOfArray, ValueTooBig {
        return getCompiledLine(parts, end, true);
    }

    private List<ByteCommand> getCompiledLine(List<Token> parts, Token end, boolean clearRegister) throws NameForDeclarationCannotBeFound, ExceptedTypeOfArray, ValueTooBig {
        List<ByteCommand> compiled = new ArrayList<>(LineCompilator.getCompiledLine(parts));
        if(clearRegister) {
            compiled.add(new ByteCommandClearImpl(end.getLine(), end.getPos(), false));
        }
        return compiled;
    }

}
