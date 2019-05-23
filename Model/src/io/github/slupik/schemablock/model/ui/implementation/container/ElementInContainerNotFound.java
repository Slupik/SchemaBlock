package io.github.slupik.schemablock.model.ui.implementation.container;

import io.github.slupik.schemablock.model.ui.error.AlgorithmErrorType;
import io.github.slupik.schemablock.model.ui.error.AlgorithmException;

/**
 * All rights reserved & copyright ©
 */
public class ElementInContainerNotFound extends AlgorithmException {
    public ElementInContainerNotFound(String message) {
        super(message);
    }

    @Override
    public AlgorithmErrorType getType() {
        return AlgorithmErrorType.ELEMENT_NOT_FOUND;
    }
}
