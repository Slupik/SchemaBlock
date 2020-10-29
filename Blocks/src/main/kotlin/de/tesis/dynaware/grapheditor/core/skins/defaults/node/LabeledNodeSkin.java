/*
 * Copyright (c) 2020. by Sebastian Witasik
 * All rights reserved. No part of this application may be reproduced or be part of other software, without the prior written permission of the publisher. For permission requests, write to the author(WitasikSebastian@gmail.com).
 */

package de.tesis.dynaware.grapheditor.core.skins.defaults.node;

import de.tesis.dynaware.grapheditor.core.skins.defaults.utils.TextSizeCalculation;
import de.tesis.dynaware.grapheditor.model.GNode;
import javafx.beans.binding.DoubleBinding;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import org.jetbrains.annotations.NotNull;

public abstract class LabeledNodeSkin extends NodeSkin {

    private final Label label = new Label();

    /**
     * Creates a new default node skin instance.
     *
     * @param node the {@link GNode} the skin is being created for
     */
    public LabeledNodeSkin(GNode node) {
        super(node);
        addDoubleClickListener();
    }

    public void setDescription(String description) {
        updateText(description);
    }

    public String getDescription() {
        return label.getText() == null ? "" : label.getText();
    }

    protected void initLabel(String text) {
        getRoot().getChildren().add(label);
        StackPane.setAlignment(label, Pos.CENTER);
        getTextAreaWidth().addListener((observable, oldValue, newValue) ->
                resizeText(newValue.doubleValue(), getTextAreaHeight().get())
        );
        getTextAreaHeight().addListener((observable, oldValue, newValue) ->
                resizeText(getTextAreaWidth().get(), newValue.doubleValue())
        );
        updateText(text);
    }

    private void addDoubleClickListener() {
        label.addEventHandler(MouseEvent.MOUSE_CLICKED, event ->{
            if (event.getButton() == MouseButton.PRIMARY) {
                if (event.getClickCount() == 2) {
                    invokeDoubleClickEventHandler(event);
                }
            }
        });
    }

    protected void updateText(String text) {
        label.setText(text);
        resizeText(getTextAreaWidth().get(), getTextAreaHeight().get());
    }

    private void resizeText(double width, double height) {
        Font font = TextSizeCalculation.getFont(label, width, height);
        label.setFont(font);
    }

    @NotNull
    protected abstract DoubleBinding getTextAreaWidth();

    @NotNull
    protected abstract DoubleBinding getTextAreaHeight();

}
