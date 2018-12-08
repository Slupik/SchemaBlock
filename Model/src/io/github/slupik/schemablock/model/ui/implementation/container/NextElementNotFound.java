package io.github.slupik.schemablock.model.ui.implementation.container;

/**
 * All rights reserved & copyright ©
 */
public class NextElementNotFound extends Exception {
    public NextElementNotFound(String elementId) {
        super("Element with id: "+elementId+" hasn't been found.");
    }
}
