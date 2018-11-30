package io.github.slupik.schemablock.javafx.element.fx.port.connector;

import io.github.slupik.schemablock.javafx.element.fx.arrow.Arrow;
import io.github.slupik.schemablock.javafx.element.fx.port.CannotSetupPort;
import io.github.slupik.schemablock.javafx.element.fx.port.PortElement;
import io.github.slupik.schemablock.model.ui.abstraction.element.Element;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public class PortConnectorOnSheet implements PortConnector {

    private final List<PortElement> portList = new ArrayList<>();
    private final Pane sheet;

    private Arrow activeArrow;
    private PortElement startPort;

    public PortConnectorOnSheet(Pane sheet) {
        this.sheet = sheet;
        sheet.addEventFilter(MouseEvent.MOUSE_DRAGGED, event -> {
            if(activeArrow!=null) {
                activeArrow.setEnd(event.getX(), event.getY());
                activeArrow.toFront();
            }
        });
        sheet.addEventFilter(MouseEvent.ANY, event -> {
            for(PortElement portLoop:portList) {
                if(portLoop.isContainsPoint(event.getX(), event.getY())){
                    portLoop.onMouseEnter();
                } else {
                    portLoop.onMouseExit();
                }
            }
        });
        sheet.addEventFilter(MouseEvent.MOUSE_RELEASED, event -> {
            if(activeArrow!=null) {
                boolean pointBelongsToPort = false;
                for(PortElement portLoop:portList) {
                    /*
                        What if point is under other element? It will works too, but shouldn't
                        It could be solved by checking all elements at the received point and if port belongs
                        to all those elements (which contains specific point) then
                        it means that port is visible for the user
                    */
                    if(portLoop.isContainsPoint(event.getX(), event.getY()) && portLoop.isAllowForInput()){
                        pointBelongsToPort = true;
                        portLoop.onMouseReleased();
                        break;
                    }
                }
                if(!pointBelongsToPort) {
                    deleteArrow();
                }
            }
        });
    }

    @Override
    public boolean isSearchingInput() {
        return startPort!=null;
    }

    @Override
    public void setInput(Element logicElement) {
        activeArrow=null;
    }

    @Override
    public void setLineStart(PortElement port, double x, double y) {
        activeArrow = new Arrow();
        sheet.getChildren().add(activeArrow);
        activeArrow.setStart(x, y);
        activeArrow.setEnd(x, y);
        startPort = port;
    }

    @Override
    public void setLineEnd(PortElement port, double x, double y) {
        //Delete when start and end is the same port or element
        if(port.getPortId().equals(startPort.getPortId()) ||
                port.getElement().getElementId().equals(startPort.getElement().getElementId())) {
            deleteArrow();
            return;
        }

        try {
            this.startPort.setNextElement(port.getElement());
        } catch (CannotSetupPort e) {
            deleteArrow();
            return;
        }

        activeArrow.setEnd(x, y);
        this.startPort.bindArrowStart(activeArrow);
        port.bindArrowEnd(activeArrow);

        clearArrow();
    }

    private void deleteArrow() {
        sheet.getChildren().remove(activeArrow);
        clearArrow();
    }

    private void clearArrow() {
        activeArrow = null;
        startPort = null;
    }

    @Override
    public void addPort(PortElement port) {
        portList.add(port);
        sheet.getChildren().add(port);
    }
}
