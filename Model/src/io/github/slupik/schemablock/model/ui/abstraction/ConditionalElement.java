package io.github.slupik.schemablock.model.ui.abstraction;

/**
 * All rights reserved & copyright ©
 */
public interface ConditionalElement extends StandardElement {
    void setOnFalse(Element element);
    void setOnTrue(Element element);
}
