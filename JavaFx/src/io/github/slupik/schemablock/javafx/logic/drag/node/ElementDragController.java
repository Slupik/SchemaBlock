package io.github.slupik.schemablock.javafx.logic.drag.node;

import io.github.slupik.schemablock.javafx.logic.drag.DragControllerBase;
import io.github.slupik.schemablock.javafx.logic.drag.DragEventState;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://stackoverflow.com/questions/17312734/how-to-make-a-draggable-node-in-javafx-2-0
 * @author phill
 */
public class ElementDragController extends DragControllerBase<DraggableElement> implements EventHandler<MouseEvent> {
    private double lastMouseX = 0, lastMouseY = 0; // scene coords

    private boolean dragging = false;

    private final DraggableElement eventNode;
    private final List<DraggableElement> dragNodes = new ArrayList<>();

    public ElementDragController(final DraggableElement node) {
        this(node, node);
    }

    public ElementDragController(final DraggableElement eventNode, final DraggableElement... dragNodes) {
        this.eventNode = eventNode;
        this.dragNodes.addAll(Arrays.asList(dragNodes));
        this.eventNode.element.getGraphic().addEventHandler(MouseEvent.ANY, this);
    }

    public final boolean addDraggedNode(final DraggableElement node) {
        if (!this.dragNodes.contains(node)) {
            return this.dragNodes.add(node);
        }
        return false;
    }

    public final void detatch() {
        this.eventNode.element.getGraphic().removeEventFilter(MouseEvent.ANY, this);
    }

    public final List<DraggableElement> getDragNodes() {
        return new ArrayList<>(this.dragNodes);
    }

    public final Node getEventNode() {
        return this.eventNode.element.getGraphic();
    }

    @Override
    public final void handle(final MouseEvent event) {
        if (MouseEvent.MOUSE_PRESSED == event.getEventType()) {
            if (this.eventNode.element.getGraphic().contains(event.getX(), event.getY())) {
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

                for (final DraggableElement dragNode : this.dragNodes) {
                    if(dragNode.useRelativePos) {
                        final double initialTranslateX = dragNode.element.getGraphic().getTranslateX();
                        final double initialTranslateY = dragNode.element.getGraphic().getTranslateY();
                        dragNode.element.getGraphic().setTranslateX(initialTranslateX + deltaX);
                        dragNode.element.getGraphic().setTranslateY(initialTranslateY + deltaY);
                    } else {
                        final double initialTranslateX = dragNode.element.getGraphic().getLayoutX();
                        final double initialTranslateY = dragNode.element.getGraphic().getLayoutY();
                        final double newX = initialTranslateX + deltaX;
                        final double newY = initialTranslateY + deltaY;

                        //Set new position and prevent before place element out of bounds
                        Pane parent = ((Pane) dragNode.element.getGraphic().getParent());
                        if(newX<0) {
                            dragNode.element.getGraphic().setLayoutX(0);
                        } else {
                            if((newX+dragNode.element.getGraphic().getBoundsInLocal().getWidth())>parent.getWidth()){
                                dragNode.element.getGraphic().setLayoutX((
                                        parent.getWidth()-dragNode.element.getGraphic().getBoundsInLocal().getWidth()
                                        ));
                            } else {
                                dragNode.element.getGraphic().setLayoutX(newX);
                            }
                        }
                        if(newY<0){
                            dragNode.element.getGraphic().setLayoutY(0);
                        } else {
                            if((newY+dragNode.element.getGraphic().getBoundsInLocal().getHeight())>parent.getHeight()) {
                                dragNode.element.getGraphic().setLayoutY((
                                        parent.getHeight()-dragNode.element.getGraphic().getBoundsInLocal().getHeight()
                                ));
                            } else {
                                dragNode.element.getGraphic().setLayoutY(newY);
                            }
                        }
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