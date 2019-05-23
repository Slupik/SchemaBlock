package io.github.slupik.schemablock.parser.math.rpn.pattern;

import io.github.slupik.schemablock.model.ui.error.AlgorithmErrorType;
import io.github.slupik.schemablock.model.ui.error.AlgorithmException;

/**
 * All rights reserved & copyright ©
 */
public class InvalidArgumentsException extends AlgorithmException {
    public InvalidArgumentsException(String token) {
        super("Invalid argument for token: "+token);
    }

    public InvalidArgumentsException() {
        super("");
    }

    @Override
    public AlgorithmErrorType getType() {
        return AlgorithmErrorType.INVALID_ARGUMENT;
    }
}
