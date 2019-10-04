package io.github.slupik.schemablock.newparser.compilator.exception;

import io.github.slupik.schemablock.model.ui.error.AlgorithmErrorType;
import io.github.slupik.schemablock.newparser.compilator.implementation.compilator.CompilationException;

/**
 * All rights reserved & copyright ©
 */
public class ComExIllegalEscapeChar extends CompilationException {

    public final char escapedChar;

    public ComExIllegalEscapeChar(int line, int position, char escapeChar, char escapedChar) {
        super("Tried to escape char \"escapedChar\" but it cannot be escaped.", line, position);
        this.escapedChar = escapedChar;
    }

    @Override
    public AlgorithmErrorType getType() {
        return AlgorithmErrorType.ILLEGAL_CHAR_EXCEPTION;
    }

}
