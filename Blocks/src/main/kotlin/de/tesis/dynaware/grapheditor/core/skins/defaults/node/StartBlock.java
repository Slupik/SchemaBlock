/*
 * Copyright (c) 2020. by Sebastian Witasik
 * All rights reserved. No part of this application may be reproduced or be part of other software, without the prior written permission of the publisher. For permission requests, write to the author(WitasikSebastian@gmail.com).
 */
package de.tesis.dynaware.grapheditor.core.skins.defaults.node;

import de.tesis.dynaware.grapheditor.core.skins.UiElementType;
import de.tesis.dynaware.grapheditor.core.skins.defaults.utils.DefaultConnectorTypes;
import de.tesis.dynaware.grapheditor.model.GNode;
import de.tesis.dynaware.grapheditor.utils.ResizableBox;

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
public class StartBlock extends RoundedNodeSkin {

    /**
     * Creates a new default node skin instance.
     *
     * @param node the {@link GNode} the skin is being created for
     */
    public StartBlock(GNode node) {
        super(node);
        initLabel("Start");
    }

    @Override
    public UiElementType getType() {
        return UiElementType.START;
    }

}
