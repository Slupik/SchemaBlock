package io.github.slupik.schemablock.newparser.executor.implementation;

import io.github.slupik.schemablock.model.ui.error.AlgorithmErrorType;
import io.github.slupik.schemablock.model.ui.error.AlgorithmException;
import io.github.slupik.schemablock.newparser.memory.element.ValueType;

/**
 * All rights reserved & copyright ©
 */
public class IllegalOperation extends AlgorithmException {

    public final String operation;
    public final ValueType type1;
    public final ValueType type2;

    public IllegalOperation(ValueType type1, ValueType type2, String operation) {
        super("Cannot end operation '" + operation + "' for values with types: " + type1 + " and " + type2);
        this.type1 = type1;
        this.type2 = type2;
        this.operation = operation;
    }

    public IllegalOperation(ValueType type, String operation) {
        super("Cannot end operation '" + operation + "' for values with types: " + type);
        this.type1 = type;
        this.type2 = null;
        this.operation = operation;
    }

    @Override
    public AlgorithmErrorType getType() {
        return AlgorithmErrorType.ILLEGAL_OPERATION;
    }

}
