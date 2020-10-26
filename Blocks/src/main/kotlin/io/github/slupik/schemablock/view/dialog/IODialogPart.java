package io.github.slupik.schemablock.view.dialog;

import io.github.slupik.schemablock.view.dialog.data.IoOperation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

/**
 * All rights reserved & copyright ©
 */
public class IODialogPart extends Pane {

    private final VBox container = new VBox();
    private final TextField content = new TextField();
    private final ChoiceBox<IOType> selector = getIOTypeSelector();
    private final Button btnRemove = new Button("Usuń");

    private Runnable remover;

    public IODialogPart(){
        getChildren().add(container);
        setupControls();
    }

    private void setupControls() {
        selector.valueProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue==IOType.INPUT) {
                content.setPromptText("Tylko 1 zmienna");
            } else {
                content.setPromptText("Tekst");
            }
        });

        HBox controllers = new HBox();
        controllers.getChildren().add(selector);
        HBox aligner = new HBox();
        aligner.prefWidthProperty().bind(content.widthProperty().subtract(119));
        aligner.getChildren().add(btnRemove);
        aligner.setAlignment(Pos.CENTER_RIGHT);
        controllers.getChildren().add(aligner);

        container.getChildren().add(controllers);
        container.getChildren().add(content);

        btnRemove.setOnAction(event -> remover.run());

        content.setPrefWidth(300);
        content.setMaxWidth(300);
    }

    public void load(String code, boolean input) {
        content.setText(code);
        if(input) {
            selector.setValue(IOType.INPUT);
        } else {
            selector.setValue(IOType.OUTPUT);
        }
    }

    public IoOperation getAsField(){
        return new IoOperation(
                selector.getValue() == IOType.INPUT,
                content.getText()
        );
    }

    public void setRemover(Runnable runnable) {
        remover = runnable;
    }

    private static ChoiceBox<IOType> getIOTypeSelector(){
        ChoiceBox<IOType> selector = new ChoiceBox<>();
        selector.getItems().addAll(IOType.values());
        selector.setConverter(new StringConverter<IOType>() {
            @Override
            public String toString(IOType object) {
                switch (object) {
                    case INPUT: {
                        return "Wczytaj wartość";
                    }
                    case OUTPUT: {
                        return "Wyświetl";
                    }
                }
                return null;
            }

            @Override
            public IOType fromString(String string) {
                return IOType.valueOf(string);
            }
        });
        return selector;
    }
}
