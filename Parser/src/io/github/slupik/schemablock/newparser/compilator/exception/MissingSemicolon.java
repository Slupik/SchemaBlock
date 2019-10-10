package io.github.slupik.schemablock.newparser.compilator.exception;

import io.github.slupik.schemablock.model.ui.error.AlgorithmErrorType;
import io.github.slupik.schemablock.newparser.compilator.implementation.compilator.CompilationException;

/**
 * All rights reserved & copyright ©
 */
public class MissingSemicolon extends CompilationException {

    public MissingSemicolon(int line, int position) {
        super("Probably semicolon is missed.", line, position);
    }

    @Override
    public AlgorithmErrorType getType() {
        return AlgorithmErrorType.PROBABLY_EXCEPTED_SEMICOLON;
    }

}
