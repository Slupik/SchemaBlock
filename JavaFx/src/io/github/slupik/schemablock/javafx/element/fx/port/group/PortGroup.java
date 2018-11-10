package io.github.slupik.schemablock.javafx.element.fx.port.group;

import io.github.slupik.schemablock.javafx.element.fx.port.PortElement;

/**
 * All rights reserved & copyright ©
 */
public interface PortGroup {
    void addPort(PortElement... ports);
    void onConnectionEstablish(PortElement activePort);
    void onConnectionEstablish(PortElement activePort, boolean isForTrue);
}
