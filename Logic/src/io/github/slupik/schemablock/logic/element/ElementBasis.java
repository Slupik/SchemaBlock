package io.github.slupik.schemablock.logic.element;

import io.github.slupik.schemablock.entity.element.ListenableElement;
import io.github.slupik.schemablock.entity.element.state.ElementState;
import io.github.slupik.schemablock.entity.element.state.ElementStateListener;

import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public class ElementBasis implements ListenableElement {

    private final List<ElementStateListener> listeners = new ArrayList<>();

    @Override
    public void addStateListener(ElementStateListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeStateListener(ElementStateListener listener) {
        listeners.remove(listener);
    }

    protected void onStateChange(ElementState newState) {
        for(ElementStateListener listener:listeners) {
            listener.onChange(newState);
        }
    }

}
