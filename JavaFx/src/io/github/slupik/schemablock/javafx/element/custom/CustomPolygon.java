package io.github.slupik.schemablock.javafx.element.custom;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

/**
 * All rights reserved & copyright ©
 */
public abstract class CustomPolygon extends CustomShapeBase {

    protected Polygon shape;

    @Override
    protected Shape createShape() {
        shape = new Polygon();
        shape.setStroke(Color.BLACK);
        shape.setStrokeWidth(1);
        return shape;
    }

    @Override
    public void setFill(Color web) {
        shape.setFill(web);
    }
}
