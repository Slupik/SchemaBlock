package de.tesis.dynaware.grapheditor.core.skins.defaults.condition.changer;

import javafx.geometry.Point2D;

/**
 * All rights reserved & copyright ©
 */
enum SegmentDirection {
    // ←
    LEFT,
    // ↑
    UP,
    // →
    RIGHT,
    // ↓
    DOWN;

    public static SegmentDirection getDirection(Point2D a, Point2D b) {
        if (a.getX() > b.getX()) {
            return SegmentDirection.LEFT;
        } else if (a.getX() < b.getX()) {
            return SegmentDirection.RIGHT;
        } else if (a.getY() > b.getY()) {
            return SegmentDirection.DOWN;
        } else {
            return SegmentDirection.UP;
        }
    }

}
