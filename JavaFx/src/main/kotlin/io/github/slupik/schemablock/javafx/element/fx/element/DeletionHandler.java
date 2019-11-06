package io.github.slupik.schemablock.javafx.element.fx.element;

/**
 * All rights reserved & copyright ©
 */
public interface DeletionHandler {
    void deleteOutgoing(String elementId);
    void deleteIngoing(String elementId);
    void deleteElement(UiElementBase element);
}
