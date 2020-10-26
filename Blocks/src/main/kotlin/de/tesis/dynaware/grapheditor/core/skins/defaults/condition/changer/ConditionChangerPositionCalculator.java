package de.tesis.dynaware.grapheditor.core.skins.defaults.condition.changer;

import javafx.geometry.Point2D;

import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public class ConditionChangerPositionCalculator {

    public Point2D calculate(List<Point2D> path, ConditionChanger changer) {
        Point2D a = path.get(0);
        Point2D b = path.get(1);
        return calculate(a, b, changer);
    }

    private Point2D calculate(Point2D start, Point2D end, ConditionChanger changer) {
        SegmentDirection direction = SegmentDirection.getDirection(start, end);
        switch (direction) {
            case UP:
                return start.add(0, changer.getHeight());
            case DOWN:
                return start.add(0, 0);
            case RIGHT:
                return start.add(0, -changer.getHeight());
            case LEFT:
                return start.add(-changer.getWidth(), -changer.getHeight());
        }
        return start;
    }

}
