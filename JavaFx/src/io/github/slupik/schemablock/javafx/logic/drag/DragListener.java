package io.github.slupik.schemablock.javafx.logic.drag;

/**
 * All rights reserved & copyright ©
 */
@FunctionalInterface
public interface DragListener<DraggingElement> {
    void onDragNewState(DragEventState newState, DraggingElement draggingElement);

    /*
    //ALT

    void onDragNewState(Info data);

    class Info<DraggingElement> {
        public final DragEventState newState;
        public final DraggingElement element;

        public Info(DragEventState newState, DraggingElement draggingElement) {
            this.newState = newState;
            element = draggingElement;
        }
    }
     */
}
