package io.github.slupik.schemablock.javafx.view;

import io.github.slupik.schemablock.javafx.element.UiElementType;
import io.github.slupik.schemablock.javafx.logic.drag.icon.DragGhostIcon;
import io.github.slupik.schemablock.model.ui.newparser.HeapController;
import io.github.slupik.schemablock.newparser.executor.Executor;
import javafx.scene.layout.Pane;

import static io.github.slupik.schemablock.javafx.element.fx.factory.UiElementFactoryKt.createUiBlockByType;

/**
 * All rights reserved & copyright ©
 */
public class DragGhostIconUiElement extends DragGhostIcon<UiElementType> {

    private final Executor executor;
    private final HeapController heap;

    DragGhostIconUiElement(UiElementType type, Executor executor, HeapController heap) {
        this.executor = executor;
        this.heap = heap;
        setData(type);
    }

    DragGhostIconUiElement(Executor executor, HeapController heap) {
        this.executor = executor;
        this.heap = heap;
    }

    @Override
    protected void onSetData(UiElementType type) {
        Pane element = createUiBlockByType(type);
        getChildren().clear();
        getChildren().add(element);
    }
}
