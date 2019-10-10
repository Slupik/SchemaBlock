package io.github.slupik.schemablock.model.ui.error;

/**
 * All rights reserved & copyright ©
 */
public class UnkownError extends AlgorithmException {

    public UnkownError() {
        super("There was occurred unknown error.");
    }

    @Override
    public AlgorithmErrorType getType() {
        return AlgorithmErrorType.UNKNOWN_ERROR;
    }

}
