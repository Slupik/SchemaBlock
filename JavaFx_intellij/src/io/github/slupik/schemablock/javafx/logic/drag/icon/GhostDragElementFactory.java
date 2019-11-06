package io.github.slupik.schemablock.javafx.logic.drag.icon;

import io.github.slupik.schemablock.javafx.element.Element;

/**
 * All rights reserved & copyright ©
 */
public interface GhostDragElementFactory {

    Element getNode(DragContainer container);

    DragGhostIcon getDragIcon();
}
