package io.github.slupik.schemablock.javafx.logic.drag.icon;

import io.github.slupik.schemablock.javafx.element.UiElementType;
import io.github.slupik.schemablock.javafx.element.fx.UiElementBase;
import io.github.slupik.schemablock.javafx.logic.drag.DragControllerBase;
import io.github.slupik.schemablock.javafx.logic.drag.DragEventState;
import io.github.slupik.schemablock.javafx.logic.drag.node.DraggableNode;
import io.github.slupik.schemablock.javafx.logic.drag.node.NodeDragController;
import io.github.slupik.schemablock.javafx.view.UiElementFactory;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;

/**
 * All rights reserved & copyright ©
 */
public class GhostDragController {
    private final Pane draggableArea;
    private final Pane placeForElement;

    private DragGhostIcon mDragOverIcon;

    private EventHandler<DragEvent> mIconDragOverRoot = null;
    private EventHandler<DragEvent> mIconDragDropped = null;
    private EventHandler<DragEvent> mIconDragOverRightPane = null;

    public GhostDragController(Pane draggableArea, Pane placeForElement) {
        this.draggableArea = draggableArea;
        this.placeForElement = placeForElement;

        mDragOverIcon = new DragGhostIcon();

        mDragOverIcon.setVisible(false);
        mDragOverIcon.setOpacity(0.65);
        draggableArea.getChildren().add(mDragOverIcon);

        buildDragHandlers();
    }

    public void addDragDetection(DragGhostIcon dragIcon) {
//        availableBlocks.getChildren().add(dragIcon);

        dragIcon.setOnDragDetected (event -> {

            // set drag event handlers on their respective objects
            draggableArea.setOnDragOver(mIconDragOverRoot);
            placeForElement.setOnDragOver(mIconDragOverRightPane);
            placeForElement.setOnDragDropped(mIconDragDropped);

            // get a reference to the clicked DragGhostIcon object
            DragGhostIcon icn = (DragGhostIcon) event.getSource();

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

            Point2D p = placeForElement.sceneToLocal(event.getSceneX(), event.getSceneY());

            //turn on transfer mode and track in the right-pane's context
            //if (and only if) the mouse cursor falls within the right pane's bounds.
            if (!placeForElement.boundsInLocalProperty().get().contains(p)) {

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

        draggableArea.setOnDragDone (event -> {

            placeForElement.removeEventHandler(DragEvent.DRAG_OVER, mIconDragOverRightPane);
            placeForElement.removeEventHandler(DragEvent.DRAG_DROPPED, mIconDragDropped);
            draggableArea.removeEventHandler(DragEvent.DRAG_OVER, mIconDragOverRoot);

            mDragOverIcon.setVisible(false);

            DragContainer container =
                    (DragContainer) event.getDragboard().getContent(DragContainer.AddNode);

            if (container != null) {
                if (container.getValue("scene_coords") != null) {

                    UiElementType type = UiElementType.valueOf(container.getValue("type"));
                    UiElementBase droppedElement = UiElementFactory.createByType(type);
                    placeForElement.getChildren().add(droppedElement);

                    Point2D cursorPoint = container.getValue("scene_coords");

                    ElementRelocator.relocateToPoint(
                            droppedElement,
                            new Point2D(cursorPoint.getX(), cursorPoint.getY())
                    );
                    NodeDragController draggingController = new NodeDragController(new DraggableNode(droppedElement, false));
                    draggingController.addListener((dragEvent, draggableNode) -> {
                        if(dragEvent == DragEventState.DRAG_START) {
                            draggingController.getEventNode().toFront();
                        }
                    });
                }
            }

            event.consume();
        });
    }
}
