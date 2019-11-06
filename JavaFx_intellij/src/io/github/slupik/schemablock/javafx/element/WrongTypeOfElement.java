package io.github.slupik.schemablock.javafx.element;

import io.github.slupik.schemablock.model.ui.abstraction.ElementType;

/**
 * All rights reserved & copyright ©
 */
public class WrongTypeOfElement extends Exception {
    public WrongTypeOfElement(String excepted, String received) {
        super("Wrong type of the given element - excepted "+excepted+" but received "+received);
    }

    public WrongTypeOfElement(ElementType type, ElementType calculation) {
        this(type.toString(), calculation.toString());
    }
}
