package io.github.slupik.schemablock.javafx.logic.drag.node;

import io.github.slupik.schemablock.javafx.element.Element;

/**
 * All rights reserved & copyright ©
 */
public class DraggableElement {

    final Element element;
    final boolean useRelativePos;

    public DraggableElement(Element element){
        this(element, false);
    }

    public DraggableElement(Element element, boolean useRelativePos){
        this.element = element;
        this.useRelativePos = useRelativePos;
    }
}