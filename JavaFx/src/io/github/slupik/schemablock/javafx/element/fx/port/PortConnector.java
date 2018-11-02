package io.github.slupik.schemablock.javafx.element.fx.port;

import io.github.slupik.schemablock.model.ui.abstraction.element.Element;

/**
 * All rights reserved & copyright ©
 */
public interface PortConnector {
    boolean isSearchingInput();

    void setInput(Element logicElement);

    void setLineStart(PortElement port, double x, double y);

    void setLineEnd(PortElement port, double x, double y);

    void addPort(PortElement port);
}
