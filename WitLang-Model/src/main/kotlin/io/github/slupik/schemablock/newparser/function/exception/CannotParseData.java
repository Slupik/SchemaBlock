package io.github.slupik.schemablock.newparser.function.exception;

import io.github.slupik.schemablock.model.ui.error.AlgorithmErrorType;
import io.github.slupik.schemablock.model.ui.error.AlgorithmException;
import io.github.slupik.schemablock.newparser.memory.element.ValueType;

/**
 * All rights reserved & copyright ©
 */
public class CannotParseData extends AlgorithmException {

    public final Object input;
    public final ValueType type;

    public CannotParseData(Object input, ValueType type) {
        super("Cannot parse input \"" + input + "\" to type " + type.name());
        this.input = input;
        this.type = type;
    }

    @Override
    public AlgorithmErrorType getType() {
        return AlgorithmErrorType.FUNCTION_DATA_PARSE_ERROR;
    }

}
