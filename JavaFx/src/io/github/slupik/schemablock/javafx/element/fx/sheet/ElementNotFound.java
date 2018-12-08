package io.github.slupik.schemablock.javafx.element.fx.sheet;

/**
 * All rights reserved & copyright ©
 */
public class ElementNotFound extends Exception {
    public ElementNotFound(String id) {
        super("Element with id "+id+" hasn't been found.");
    }
}
