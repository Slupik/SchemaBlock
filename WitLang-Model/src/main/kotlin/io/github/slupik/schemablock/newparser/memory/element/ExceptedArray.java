package io.github.slupik.schemablock.newparser.memory.element;

import io.github.slupik.schemablock.model.ui.error.AlgorithmErrorType;
import io.github.slupik.schemablock.model.ui.error.AlgorithmException;

/**
 * All rights reserved & copyright ©
 */
public class ExceptedArray extends AlgorithmException {

    public ExceptedArray() {
        super("Expected array but received something else.");
    }

    @Override
    public AlgorithmErrorType getType() {
        return AlgorithmErrorType.EXCEPTED_ARRAY;
    }

}
