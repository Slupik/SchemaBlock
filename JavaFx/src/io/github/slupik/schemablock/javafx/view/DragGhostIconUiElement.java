package io.github.slupik.schemablock.javafx.view;

import io.github.slupik.schemablock.javafx.element.UiElementType;
import io.github.slupik.schemablock.javafx.element.fx.element.UiElementBase;
import io.github.slupik.schemablock.javafx.element.fx.factory.UiElementFactory;
import io.github.slupik.schemablock.javafx.logic.drag.icon.DragGhostIcon;
import io.github.slupik.schemablock.model.ui.newparser.HeapController;
import io.github.slupik.schemablock.newparser.executor.Executor;

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
        UiElementBase element = UiElementFactory.createByType(type, executor, heap);
        getChildren().clear();
        getChildren().add(element);
    }
}
