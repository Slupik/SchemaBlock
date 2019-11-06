package io.github.slupik.schemablock.model.ui.abstraction.element;

/**
 * All rights reserved & copyright ©
 */
public interface StartElement extends Element {
    void setNextElement(String elementId);
    String getNextElement();
    void removeNextElement(String elementId);
}
