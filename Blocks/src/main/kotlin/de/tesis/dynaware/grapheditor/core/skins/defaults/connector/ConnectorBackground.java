/*
 * Copyright (c) 2020. by Sebastian Witasik
 * All rights reserved. No part of this application may be reproduced or be part of other software, without the prior written permission of the publisher. For permission requests, write to the author(WitasikSebastian@gmail.com).
 */

package de.tesis.dynaware.grapheditor.core.skins.defaults.connector;

import de.tesis.dynaware.grapheditor.GConnectorStyle;
import de.tesis.dynaware.grapheditor.core.skins.defaults.utils.DefaultConnectorTypes;
import javafx.geometry.Side;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;

class ConnectorBackground {

    private static final String STYLE_CLASS_ACTIVE = "default-connector-part-active";
    private static final String STYLE_CLASS_NONACTIVE = "default-connector-part-nonactive";

    private final double OUTER_SIZE;
    private final String TYPE;

    private final VBox root = new VBox();
    private final Arc inPart = new Arc();
    private final Arc outPart = new Arc();

    ConnectorBackground(final String type, final double outerSize) {
        TYPE = type;
        OUTER_SIZE = outerSize;

        createInPart();
        createOutPart();
        root.getChildren().addAll(inPart, outPart);
        adjustRotation();
    }

    private void adjustRotation() {
        Side side = DefaultConnectorTypes.getSide(TYPE);
        if (side == null) side = Side.TOP;
        switch (side) {
            case TOP: {
                root.setRotate(180);
                break;
            }
            case BOTTOM: {
                root.setRotate(0);
                break;
            }
            case LEFT: {
                root.setRotate(90);
                break;
            }
            case RIGHT: {
                root.setRotate(-90);
                break;
            }
        }
    }

    private void createInPart() {
        inPart.setStartAngle(0f);
        inPart.setLength(180f);
        inPart.setRadiusX(OUTER_SIZE / 2f);
        inPart.setRadiusY(OUTER_SIZE / 2f);
        inPart.setType(ArcType.CHORD);
        if (DefaultConnectorTypes.isInput(TYPE) || DefaultConnectorTypes.isBoth(TYPE)) {
            inPart.getStyleClass().addAll(STYLE_CLASS_ACTIVE);
        } else {
            inPart.getStyleClass().addAll(STYLE_CLASS_NONACTIVE);
        }
    }

    private void createOutPart() {
        outPart.setStartAngle(-180f);
        outPart.setLength(180f);
        outPart.setRadiusX(OUTER_SIZE / 2f);
        outPart.setRadiusY(OUTER_SIZE / 2f);
        outPart.setType(ArcType.CHORD);
        if (DefaultConnectorTypes.isOutput(TYPE) || DefaultConnectorTypes.isBoth(TYPE)) {
            outPart.getStyleClass().addAll(STYLE_CLASS_ACTIVE);
        } else {
            outPart.getStyleClass().addAll(STYLE_CLASS_NONACTIVE);
        }
    }

    void applyStyle(final GConnectorStyle style) {
        if (DefaultConnectorTypes.isOutput(TYPE)) {
            ConnectorStyleUtils.applyStyle(style, outPart);
        } else if (DefaultConnectorTypes.isInput(TYPE)) {
            ConnectorStyleUtils.applyStyle(style, inPart);
        } else if (DefaultConnectorTypes.isBoth(TYPE)) {
            ConnectorStyleUtils.applyStyle(style, outPart);
            ConnectorStyleUtils.applyStyle(style, inPart);
        }
    }

    public Region getRoot() {
        return root;
    }

}
