/*
 * Copyright (c) 2020. by Sebastian Witasik
 * All rights reserved. No part of this application may be reproduced or be part of other software, without the prior written permission of the publisher. For permission requests, write to the author(WitasikSebastian@gmail.com).
 */
package de.tesis.dynaware.grapheditor.core.skins.defaults.node;

import de.tesis.dynaware.grapheditor.core.skins.defaults.connector.DefaultConnectorSkin;
import de.tesis.dynaware.grapheditor.core.skins.defaults.utils.DefaultConnectorTypes;
import de.tesis.dynaware.grapheditor.model.GNode;
import de.tesis.dynaware.grapheditor.utils.ResizableBox;
import javafx.beans.binding.DoubleBinding;
import javafx.scene.Node;
import javafx.scene.shape.Ellipse;
import org.jetbrains.annotations.NotNull;

/**
 * The default node skin. Uses a {@link ResizableBox}.
 *
 * <p>
 * If a node uses this skin its connectors must have one of the 8 types defined in {@link DefaultConnectorTypes}. If a
 * connector does not have one of these types, it will be set to <b>left-input</b>.
 * </p>
 *
 * <p>
 * Connectors are evenly spaced along the sides of the node according to their type.
 * </p>
 */
public class StartBlock extends LabeledNodeSkin {

    private static final String STYLE_CLASS_BORDER = "default-node-border";
    private static final String STYLE_CLASS_BACKGROUND = "default-node-background";
    private static final String STYLE_CLASS_SELECTION_HALO = "default-node-selection-halo";

    private static final double HALO_OFFSET = 5;
    private static final double HALO_CORNER_SIZE = 10;

    private final Ellipse selectionHalo = new Ellipse();
    // Border and background are separated into 2 rectangles so they can have different effects applied to them.
    private final Ellipse border = new Ellipse();
    private final Ellipse background = new Ellipse();

    /**
     * Creates a new default node skin instance.
     *
     * @param node the {@link GNode} the skin is being created for
     */
    public StartBlock(GNode node) {
        super(node);
        initElements();
        initLabel("Start");
    }

    @Override
    protected void initElements() {
        background.radiusXProperty().bind(border.radiusXProperty().subtract(border.strokeWidthProperty()));
        background.radiusYProperty().bind(border.radiusYProperty().subtract(border.strokeWidthProperty()));

        border.radiusXProperty().bind(getRoot().widthProperty().divide(2.0));
        border.radiusYProperty().bind(getRoot().heightProperty().divide(2.0));

        border.getStyleClass().setAll(STYLE_CLASS_BORDER);
        background.getStyleClass().setAll(STYLE_CLASS_BACKGROUND);

        onElementsInitialized();
    }

    @Override
    protected void addSelectionHalo() {
        getRoot().getChildren().add(selectionHalo);

        selectionHalo.setManaged(false);
        selectionHalo.setMouseTransparent(false);
        selectionHalo.setVisible(false);

        selectionHalo.getStyleClass().add(STYLE_CLASS_SELECTION_HALO);
    }

    @Override
    protected void layoutSelectionHalo() {
        if (selectionHalo.isVisible()) {

            double a = border.getRadiusX() + HALO_OFFSET;
            double b = border.getRadiusY() + HALO_OFFSET;

            selectionHalo.setRadiusX(a);
            selectionHalo.setRadiusY(b);

            double circuit = Math.PI * (3d / 2d * (a + b) - Math.sqrt(a * b));
            double lineLength = HALO_CORNER_SIZE*2;
            double spaceLength = (circuit-lineLength*4)/4;

            selectionHalo.setStrokeDashOffset(lineLength+spaceLength/2d);
            selectionHalo.getStrokeDashArray().setAll(lineLength, spaceLength);

            selectionHalo.setLayoutX(selectionHalo.getRadiusX()-HALO_OFFSET);
            selectionHalo.setLayoutY(selectionHalo.getRadiusY()-HALO_OFFSET);
        }
    }

    @Override
    @NotNull
    protected DoubleBinding getTextAreaWidth() {
        return border.radiusXProperty()
                .subtract(DefaultConnectorSkin.OUTER_SIZE/2d)
                .multiply(2*9/10d);
    }

    @Override
    @NotNull
    protected DoubleBinding getTextAreaHeight() {
        return border.radiusYProperty()
                .subtract(DefaultConnectorSkin.OUTER_SIZE/2d)
                .multiply(2*9/10d);
    }

    @Override
    protected Node getBackground() {
        return background;
    }

    @Override
    protected Node getBorder() {
        return border;
    }

    @Override
    protected Node getSelectionHalo() {
        return selectionHalo;
    }
}
