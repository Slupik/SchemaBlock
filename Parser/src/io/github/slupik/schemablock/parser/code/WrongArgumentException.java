package io.github.slupik.schemablock.parser.code;

import io.github.slupik.schemablock.model.ui.error.AlgorithmErrorType;
import io.github.slupik.schemablock.model.ui.error.AlgorithmException;

/**
 * All rights reserved & copyright ©
 */
public class WrongArgumentException extends AlgorithmException {
    public WrongArgumentException(String excepted, String received) {
        super("Excepted "+excepted+" but received "+received);
    }

    @Override
    public AlgorithmErrorType getType() {
        return AlgorithmErrorType.WRONG_ARGUMENT;
    }
}
