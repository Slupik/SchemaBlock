package io.github.slupik.schemablock.newparser.memory.element;

import io.github.slupik.schemablock.model.ui.error.AlgorithmErrorType;
import io.github.slupik.schemablock.model.ui.error.AlgorithmException;

/**
 * All rights reserved & copyright ©
 */
public class ExceptedValue extends AlgorithmException {

    public ExceptedValue() {
        super("Expected value but received something else.");
    }

    @Override
    public AlgorithmErrorType getType() {
        return AlgorithmErrorType.EXCEPTED_VALUE;
    }

}
