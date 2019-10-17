package io.github.slupik.schemablock.newparser.function.exception;

import io.github.slupik.schemablock.model.ui.error.AlgorithmException;

/**
 * All rights reserved & copyright ©
 */
public abstract class FunctionExecutionException extends AlgorithmException {

    public FunctionExecutionException(String error) {
        super(error);
    }

}
