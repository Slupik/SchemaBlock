/*
 * Copyright (c) 2020. by Sebastian Witasik
 * All rights reserved. No part of this application may be reproduced or be part of other software, without the prior written permission of the publisher. For permission requests, write to the author(WitasikSebastian@gmail.com).
 */

package de.tesis.dynaware.grapheditor.core.skins.defaults.node;

import de.tesis.dynaware.grapheditor.core.skins.defaults.connector.DefaultConnectorSkin;
import de.tesis.dynaware.grapheditor.model.GNode;
import javafx.beans.binding.DoubleBinding;
import javafx.scene.Node;
import javafx.scene.shape.Polygon;

public class ConditionalBlock extends Block {

    private static final String STYLE_CLASS_BORDER = "default-node-border";
    private static final String STYLE_CLASS_BACKGROUND = "default-node-background";
    private static final String STYLE_CLASS_SELECTION_HALO = "default-node-selection-halo";

    private static final double HALO_OFFSET = 25;
    private static final double HALO_CORNER_SIZE = 10;

    private final Polygon selectionHalo = new Polygon();
    // Border and background are separated into 2 rectangles so they can have different effects applied to them.
    private final Polygon border = new Polygon();
    private final Polygon background = new Polygon();

    private String code = "";

    /**
     * Creates a new default node skin instance.
     *
     * @param node the {@link GNode} the skin is being created for
     */
    public ConditionalBlock(GNode node) {
        super(node);
        initElements();
        initLabel("IF");
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

    private void recreateHalo(double width, double height) {
        createDiamond(selectionHalo, width, height);
    }

    private void recreateBorder(double width, double height) {
        createDiamond(border, width, height);
    }

    private void recreateBackground(double width, double height) {
        createDiamond(background, width, height);
    }

    private void createDiamond(Polygon polygon, double width, double height) {
        polygon.getPoints().clear();
        polygon.getPoints().addAll(
                width / 2d, 0.0,
                width, height / 2d,
                width / 2d, height,
                0.0, height / 2d,
                width / 2d, 0.0
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

            final double e = background.getBoundsInLocal().getWidth() + border.getStrokeWidth() * 2 + HALO_OFFSET * 2;
            final double f = background.getBoundsInLocal().getHeight() + border.getStrokeWidth() * 2 + HALO_OFFSET * 2;
            recreateHalo(e, f);

            final double cornerLength = 2 * HALO_CORNER_SIZE;
            final double sideLength = Math.sqrt(Math.pow(e/2d, 2)+Math.pow(f/2d, 2));
            final double gap = sideLength-cornerLength;

            selectionHalo.setStrokeDashOffset(HALO_CORNER_SIZE);
            selectionHalo.getStrokeDashArray().setAll(cornerLength, gap);
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
    public Node getBackground() {
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
