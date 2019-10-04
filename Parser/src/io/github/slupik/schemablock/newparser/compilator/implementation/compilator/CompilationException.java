package io.github.slupik.schemablock.newparser.compilator.implementation.compilator;

import io.github.slupik.schemablock.model.ui.error.AlgorithmException;
import io.github.slupik.schemablock.newparser.compilator.implementation.Token;

/**
 * All rights reserved & copyright ©
 */
public abstract class CompilationException extends AlgorithmException {

    public CompilationException(Token token, String message) {
        this(message, token.getLine(), token.getPos());
    }

    public CompilationException(String message, int line, int pos) {
        super(message+", at line: "+line+ " and at position: "+pos);
    }
}
