package io.github.slupik.schemablock.newparser.function.exception;

import io.github.slupik.schemablock.model.ui.error.AlgorithmErrorType;
import io.github.slupik.schemablock.newparser.compilator.implementation.compilator.CompilationException;
import io.github.slupik.schemablock.newparser.memory.element.ValueType;

/**
 * All rights reserved & copyright ©
 */
public class CannotParseData extends CompilationException {

    public final Object input;
    public final ValueType type;

    public CannotParseData(Object input, ValueType type, int line, int position) {
        super("Cannot parse input \"" + input + "\" to type " + type.name(), line, position);
        this.input = input;
        this.type = type;
    }

    @Override
    public AlgorithmErrorType getType() {
        return AlgorithmErrorType.FUNCTION_DATA_PARSE_ERROR;
    }

}
