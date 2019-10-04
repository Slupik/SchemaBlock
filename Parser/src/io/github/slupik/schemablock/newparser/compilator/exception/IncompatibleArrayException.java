package io.github.slupik.schemablock.newparser.compilator.exception;

import io.github.slupik.schemablock.model.ui.error.AlgorithmErrorType;
import io.github.slupik.schemablock.model.ui.error.AlgorithmException;

/**
 * All rights reserved & copyright ©
 */
public class IncompatibleArrayException extends AlgorithmException {

    private final int excepted;
    private final int actual;

    public IncompatibleArrayException(int excepted, int actual) {
        super("Excepted array with "+excepted+" dimensions but received "+actual);
        this.excepted = excepted;
        this.actual = actual;
    }

    public int getExcepted() {
        return excepted;
    }

    public int getActual() {
        return actual;
    }

    @Override
    public AlgorithmErrorType getType() {
        return AlgorithmErrorType.INCOMPATIBLE_TYPE;
    }

}
