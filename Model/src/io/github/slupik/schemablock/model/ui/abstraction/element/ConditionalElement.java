package io.github.slupik.schemablock.model.ui.abstraction.element;

/**
 * All rights reserved & copyright ©
 */
public interface ConditionalElement extends StandardElement {
    void setOnFalse(String elementId);
    void setOnTrue(String elementId);
}
