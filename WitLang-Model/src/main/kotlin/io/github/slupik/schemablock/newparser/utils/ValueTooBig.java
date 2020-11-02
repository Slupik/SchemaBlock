package io.github.slupik.schemablock.newparser.utils;

import io.github.slupik.schemablock.model.ui.error.AlgorithmErrorType;
import io.github.slupik.schemablock.model.ui.error.AlgorithmException;

/**
 * All rights reserved & copyright ©
 */
public class ValueTooBig extends AlgorithmException {

    public final String value;

    public ValueTooBig(String value) {
        super("Given number (" + value + ") is too big to be processed.");
        this.value = value;
    }

    @Override
    public AlgorithmErrorType getType() {
        return AlgorithmErrorType.NUMBER_TOO_BIG;
    }

}
