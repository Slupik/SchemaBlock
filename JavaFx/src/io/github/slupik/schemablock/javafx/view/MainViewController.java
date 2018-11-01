package io.github.slupik.schemablock.javafx.view;

import io.github.slupik.schemablock.javafx.element.UiElementType;
import io.github.slupik.schemablock.javafx.element.fx.UiElementBase;
import io.github.slupik.schemablock.javafx.element.fx.special.StartElement;
import io.github.slupik.schemablock.javafx.element.fx.special.StopElement;
import io.github.slupik.schemablock.javafx.element.fx.special.UiSpecialElement;
import io.github.slupik.schemablock.javafx.element.fx.standard.ConditionBlock;
import io.github.slupik.schemablock.javafx.element.fx.standard.IOBlock;
import io.github.slupik.schemablock.javafx.element.fx.standard.OperatingBlock;
import io.github.slupik.schemablock.javafx.element.fx.standard.UiStandardElement;
import io.github.slupik.schemablock.javafx.logic.drag.icon.DragContainer;
import io.github.slupik.schemablock.javafx.logic.drag.icon.DragIcon;
import io.github.slupik.schemablock.javafx.logic.drag.icon.ElementRelocator;
import io.github.slupik.schemablock.javafx.logic.drag.node.Draggable;
import io.github.slupik.schemablock.javafx.logic.drag.node.DraggingController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.control.*;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
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
//        spawnAvailableBlocks();
        testDragging();
    }

    private void spawnAvailableBlocks() {
        double height = 31;
        double width = 50;

        UiSpecialElement start = new StartElement();
        start.setElementSize(width, height);

        UiSpecialElement stop = new StopElement();
        stop.setElementSize(width, height);

        UiStandardElement operating = new OperatingBlock();
        operating.setElementSize(width, height);

        UiStandardElement io = new IOBlock();
        io.setElementSize(width, height);

        UiStandardElement condition = new ConditionBlock();
        condition.setElementSize(width, height);

        availableBlocks.getChildren().addAll(start, stop, operating, io, condition);
    }

    private void testDragging() {
//        double height = 31;
//        double width = 50;
//        UiSpecialElement test = new StartElement();
//        test.setElementSize(width, height);
//        sheet.getChildren().add(test);

        mDragOverIcon = new DragIcon();

        mDragOverIcon.setVisible(false);
        mDragOverIcon.setOpacity(0.65);
        mainContainer.getChildren().add(mDragOverIcon);

        addDragDetection(new DragIcon().setType(UiElementType.START));
        addDragDetection(new DragIcon().setType(UiElementType.STOP));
        addDragDetection(new DragIcon().setType(UiElementType.CALCULATION));
        addDragDetection(new DragIcon().setType(UiElementType.IF));
        addDragDetection(new DragIcon().setType(UiElementType.IO));

        buildDragHandlers();
    }



    private DragIcon mDragOverIcon = null;

    private EventHandler<DragEvent> mIconDragOverRoot = null;
    private EventHandler<DragEvent> mIconDragDropped = null;
    private EventHandler<DragEvent> mIconDragOverRightPane = null;

    private void addDragDetection(DragIcon dragIcon) {
        availableBlocks.getChildren().add(dragIcon);

        dragIcon.setOnDragDetected (event -> {

            // set drag event handlers on their respective objects
            mainContainer.setOnDragOver(mIconDragOverRoot);
            sheet.setOnDragOver(mIconDragOverRightPane);
            sheet.setOnDragDropped(mIconDragDropped);

            // get a reference to the clicked DragIcon object
            DragIcon icn = (DragIcon) event.getSource();

            //begin drag ops
            mDragOverIcon.setType(icn.getType());
            mDragOverIcon.relocateToPoint(new Point2D(event.getSceneX(), event.getSceneY()));

            ClipboardContent content = new ClipboardContent();
            DragContainer container = new DragContainer();

            container.addData ("type", mDragOverIcon.getType().toString());
            content.put(DragContainer.AddNode, container);

            mDragOverIcon.startDragAndDrop (TransferMode.ANY).setContent(content);
            mDragOverIcon.setVisible(true);
            mDragOverIcon.setMouseTransparent(true);
            event.consume();
        });
    }

    private void buildDragHandlers() {

        //drag over transition to move widget form left pane to right pane
        mIconDragOverRoot = event -> {

            Point2D p = sheet.sceneToLocal(event.getSceneX(), event.getSceneY());

            //turn on transfer mode and track in the right-pane's context
            //if (and only if) the mouse cursor falls within the right pane's bounds.
            if (!sheet.boundsInLocalProperty().get().contains(p)) {

                event.acceptTransferModes(TransferMode.ANY);
                mDragOverIcon.relocateToPoint(new Point2D(event.getSceneX(), event.getSceneY()));
                return;
            }

            event.consume();
        };

        mIconDragOverRightPane = event -> {

            event.acceptTransferModes(TransferMode.ANY);

            //convert the mouse coordinates to scene coordinates,
            //then convert back to coordinates that are relative to
            //the parent of mDragIcon.  Since mDragIcon is a child of the root
            //pane, coodinates must be in the root pane's coordinate system to work
            //properly.
            mDragOverIcon.relocateToPoint(
                    new Point2D(event.getSceneX(), event.getSceneY())
            );
            event.consume();
        };

        mIconDragDropped = event -> {

            DragContainer container =
                    (DragContainer) event.getDragboard().getContent(DragContainer.AddNode);

            container.addData("scene_coords",
                    new Point2D(event.getSceneX(), event.getSceneY()));

            ClipboardContent content = new ClipboardContent();
            content.put(DragContainer.AddNode, container);

            event.getDragboard().setContent(content);
            event.setDropCompleted(true);
        };

        mainContainer.setOnDragDone (event -> {

            sheet.removeEventHandler(DragEvent.DRAG_OVER, mIconDragOverRightPane);
            sheet.removeEventHandler(DragEvent.DRAG_DROPPED, mIconDragDropped);
            mainContainer.removeEventHandler(DragEvent.DRAG_OVER, mIconDragOverRoot);

            mDragOverIcon.setVisible(false);

            DragContainer container =
                    (DragContainer) event.getDragboard().getContent(DragContainer.AddNode);

            if (container != null) {
                if (container.getValue("scene_coords") != null) {

                    UiElementType type = UiElementType.valueOf(container.getValue("type"));
                    UiElementBase droppedElement = UiElementFactory.createByType(type);
                    sheet.getChildren().add(droppedElement);

                    Point2D cursorPoint = container.getValue("scene_coords");

                    ElementRelocator.relocateToPoint(
                            droppedElement,
                            new Point2D(cursorPoint.getX(), cursorPoint.getY())
                    );
                    DraggingController draggingController = new DraggingController(new Draggable(droppedElement, false));
                    draggingController.addListener((draggingController1, dragEvent) -> {
                        if(dragEvent == DraggingController.Event.DragStart) {
                            draggingController.getEventNode().toFront();
                        }
                    });
                }
            }

            event.consume();
        });
    }
}
