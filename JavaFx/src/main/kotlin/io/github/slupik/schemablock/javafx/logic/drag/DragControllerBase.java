package io.github.slupik.schemablock.javafx.logic.drag;

import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public abstract class DragControllerBase<DraggingElement> {
    private final List<DragListener<DraggingElement>> dragListeners = new ArrayList<>();

    public final boolean removeListener(final DragListener listener) {
        return this.dragListeners.remove(listener);
    }

    public final boolean addListener(final DragListener<DraggingElement> listener) {
        return this.dragListeners.add(listener);
    }

    protected void onStateChanged(DragEventState newState, DraggingElement eventTarget) {
        for(DragListener<DraggingElement> listener:dragListeners) {
            listener.onDragNewState(newState, eventTarget);
        }
    }

    protected void onStateChanged(DragEventState newState) {
        onStateChanged(newState, null);
    }
}
