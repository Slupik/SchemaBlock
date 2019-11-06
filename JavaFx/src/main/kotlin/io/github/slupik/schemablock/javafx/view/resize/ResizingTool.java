package io.github.slupik.schemablock.javafx.view.resize;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

/**
 * All rights reserved & copyright ©
 */
public class ResizingTool {

    private final Pane resizableElement;
    private final Node icon;
    private final MinimumSizeProvider minSize;

    private double startX = 0;
    private double startY = 0;
    private double startWidth = 0;
    private double startHeight = 0;

    public ResizingTool(Pane resizableElement, Node icon, MinimumSizeProvider minSize) {
        this.resizableElement = resizableElement;
        this.icon = icon;
        this.minSize = minSize;
    }

    public void init() {
        stickIconToCornerOfElement();
        initDragging();
    }

    private void stickIconToCornerOfElement() {
        Platform.runLater(() -> {
            icon.layoutXProperty().bind(
                    resizableElement.layoutXProperty()
                            .add(resizableElement.widthProperty())
            );
            icon.layoutYProperty().bind(
                    resizableElement.layoutYProperty()
                            .add(resizableElement.heightProperty())
            );
        });
    }

    private void initDragging() {
        icon.setOnMousePressed(event -> {
            startX = event.getSceneX();
            startY = event.getSceneY();
            startWidth = resizableElement.getWidth();
            startHeight = resizableElement.getHeight();
        });
        icon.setOnMouseDragged(event -> {
            double newWidth = startWidth + (event.getSceneX() - startX);
            if (minSize.getWidth() <= newWidth) {
                resizableElement.setPrefWidth(newWidth);
            }

            double newHeight = startHeight + (event.getSceneY() - startY);
            if (minSize.getHeight() <= newHeight) {
                resizableElement.setPrefHeight(newHeight);
            }
        });
    }

}

