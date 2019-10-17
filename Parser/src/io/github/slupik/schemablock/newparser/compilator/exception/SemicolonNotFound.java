package io.github.slupik.schemablock.newparser.compilator.exception;

import io.github.slupik.schemablock.model.ui.error.AlgorithmErrorType;
import io.github.slupik.schemablock.newparser.compilator.implementation.compilator.CompilationException;

/**
 * All rights reserved & copyright ©
 */
public class SemicolonNotFound extends CompilationException {

    public SemicolonNotFound(int line, int position) {
        super("Excepted semicolon (;) but it wasn't found.", line, position);
    }

    @Override
    public AlgorithmErrorType getType() {
        return AlgorithmErrorType.EXCEPTED_SEMICOLON;
    }

}
