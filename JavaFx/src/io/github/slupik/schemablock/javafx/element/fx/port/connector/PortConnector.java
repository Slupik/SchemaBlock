package io.github.slupik.schemablock.javafx.element.fx.port.connector;

import io.github.slupik.schemablock.javafx.element.fx.port.PortElement;
import io.github.slupik.schemablock.model.ui.abstraction.element.Element;

import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public interface PortConnector {
    boolean isSearchingInput();

    void setInput(Element logicElement);

    void setLineStart(PortElement port, double x, double y);

    void setLineEnd(PortElement port, double x, double y);

    void addPort(PortElement port);

    String stringify();

    void restore(String portsArray);

    void deleteAllPorts();

    List<PortElement> getPorts();

    void deleteOutgoing(PortElement port);

    void deletePort(PortElement port);
}
