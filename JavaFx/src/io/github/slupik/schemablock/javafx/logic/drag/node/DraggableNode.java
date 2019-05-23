package io.github.slupik.schemablock.javafx.logic.drag.node;

import javafx.scene.Node;

/**
 * All rights reserved & copyright ©
 */
public class DraggableNode {

    final Node node;
    final boolean useRelativePos;

    public DraggableNode(Node node){
        this(node, false);
    }

    public DraggableNode(Node node, boolean useRelativePos){
        this.node = node;
        this.useRelativePos = useRelativePos;
    }
}