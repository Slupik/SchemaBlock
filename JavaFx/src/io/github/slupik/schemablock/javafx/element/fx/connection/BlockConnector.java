package io.github.slupik.schemablock.javafx.element.fx.connection;

import io.github.slupik.schemablock.javafx.element.fx.UiElementBase;

/**
 * All rights reserved & copyright ©
 */
public interface BlockConnector {
    void spawnPointAt(double x, double y, UiElementBase element, ConnectorCallback callback);
}
