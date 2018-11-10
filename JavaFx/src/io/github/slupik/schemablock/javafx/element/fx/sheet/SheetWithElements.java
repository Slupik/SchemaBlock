package io.github.slupik.schemablock.javafx.element.fx.sheet;

import io.github.slupik.schemablock.javafx.element.UiElement;
import io.github.slupik.schemablock.javafx.element.fx.port.PortConnector;
import io.github.slupik.schemablock.javafx.element.fx.port.PortSpawner;

/**
 * All rights reserved & copyright ©
 */
public interface SheetWithElements {

    void run();

    void addElement(UiElement element) throws InvalidTypeException;
    void clear();

    PortConnector getPortConnector();
    PortSpawner getPortSpawner();
}
