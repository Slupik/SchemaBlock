package io.github.slupik.schemablock.javafx.logic.drag.node;

import io.github.slupik.schemablock.javafx.element.DraggableElement;

/**
 * All rights reserved & copyright ©
 */
public class DraggableElementContainer {

    final DraggableElement element;
    final boolean useRelativePos;

    public DraggableElementContainer(DraggableElement element, boolean useRelativePos){
        this.element = element;
        this.useRelativePos = useRelativePos;
    }

}