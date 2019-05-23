package io.github.slupik.schemablock.model.ui.error;

/**
 * All rights reserved & copyright ©
 */
public abstract class AlgorithmException extends Exception {

    public AlgorithmException(String error) {
        super(error);
    }

    public abstract AlgorithmErrorType getType();
}
