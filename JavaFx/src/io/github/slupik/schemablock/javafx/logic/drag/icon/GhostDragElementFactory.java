package io.github.slupik.schemablock.javafx.logic.drag.icon;

import io.github.slupik.schemablock.model.ui.newparser.HeapController;
import io.github.slupik.schemablock.newparser.executor.Executor;
import javafx.scene.Node;

/**
 * All rights reserved & copyright ©
 */
public interface GhostDragElementFactory {

    Node getNode(DragContainer container, Executor executor, HeapController heap);

    DragGhostIcon getDragIcon(Executor executor, HeapController heap);
}
