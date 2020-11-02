package io.github.slupik.schemablock.model.ui.abstraction.element;

/**
 * All rights reserved & copyright ©
 */
public interface ConditionalElement extends StandardElement {
    String getOnFalse();

    void setOnFalse(String elementId);

    String getOnTrue();

    void setOnTrue(String elementId);

    void removeOnFalse(String elementId);

    void removeOnTrue(String elementId);
}
