package io.github.slupik.schemablock.model.ui.abstraction.element;

/**
 * All rights reserved & copyright ©
 */
public interface OperationElement extends StandardElement {
    void setNextElement(String elementId);
    String getNextElement();
    void removeNextElement(String elementId);
}
