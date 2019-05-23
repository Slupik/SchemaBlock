package io.github.slupik.schemablock.javafx.logic.drag.icon;

import javafx.scene.Node;

/**
 * All rights reserved & copyright ©
 */
public interface GhostDragElementFactory {
    Node getNode(DragContainer container);

    DragGhostIcon getDragIcon();
}
