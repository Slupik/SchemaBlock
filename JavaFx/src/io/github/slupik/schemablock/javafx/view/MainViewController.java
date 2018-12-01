package io.github.slupik.schemablock.javafx.view;

import io.github.slupik.schemablock.javafx.element.UiElementType;
import io.github.slupik.schemablock.javafx.element.fx.communication.UIIOCommunicator;
import io.github.slupik.schemablock.javafx.element.fx.sheet.DefaultSheetWithElements;
import io.github.slupik.schemablock.javafx.element.fx.sheet.SheetWithElements;
import io.github.slupik.schemablock.javafx.logic.drag.icon.DragGhostIcon;
import io.github.slupik.schemablock.javafx.logic.drag.icon.GhostDragController;
import io.github.slupik.schemablock.model.ui.implementation.element.specific.IOCommunicable;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    @FXML
    private BorderPane mainContainer;

    @FXML
    private MenuItem miSave;

    @FXML
    private MenuItem miLoad;

    @FXML
    private MenuItem miClose;

    @FXML
    private MenuItem miNew;

    @FXML
    private MenuItem miDelete;

    @FXML
    private MenuItem miAbout;

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
    private VBox vbIOContainer;

    @FXML
    private WebView outputView;

    @FXML
    private HBox hbInputContainer;

    @FXML
    private TextField tfInput;

    @FXML
    private Button btnEnter;

    @FXML
    private TableView<?> tvVariables;

    @FXML
    private TableColumn<?, ?> tcVarType;

    @FXML
    private TableColumn<?, ?> tcVarName;

    @FXML
    private TableColumn<?, ?> tcVarValue;

    private SheetWithElements container;
    private GhostDragController ghost;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupDragging();
    }

    private void setupDragging() {
        IOCommunicable communicable = new UIIOCommunicator(tfInput, outputView, btnEnter);
        container = new DefaultSheetWithElements(sheet, communicable);
        ghost = new GhostDragController(mainContainer, sheet, new GhostDragElementFactoryImpl(container.getPortSpawner()), container.getChildrenHandler());
        addIconsToMenu();
        btnRun.setOnAction((event)-> {
            communicable.clearOutput();
            container.run();
        });
        bindIOView();
        setupMenu();
    }

    private void setupMenu() {
        miSave.setOnAction(event -> {
            //TODO make system to save data to a file
            System.out.println(container.stringify());
        });
    }

    private void bindIOView() {
        hbInputContainer.prefWidthProperty().bind(vbIOContainer.widthProperty());
        hbInputContainer.prefHeightProperty().bind(tfInput.heightProperty());

        outputView.prefWidthProperty().bind(vbIOContainer.widthProperty());
        outputView.prefHeightProperty().bind(vbIOContainer.heightProperty().subtract(hbInputContainer.heightProperty()));

        Platform.runLater(()-> tfInput.prefWidthProperty().bind(hbInputContainer.widthProperty().subtract(btnEnter.widthProperty()).subtract(16)));
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
