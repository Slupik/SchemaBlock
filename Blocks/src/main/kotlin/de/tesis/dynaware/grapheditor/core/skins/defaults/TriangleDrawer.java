/*
 * Copyright (c) 2020. by Sebastian Witasik
 * All rights reserved. No part of this application may be reproduced or be part of other software, without the prior written permission of the publisher. For permission requests, write to the author(WitasikSebastian@gmail.com).
 */

package de.tesis.dynaware.grapheditor.core.skins.defaults;

import de.tesis.dynaware.grapheditor.core.skins.defaults.utils.DefaultConnectorTypes;
import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;

public class TriangleDrawer {

    public static final double OUTER_SIZE = 17;

    public static String getTriangleType(Point2D lastJoint, Point2D port) {
        double accuracy = 0;
        if (Math.abs(port.getY() - lastJoint.getY()) < OUTER_SIZE / 2) {
            if (port.getX() - lastJoint.getX() >= accuracy) {
                return DefaultConnectorTypes.RIGHT_OUTPUT;
            } else {
                return DefaultConnectorTypes.LEFT_OUTPUT;
            }
        } else {
            if (port.getY() - lastJoint.getY() >= accuracy) {
                return DefaultConnectorTypes.BOTTOM_OUTPUT;
            } else {
                return DefaultConnectorTypes.TOP_OUTPUT;
            }
        }
    }

    /**
     * Draws the given polygon to have a triangular shape.
     *
     * @param type    the connector type
     * @param polygon the polygon to be drawn
     */
    public static void drawTriangleConnector(final String type, final Polygon polygon) {

        switch (type) {

            case DefaultConnectorTypes.TOP_INPUT:
                drawVertical(false, polygon);
                break;

            case DefaultConnectorTypes.TOP_OUTPUT:
                drawVertical(true, polygon);
                break;

            case DefaultConnectorTypes.RIGHT_INPUT:
                drawHorizontal(false, polygon);
                break;

            case DefaultConnectorTypes.RIGHT_OUTPUT:
                drawHorizontal(true, polygon);
                break;

            case DefaultConnectorTypes.BOTTOM_INPUT:
                drawVertical(true, polygon);
                break;

            case DefaultConnectorTypes.BOTTOM_OUTPUT:
                drawVertical(false, polygon);
                break;

            case DefaultConnectorTypes.LEFT_INPUT:
                drawHorizontal(true, polygon);
                break;

            case DefaultConnectorTypes.LEFT_OUTPUT:
                drawHorizontal(false, polygon);
                break;
        }
    }

    /**
     * Draws the polygon for a horizontal orientation, pointing right or left.
     *
     * @param pointingRight {@code true} to point right, {@code false} to point left
     * @param polygon       the polygon to be drawn
     */
    private static void drawHorizontal(final boolean pointingRight, final Polygon polygon) {

        if (pointingRight) {
            polygon.getPoints().addAll(0D, 0D, OUTER_SIZE, OUTER_SIZE / 2, 0D, OUTER_SIZE);
        } else {
            polygon.getPoints().addAll(OUTER_SIZE, 0D, OUTER_SIZE, OUTER_SIZE, 0D, OUTER_SIZE / 2);
        }
    }

    /**
     * Draws the polygon for a vertical orientation, pointing up or down.
     *
     * @param pointingUp {@code true} to point up, {@code false} to point down
     * @param polygon    the polygon to be drawn
     */
    private static void drawVertical(final boolean pointingUp, final Polygon polygon) {

        if (pointingUp) {
            polygon.getPoints().addAll(OUTER_SIZE / 2, 0D, OUTER_SIZE, OUTER_SIZE, 0D, OUTER_SIZE);
        } else {
            polygon.getPoints().addAll(0D, 0D, OUTER_SIZE, 0D, OUTER_SIZE / 2, OUTER_SIZE);
        }
    }

}
