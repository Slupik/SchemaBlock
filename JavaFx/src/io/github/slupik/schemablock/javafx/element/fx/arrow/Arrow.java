package io.github.slupik.schemablock.javafx.element.fx.arrow;

import javafx.scene.layout.AnchorPane;

/**
 * All rights reserved & copyright ©
 */
public class Arrow extends AnchorPane {
    private Point startPoint = new Point(0,0);
    private Point endPoint = new Point(0,0);

    private final Line line = new Line(true);
    private final ArrowHead head = new ArrowHead();

    public Arrow(){
        getChildren().addAll(line, head);
    }

    public void setEnd(double x, double y) {
        endPoint = new Point(x, y);
        rebuild();
    }

    public void setStart(double x, double y) {
        startPoint = new Point(x, y);
        line.setStart(0, 0);
        rebuild();
    }

    private void rebuild() {
        setLayoutX(startPoint.x);
        setLayoutY(startPoint.y);
        Point localEnd = getLocalEnd();
        Point fixedLocalEnd = getFixedForLineLocalEnd(localEnd);
        line.setEnd(fixedLocalEnd.x, fixedLocalEnd.y);

        double angel = getArrowAngel();
        head.setRotate(angel);
        head.setArrowEnd(localEnd.x, localEnd.y, line.getStrokeWidth());
    }

    private Point getFixedForLineLocalEnd(Point localEnd) {
        double fX;
        double fY;
        if(localEnd.y!=0) {
            if(localEnd.y>0) {
                fY = localEnd.y-head.HEIGHT*0.3;
            } else {
                fY = localEnd.y+head.HEIGHT*0.3;
            }
            fX = localEnd.x;
        } else {
            fX = localEnd.x;
            fY = localEnd.y;
        }
        return new Point(fX, fY);
    }

    private double getArrowAngel() {
        double angel = 0;
        if(line.getPoints().size()>=4) {
            double ax = line.getPoints().get(line.getPoints().size() - 2);
            double ay = line.getPoints().get(line.getPoints().size() - 1);

            double bx = line.getPoints().get(line.getPoints().size() - 4);
            double by = line.getPoints().get(line.getPoints().size() - 3);

            angel = Math.atan2(ay - by, ax - bx) * 180 / Math.PI;
        }

        angel+=90;

        return angel;
    }

    private Point getLocalEnd() {
        return new Point(
                endPoint.x-getLayoutX(),
                endPoint.y-getLayoutY()
        );
    }
}
