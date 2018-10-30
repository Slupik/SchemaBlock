package io.github.slupik.schemablock.javafx.element.custom;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;

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
}
