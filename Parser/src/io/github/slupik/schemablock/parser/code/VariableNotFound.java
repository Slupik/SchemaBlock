package io.github.slupik.schemablock.parser.code;

import io.github.slupik.schemablock.model.ui.error.AlgorithmErrorType;
import io.github.slupik.schemablock.model.ui.error.AlgorithmException;

/**
 * All rights reserved & copyright ©
 */
public class VariableNotFound extends AlgorithmException {
    public VariableNotFound(String varName) {
        super("Variable with name "+varName+" doesn't exists");
    }

    @Override
    public AlgorithmErrorType getType() {
        return AlgorithmErrorType.VARIABLE_NOT_FOUND;
    }
}
