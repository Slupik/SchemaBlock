/*
 * Copyright (c) 2020. by Sebastian Witasik
 * All rights reserved. No part of this application may be reproduced or be part of other software, without the prior written permission of the publisher. For permission requests, write to the author(WitasikSebastian@gmail.com).
 */

package de.tesis.dynaware.grapheditor.core.skins.defaults.utils;

import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;

public class TextSizeCalculation {

    private static final double MAX_FONT_SIZE = 35.0;

    public static Font getFont(Label label, double width, double height) {
        Font font = getFontFittedForWidth(label, width);

        if (isFontHigherThanAllowed(label, font, height)) {
            font = getFontFittedForHeight(label, height);
        }

        if (font.getSize() > MAX_FONT_SIZE) {
            return getMaxFont(label);
        } else {
            return font;
        }
    }

    private static Font getFontFittedForWidth(Label label, double width) {
        double unitWidth = getUnitWidth(label);
        return Font.font(label.getFont().getFamily(), FontPosture.findByName(label.getFont().getStyle()), width / unitWidth);
    }

    private static double getUnitWidth(Label label) {
        Text text = getUnitText(label);
        return text.getBoundsInLocal().getWidth();
    }

    private static boolean isFontHigherThanAllowed(Label label, Font font, double maxHeight) {
        String text = label.getText();
        Text textArea = new Text(text);
        textArea.setFont(font);

        return textArea.getBoundsInLocal().getHeight() > maxHeight;
    }

    private static Font getFontFittedForHeight(Label label, double height) {
        double unitHeight = getUnitHeight(label);
        return Font.font(label.getFont().getFamily(), FontPosture.findByName(label.getFont().getStyle()), height / unitHeight);
    }

    private static double getUnitHeight(Label label) {
        Text text = getUnitText(label);
        return text.getBoundsInLocal().getHeight();
    }

    private static Text getUnitText(Label label) {
        Font unitFont = getUnitFont(label);
        String content = label.getText();

        Text text = new Text(content);
        text.setFont(unitFont);

        return text;
    }

    private static Font getMaxFont(Label label) {
        return Font.font(label.getFont().getFamily(), FontPosture.findByName(label.getFont().getStyle()), MAX_FONT_SIZE);
    }

    private static Font getUnitFont(Label label) {
        return Font.font(label.getFont().getFamily(), FontPosture.findByName(label.getFont().getStyle()), 1.0);
    }

}
