package io.github.slupik.schemablock.javafx.element.fx.dialog;

import io.github.slupik.schemablock.javafx.element.UiElement;
import javafx.scene.control.Dialog;

import java.util.HashMap;
import java.util.Optional;

/**
 * All rights reserved & copyright ©
 */
abstract class BaseOfDialogForElements implements DialogOfElement {

    protected final UiElement element;
    private Dialog<HashMap<DialogData, String>> dialog;

    BaseOfDialogForElements(UiElement element) {
        this.element = element;
        dialog = createDialog();
    }

    protected abstract Dialog<HashMap<DialogData, String>> createDialog();

    @Override
    public void show() {
        Optional<HashMap<DialogData, String>> result = dialog.showAndWait();
        result.ifPresent(this::handleResult);
    }

    protected abstract void handleResult(HashMap<DialogData, String> result);

    @Override
    public void exit() {
        dialog.close();
    }
}
