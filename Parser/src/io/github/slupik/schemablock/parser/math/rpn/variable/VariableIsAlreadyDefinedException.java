package io.github.slupik.schemablock.parser.math.rpn.variable;

import io.github.slupik.schemablock.model.ui.error.AlgorithmErrorType;
import io.github.slupik.schemablock.model.ui.error.AlgorithmException;

/**
 * All rights reserved & copyright ©
 */
public class VariableIsAlreadyDefinedException extends AlgorithmException {

    public VariableIsAlreadyDefinedException(String name) {
        super("Variable with name " + name + " is already defined.");
    }

    @Override
    public AlgorithmErrorType getType() {
        return AlgorithmErrorType.VARIABLE_IS_ALREADY_DEFINED;
    }
}
