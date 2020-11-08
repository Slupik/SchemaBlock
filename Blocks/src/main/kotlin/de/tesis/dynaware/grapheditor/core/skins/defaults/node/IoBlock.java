/*
 * Copyright (c) 2020. by Sebastian Witasik
 * All rights reserved. No part of this application may be reproduced or be part of other software, without the prior written permission of the publisher. For permission requests, write to the author(WitasikSebastian@gmail.com).
 */

package de.tesis.dynaware.grapheditor.core.skins.defaults.node;

import de.tesis.dynaware.grapheditor.core.skins.UiElementType;
import de.tesis.dynaware.grapheditor.core.skins.defaults.connector.DefaultConnectorSkin;
import de.tesis.dynaware.grapheditor.core.skins.defaults.utils.DefaultConnectorTypes;
import de.tesis.dynaware.grapheditor.model.GConnector;
import de.tesis.dynaware.grapheditor.model.GNode;
import io.github.slupik.schemablock.view.dialog.data.IoOperation;
import javafx.beans.binding.DoubleBinding;
import javafx.scene.Node;
import javafx.scene.shape.Polygon;

import java.util.ArrayList;
import java.util.List;

public class IoBlock extends Block {

    private static final String STYLE_CLASS_BORDER = "default-node-border";
    private static final String STYLE_CLASS_BACKGROUND = "default-node-background";
    private static final String STYLE_CLASS_SELECTION_HALO = "default-node-selection-halo";

    private static final double HALO_OFFSET = 5;
    private static final double HALO_CORNER_SIZE = 10;

    private final Polygon selectionHalo = new Polygon();
    // Border and background are separated into 2 rectangles so they can have different effects applied to them.
    private final Polygon border = new Polygon();
    private final Polygon background = new Polygon();

    private List<IoOperation> operations = new ArrayList<>();

    /**
     * Creates a new default node skin instance.
     *
     * @param node the {@link GNode} the skin is being created for
     */
    public IoBlock(GNode node) {
        super(node);
        initElements();
        initLabel("I/O");
    }

    @Override
    public UiElementType getType() {
        return UiElementType.IO;
    }

    protected void initElements() {
        getRoot().widthProperty().addListener((observable, oldValue, newValue) ->
                recreateBackground(newValue.doubleValue() - border.getStrokeWidth() * 2, getRoot().getHeight() - border.getStrokeWidth() * 2));
        getRoot().heightProperty().addListener((observable, oldValue, newValue) ->
                recreateBackground(getRoot().getWidth() - border.getStrokeWidth() * 2, newValue.doubleValue() - border.getStrokeWidth() * 2));

        getRoot().widthProperty().addListener((observable, oldValue, newValue) ->
                recreateBorder(newValue.doubleValue(), getRoot().getHeight()));
        getRoot().heightProperty().addListener((observable, oldValue, newValue) ->
                recreateBorder(getRoot().getWidth(), newValue.doubleValue()));

        border.getStyleClass().setAll(STYLE_CLASS_BORDER);
        background.getStyleClass().setAll(STYLE_CLASS_BACKGROUND);

        onElementsInitialized();
    }

    private void recreateBorder(double width, double height) {
        createParallelogram(border, width, height);
    }

    private void recreateBackground(double width, double height) {
        createParallelogram(background, width, height);
    }

    private void createParallelogram(Polygon polygon, double width, double height) {
        polygon.getPoints().clear();
        polygon.getPoints().addAll(
                (width / 5d), 0.0,
                width, 0.0,
                (width / 5d * 4), height,
                0.0, height,
                (width / 5d), 0.0
        );
    }

    private void recreateHalo(double partialWidth, double height) {
        selectionHalo.getPoints().clear();
        double scrap = partialWidth * HALO_OFFSET / 5d / height;
        selectionHalo.getPoints().addAll(
                (partialWidth / 5d) - scrap, 0.0,
                partialWidth + scrap, 0.0,
                (partialWidth / 5d * 4) + scrap, height,
                0.0 - scrap, height,
                (partialWidth / 5d) - scrap, 0.0
        );
    }

    @Override
    protected void addSelectionHalo() {

        getRoot().getChildren().add(selectionHalo);

        selectionHalo.setManaged(false);
        selectionHalo.setMouseTransparent(false);
        selectionHalo.setVisible(false);

        selectionHalo.setLayoutX(-HALO_OFFSET);
        selectionHalo.setLayoutY(-HALO_OFFSET);

        selectionHalo.getStyleClass().add(STYLE_CLASS_SELECTION_HALO);
    }

    @Override
    protected void layoutSelectionHalo() {
        if (selectionHalo.isVisible()) {

            double h = background.getBoundsInLocal().getHeight() + border.getStrokeWidth() * 2 + HALO_OFFSET * 2;
            double partialWidth = background.getBoundsInLocal().getWidth() + border.getStrokeWidth() * 2 + HALO_OFFSET * 2;
            double scrap = partialWidth * HALO_OFFSET / 5d / h;
            recreateHalo(partialWidth, h);

            double b = Math.sqrt(Math.pow(1 / 5d * partialWidth, 2) + Math.pow(h, 2));

            final double cornerLength = 2 * HALO_CORNER_SIZE;
            final double xGap = partialWidth * 4 / 5d + scrap * 2 - 2 * HALO_CORNER_SIZE;
            final double yGap = b - 2 * HALO_CORNER_SIZE;

            selectionHalo.setStrokeDashOffset(HALO_CORNER_SIZE);
            selectionHalo.getStrokeDashArray().setAll(cornerLength, xGap, cornerLength, yGap);
        }
    }

    @Override
    protected double getMinorOffsetX(GConnector connector) {
        String type = connector.getType();
        double partialWidth = background.getBoundsInLocal().getWidth() + border.getStrokeWidth() * 2 + HALO_OFFSET * 2;
        if (DefaultConnectorTypes.isLeft(type)) {
            return partialWidth / 10;
        } else if (DefaultConnectorTypes.isRight(type)) {
            return -partialWidth / 10;
        } else if (DefaultConnectorTypes.isTop(type)) {
            return partialWidth / 10;
        } else if (DefaultConnectorTypes.isBottom(type)) {
            return -partialWidth / 10;
        } else {
            return super.getMinorOffsetX(connector);
        }
    }

    @Override
    protected DoubleBinding getTextAreaWidth() {
        return getRoot().widthProperty()
                .subtract(DefaultConnectorSkin.OUTER_SIZE)
                .multiply(3 / 5d);
    }

    @Override
    protected DoubleBinding getTextAreaHeight() {
        return getRoot().heightProperty()
                .subtract(DefaultConnectorSkin.OUTER_SIZE)
                .multiply(4 / 5d);
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

    public List<IoOperation> getOperations() {
        return operations;
    }

    public void setOperations(List<IoOperation> operations) {
        this.operations = operations;
    }
}
