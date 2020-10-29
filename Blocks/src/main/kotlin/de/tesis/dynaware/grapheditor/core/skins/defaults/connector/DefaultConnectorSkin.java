/*
 * Copyright (c) 2020. by Sebastian Witasik
 * All rights reserved. No part of this application may be reproduced or be part of other software, without the prior written permission of the publisher. For permission requests, write to the author(WitasikSebastian@gmail.com).
 */
package de.tesis.dynaware.grapheditor.core.skins.defaults.connector;

import de.tesis.dynaware.grapheditor.GConnectorSkin;
import de.tesis.dynaware.grapheditor.GConnectorStyle;
import de.tesis.dynaware.grapheditor.core.skins.defaults.utils.DefaultConnectorTypes;
import de.tesis.dynaware.grapheditor.core.utils.LogMessages;
import de.tesis.dynaware.grapheditor.model.GConnector;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The default connector skin.
 *
 * <p>
 * A connector that uses this skin must have one of the 8 types defined in {@link DefaultConnectorTypes}. If the
 * connector does not have one of these types, it will be set to <b>left-input</b>.
 * </p>
 */
public class DefaultConnectorSkin extends GConnectorSkin {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultConnectorSkin.class);

    private static final String STYLE_CLASS_OUTER_BORDER = "default-connector-outer-border";
    private static final String STYLE_CLASS_MIDDLE_BORDER = "default-connector-middle-border";

    public static final double OUTER_SIZE = 17;
    private static final double BORDER_WIDTH = 1.5;

    private final StackPane root = new StackPane();
    private final Line middleBorder = new Line();
    private final Circle outerBorder = new Circle();
    private ConnectorBackground background;

    /**
     * Creates a new default connector skin instance.
     *
     * @param connector the {@link GConnector} the skin is being created for
     */
    public DefaultConnectorSkin(final GConnector connector) {

        super(connector);

        performChecks();

        root.setManaged(false);
        root.resize(OUTER_SIZE, OUTER_SIZE);
        root.setPickOnBounds(false);

        background = new ConnectorBackground(connector.getType(), OUTER_SIZE);

        outerBorder.getStyleClass().addAll(STYLE_CLASS_OUTER_BORDER);
        outerBorder.setRadius(OUTER_SIZE / 2f);
        outerBorder.setFill(Color.TRANSPARENT);
        outerBorder.setStrokeWidth(BORDER_WIDTH);
        outerBorder.setStroke(Color.BLACK);

        middleBorder.getStyleClass().addAll(STYLE_CLASS_MIDDLE_BORDER);
        middleBorder.setStartX(0f);
        middleBorder.setStartY(0f);
        middleBorder.setEndX(OUTER_SIZE);
        middleBorder.setEndY(0f);
        middleBorder.setStrokeWidth(BORDER_WIDTH);

        root.setAlignment(Pos.CENTER);

        adjustRotation(connector.getType());

        root.getChildren().addAll(background.getRoot(), middleBorder, outerBorder);
    }

    private void adjustRotation(final String type) {
        Side side = DefaultConnectorTypes.getSide(type);
        if (side == null) side = Side.TOP;
        switch (side) {
            case TOP: {
                middleBorder.setRotate(180);
                break;
            }
            case BOTTOM: {
                middleBorder.setRotate(0);
                break;
            }
            case LEFT: {
                middleBorder.setRotate(90);
                break;
            }
            case RIGHT: {
                middleBorder.setRotate(-90);
                break;
            }
        }
    }

    @Override
    public Node getRoot() {
        return root;
    }

    @Override
    public double getWidth() {
        return OUTER_SIZE;
    }

    @Override
    public double getHeight() {
        return OUTER_SIZE;
    }

    @Override
    public void applyStyle(final GConnectorStyle style) {
        background.applyStyle(style);
        ConnectorStyleUtils.applyState(style, outerBorder);
    }

    /**
     * Checks that the connector has the correct values to be displayed using this skin.
     */
    private void performChecks() {
        if (!DefaultConnectorTypes.isValid(getConnector().getType())) {
            LOGGER.error(LogMessages.UNSUPPORTED_CONNECTOR, getConnector().getType());
            getConnector().setType(DefaultConnectorTypes.LEFT_INPUT);
        }
    }
}
