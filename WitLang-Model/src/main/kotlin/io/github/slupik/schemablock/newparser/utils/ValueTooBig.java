package io.github.slupik.schemablock.newparser.utils;

import io.github.slupik.schemablock.model.ui.error.AlgorithmErrorType;
import io.github.slupik.schemablock.newparser.compilator.implementation.compilator.CompilationException;

/**
 * All rights reserved & copyright ©
 */
public class ValueTooBig extends CompilationException {

    public final String value;

    public ValueTooBig(String value, int line, int position) {
        super("Given number (" + value + ") is too big to be processed.", line, position);
        this.value = value;
    }

    @Override
    public AlgorithmErrorType getType() {
        return AlgorithmErrorType.NUMBER_TOO_BIG;
    }

}
