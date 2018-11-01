package io.github.slupik.schemablock.javafx.logic.drag.node;

import javafx.scene.Node;

/**
 * All rights reserved & copyright ©
 */


public class Draggable {

    final Node node;
    final boolean useRelativePos;

    public Draggable(Node node){
        this(node, false);
    }

    public Draggable(Node node, boolean useRelativePos){
        this.node = node;
        this.useRelativePos = useRelativePos;
    }
}