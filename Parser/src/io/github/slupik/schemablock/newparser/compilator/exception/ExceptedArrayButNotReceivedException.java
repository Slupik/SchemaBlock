package io.github.slupik.schemablock.newparser.compilator.exception;

import io.github.slupik.schemablock.model.ui.error.AlgorithmErrorType;
import io.github.slupik.schemablock.model.ui.error.AlgorithmException;

/**
 * All rights reserved & copyright ©
 */
public class ExceptedArrayButNotReceivedException extends AlgorithmException {

    public ExceptedArrayButNotReceivedException(){
        super("Excepted array but received only single value");
    }

    @Override
    public AlgorithmErrorType getType() {
        return AlgorithmErrorType.EXPECTED_ARRAY;
    }

}
