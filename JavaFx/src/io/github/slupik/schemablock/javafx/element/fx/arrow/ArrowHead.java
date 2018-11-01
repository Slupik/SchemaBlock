package io.github.slupik.schemablock.javafx.element.fx.arrow;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;

/**
 * All rights reserved & copyright ©
 */
public class ArrowHead extends Pane {
    public static final double WIDTH = 15;
    public static final double HEIGHT = 15;

    private final Polygon triangle = new Polygon();

    public ArrowHead(){
        getChildren().add(triangle);
        triangle.getPoints().addAll(
            WIDTH/2, 0.0,
                WIDTH, HEIGHT,
                0.0, HEIGHT,
                WIDTH/2, 0.0
        );
    }

    public void setArrowEnd(double x, double y, double strokeWidth) {
        if(getRotate()%180==0) {
            if(y>0) {
                setLayoutX(x-WIDTH/2);
                setLayoutY(y-HEIGHT+(strokeWidth/2));
            } else {
                setLayoutX(x-WIDTH/2);
                setLayoutY(y);
            }
        } else {
            if(x>0) {
                setLayoutX(x-WIDTH+(strokeWidth/2));
                setLayoutY(y-WIDTH/2);
            } else {
                setLayoutX(x-(strokeWidth/2));
                setLayoutY(y-WIDTH/2);
            }
        }
    }
}
