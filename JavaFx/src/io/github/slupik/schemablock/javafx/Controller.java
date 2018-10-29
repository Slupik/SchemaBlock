package io.github.slupik.schemablock.javafx;

import io.github.slupik.schemablock.javafx.element.UiSpecialElement;
import io.github.slupik.schemablock.javafx.element.fx.special.StartElement;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private VBox vbtest;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UiSpecialElement element = new StartElement();
        vbtest.getChildren().add(element);
//        element.setWidth(100);
//        element.setHeight(100);
    }
}
