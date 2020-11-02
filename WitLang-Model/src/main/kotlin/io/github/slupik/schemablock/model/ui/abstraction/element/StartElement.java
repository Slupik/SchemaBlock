package io.github.slupik.schemablock.model.ui.abstraction.element;

/**
 * All rights reserved & copyright ©
 */
public interface StartElement extends Element {
    String getNextElement();

    void setNextElement(String elementId);

    void removeNextElement(String elementId);
}
