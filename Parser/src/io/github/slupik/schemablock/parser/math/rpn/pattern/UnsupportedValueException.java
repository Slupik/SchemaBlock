package io.github.slupik.schemablock.parser.math.rpn.pattern;

import io.github.slupik.schemablock.model.ui.error.AlgorithmErrorType;
import io.github.slupik.schemablock.model.ui.error.AlgorithmException;

/**
 * All rights reserved & copyright ©
 */
public class UnsupportedValueException extends AlgorithmException {

    public UnsupportedValueException(Object value) {
        super("Unsupported value: "+value);
    }

    public UnsupportedValueException() {
        super("One of the received value is not supported.");
    }

    @Override
    public AlgorithmErrorType getType() {
        return AlgorithmErrorType.UNSUPPORTED_VALUE;
    }
}
