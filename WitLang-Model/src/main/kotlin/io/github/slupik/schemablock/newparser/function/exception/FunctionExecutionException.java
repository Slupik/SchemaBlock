package io.github.slupik.schemablock.newparser.function.exception;

import io.github.slupik.schemablock.newparser.compilator.implementation.compilator.CompilationException;

/**
 * All rights reserved & copyright ©
 */
public abstract class FunctionExecutionException extends CompilationException {

    public FunctionExecutionException(String error, int line, int position) {
        super(error, line, position);
    }

}
