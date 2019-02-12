package io.github.slupik.schemablock.newparser.compilator.implementation.compilator;

/**
 * All rights reserved & copyright ©
 */
public abstract class CompilationException extends Exception {

    public CompilationException(String message, int line, int pos) {
        super(message+", at line: "+line+ " and at position: "+pos);
    }
}
