package io.github.slupik.schemablock.newparser.compilator.exception;

import io.github.slupik.schemablock.model.ui.error.AlgorithmErrorType;
import io.github.slupik.schemablock.model.ui.error.AlgorithmException;
import io.github.slupik.schemablock.newparser.memory.element.ValueType;

/**
 * All rights reserved & copyright ©
 */
public class IncompatibleTypeException extends AlgorithmException {

    public final ValueType excepted;
    public final ValueType actual;

    public IncompatibleTypeException(ValueType excepted, ValueType actual) {
        super("Incompatibility types: " + excepted + " and " + actual);
        this.excepted = excepted;
        this.actual = actual;
    }

    @Override
    public AlgorithmErrorType getType() {
        return AlgorithmErrorType.INCOMPATIBLE_TYPE;
    }

}
