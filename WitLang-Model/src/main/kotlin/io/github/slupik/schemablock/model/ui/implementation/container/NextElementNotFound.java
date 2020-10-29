package io.github.slupik.schemablock.model.ui.implementation.container;

import io.github.slupik.schemablock.model.ui.error.AlgorithmErrorType;
import io.github.slupik.schemablock.model.ui.error.AlgorithmException;

/**
 * All rights reserved & copyright ©
 */
public class NextElementNotFound extends AlgorithmException {
    private final String elementId;

    public NextElementNotFound(String elementId) {
        super("Element with id: "+elementId+" hasn't been found.");
        this.elementId = elementId;
    }

    @Override
    public AlgorithmErrorType getType() {
        return AlgorithmErrorType.NEXT_ELEMENT_NOT_FOUND;
    }

    public String getElementId() {
        return elementId;
    }
}
