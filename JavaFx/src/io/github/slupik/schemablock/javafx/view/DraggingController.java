package io.github.slupik.schemablock.javafx.view;

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
public class DraggingController implements EventHandler<MouseEvent> {
    private double lastMouseX = 0, lastMouseY = 0; // scene coords

    private boolean dragging = false;

    private final boolean enabled = true;
    private final Draggable eventNode;
    private final List<Draggable> dragNodes = new ArrayList<>();
    private final List<Listener> dragListeners = new ArrayList<>();

    public DraggingController(final Draggable node) {
        this(node, node);
    }

    public DraggingController(final Draggable eventNode, final Draggable... dragNodes) {
        this.eventNode = eventNode;
        this.dragNodes.addAll(Arrays.asList(dragNodes));
        this.eventNode.node.addEventHandler(MouseEvent.ANY, this);
    }

    public final boolean addDraggedNode(final Draggable node) {
        if (!this.dragNodes.contains(node)) {
            return this.dragNodes.add(node);
        }
        return false;
    }

    public final boolean addListener(final Listener listener) {
        return this.dragListeners.add(listener);
    }

    public final void detatch() {
        this.eventNode.node.removeEventFilter(MouseEvent.ANY, this);
    }

    public final List<Draggable> getDragNodes() {
        return new ArrayList<>(this.dragNodes);
    }

    public final Node getEventNode() {
        return this.eventNode.node;
    }

    @Override
    public final void handle(final MouseEvent event) {
        if (MouseEvent.MOUSE_PRESSED == event.getEventType()) {
            if (this.enabled && this.eventNode.node.contains(event.getX(), event.getY())) {
                this.lastMouseX = event.getSceneX();
                this.lastMouseY = event.getSceneY();
                event.consume();
            }
        } else if (MouseEvent.MOUSE_DRAGGED == event.getEventType()) {
            if (!this.dragging) {
                this.dragging = true;
                for (final Listener listener : this.dragListeners) {
                    listener.accept(this, DraggingController.Event.DragStart);
                }
            }
            if (this.dragging) {
                final double deltaX = event.getSceneX() - this.lastMouseX;
                final double deltaY = event.getSceneY() - this.lastMouseY;

                for (final Draggable dragNode : this.dragNodes) {
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
                for (final Listener listener : this.dragListeners) {
                    listener.accept(this, DraggingController.Event.Drag);
                }
            }
        } else if (MouseEvent.MOUSE_RELEASED == event.getEventType()) {
            if (this.dragging) {
                event.consume();
                this.dragging = false;
                for (final Listener listener : this.dragListeners) {
                    listener.accept(this, DraggingController.Event.DragEnd);
                }
            }
        }

    }

    public final boolean removeDraggedNode(final Node node) {
        return this.dragNodes.remove(node);
    }

    public final boolean removeListener(final Listener listener) {
        return this.dragListeners.remove(listener);
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

    public enum Event {
        None, DragStart, Drag, DragEnd
    }

    public interface Interface {
        public abstract DraggingController getDraggableNature();
    }

    public interface Listener {
        public void accept(DraggingController draggingController, Event dragEvent);
    }
}