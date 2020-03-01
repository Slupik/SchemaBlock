package io.github.slupik.schemablock.javafx.element.fx.sheet;

import io.github.slupik.schemablock.model.ui.error.AlgorithmErrorType;
import io.github.slupik.schemablock.model.ui.error.AlgorithmException;

/**
 * All rights reserved & copyright ©
 */
public class ElementNotFound extends AlgorithmException {

    public final String id;

    public ElementNotFound(String id) {
        super("Element with id "+id+" hasn't been found.");
        this.id = id;
    }

    @Override
    public AlgorithmErrorType getType() {
        return AlgorithmErrorType.ELEMENT_NOT_FOUND;
    }

}
