package io.github.slupik.schemablock.javafx.element.fx.sheet;

import io.github.slupik.schemablock.javafx.element.UiElement;
import io.github.slupik.schemablock.javafx.element.UiElementType;
import io.github.slupik.schemablock.javafx.element.fx.UiElementBase;
import io.github.slupik.schemablock.javafx.element.fx.factory.UiElementFactory;
import io.github.slupik.schemablock.javafx.element.fx.port.PortConnector;
import io.github.slupik.schemablock.javafx.element.fx.port.PortSpawner;
import io.github.slupik.schemablock.javafx.element.fx.special.StartElement;
import io.github.slupik.schemablock.javafx.logic.drag.DragEventState;
import io.github.slupik.schemablock.javafx.logic.drag.node.DraggableNode;
import io.github.slupik.schemablock.javafx.logic.drag.node.NodeDragController;
import io.github.slupik.schemablock.javafx.view.PortConnectorOnSheet;
import io.github.slupik.schemablock.javafx.view.PortSpawnerOnSheet;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

/**
 * All rights reserved & copyright ©
 */
public class DefaultSheetWithElements implements SheetWithElements {

    private final Pane sheet;
    private final StartElement startElement;

    private PortConnector connector;
    private PortSpawner spawner;

    public DefaultSheetWithElements(Pane sheet) {
        this.sheet = sheet;
        startElement = ((StartElement) UiElementFactory.createByType(UiElementType.START));
        init();
        setup();
    }

    private void init() {
        connector = new PortConnectorOnSheet(sheet);
        spawner = new PortSpawnerOnSheet(connector);
    }

    private void setup() {
        spawnStartElement();

        spawnStop();
    }

    private void spawnStartElement() {
        Platform.runLater(()->{
            sheet.getChildren().add(startElement);
            if(sheet.getWidth()<150){
                startElement.setLayoutX(10);
            } else if (sheet.getWidth()<600){
                double elementWidth = startElement.boundsInLocalProperty().get().getWidth();
                startElement.setLayoutX(sheet.getWidth()/2-elementWidth/2);
            } else {
                startElement.setLayoutX(300);
            }
            startElement.setLayoutY(10);
            new NodeDragController(new DraggableNode(startElement, false)).
                    addListener((newState, draggableNode) -> {
                        if(newState == DragEventState.DRAG_START) {
                            startElement.toFront();
                        }
                    });
            spawner.spawnForElement(startElement);
        });
    }

    //TODO delete spawnStop()
    private void spawnStop() {
        Platform.runLater(()->{
            UiElementBase end = UiElementFactory.createByType(UiElementType.STOP);
            sheet.getChildren().add(end);
            if(sheet.getWidth()<150){
                end.setLayoutX(100);
            } else {
                end.setLayoutX(400);
            }
            end.setLayoutY(0);
            new NodeDragController(new DraggableNode(end, false)).
                    addListener((newState, draggableNode) -> {
                        if(newState == DragEventState.DRAG_START) {
                            end.toFront();
                        }
                    });
            spawner.spawnForElement(end);
        });
    }

    @Override
    public void addElement(UiElement element) throws InvalidTypeException {
        if(element instanceof Node) {
            sheet.getChildren().add(((Node) element));
        } else {
            throw new InvalidTypeException();
        }
    }

    @Override
    public void clear() {
        sheet.getChildren().clear();
    }

    @Override
    public StartElement getStartElement() {
        return startElement;
    }

    @Override
    public PortConnector getPortConnector() {
        return connector;
    }

    @Override
    public PortSpawner getPortSpawner() {
        return spawner;
    }
}
