package io.github.slupik.schemablock.newparser.executor.implementation;

import io.github.slupik.schemablock.model.ui.error.AlgorithmErrorType;
import io.github.slupik.schemablock.model.ui.error.AlgorithmException;

/**
 * All rights reserved & copyright ©
 */
public class UnknownOperation extends AlgorithmException {

    public final String symbol;

    public UnknownOperation(String symbol) {
        super("Unknown operation marked as: " + symbol);
        this.symbol = symbol;
    }

    @Override
    public AlgorithmErrorType getType() {
        return AlgorithmErrorType.UNKNOWN_OPERATION;
    }

}
