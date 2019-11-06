package io.github.slupik.schemablock.entity.element;

import io.github.slupik.schemablock.entity.element.state.ElementStateListener;

/**
 * All rights reserved & copyright ©
 */
public interface ListenableElement {

    void addStateListener(ElementStateListener listener);
    void removeStateListener(ElementStateListener listener);

}
