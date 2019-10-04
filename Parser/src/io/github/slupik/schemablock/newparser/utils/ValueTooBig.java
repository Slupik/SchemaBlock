package io.github.slupik.schemablock.newparser.utils;

import io.github.slupik.schemablock.model.ui.error.AlgorithmErrorType;
import io.github.slupik.schemablock.model.ui.error.AlgorithmException;

/**
 * All rights reserved & copyright ©
 */
public class ValueTooBig extends AlgorithmException {

    public ValueTooBig() {
        super("Given number is too big to be processed.");
    }

    @Override
    public AlgorithmErrorType getType() {
        return AlgorithmErrorType.NUMBER_TOO_BIG;
    }

}
