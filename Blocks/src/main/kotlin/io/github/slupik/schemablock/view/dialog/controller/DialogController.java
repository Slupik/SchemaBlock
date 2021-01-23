package io.github.slupik.schemablock.view.dialog.controller;

import io.github.slupik.schemablock.view.dialog.data.UiBlockSettings;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * All rights reserved & copyright ©
 */
public abstract class DialogController<InputType extends UiBlockSettings> implements Initializable {

    private Stage stage;
    private boolean saved;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void injectStage(@NotNull Stage stage) {
        this.stage = stage;
    }

    protected void cancel() {
        saved = false;
        stage.close();
    }

    protected void save() {
        saved = true;
        stage.close();
    }

    public abstract void loadModel(@NotNull InputType input);

    public Optional<UiBlockSettings> getResult() {
        return Optional.ofNullable(saved ? getDataFromUi() : null);
    }

    protected abstract UiBlockSettings getDataFromUi();

}
