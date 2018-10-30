package io.github.slupik.schemablock.javafx;

import io.github.slupik.schemablock.javafx.element.fx.standard.UiStandardElement;
import io.github.slupik.schemablock.javafx.element.fx.special.StartElement;
import io.github.slupik.schemablock.javafx.element.fx.special.StopElement;
import io.github.slupik.schemablock.javafx.element.fx.special.UiSpecialElement;
import io.github.slupik.schemablock.javafx.element.fx.standard.ConditionBlock;
import io.github.slupik.schemablock.javafx.element.fx.standard.IOBlock;
import io.github.slupik.schemablock.javafx.element.fx.standard.OperatingBlock;
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
    }

    private void spawnAvailableBlocks() {
        double height = 31;
        double width = 50;

        UiSpecialElement start = new StartElement();
        start.setElementSize(width, height);

        UiSpecialElement stop = new StopElement();
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
