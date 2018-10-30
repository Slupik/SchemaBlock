package io.github.slupik.schemablock.javafx.element.background;

/**
 * All rights reserved & copyright ©
 */
public class Parallelogram extends CustomPolygon {

    @Override
    protected void recreate() {
        shape.getPoints().clear();
        shape.getPoints().addAll(
                (getOuterWidth()/5), 0.0,
                getOuterWidth(), 0.0, (getOuterWidth()/5*4),
                getOuterHeight(), 0.0, getOuterHeight(),
                (getOuterWidth()/5), 0.0
        );
    }

    @Override
    protected void onDimensionsBinding() {
        innerWidth.bind(width.divide(5).multiply(3));
        innerHeight.bind(height);

        startX.bind(width.divide(5));
    }
}
