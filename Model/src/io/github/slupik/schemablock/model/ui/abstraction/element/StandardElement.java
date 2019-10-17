package io.github.slupik.schemablock.model.ui.abstraction.element;

/**
 * All rights reserved & copyright ©
 */
public interface StandardElement extends Element {

    void setContent(String content);
    String getContent();
    void setStateListener(ElementStateListener listener);

}
