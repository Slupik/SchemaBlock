package io.github.slupik.schemablock.javafx.element.background;

import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;

/**
 * All rights reserved & copyright ©
 */
public class MyEllipse extends CustomShapeBase {

    private Ellipse ellipse;

    @Override
    protected void onDimensionsBinding() {
        innerWidth.bind(outerWidthProperty().multiply(0.85));
        innerHeight.bind(outerHeightProperty().multiply(0.9));
    }

    @Override
    protected Shape createShape() {
        ellipse = new Ellipse();
        ellipse.setStroke(Color.BLACK);
        ellipse.setStrokeWidth(1);
        return ellipse;
    }

    @Override
    protected void recreate() {
        ellipse.setRadiusX(getOuterWidth()/2);
        ellipse.setRadiusY(getOuterHeight()/2);

        ellipse.setCenterX(getOuterWidth()/2);
        ellipse.setCenterY(getOuterHeight()/2);
    }

    @Override
    public void setFill(Color web) {
        ellipse.setFill(web);
    }
}
