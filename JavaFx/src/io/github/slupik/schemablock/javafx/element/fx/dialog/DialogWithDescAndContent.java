package io.github.slupik.schemablock.javafx.element.fx.dialog;

import io.github.slupik.schemablock.javafx.element.UiElement;
import io.github.slupik.schemablock.model.ui.abstraction.element.Element;
import io.github.slupik.schemablock.model.ui.abstraction.element.StandardElement;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.HashMap;

/**
 * All rights reserved & copyright ©
 */
//TODO cleanup mess and unused classes but only after make all dialogs for all blocks
public class DialogWithDescAndContent extends BaseOfDialogForElements {

    public DialogWithDescAndContent(UiElement element) {
        super(element);
    }

    @Override
    protected Dialog<HashMap<DialogData, String>> createDialog() {
        Dialog<HashMap<DialogData, String>> dialog = new Dialog<>();
        dialog.setTitle("Edycja bloku");

        ButtonType saveButtonType = new ButtonType("Zapisz", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        VBox verContainer = new VBox();
        verContainer.setPadding(new Insets(20, 150, 10, 10));

        HBox horContainer = new HBox();

        TextField title = new TextField();
        title.setPromptText("Krótki tytuł");

        horContainer.getChildren().add(new Label("Tytuł:  "));
        horContainer.getChildren().add(title);
        horContainer.setAlignment(Pos.CENTER_LEFT);
        verContainer.getChildren().add(horContainer);

        Label contentLabel = new Label("Zawartość:");
        verContainer.getChildren().add(contentLabel);
        contentLabel.setPadding(new Insets(8, 0, 8, 0));
        TextArea content = new TextArea();
        content.setPromptText("Kod");
        content.setPrefWidth(400);
        content.setPrefHeight(400);
        verContainer.getChildren().add(content);

        Node loginButton = dialog.getDialogPane().lookupButton(saveButtonType);
        loginButton.setDisable(true);

        title.textProperty().addListener((observable, oldValue, newValue) ->
                loginButton.setDisable(newValue.trim().isEmpty()));

        dialog.getDialogPane().setContent(verContainer);

        Platform.runLater(title::requestFocus);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                HashMap<DialogData, String> data = new HashMap<>();
                data.put(DialogData.DESC, title.getText());
                data.put(DialogData.CODE, content.getText());
                return data;
            }
            return null;
        });

        return dialog;
    }

    @Override
    protected void handleResult(HashMap<DialogData, String> result) {
        if(result != null) {
            String desc = result.get(DialogData.DESC);
            String code = result.get(DialogData.CODE);

            element.setDesc(desc);
            Element logicElement = element.getLogicElement();
            if(logicElement instanceof StandardElement) {
                ((StandardElement) logicElement).setContent(code);
            }
        }
    }
}
