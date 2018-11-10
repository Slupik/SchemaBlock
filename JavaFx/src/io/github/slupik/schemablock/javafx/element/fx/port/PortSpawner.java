package io.github.slupik.schemablock.javafx.element.fx.port;

import io.github.slupik.schemablock.javafx.element.fx.UiElementBase;

/**
 * All rights reserved & copyright ©
 */
public interface PortSpawner {
    void spawnForElement(UiElementBase element);
    void restorePorts();
}
