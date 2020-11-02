package io.github.slupik.schemablock.newparser.memory;

import io.github.slupik.schemablock.model.ui.error.AlgorithmErrorType;
import io.github.slupik.schemablock.model.ui.error.AlgorithmException;

/**
 * All rights reserved & copyright ©
 */
public class VariableAlreadyDefined extends AlgorithmException {

    public final String name;

    public VariableAlreadyDefined(String name) {
        super("Variable with name " + name + " already exists.");
        this.name = name;
    }

    @Override
    public AlgorithmErrorType getType() {
        return AlgorithmErrorType.VARIABLE_IS_ALREADY_DEFINED;
    }
}
