package io.github.slupik.schemablock.model.ui.abstraction.element;

/**
 * All rights reserved & copyright ©
 */
public interface ConditionalElement extends StandardElement {
    void setOnFalse(String elementId);
    void setOnTrue(String elementId);
    String getOnFalse();
    String getOnTrue();
    void removeOnFalse(String elementId);
    void removeOnTrue(String elementId);
}
