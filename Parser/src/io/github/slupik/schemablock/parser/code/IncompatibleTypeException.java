package io.github.slupik.schemablock.parser.code;

import io.github.slupik.schemablock.model.ui.error.AlgorithmErrorType;
import io.github.slupik.schemablock.model.ui.error.AlgorithmException;
import io.github.slupik.schemablock.parser.math.rpn.variable.value.ValueType;

/**
 * All rights reserved & copyright ©
 */
public class IncompatibleTypeException extends AlgorithmException {
    public IncompatibleTypeException(ValueType type1, ValueType type2) {
        super("Excepted type "+type1+" but received "+type2);
    }

    @Override
    public AlgorithmErrorType getType() {
        return AlgorithmErrorType.INCOMPATIBILITY_TYPE;
    }
}
