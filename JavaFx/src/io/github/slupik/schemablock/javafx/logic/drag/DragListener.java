package io.github.slupik.schemablock.javafx.logic.drag;

/**
 * All rights reserved & copyright ©
 */
public interface DragListener<DraggingElement> {
    void onDragNewState(DragEventState newState, DraggingElement draggingElement);
}
