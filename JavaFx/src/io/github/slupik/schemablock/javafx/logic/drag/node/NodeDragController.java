package io.github.slupik.schemablock.javafx.logic.drag.node;

import io.github.slupik.schemablock.javafx.logic.drag.DragControllerBase;
import io.github.slupik.schemablock.javafx.logic.drag.DragEventState;
import io.github.slupik.schemablock.javafx.logic.drag.icon.DragGhostIcon;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://stackoverflow.com/questions/17312734/how-to-make-a-draggable-node-in-javafx-2-0
 * @author phill
 */
public class NodeDragController extends DragControllerBase<DraggableNode> implements EventHandler<MouseEvent> {
    private double lastMouseX = 0, lastMouseY = 0; // scene coords

    private boolean dragging = false;

    private final DraggableNode eventNode;
    private final List<DraggableNode> dragNodes = new ArrayList<>();

    public NodeDragController(final DraggableNode node) {
        this(node, node);
    }

    public NodeDragController(final DraggableNode eventNode, final DraggableNode... dragNodes) {
        this.eventNode = eventNode;
        this.dragNodes.addAll(Arrays.asList(dragNodes));
        this.eventNode.node.addEventHandler(MouseEvent.ANY, this);
    }

    public final boolean addDraggedNode(final DraggableNode node) {
        if (!this.dragNodes.contains(node)) {
            return this.dragNodes.add(node);
        }
        return false;
    }

    public final void detatch() {
        this.eventNode.node.removeEventFilter(MouseEvent.ANY, this);
    }

    public final List<DraggableNode> getDragNodes() {
        return new ArrayList<>(this.dragNodes);
    }

    public final Node getEventNode() {
        return this.eventNode.node;
    }

    @Override
    public final void handle(final MouseEvent event) {
        if (MouseEvent.MOUSE_PRESSED == event.getEventType()) {
            if (this.eventNode.node.contains(event.getX(), event.getY())) {
                this.lastMouseX = event.getSceneX();
                this.lastMouseY = event.getSceneY();
                event.consume();
            }
        } else if (MouseEvent.MOUSE_DRAGGED == event.getEventType()) {
            if (!this.dragging) {
                this.dragging = true;
                onStateChanged(DragEventState.DRAG_START, eventNode);
            }
            if (this.dragging) {
                final double deltaX = event.getSceneX() - this.lastMouseX;
                final double deltaY = event.getSceneY() - this.lastMouseY;

                for (final DraggableNode dragNode : this.dragNodes) {
                    if(dragNode.useRelativePos) {
                        final double initialTranslateX = dragNode.node.getTranslateX();
                        final double initialTranslateY = dragNode.node.getTranslateY();
                        dragNode.node.setTranslateX(initialTranslateX + deltaX);
                        dragNode.node.setTranslateY(initialTranslateY + deltaY);
                    } else {
                        final double initialTranslateX = dragNode.node.getLayoutX();
                        final double initialTranslateY = dragNode.node.getLayoutY();
                        dragNode.node.setLayoutX(initialTranslateX + deltaX);
                        dragNode.node.setLayoutY(initialTranslateY + deltaY);
                    }
                }

                this.lastMouseX = event.getSceneX();
                this.lastMouseY = event.getSceneY();

                event.consume();
                onStateChanged(DragEventState.DRAG, eventNode);
            }
        } else if (MouseEvent.MOUSE_RELEASED == event.getEventType()) {
            if (this.dragging) {
                event.consume();
                this.dragging = false;
                onStateChanged(DragEventState.DRAG_END, eventNode);
            }
        }

    }

    public final boolean removeDraggedNode(final Node node) {
        return this.dragNodes.remove(node);
    }

    /**
     * When the initial mousePressed is missing we can supply the first coordinates programmatically.
     * @param lastMouseX
     * @param lastMouseY
     */
    public final void setLastMouse(final double lastMouseX, final double lastMouseY) {
        this.lastMouseX = lastMouseX;
        this.lastMouseY = lastMouseY;
    }
}