package io.github.slupik.schemablock.parser.math.rpn.variable.value;

import io.github.slupik.schemablock.model.ui.error.AlgorithmErrorType;
import io.github.slupik.schemablock.model.ui.error.AlgorithmException;

/**
 * All rights reserved & copyright ©
 */
public class NotFoundTypeException extends AlgorithmException {
    public NotFoundTypeException(String value) {
        super("Type for string: "+value+", hasn't been found");
    }

    public NotFoundTypeException(Object value) {
        super("Type for object: "+value+", hasn't been found");
    }

    @Override
    public AlgorithmErrorType getType() {
        return AlgorithmErrorType.TYPE_NOT_FOUND;
    }
}
