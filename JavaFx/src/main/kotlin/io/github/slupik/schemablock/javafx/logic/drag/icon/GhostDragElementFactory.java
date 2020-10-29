package io.github.slupik.schemablock.javafx.logic.drag.icon;

import io.github.slupik.schemablock.javafx.element.block.Block;

/**
 * All rights reserved & copyright ©
 */
public interface GhostDragElementFactory {

    Block getNode(DragContainer container);

    DragGhostIcon getDragIcon();
}
