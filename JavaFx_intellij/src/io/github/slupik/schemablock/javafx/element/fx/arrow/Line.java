package io.github.slupik.schemablock.javafx.element.fx.arrow;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polyline;

/**
 * All rights reserved & copyright ©
 */
public class Line extends Polyline {

    private Point startPoint = new Point(0,0);
    private Point endPoint = new Point(0,0);

    private double ratio = 0.5;
    private final boolean isNested;

    private Paint shapeColor = Color.BLACK;

    public Line() {
        this(false);
    }

    public Line(boolean isNested) {
        this.isNested = isNested;
    }

    public void setEnd(double x, double y) {
        endPoint = new Point(x, y);
        rebuild();
    }

    public void setStart(double x, double y) {
        startPoint = new Point(x, y);
        rebuild();
    }

    private void rebuild() {
        getPoints().clear();
        getPoints().addAll(0.0, 0.0);

        if(!isNested) {
            setLayoutX(startPoint.x);
            setLayoutY(startPoint.y);
        }

        setStroke(shapeColor);
        setStrokeWidth(3);

        Point localEnd = getLocalEnd();
        if(startPoint.x!=endPoint.x && startPoint.y!=endPoint.y) {
            getPoints().addAll(
                    0.0, localEnd.y*ratio,
                    localEnd.x, localEnd.y*ratio,
                    localEnd.x, localEnd.y
            );
        } else if((startPoint.x==endPoint.x && startPoint.y!=endPoint.y) ||
                (startPoint.x!=endPoint.x && startPoint.y==endPoint.y)) {
            getPoints().addAll(localEnd.x, localEnd.y);
        }
    }

    private Point getLocalEnd() {
        if(isNested) {
            return endPoint;
        } else {
            return new Point(
                    endPoint.x-getLayoutX(),
                    endPoint.y-getLayoutY()
            );
        }
    }

    public void setLineFill(Paint fill) {
        shapeColor = fill;
        setStroke(shapeColor);
    }
}
