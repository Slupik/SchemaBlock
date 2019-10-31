package io.github.slupik.schemablock.javafx.element.fx.sheet.old;

import io.github.slupik.schemablock.javafx.element.block.implementation.DescribedBlockPrototype;
import io.github.slupik.schemablock.javafx.element.fx.port.connector.old.PortConnector;
import io.github.slupik.schemablock.javafx.element.fx.port.spawner.PortSpawner;
import io.github.slupik.schemablock.javafx.logic.drag.icon.DestContainerAfterDrop;
import javafx.scene.Node;

/**
 * All rights reserved & copyright ©
 */
public interface SheetWithElements {

    void addElement(DescribedBlockPrototype element) throws InvalidTypeException;
    void removeElement(Node element);
    void clear();

    PortConnector getPortConnector();
    PortSpawner getPortSpawner();
    DestContainerAfterDrop getChildrenHandler();

    String stringify();
    void restore(String data);
}
