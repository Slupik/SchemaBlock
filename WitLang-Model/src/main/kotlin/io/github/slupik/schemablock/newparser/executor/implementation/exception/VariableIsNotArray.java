package io.github.slupik.schemablock.newparser.executor.implementation.exception;

import io.github.slupik.schemablock.model.ui.error.AlgorithmErrorType;
import io.github.slupik.schemablock.newparser.compilator.implementation.compilator.CompilationException;

/**
 * All rights reserved & copyright ©
 */
public class VariableIsNotArray extends CompilationException {

    private final String variableName;

    public VariableIsNotArray(String variableName, int line, int pos) {
        super("Variable '" + variableName + "' is not array but it should be.", line, pos);
        this.variableName = variableName;
    }

    @Override
    public AlgorithmErrorType getType() {
        return AlgorithmErrorType.SIMPLE_VALUE_EXPECTED;
    }

    public String getVariableName() {
        return variableName;
    }
}
