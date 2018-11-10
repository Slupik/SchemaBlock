package io.github.slupik.schemablock.javafx.element.fx.sheet;

import io.github.slupik.schemablock.javafx.element.UiElement;
import io.github.slupik.schemablock.javafx.element.UiElementType;
import io.github.slupik.schemablock.javafx.element.fx.UiElementBase;
import io.github.slupik.schemablock.javafx.element.fx.factory.UiElementFactory;
import io.github.slupik.schemablock.javafx.element.fx.port.PortConnector;
import io.github.slupik.schemablock.javafx.element.fx.port.PortSpawner;
import io.github.slupik.schemablock.javafx.element.fx.special.StartUiElement;
import io.github.slupik.schemablock.javafx.logic.drag.DragEventState;
import io.github.slupik.schemablock.javafx.logic.drag.node.DraggableNode;
import io.github.slupik.schemablock.javafx.logic.drag.node.NodeDragController;
import io.github.slupik.schemablock.javafx.view.PortConnectorOnSheet;
import io.github.slupik.schemablock.javafx.view.PortSpawnerOnSheet;
import io.github.slupik.schemablock.model.ui.abstraction.container.ElementContainer;
import io.github.slupik.schemablock.model.ui.implementation.container.DefaultElementContainer;
import io.github.slupik.schemablock.model.ui.implementation.container.NextElementNotFound;
import io.github.slupik.schemablock.model.ui.implementation.container.StartBlockNotFound;
import io.github.slupik.schemablock.parser.code.IncompatibleTypeException;
import io.github.slupik.schemablock.parser.code.VariableNotFound;
import io.github.slupik.schemablock.parser.code.WrongArgumentException;
import io.github.slupik.schemablock.parser.math.rpn.pattern.InvalidArgumentsException;
import io.github.slupik.schemablock.parser.math.rpn.pattern.UnsupportedValueException;
import io.github.slupik.schemablock.parser.math.rpn.variable.VariableIsAlreadyDefinedException;
import io.github.slupik.schemablock.parser.math.rpn.variable.value.NotFoundTypeException;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

/**
 * All rights reserved & copyright ©
 */
public class DefaultSheetWithElements implements SheetWithElements {

    private final ElementContainer container;
    private final Pane sheet;
    private final StartUiElement startElement;

    private PortConnector connector;
    private PortSpawner spawner;

    public DefaultSheetWithElements(Pane sheet) {
        this.sheet = sheet;
        startElement = ((StartUiElement) UiElementFactory.createByType(UiElementType.START));
        container = new DefaultElementContainer();
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
            container.addElement(startElement.getLogicElement());
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
    public void run() {
        try {
            container.run();
        } catch (NotFoundTypeException | IncompatibleTypeException | UnsupportedValueException |
                VariableIsAlreadyDefinedException | NextElementNotFound | WrongArgumentException |
                InvalidArgumentsException | VariableNotFound | StartBlockNotFound e) {
            e.printStackTrace();
        }
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
    public PortConnector getPortConnector() {
        return connector;
    }

    @Override
    public PortSpawner getPortSpawner() {
        return spawner;
    }
}
