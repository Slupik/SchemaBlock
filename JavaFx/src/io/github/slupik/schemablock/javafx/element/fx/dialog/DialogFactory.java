package io.github.slupik.schemablock.javafx.element.fx.dialog;

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
//TODO cleanup code
public class DialogFactory {

    private DialogFactory(){}

    public static Dialog<HashMap<DialogData, String>> buildWithDescAndContent(String descText, String contentText) {
        Dialog<HashMap<DialogData, String>> dialog = new Dialog<>();
        dialog.setTitle("Edycja bloku");

        ButtonType saveButtonType = new ButtonType("Zapisz", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        VBox verContainer = new VBox();
        verContainer.setPadding(new Insets(20, 150, 10, 10));

        HBox horContainer = new HBox();

        TextField title = new TextField(descText);
        title.setPromptText("Krótki tytuł");

        horContainer.getChildren().add(new Label("Tytuł:  "));
        horContainer.getChildren().add(title);
        horContainer.setAlignment(Pos.CENTER_LEFT);
        verContainer.getChildren().add(horContainer);

        Label contentLabel = new Label("Zawartość:");
        verContainer.getChildren().add(contentLabel);
        contentLabel.setPadding(new Insets(8, 0, 8, 0));
        TextArea content = new TextArea(contentText);
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

    public static Dialog<HashMap<DialogData, String>> buildWithDescAndShortContent(String descText, String singleLine) {
        Dialog<HashMap<DialogData, String>> dialog = new Dialog<>();
        dialog.setTitle("Edycja bloku");

        ButtonType saveButtonType = new ButtonType("Zapisz", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        VBox verContainer = new VBox();
        verContainer.setPadding(new Insets(20, 150, 10, 10));

        HBox horContainer = new HBox();

        TextField title = new TextField(descText);
        title.setPromptText("Krótki tytuł");

        horContainer.getChildren().add(new Label("Tytuł:  "));
        horContainer.getChildren().add(title);
        horContainer.setAlignment(Pos.CENTER_LEFT);
        verContainer.getChildren().add(horContainer);

        Label contentLabel = new Label("Zdanie logiczne:");
        verContainer.getChildren().add(contentLabel);
        contentLabel.setPadding(new Insets(8, 0, 8, 0));
        TextField content = new TextField(singleLine);
        content.setPromptText("Wyrażenie jeżeli...");
        content.setPrefWidth(400);
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

    public static Dialog<IODialogInput> buildIO(IODialogInput input) {
        Dialog<IODialogInput> dialog = new Dialog<>();
        dialog.setTitle("Edycja bloku");

        ButtonType saveButtonType = new ButtonType("Zapisz", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        VBox verContainer = new VBox();
        verContainer.setPadding(new Insets(20, 150, 10, 10));

        HBox horContainer = new HBox();

        TextField title = new TextField(input.desc);
        title.setPromptText("Krótki tytuł");

        Label titleLbl = new Label("Tytuł:  ");
        titleLbl.setPadding(new Insets(8, 0, 8, 0));

        horContainer.getChildren().add(titleLbl);
        horContainer.getChildren().add(title);
        horContainer.setAlignment(Pos.CENTER_LEFT);
        verContainer.getChildren().add(horContainer);

        Label contentLabel = new Label("Zawartość:");
        verContainer.getChildren().add(contentLabel);
        for(IODialogInput.Value value:input.data) {
            verContainer.getChildren().add(getIOOption(value));
        }

        Node loginButton = dialog.getDialogPane().lookupButton(saveButtonType);
        loginButton.setDisable(true);

        title.textProperty().addListener((observable, oldValue, newValue) ->
                loginButton.setDisable(newValue.trim().isEmpty()));

        dialog.getDialogPane().setContent(verContainer);

        Platform.runLater(title::requestFocus);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                IODialogInput output = new IODialogInput();
                output.desc = title.getText();

                return output;
            }
            return null;
        });

        return dialog;
    }

    private static Node getIOOption(IODialogInput.Value value) {
        VBox element = new VBox();

        Label info = new Label();
        info.setPadding(new Insets(4, 0, 4, 0));
        if(value.isInput) {
            info.setText("Wczytaj wartość do zmiennej:");
        } else {
            info.setText("Wyświetl:");
        }
        element.getChildren().add(info);

        TextField content = new TextField();
        element.getChildren().add(content);

        return element;
    }
}
