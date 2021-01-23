package io.github.slupik.schemablock.newparser.executor.implementation.exception;

import io.github.slupik.schemablock.model.ui.error.AlgorithmErrorType;
import io.github.slupik.schemablock.newparser.compilator.implementation.compilator.CompilationException;

/**
 * All rights reserved & copyright ©
 */
public class SimpleValueExpected extends CompilationException {

    public SimpleValueExpected(int line, int pos) {
        super("Expected value which is not array but received something else.", line, pos);
    }

    @Override
    public AlgorithmErrorType getType() {
        return AlgorithmErrorType.SIMPLE_VALUE_EXPECTED;
    }
}
