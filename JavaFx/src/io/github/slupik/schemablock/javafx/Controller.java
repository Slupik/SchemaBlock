package io.github.slupik.schemablock.javafx;

import io.github.slupik.schemablock.javafx.element.UiSpecialElement;
import io.github.slupik.schemablock.javafx.element.UiStandardElement;
import io.github.slupik.schemablock.javafx.element.fx.special.ConditionBlock;
import io.github.slupik.schemablock.javafx.element.fx.special.IOBlock;
import io.github.slupik.schemablock.javafx.element.fx.special.OperatingBlock;
import io.github.slupik.schemablock.javafx.element.fx.special.StartElement;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private HBox availableBlocks;

    @FXML
    private VBox vbtest;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        spawnAvailableBlocks();
//        UiSpecialElement element = new StartElement();
//        element.setElementSize(100, 200);
//        vbtest.getChildren().add(element);

//        UiStandardElement element = new OperatingBlock();
//        vbtest.getChildren().add(element);
//        element.setWidth(100);
//        element.setHeight(100);

//        UiStandardElement element = new ConditionBlock();
//        vbtest.getChildren().add(element);
    }

    private void spawnAvailableBlocks() {
        double height = 31;
        double width = 50;

        UiSpecialElement start = new StartElement();
        start.setElementSize(width, height);

        UiSpecialElement stop = new StartElement();
        stop.setElementSize(width, height);

        UiStandardElement operating = new OperatingBlock();
        operating.setElementSize(width, height);

        UiStandardElement io = new IOBlock();
        io.setElementSize(width, height);

        UiStandardElement condition = new ConditionBlock();
        condition.setElementSize(width, height);

        availableBlocks.getChildren().addAll(start, stop, operating, io, condition);
    }
}
