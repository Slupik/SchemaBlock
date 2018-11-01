package io.github.slupik.schemablock.javafx.view;

import io.github.slupik.schemablock.javafx.element.UiElementType;
import io.github.slupik.schemablock.javafx.element.fx.UiElementBase;
import io.github.slupik.schemablock.javafx.logic.drag.icon.DragGhostIcon;

/**
 * All rights reserved & copyright ©
 */
public class DragGhostIconUiElement  extends DragGhostIcon<UiElementType> {

    DragGhostIconUiElement(UiElementType type) {
        setData(type);
    }

    DragGhostIconUiElement() {}

    @Override
    protected void onSetData(UiElementType type) {
        UiElementBase element = UiElementFactory.createByType(type);
        getChildren().clear();
        getChildren().add(element);
    }
}
