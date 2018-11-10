package io.github.slupik.schemablock.javafx.view;

import io.github.slupik.schemablock.javafx.element.UiElementType;
import io.github.slupik.schemablock.javafx.element.fx.UiElementBase;
import io.github.slupik.schemablock.javafx.element.fx.port.PortConnector;
import io.github.slupik.schemablock.javafx.element.fx.port.PortSpawner;
import io.github.slupik.schemablock.javafx.logic.drag.DragEventState;
import io.github.slupik.schemablock.javafx.logic.drag.icon.DragGhostIcon;
import io.github.slupik.schemablock.javafx.logic.drag.icon.GhostDragController;
import io.github.slupik.schemablock.javafx.logic.drag.node.DraggableNode;
import io.github.slupik.schemablock.javafx.logic.drag.node.NodeDragController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    @FXML
    private Pane mainContainer;

    @FXML
    private Button btnRun;

    @FXML
    private Button btnDebug;

    @FXML
    private HBox availableBlocks;

    @FXML
    private SplitPane centerContainer;

    @FXML
    private ScrollPane sheetScroller;

    @FXML
    private Pane sheet;

    @FXML
    private WebView outputView;

    @FXML
    private TableView<?> tvVariables;

    @FXML
    private TableColumn<?, ?> tcVarType;

    @FXML
    private TableColumn<?, ?> tcVarName;

    @FXML
    private TableColumn<?, ?> tcVarValue;

    private PortConnector connector;
    private PortSpawner spawner;
    private GhostDragController ghost;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupDragging();
    }

    private void setupDragging() {
        connector = new PortConnectorOnSheet(sheet);
        spawner = new PortSpawnerOnSheet(connector);
        ghost = new GhostDragController(mainContainer, sheet, new GhostDragElementFactoryImpl(spawner));
        addIconsToMenu();
        spawnStartElement();
    }

    private void spawnStartElement() {
        Platform.runLater(()->{
            UiElementBase start = UiElementFactory.createByType(UiElementType.START);
            sheet.getChildren().add(start);
            if(sheet.getWidth()<150){
                start.setLayoutX(10);
            } else if (sheet.getWidth()<600){
                double elementWidth = start.boundsInLocalProperty().get().getWidth();
                start.setLayoutX(sheet.getWidth()/2-elementWidth/2);
            } else {
                start.setLayoutX(300);
            }
            start.setLayoutY(10);
            new NodeDragController(new DraggableNode(start, false)).
                    addListener((newState, draggableNode) -> {
                        if(newState == DragEventState.DRAG_START) {
                            start.toFront();
                        }
                    });
            spawner.spawnForElement(start);

            testPort();
        });
    }

    //TODO delete testPort()
    private void testPort() {
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

    private void addIconsToMenu() {
        addDragDetection(new DragGhostIconUiElement(UiElementType.STOP));
        addDragDetection(new DragGhostIconUiElement(UiElementType.CALCULATION));
        addDragDetection(new DragGhostIconUiElement(UiElementType.IF));
        addDragDetection(new DragGhostIconUiElement(UiElementType.IO));
    }

    private void addDragDetection(DragGhostIcon dragIcon) {
        availableBlocks.getChildren().add(dragIcon);
        ghost.addDragDetection(dragIcon);
    }
}
