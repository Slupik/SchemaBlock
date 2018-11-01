package io.github.slupik.schemablock.javafx.view;

import io.github.slupik.schemablock.javafx.element.UiElementType;
import io.github.slupik.schemablock.javafx.element.fx.arrow.Arrow;
import io.github.slupik.schemablock.javafx.element.fx.arrow.Line;
import io.github.slupik.schemablock.javafx.logic.drag.icon.DragGhostIcon;
import io.github.slupik.schemablock.javafx.logic.drag.icon.GhostDragController;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupDragging();
    }

    private void setupDragging() {
        ghost = new GhostDragController(mainContainer, sheet, new GhostDragElementFactoryImpl());
        addIconsToMenu();
        testAddArrow();
    }

    private void testAddArrow() {
        Arrow line = new Arrow();
        sheet.getChildren().addAll(line);
        line.setStart(50, 100);
        line.setEnd(100, 100);
    }

    private void addIconsToMenu() {
        addDragDetection(new DragGhostIconUiElement(UiElementType.START));
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
