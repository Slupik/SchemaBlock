package io.github.slupik.schemablock.javafx.element.custom;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * All rights reserved & copyright ©
 */
public class MyRectangle extends CustomShapeBase {

    private Rectangle rectangle;

    @Override
    protected void onDimensionsBinding() {
        innerWidth.bind(outerWidthProperty().multiply(0.9));
        innerHeight.bind(outerHeightProperty().multiply(0.9));
    }

    @Override
    protected Shape createShape() {
        rectangle = new Rectangle();
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeWidth(1);
        return rectangle;
    }

    @Override
    protected void recreate() {
        rectangle.setWidth(getOuterWidth());
        rectangle.setHeight(getOuterHeight());
    }

    @Override
    public void setFill(Color web) {
        rectangle.setFill(web);
    }
}
