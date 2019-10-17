package io.github.slupik.schemablock.javafx.logic.drag.icon;

import io.github.slupik.schemablock.javafx.logic.drag.DragControllerBase;
import io.github.slupik.schemablock.javafx.logic.drag.DragEventState;
import io.github.slupik.schemablock.model.ui.newparser.HeapController;
import io.github.slupik.schemablock.newparser.executor.Executor;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;

/**
 * All rights reserved & copyright ©
 */
public class GhostDragController extends DragControllerBase<DragGhostIcon> {
    private final Pane draggableArea;
    private final Pane placeForElement;
    private final GhostDragElementFactory factory;

    private DragGhostIcon mDragOverIcon;
    private final Executor executor;
    private final HeapController heap;
    private final DestContainerAfterDrop container;

    private EventHandler<DragEvent> mIconDragOverRoot = null;
    private EventHandler<DragEvent> mIconDragDropped = null;
    private EventHandler<DragEvent> mIconDragOverRightPane = null;

    public GhostDragController(Pane draggableArea, Pane placeForElement, GhostDragElementFactory factory, DestContainerAfterDrop container, Executor executor, HeapController heap) {
        this.draggableArea = draggableArea;
        this.placeForElement = placeForElement;
        this.factory = factory;
        this.container = container;

        mDragOverIcon = factory.getDragIcon(executor, heap);
        this.executor = executor;
        this.heap = heap;

        mDragOverIcon.setVisible(false);
        mDragOverIcon.setOpacity(0.65);
        draggableArea.getChildren().add(mDragOverIcon);

        buildDragHandlers();
    }

    public void addDragDetection(DragGhostIcon dragIcon) {
        dragIcon.setOnDragDetected (event -> {

            // set drag event handlers on their respective objects
            draggableArea.setOnDragOver(mIconDragOverRoot);
            placeForElement.setOnDragOver(mIconDragOverRightPane);
            placeForElement.setOnDragDropped(mIconDragDropped);

            // get a reference to the clicked DragGhostIcon object
            DragGhostIcon icn = (DragGhostIcon) event.getSource();

            //begin drag ops
            mDragOverIcon.setData(icn.getData());
            mDragOverIcon.relocateToPoint(new Point2D(event.getSceneX(), event.getSceneY()));

            ClipboardContent content = new ClipboardContent();
            DragContainer container = new DragContainer();

            container.addData ("type", mDragOverIcon.getData().toString());
            content.put(DragContainer.AddNode, container);

            mDragOverIcon.startDragAndDrop (TransferMode.ANY).setContent(content);
            mDragOverIcon.setVisible(true);
            mDragOverIcon.setMouseTransparent(true);
            event.consume();
            onStateChanged(DragEventState.DRAG_START, dragIcon);
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
            onStateChanged(DragEventState.DRAG);
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
            onStateChanged(DragEventState.DRAG);
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

                    Node droppedElement = factory.getNode(container, executor, heap);
                    this.container.addNode(droppedElement);

                    Point2D cursorPoint = container.getValue("scene_coords");

                    ElementRelocator.relocateToPoint(
                            droppedElement,
                            new Point2D(cursorPoint.getX(), cursorPoint.getY())
                    );
                    event.consume();
                    onStateChanged(DragEventState.DRAG_END);
                    return;
                }
            }

            event.consume();
        });
    }
}
