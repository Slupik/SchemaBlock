package io.github.slupik.schemablock.javafx.logic.drag.icon;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

/**
 * All rights reserved & copyright ©
 */
public class DestContainerAfterDropImpl implements DestContainerAfterDrop {

    private final Pane region;

    public DestContainerAfterDropImpl(Pane region) {
        this.region = region;
    }

    @Override
    public void addNode(Node node) {
        region.getChildren().add(node);
    }

    @Override
    public void removeNode(Node node) {
        region.getChildren().remove(node);
    }
}
