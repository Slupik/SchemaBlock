package io.github.slupik.schemablock.view.dialog.controller;

import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.controlsfx.control.textfield.CustomTextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * All rights reserved & copyright ©
 */
public class IoItemController implements Initializable {

    @FXML
    CustomTextField code;

    @FXML
    MaterialIconView delete;

    @FXML
    Label operationTypeInfo;

    @FXML
    HBox container;

    @FXML
    VBox fieldContainer;

    private final IoItemTypeSwitcher typeSwitcher = new IoItemTypeSwitcher();
    private final List<Runnable> deletionListeners = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        code.setRight(typeSwitcher);
        typeSwitcher.isInput.addListener((observableValue, old, current) -> {
            if (current) {
                operationTypeInfo.setText("Wczytaj wartość do zmiennej");
            } else {
                operationTypeInfo.setText("Wyświetl wynik");
            }
        });
        delete.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> deletionListeners.forEach(Runnable::run));
        bindSize();
    }

    private void bindSize() {
        container.widthProperty().addListener((observableValue, oldValue, newValue) -> {
            fieldContainer.setPrefWidth(newValue.doubleValue() - delete.getBoundsInLocal().getWidth());
        });
    }

    public void addDeletionListener(Runnable runnable) {
        deletionListeners.add(runnable);
    }

    public void removeDeletionListener(Runnable runnable) {
        deletionListeners.remove(runnable);
    }

    public void setCode(String code) {
        this.code.setText(code);
    }

    public String getCode() {
        return code.getText();
    }

    public void setInput(boolean isInput) {
        typeSwitcher.isInput.setValue(isInput);
    }

    public boolean isInput() {
        return typeSwitcher.isInput.get();
    }

}
