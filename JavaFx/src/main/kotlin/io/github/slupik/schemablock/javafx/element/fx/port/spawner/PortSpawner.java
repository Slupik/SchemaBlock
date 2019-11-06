package io.github.slupik.schemablock.javafx.element.fx.port.spawner;

import io.github.slupik.schemablock.javafx.element.block.implementation.DescribedBlockPrototype;

/**
 * All rights reserved & copyright ©
 */
public interface PortSpawner {
    void spawnForElement(DescribedBlockPrototype element);
    void restorePorts(String portsArray);
}
