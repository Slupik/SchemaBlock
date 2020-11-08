package io.github.slupik.schemablock.model.ui.error;

/**
 * All rights reserved & copyright ©
 */
public class UnknownError extends AlgorithmException {

    public UnknownError() {
        super("There was occurred unknown error.");
    }

    @Override
    public AlgorithmErrorType getType() {
        return AlgorithmErrorType.UNKNOWN_ERROR;
    }

}
