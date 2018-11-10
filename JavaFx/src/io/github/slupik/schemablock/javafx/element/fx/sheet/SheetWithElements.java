package io.github.slupik.schemablock.javafx.element.fx.sheet;

import io.github.slupik.schemablock.javafx.element.UiElement;
import io.github.slupik.schemablock.javafx.element.fx.port.PortConnector;
import io.github.slupik.schemablock.javafx.element.fx.port.PortSpawner;
import io.github.slupik.schemablock.javafx.element.fx.special.StartElement;

/**
 * All rights reserved & copyright ©
 */
public interface SheetWithElements {
    void addElement(UiElement element) throws InvalidTypeException;
    void clear();
    StartElement getStartElement();

    PortConnector getPortConnector();
    PortSpawner getPortSpawner();
}
