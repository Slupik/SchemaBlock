package io.github.slupik.schemablock.newparser.memory;

import io.github.slupik.schemablock.model.ui.error.AlgorithmErrorType;
import io.github.slupik.schemablock.newparser.compilator.implementation.compilator.CompilationException;

/**
 * All rights reserved & copyright ©
 */
public class UnexpectedCharBetweenIndexes extends CompilationException {

    public UnexpectedCharBetweenIndexes(int line, int position, char errorChar) {
        super("Excepted next index but received: " + errorChar, line, position);
    }

    @Override
    public AlgorithmErrorType getType() {
        return AlgorithmErrorType.UNEXPECTED_CHAR_BETWEEN_INDEXES;
    }

}
