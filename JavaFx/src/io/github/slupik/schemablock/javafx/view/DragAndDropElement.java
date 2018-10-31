package io.github.slupik.schemablock.javafx.view;

import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;

/**
 * All rights reserved & copyright ©
 */
class DragAndDropElement {

    private final Region container;
    private final Node element;

    private double x;
    private double y;

    DragAndDropElement(Region container, Node element) {
        this.container = container;
        this.element = element;
//        element.addEventFilter(MouseEvent.ANY, event -> {
//            if(event.getEventType()!=MouseEvent.MOUSE_MOVED && event.getEventType()!=MouseEvent.MOUSE_EXITED_TARGET && event.getEventType()!=MouseEvent.MOUSE_EXITED) {
//                System.out.println("event = " + event);
//            }
//        });
        element.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
            x = element.getLayoutX();
            y = element.getLayoutY();
        });
        element.addEventFilter(MouseEvent.MOUSE_RELEASED, event -> {
            element.setLayoutX(event.getX());
            element.setLayoutY(event.getY());
        });
        element.addEventFilter(MouseEvent.MOUSE_DRAGGED, event -> {
            element.setLayoutX(event.getX());
            element.setLayoutY(event.getY());
        });
    }
}
