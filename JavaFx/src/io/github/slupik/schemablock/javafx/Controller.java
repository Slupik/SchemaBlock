package io.github.slupik.schemablock.javafx;

import io.github.slupik.schemablock.javafx.element.UiStandardElement;
import io.github.slupik.schemablock.javafx.element.fx.special.IOBlock;
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
//        UiSpecialElement element = new StartElement();
//        element.setElementSize(100, 200);
//        vbtest.getChildren().add(element);

//        UiStandardElement element = new OperatingBlock();
//        vbtest.getChildren().add(element);
//        element.setWidth(100);
//        element.setHeight(100);

        UiStandardElement element = new IOBlock();
        vbtest.getChildren().add(element);
    }
}
