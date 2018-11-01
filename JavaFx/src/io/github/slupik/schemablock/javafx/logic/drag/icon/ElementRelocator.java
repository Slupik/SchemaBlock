package io.github.slupik.schemablock.javafx.logic.drag.icon;

import javafx.geometry.Point2D;
import javafx.scene.Node;

/**
 * All rights reserved & copyright ©
 */
public class ElementRelocator {

    private ElementRelocator(){}

    public static void relocateToPoint(Node element, Point2D p) {

        //relocates the object to a point that has been converted to
        //scene coordinates
        Point2D localCoords = element.getParent().sceneToLocal(p);

        element.relocate (
                (int) (localCoords.getX() - (element.getBoundsInLocal().getWidth() / 2)),
                (int) (localCoords.getY() - (element.getBoundsInLocal().getHeight() / 2))
        );
    }
}
