package io.github.slupik.schemablock.both.execution;

import io.github.slupik.schemablock.model.ui.error.AlgorithmErrorType;
import io.github.slupik.schemablock.model.ui.error.AlgorithmException;

/**
 * All rights reserved & copyright ©
 */
public class VariableNotFound extends AlgorithmException {

    public final String varName;

    public VariableNotFound(String varName) {
        super("Variable with name "+varName+" doesn't exists");
        this.varName = varName;
    }

    @Override
    public AlgorithmErrorType getType() {
        return AlgorithmErrorType.VARIABLE_NOT_FOUND;
    }

}
