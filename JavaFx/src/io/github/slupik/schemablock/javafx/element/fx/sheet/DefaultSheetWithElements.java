package io.github.slupik.schemablock.javafx.element.fx.sheet;

import com.google.gson.*;
import io.github.slupik.schemablock.javafx.element.UiElement;
import io.github.slupik.schemablock.javafx.element.UiElementType;
import io.github.slupik.schemablock.javafx.element.fx.UiElementBase;
import io.github.slupik.schemablock.javafx.element.fx.UiElementPOJO;
import io.github.slupik.schemablock.javafx.element.fx.element.special.StartUiElement;
import io.github.slupik.schemablock.javafx.element.fx.element.standard.IOUiElement;
import io.github.slupik.schemablock.javafx.element.fx.factory.UiElementFactory;
import io.github.slupik.schemablock.javafx.element.fx.port.connector.PortConnector;
import io.github.slupik.schemablock.javafx.element.fx.port.connector.PortConnectorOnSheet;
import io.github.slupik.schemablock.javafx.element.fx.port.spawner.PortSpawner;
import io.github.slupik.schemablock.javafx.element.fx.port.spawner.PortSpawnerOnSheet;
import io.github.slupik.schemablock.javafx.logic.drag.DragEventState;
import io.github.slupik.schemablock.javafx.logic.drag.icon.DestContainerAfterDrop;
import io.github.slupik.schemablock.javafx.logic.drag.icon.DestContainerAfterDropImpl;
import io.github.slupik.schemablock.javafx.logic.drag.node.DraggableNode;
import io.github.slupik.schemablock.javafx.logic.drag.node.NodeDragController;
import io.github.slupik.schemablock.model.ui.abstraction.container.ElementContainer;
import io.github.slupik.schemablock.model.ui.implementation.container.DefaultElementContainer;
import io.github.slupik.schemablock.model.ui.implementation.container.NextElementNotFound;
import io.github.slupik.schemablock.model.ui.implementation.container.StartBlockNotFound;
import io.github.slupik.schemablock.model.ui.implementation.element.specific.IOCommunicable;
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

import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public class DefaultSheetWithElements implements SheetWithElements {

    private final ElementContainer container;
    private final Pane sheet;
    private final IOCommunicable communicable;
    private final StartUiElement startElement;
    private final VisibleUIContainer uiContainer;

    private PortConnector connector;
    private PortSpawner spawner;
    private DestContainerAfterDrop childHandler;

    public DefaultSheetWithElements(Pane sheet, IOCommunicable communicable) {
        this.sheet = sheet;
        this.communicable = communicable;
        startElement = ((StartUiElement) UiElementFactory.createByType(UiElementType.START));
        container = new DefaultElementContainer();
        uiContainer = new VisibleUIContainerImpl();
        init();
        setup();
    }

    private void init() {
        connector = new PortConnectorOnSheet(sheet);
        spawner = new PortSpawnerOnSheet(uiContainer, connector);
        childHandler = new DestContainerAfterDropImpl(sheet) {
            @Override
            public void addNode(Node node) {
                if(node instanceof UiElement) {
                    try {
                        addElement(((UiElement) node));
                    } catch (InvalidTypeException ignore) {/*Impossible*/}
                } else {
                    super.addNode(node);
                }
            }
            @Override
            public void removeNode(Node node) {
                if(node instanceof UiElement) {
                    UiElement element = ((UiElement) node);
                    removeElement(element);
                } else {
                    super.addNode(node);
                }
            }
        };
    }

    private void setup() {
        spawnStartElement();
        spawnStop();
    }

    private void spawnStartElement() {
        Platform.runLater(()->{
            childHandler.addNode(startElement);
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
        });
    }

    //TODO delete spawnStop()
    private void spawnStop() {
        Platform.runLater(()->{
            UiElementBase end = UiElementFactory.createByType(UiElementType.STOP);
            childHandler.addNode(end);
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
        });
    }

    @Override
    public void run() {
        new Thread(()->{
            try {
                container.run();
            } catch (NotFoundTypeException | IncompatibleTypeException | UnsupportedValueException |
                    VariableIsAlreadyDefinedException | NextElementNotFound | WrongArgumentException |
                    InvalidArgumentsException | VariableNotFound | StartBlockNotFound e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    public void addElement(UiElement element) throws InvalidTypeException {
        if(element instanceof Node) {
            container.addElement(element.getLogicElement());
            addElementWithoutPorts(element);
            if(element instanceof UiElementBase) {
                spawner.spawnForElement(((UiElementBase) element));
            }
        } else {
            throw new InvalidTypeException();
        }
    }

    private void addElementWithoutPorts(UiElement element) throws InvalidTypeException {
        if(element instanceof Node) {
            sheet.getChildren().add(((Node) element));
            if(element instanceof UiElementBase) {
                uiContainer.add((UiElementBase) element);
            }

            if(element.getType()==UiElementType.IO) {
                ((IOUiElement) element).setCommunicator(communicable);
            }
        } else {
            throw new InvalidTypeException();
        }
    }

    @Override
    public void removeElement(UiElement element) {
        container.removeElement(element.getElementId());
        if(element instanceof Node) {
            sheet.getChildren().remove(element);
            uiContainer.remove(element.getElementId());
        }
    }

    @Override
    public void clear() {
        List<UiElement> toDelete = new ArrayList<>();
        for(Node child:sheet.getChildren()) {
            if(child instanceof  UiElement) {
                toDelete.add(((UiElement) child));
            }
        }
        for(UiElement element:toDelete) {
            removeElement(element);
        }
        sheet.getChildren().clear();

        getPortConnector().deleteAllPorts();
        container.deleteAll();
    }

    @Override
    public PortConnector getPortConnector() {
        return connector;
    }

    @Override
    public PortSpawner getPortSpawner() {
        return spawner;
    }

    @Override
    public DestContainerAfterDrop getChildrenHandler() {
        return childHandler;
    }

    @Override
    public String stringify() {
        JsonParser parser = new JsonParser();

        JsonArray blocks = new JsonArray();
        for(Node node:sheet.getChildren()) {
            if(node instanceof UiElementBase) {
                blocks.add(parser.parse(((UiElementBase) node).stringify()).getAsJsonObject());
            }
        }

        JsonObject data = new JsonObject();
        data.add("logicElements", parser.parse(container.stringify()));
        data.add("blocks", blocks);
        data.add("ports", parser.parse(connector.stringify()).getAsJsonArray());
        return data.toString();
    }

    @Override
    public void restore(String data) {
        clear();

        JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(data).getAsJsonObject();

        JsonObject logicElements = json.get("logicElements").getAsJsonObject();
        container.restore(logicElements.toString());

        JsonArray blocks = json.get("blocks").getAsJsonArray();
        for(JsonElement element:blocks) {
            UiElementPOJO blockInfo = new Gson().fromJson(element, UiElementPOJO.class);

            UiElementBase uiElement = UiElementFactory.createByType(blockInfo.type);
            try {
                uiElement.restore(element.toString(), container);
                addElementWithoutPorts(uiElement);

                //Make element draggable
                NodeDragController draggingController = new NodeDragController(new DraggableNode(uiElement, false));
                draggingController.addListener((dragEvent, draggableNode) -> {
                    if(dragEvent == DragEventState.DRAG_START) {
                        draggingController.getEventNode().toFront();
                    }
                });

            } catch (InvalidTypeException e) {
                e.printStackTrace();
            }
        }

        JsonArray ports = json.get("ports").getAsJsonArray();
        spawner.restorePorts(ports.toString());

        connector.restore(ports.toString());
    }
}
