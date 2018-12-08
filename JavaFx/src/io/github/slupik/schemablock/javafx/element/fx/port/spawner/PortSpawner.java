package io.github.slupik.schemablock.javafx.element.fx.port.spawner;

import io.github.slupik.schemablock.javafx.element.fx.element.UiElementBase;

/**
 * All rights reserved & copyright ©
 */
public interface PortSpawner {
    void spawnForElement(UiElementBase element);
    void restorePorts(String portsArray);
}
