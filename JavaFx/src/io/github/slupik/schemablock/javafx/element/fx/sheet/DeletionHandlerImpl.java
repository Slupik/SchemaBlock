package io.github.slupik.schemablock.javafx.element.fx.sheet;

import io.github.slupik.schemablock.javafx.element.fx.element.DeletionHandler;
import io.github.slupik.schemablock.javafx.element.fx.element.UiElementBase;
import io.github.slupik.schemablock.javafx.element.fx.port.PortElement;
import io.github.slupik.schemablock.javafx.element.fx.port.connector.PortConnector;

import java.util.List;

/**
 * All rights reserved & copyright ©
 */
class DeletionHandlerImpl implements DeletionHandler {
    private final SheetWithElements sheetWithElements;
    private final PortConnector connector;

    public DeletionHandlerImpl(SheetWithElements sheetWithElements, PortConnector connector) {
        this.sheetWithElements = sheetWithElements;
        this.connector = connector;
    }

    @Override
    public void deleteOutgoing(String elementId) {
        List<PortElement> ports = connector.getPorts();
        for(PortElement port:ports) {
            if(port.getElement().getElementId().equals(elementId) && port.getEndPortName().length()>0) {
                connector.deleteOutgoing(port);
            }
        }
    }

    @Override
    public void deleteIngoing(String elementId) {
        List<PortElement> ports = connector.getPorts();
        for(PortElement port:ports) {
            if(port.getEndPortName().split(";")[0].equals(elementId)) {
                connector.deleteOutgoing(port);
            }
        }
    }

    @Override
    public void deleteElement(UiElementBase element) {
        sheetWithElements.removeElement(element);
    }
}
