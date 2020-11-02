package io.github.slupik.schemablock.model.ui.abstraction.element;

/**
 * All rights reserved & copyright ©
 */
public interface StandardElement extends Element {

    String getContent();

    void setContent(String content);

    void setStateListener(ElementStateListener listener);

}
