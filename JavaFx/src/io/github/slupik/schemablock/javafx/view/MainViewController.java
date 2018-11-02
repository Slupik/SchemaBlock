package io.github.slupik.schemablock.javafx.view;

import io.github.slupik.schemablock.javafx.element.UiElementType;
import io.github.slupik.schemablock.javafx.element.fx.UiElementBase;
import io.github.slupik.schemablock.javafx.element.fx.arrow.Arrow;
import io.github.slupik.schemablock.javafx.element.fx.port.PortElement;
import io.github.slupik.schemablock.javafx.element.fx.port.PortConnector;
import io.github.slupik.schemablock.javafx.logic.drag.DragEventState;
import io.github.slupik.schemablock.javafx.logic.drag.icon.DragGhostIcon;
import io.github.slupik.schemablock.javafx.logic.drag.icon.GhostDragController;
import io.github.slupik.schemablock.javafx.logic.drag.node.DraggableNode;
import io.github.slupik.schemablock.javafx.logic.drag.node.NodeDragController;
import io.github.slupik.schemablock.model.ui.abstraction.element.Element;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupDragging();
    }

    private void setupDragging() {
        ghost = new GhostDragController(mainContainer, sheet, new GhostDragElementFactoryImpl());
        addIconsToMenu();
        spawnStartElement();
    }

    private void spawnStartElement() {
        Platform.runLater(()->{
            UiElementBase start = UiElementFactory.createByType(UiElementType.START);
            sheet.getChildren().add(start);
            if(sheet.getWidth()<150){
                start.setLayoutX(0);
            } else {
                start.setLayoutX(100);
            }
            start.setLayoutY(0);
            new NodeDragController(new DraggableNode(start, false)).
                    addListener((newState, draggableNode) -> {
                        if(newState == DragEventState.DRAG_START) {
                            start.toFront();
                        }
                    });
            testPort(start);
        });
    }

    private void testPort(UiElementBase element) {

        //TODO spawn port inside UI element
        PortConnector connector = new PortConnectorOnSheet(sheet);
        PortElement port = new PortElement(element, connector, false, true);
        sheet.getChildren().add(port);
        port.setRelativePos(25, 20);
        connector.addPort(port);

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

            PortElement portEnd = new PortElement(end, connector, true, false);
            sheet.getChildren().add(portEnd);
            portEnd.setRelativePos(25, 20);
            connector.addPort(portEnd);
        });
    }

    private void addIconsToMenu() {
        addDragDetection(new DragGhostIconUiElement(UiElementType.STOP));
        addDragDetection(new DragGhostIconUiElement(UiElementType.CALCULATION));
        addDragDetection(new DragGhostIconUiElement(UiElementType.IF));
        addDragDetection(new DragGhostIconUiElement(UiElementType.IO));
    }

    private GhostDragController ghost;

    private void addDragDetection(DragGhostIcon dragIcon) {
        availableBlocks.getChildren().add(dragIcon);
        ghost.addDragDetection(dragIcon);
    }

}
