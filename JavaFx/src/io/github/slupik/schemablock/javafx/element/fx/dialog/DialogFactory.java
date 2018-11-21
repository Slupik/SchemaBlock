package io.github.slupik.schemablock.javafx.element.fx.dialog;

import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
        loginButton.setDisable(descText.length()==0);

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
        loginButton.setDisable(descText.length()==0);

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
        verContainer.setPadding(new Insets(20, 50, 10, 10));

        HBox horContainer = new HBox();

        TextField title = new TextField(input.desc);
        title.setPromptText("Krótki tytuł");

        Label titleLbl = new Label("Tytuł:  ");
        titleLbl.setPadding(new Insets(8, 0, 8, 0));

        horContainer.getChildren().add(titleLbl);
        horContainer.getChildren().add(title);
        horContainer.setAlignment(Pos.CENTER_LEFT);
        verContainer.getChildren().add(horContainer);

        Separator sepBetweenTitleAndContent = new Separator();
        sepBetweenTitleAndContent.setOrientation(Orientation.HORIZONTAL);
        sepBetweenTitleAndContent.setPadding(new Insets(8, 0, 8, 0));
        verContainer.getChildren().add(sepBetweenTitleAndContent);

        List<IODialogPart> options = new ArrayList<>();

        Label contentLabel = new Label("Zawartość:");
        verContainer.getChildren().add(contentLabel);

        ScrollPane scrollingContent = new ScrollPane();
        scrollingContent.setPrefHeight(200);
        scrollingContent.setPrefWidth(324);
        verContainer.getChildren().add(scrollingContent);
        VBox optionsContainer = new VBox();
        scrollingContent.setContent(optionsContainer);

        for(IODialogInput.Value value:input.data) {
            IODialogPart option = new IODialogPart();
            option.load(value);
            option.setRemover(() -> {
                optionsContainer.getChildren().remove(option);
                options.remove(option);
            });
            options.add(option);
            optionsContainer.getChildren().add(option);
        }

        Node loginButton = dialog.getDialogPane().lookupButton(saveButtonType);
        loginButton.setDisable(input.desc.length()==0);

        title.textProperty().addListener((observable, oldValue, newValue) ->
                loginButton.setDisable(newValue.trim().isEmpty()));

        Button btnAddOption = new Button("Dodaj");
        btnAddOption.setOnAction(event -> {
            IODialogPart option = new IODialogPart();
            option.load(new IODialogInput.Value());
            option.setRemover(() -> {
                optionsContainer.getChildren().remove(option);
                options.remove(option);
            });
            options.add(option);
            optionsContainer.getChildren().add(option);
        });
        verContainer.getChildren().add(btnAddOption);
        VBox.setMargin(btnAddOption, new Insets(16, 0, 16, 0));

        dialog.getDialogPane().setContent(verContainer);

        Platform.runLater(title::requestFocus);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                IODialogInput output = new IODialogInput();
                output.desc = title.getText();

                for(IODialogPart part:options) {
                    output.data.add(part.getAsValue());
                }

                return output;
            }
            return null;
        });

        optionsContainer.getChildren().addListener(new ListChangeListener<Node>() {

            private boolean ignore = false;

            @Override
            public void onChanged(Change<? extends Node> c) {
                if(ignore) {
                    return;
                }
                ignore = true;
                Platform.runLater(()->{
                    double newHeight = 50 * optionsContainer.getChildren().size();
                    optionsContainer.setMinHeight(newHeight);
                    ignore = false;
                });
            }
        });

        return dialog;
    }
}
