package io.github.slupik.schemablock.view.dialog.controller;

import com.jfoenix.controls.JFXTextField;
import de.tesis.dynaware.grapheditor.demo.GraphEditorDemo;
import io.github.slupik.schemablock.view.dialog.data.DescriptionAndIO;
import io.github.slupik.schemablock.view.dialog.data.IoOperation;
import io.github.slupik.schemablock.view.dialog.data.UiBlockSettings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * All rights reserved & copyright ©
 */
public class IoDialogController extends DialogController<DescriptionAndIO> {

    @FXML
    private JFXTextField name;

    @FXML
    private HBox addMoreContainer;

    @FXML
    private VBox ioItems;

    private final List<IoItemController> itemsControllers = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addMoreContainer.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            addItem(true, "");
        });
    }

    private void addItem(boolean isInput, String code) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(GraphEditorDemo.MAIN_RESOURCE_ROOT + "dialog/IoItem.fxml"));
            HBox box = loader.load();
            box.getStylesheets().add(getClass().getResource("/de/tesis/dynaware/core/view/defaults.css").toExternalForm());
            IoItemController controller = loader.getController();
            Runnable deletionListener = () -> {
                ioItems.getChildren().remove(box);
                itemsControllers.remove(controller);
            };
            controller.addDeletionListener(deletionListener);
            controller.setCode(code);
            controller.setInput(isInput);
            ioItems.getChildren().add(box);
            itemsControllers.add(controller);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onCancel(ActionEvent event) {
        cancel();
    }

    @FXML
    void onSave(ActionEvent event) {
        save();
    }

    @Override
    public void loadModel(@NotNull DescriptionAndIO input) {
        name.setText(input.getDescription());
        input.getOperations().forEach(operation -> {
            addItem(operation.getInput(), operation.getCode());
        });
    }

    @Override
    public UiBlockSettings getDataFromUi() {
        return new DescriptionAndIO(
                name.getText(),
                getOperations()
        );
    }

    private List<IoOperation> getOperations() {
        return itemsControllers.stream()
                .map(controller -> new IoOperation(
                        controller.isInput(),
                        controller.getCode()
                ))
                .collect(Collectors.toList());
    }

}
