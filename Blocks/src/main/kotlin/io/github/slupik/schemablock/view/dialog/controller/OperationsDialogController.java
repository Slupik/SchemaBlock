package io.github.slupik.schemablock.view.dialog.controller;

import com.jfoenix.controls.JFXTextField;
import io.github.slupik.schemablock.view.dialog.data.CodeAndDescription;
import io.github.slupik.schemablock.view.dialog.data.UiBlockSettings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * All rights reserved & copyright ©
 */
public class OperationsDialogController implements Initializable {

    @FXML
    private JFXTextField name;

    @FXML
    private TextArea code;

    private Stage stage;
    private boolean saved;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void onCancel(ActionEvent event) {
        saved = false;
        stage.close();
    }

    @FXML
    void onSave(ActionEvent event) {
        saved = true;
        stage.close();
    }

    public void loadModel(@NotNull CodeAndDescription input) {
        name.setText(input.getDescription());
        code.setText(input.getCode());
        code.positionCaret(input.getCode().length());
    }

    public void injectStage(@NotNull Stage stage) {
        this.stage = stage;
    }

    public Optional<UiBlockSettings> getResult() {
        return Optional.ofNullable(saved ? new CodeAndDescription(
                name.getText(),
                code.getText()
        ) : null);
    }

}
