package io.github.slupik.schemablock.newparser.compilator.exception;

import io.github.slupik.schemablock.model.ui.error.AlgorithmErrorType;
import io.github.slupik.schemablock.model.ui.error.AlgorithmException;

/**
 * All rights reserved & copyright ©
 */
public class IndexOutOfBoundsException extends AlgorithmException {

    private final int length;
    private final int index;

    public IndexOutOfBoundsException(int length, int index) {
        super("Tried to receive value at index " + index + " but array have only " + length + " values.");
        this.length = length;
        this.index = index;
    }

    public int getLength() {
        return length;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public AlgorithmErrorType getType() {
        return AlgorithmErrorType.INDEX_OUT_OF_BOUNDS;
    }

}
