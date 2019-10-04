package io.github.slupik.schemablock.newparser.compilator.exception;

import io.github.slupik.schemablock.model.ui.error.AlgorithmErrorType;
import io.github.slupik.schemablock.model.ui.error.AlgorithmException;

/**
 * All rights reserved & copyright ©
 */
public class ComExIllegalEscapeChar extends AlgorithmException {

    public ComExIllegalEscapeChar(int line, int position, char escapeChar, char escapedChar) {
        super("Illegal escape char in line "+line+" at position "+position+". Tried to escape char "+escapedChar);
    }

    @Override
    public AlgorithmErrorType getType() {
        return AlgorithmErrorType.ILLEGAL_CHAR_EXCEPTION;
    }

}
