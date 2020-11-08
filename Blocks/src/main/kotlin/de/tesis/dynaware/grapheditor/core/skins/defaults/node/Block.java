package de.tesis.dynaware.grapheditor.core.skins.defaults.node;

import de.tesis.dynaware.grapheditor.core.skins.UiElementType;
import de.tesis.dynaware.grapheditor.model.GNode;
import javafx.css.PseudoClass;

/**
 * All rights reserved & copyright ©
 */
public abstract class Block extends LabeledNodeSkin {

    private static final PseudoClass PSEUDO_CLASS_ERROR = PseudoClass.getPseudoClass("error");
    private static final PseudoClass PSEUDO_CLASS_CURRENT = PseudoClass.getPseudoClass("current");

    /**
     * Creates a new default node skin instance.
     *
     * @param node the {@link GNode} the skin is being created for
     */
    public Block(GNode node) {
        super(node);
    }

    public void markAsNeutral() {
        getBackground().pseudoClassStateChanged(PSEUDO_CLASS_ERROR, false);
        getBackground().pseudoClassStateChanged(PSEUDO_CLASS_CURRENT, false);
    }

    public void markAsCurrent() {
        getBackground().pseudoClassStateChanged(PSEUDO_CLASS_ERROR, false);
        getBackground().pseudoClassStateChanged(PSEUDO_CLASS_CURRENT, true);
    }

    public void markAsError() {
        getBackground().pseudoClassStateChanged(PSEUDO_CLASS_ERROR, true);
        getBackground().pseudoClassStateChanged(PSEUDO_CLASS_CURRENT, false);
    }

    public abstract UiElementType getType();

}
