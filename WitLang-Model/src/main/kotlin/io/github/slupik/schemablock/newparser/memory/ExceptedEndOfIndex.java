package io.github.slupik.schemablock.newparser.memory;

import io.github.slupik.schemablock.model.ui.error.AlgorithmErrorType;
import io.github.slupik.schemablock.newparser.compilator.implementation.compilator.CompilationException;

/**
 * All rights reserved & copyright ©
 */
public class ExceptedEndOfIndex extends CompilationException {

    public ExceptedEndOfIndex(int line, int position) {
        super("Excepted char ']' but not received it", line, position);
    }

    @Override
    public AlgorithmErrorType getType() {
        return AlgorithmErrorType.UNEXPECTED_CHAR_BETWEEN_INDEXES;
    }

}
