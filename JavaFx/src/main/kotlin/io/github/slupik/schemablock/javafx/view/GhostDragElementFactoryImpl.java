package io.github.slupik.schemablock.javafx.view;

import io.github.slupik.schemablock.javafx.element.Element;
import io.github.slupik.schemablock.javafx.element.UiElementType;
import io.github.slupik.schemablock.javafx.element.fx.factory.UiBlockFactory;
import io.github.slupik.schemablock.javafx.logic.drag.icon.DragContainer;
import io.github.slupik.schemablock.javafx.logic.drag.icon.DragGhostIcon;
import io.github.slupik.schemablock.javafx.logic.drag.icon.GhostDragElementFactory;

/**
 * All rights reserved & copyright ©
 */
class GhostDragElementFactoryImpl implements GhostDragElementFactory {

    private final UiBlockFactory factory;

    GhostDragElementFactoryImpl(UiBlockFactory factory) {
        this.factory = factory;
    }

    @Override
    public Element getNode(DragContainer container) {
        UiElementType type = UiElementType.valueOf(container.getValue("type"));

        return factory.createUsableBlock(type);
    }

    @Override
    public DragGhostIcon getDragIcon() {
        return new DragGhostIconUiElement(factory);
    }

}
