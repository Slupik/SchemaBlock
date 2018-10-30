package io.github.slupik.schemablock.javafx.element.background;

/**
 * All rights reserved & copyright ©
 */
public class Rhombus extends CustomPolygon {

    @Override
    protected void recreate() {
        shape.getPoints().clear();
        shape.getPoints().addAll(
                    getOuterWidth()/2, 0d,
                    getOuterWidth(), getOuterHeight()/2,
                    getOuterWidth()/2, getOuterHeight(),
                    0d, getOuterHeight()/2,
                    getOuterWidth()/2, 0d
                );
    }

    @Override
    protected void onDimensionsBinding() {
        innerWidth.bind(width.divide(2));
        innerHeight.bind(height.divide(2));

        startX.bind(width.divide(4));
        startY.bind(height.divide(4));
    }
}
