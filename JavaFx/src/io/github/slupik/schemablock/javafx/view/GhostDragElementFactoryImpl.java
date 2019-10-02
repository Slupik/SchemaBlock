package io.github.slupik.schemablock.javafx.view;

import io.github.slupik.schemablock.javafx.element.UiElementType;
import io.github.slupik.schemablock.javafx.element.fx.element.UiElementBase;
import io.github.slupik.schemablock.javafx.element.fx.factory.UiElementFactory;
import io.github.slupik.schemablock.javafx.element.fx.port.spawner.PortSpawner;
import io.github.slupik.schemablock.javafx.logic.drag.DragEventState;
import io.github.slupik.schemablock.javafx.logic.drag.icon.DragContainer;
import io.github.slupik.schemablock.javafx.logic.drag.icon.DragGhostIcon;
import io.github.slupik.schemablock.javafx.logic.drag.icon.GhostDragElementFactory;
import io.github.slupik.schemablock.javafx.logic.drag.node.DraggableNode;
import io.github.slupik.schemablock.javafx.logic.drag.node.NodeDragController;
import io.github.slupik.schemablock.model.ui.newparser.HeapController;
import io.github.slupik.schemablock.newparser.executor.Executor;
import javafx.scene.Node;

/**
 * All rights reserved & copyright ©
 */
class GhostDragElementFactoryImpl implements GhostDragElementFactory {

    private final PortSpawner spawner;

    GhostDragElementFactoryImpl(PortSpawner spawner) {
        this.spawner = spawner;
    }

    @Override
    public Node getNode(DragContainer container, Executor executor, HeapController heap) {
        UiElementType type = UiElementType.valueOf(container.getValue("type"));
        UiElementBase droppedElement = UiElementFactory.createByType(type, executor, heap);

        NodeDragController draggingController = new NodeDragController(new DraggableNode(droppedElement, false));
        draggingController.addListener((dragEvent, draggableNode) -> {
            if(dragEvent == DragEventState.DRAG_START) {
                draggingController.getEventNode().toFront();
            }
        });

        return droppedElement;
    }

    @Override
    public DragGhostIcon getDragIcon(Executor executor, HeapController heap) {
        return new DragGhostIconUiElement(executor, heap);
    }
}
