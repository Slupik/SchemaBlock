package io.github.slupik.schemablock.newparser.executor.implementation;

import io.github.slupik.schemablock.model.ui.error.AlgorithmErrorType;
import io.github.slupik.schemablock.newparser.compilator.implementation.compilator.CompilationException;

/**
 * All rights reserved & copyright ©
 */
public class UnknownOperation extends CompilationException {

    public final String symbol;

    public UnknownOperation(String symbol, int line, int position) {
        super("Unknown operation marked as: " + symbol, line, position);
        this.symbol = symbol;
    }

    @Override
    public AlgorithmErrorType getType() {
        return AlgorithmErrorType.UNKNOWN_OPERATION;
    }

}
