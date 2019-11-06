package io.github.slupik.schemablock.javafx.view;

import io.github.slupik.schemablock.javafx.element.UiElementType;
import io.github.slupik.schemablock.javafx.element.fx.factory.UiBlockFactory;
import io.github.slupik.schemablock.javafx.logic.drag.icon.DragGhostIcon;
import javafx.scene.layout.Pane;

/**
 * All rights reserved & copyright ©
 */
public class DragGhostIconUiElement extends DragGhostIcon<UiElementType> {

    DragGhostIconUiElement(UiElementType type) {
        setData(type);
    }

    DragGhostIconUiElement() {

    }

    @Override
    protected void onSetData(UiElementType type) {
        Pane element = UiBlockFactory.INSTANCE.createIcon(type);
        getChildren().clear();
        getChildren().add(element);
    }
}
