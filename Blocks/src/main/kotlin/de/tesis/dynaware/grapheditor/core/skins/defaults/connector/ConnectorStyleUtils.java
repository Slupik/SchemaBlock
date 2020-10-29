/*
 * Copyright (c) 2020. by Sebastian Witasik
 * All rights reserved. No part of this application may be reproduced or be part of other software, without the prior written permission of the publisher. For permission requests, write to the author(WitasikSebastian@gmail.com).
 */

package de.tesis.dynaware.grapheditor.core.skins.defaults.connector;

import de.tesis.dynaware.grapheditor.GConnectorStyle;
import de.tesis.dynaware.grapheditor.core.skins.defaults.utils.AnimatedColor;
import de.tesis.dynaware.grapheditor.core.skins.defaults.utils.ColorAnimationUtils;
import javafx.css.PseudoClass;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

class ConnectorStyleUtils {

    private static final PseudoClass PSEUDO_CLASS_ALLOWED = PseudoClass.getPseudoClass("allowed");
    private static final PseudoClass PSEUDO_CLASS_FORBIDDEN = PseudoClass.getPseudoClass("forbidden");

    private static final String ALLOWED = "-animated-color-allowed";
    private static final String FORBIDDEN = "-animated-color-forbidden";
    private static final AnimatedColor animatedColorAllowed = new AnimatedColor(ALLOWED, Color.WHITE, Color.MEDIUMSEAGREEN, Duration.millis(500));
    private static final AnimatedColor animatedColorForbidden = new AnimatedColor(FORBIDDEN, Color.WHITE, Color.TOMATO, Duration.millis(500));

    static void applyStyle(final GConnectorStyle style, Shape shape) {
        applyState(style, shape);
        switch (style) {
            case DEFAULT:
                ColorAnimationUtils.removeAnimation(shape);
                break;

            case DRAG_OVER_ALLOWED:
                ColorAnimationUtils.animateColor(shape, animatedColorAllowed);
                break;

            case DRAG_OVER_FORBIDDEN:
                ColorAnimationUtils.animateColor(shape, animatedColorForbidden);
                break;
        }
    }

    static void applyState(final GConnectorStyle style, Node node) {
        switch (style) {
            case DEFAULT:
                node.pseudoClassStateChanged(PSEUDO_CLASS_FORBIDDEN, false);
                node.pseudoClassStateChanged(PSEUDO_CLASS_ALLOWED, false);
                break;

            case DRAG_OVER_ALLOWED:
                node.pseudoClassStateChanged(PSEUDO_CLASS_FORBIDDEN, false);
                node.pseudoClassStateChanged(PSEUDO_CLASS_ALLOWED, true);
                break;

            case DRAG_OVER_FORBIDDEN:
                node.pseudoClassStateChanged(PSEUDO_CLASS_FORBIDDEN, true);
                node.pseudoClassStateChanged(PSEUDO_CLASS_ALLOWED, false);
                break;
        }
    }

}
