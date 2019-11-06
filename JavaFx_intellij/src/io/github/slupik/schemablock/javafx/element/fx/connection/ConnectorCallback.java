package io.github.slupik.schemablock.javafx.element.fx.connection;

import io.github.slupik.schemablock.javafx.element.UiElement;

/**
 * All rights reserved & copyright ©
 */
@FunctionalInterface
public interface ConnectorCallback {
    void onConnectionTo(UiElement element, boolean isConnectionForTrue);
}
