package io.github.slupik.schemablock.view.dialog.controller;

import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

/**
 * All rights reserved & copyright ©
 */
public class IoItemTypeSwitcher extends StackPane {

    private static final boolean IS_INPUT_BY_DEFAULT = false;

    public final SimpleBooleanProperty isInput = new SimpleBooleanProperty(IS_INPUT_BY_DEFAULT);
    private final double SIZE = 25;
    private final MaterialIconView outputIcon = new MaterialIconView(MaterialIcon.AIRPLAY);
    private final MaterialIconView inputIcon = new MaterialIconView(MaterialIcon.VERTICAL_ALIGN_BOTTOM);

    public IoItemTypeSwitcher() {
        setupIcon(outputIcon);
        setupIcon(inputIcon);

        isInput.addListener((observableValue, old, current) -> updateState(current));
        updateState(isInput.get());
    }

    private void updateState(Boolean isInput) {
        outputIcon.setVisible(!isInput);
        inputIcon.setVisible(isInput);
    }

    private void setupIcon(MaterialIconView icon) {
        icon.setGlyphSize(SIZE);
        icon.setCursor(Cursor.HAND);
        getChildren().add(icon);
        icon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> isInput.set(!isInput.get()));
    }

}
