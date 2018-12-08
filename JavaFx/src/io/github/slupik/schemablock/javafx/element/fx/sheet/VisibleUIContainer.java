package io.github.slupik.schemablock.javafx.element.fx.sheet;

import io.github.slupik.schemablock.javafx.element.fx.element.UiElementBase;

/**
 * All rights reserved & copyright ©
 */
public interface VisibleUIContainer {
    UiElementBase get(String id) throws ElementNotFound;
    void add(UiElementBase element);
    void remove(String id);
}
