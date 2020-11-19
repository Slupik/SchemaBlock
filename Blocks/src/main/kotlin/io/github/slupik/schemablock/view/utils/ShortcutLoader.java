package io.github.slupik.schemablock.view.utils;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCodeCombination;

/**
 * All rights reserved & copyright ©
 */
public class ShortcutLoader {

    public static void saveAccelerator(Button button, KeyCodeCombination combination) {
        Scene scene = button.getScene();
        if (scene == null) {
            throw new IllegalArgumentException("setSaveAccelerator must be called when a button is attached to a scene");
        }
        scene.getAccelerators().put(
                combination,
                new Runnable() {
                    @FXML
                    public void run() {
                        button.fire();
                    }
                }
        );
    }

}
