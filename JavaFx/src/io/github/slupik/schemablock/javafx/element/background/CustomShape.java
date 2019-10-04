package io.github.slupik.schemablock.javafx.element.background;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.scene.paint.Color;

/**
 * All rights reserved & copyright ©
 */
public interface CustomShape {
    void setOuterSize(double width, double height);

    void setOuterWidth(double width);
    double getOuterWidth();
    DoubleProperty outerWidthProperty();

    void setOuterHeight(double height);
    double getOuterHeight();
    DoubleProperty outerHeightProperty();

    double getInnerHeight();
    ReadOnlyDoubleProperty innerHeightProperty();

    double getInnerWidth();
    ReadOnlyDoubleProperty innerWidthProperty();

    ReadOnlyDoubleProperty innerStartX();
    ReadOnlyDoubleProperty innerStartY();

    void setFill(Color web);
    void highlight();
    void resetColor();

    void markAsError();

    void markAsExecuting();

    boolean isContainsCoords(double x, double y);
}
