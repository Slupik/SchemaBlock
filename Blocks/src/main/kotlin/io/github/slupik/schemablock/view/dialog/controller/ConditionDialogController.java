package io.github.slupik.schemablock.view.dialog.controller;

import com.jfoenix.controls.JFXTextField;
import io.github.slupik.schemablock.view.dialog.data.CodeAndDescription;
import io.github.slupik.schemablock.view.dialog.data.UiBlockSettings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.jetbrains.annotations.NotNull;

/**
 * All rights reserved & copyright ©
 */
public class ConditionDialogController extends DialogController<CodeAndDescription> {

    @FXML
    private JFXTextField name;

    @FXML
    private JFXTextField code;

    @FXML
    void onCancel(ActionEvent event) {
        cancel();
    }

    @FXML
    void onSave(ActionEvent event) {
        save();
    }

    @Override
    public void loadModel(@NotNull CodeAndDescription input) {
        name.setText(input.getDescription());
        code.setText(input.getCode());
        code.positionCaret(input.getCode().length());
    }

    @Override
    public UiBlockSettings getDataFromUi() {
        return new CodeAndDescription(
                name.getText(),
                code.getText()
        );
    }

}
