package io.github.slupik.schemablock.javafx.view;

import io.github.slupik.schemablock.javafx.element.fx.special.StartElement;
import io.github.slupik.schemablock.javafx.element.fx.special.StopElement;
import io.github.slupik.schemablock.javafx.element.fx.special.UiSpecialElement;
import io.github.slupik.schemablock.javafx.element.fx.standard.ConditionBlock;
import io.github.slupik.schemablock.javafx.element.fx.standard.IOBlock;
import io.github.slupik.schemablock.javafx.element.fx.standard.OperatingBlock;
import io.github.slupik.schemablock.javafx.element.fx.standard.UiStandardElement;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    @FXML
    private Pane mainContainer;

    @FXML
    private Button btnRun;

    @FXML
    private Button btnDebug;

    @FXML
    private HBox availableBlocks;

    @FXML
    private SplitPane centerContainer;

    @FXML
    private ScrollPane sheetScroller;

    @FXML
    private Pane sheet;

    @FXML
    private WebView outputView;

    @FXML
    private TableView<?> tvVariables;

    @FXML
    private TableColumn<?, ?> tcVarType;

    @FXML
    private TableColumn<?, ?> tcVarName;

    @FXML
    private TableColumn<?, ?> tcVarValue;

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


        UiSpecialElement test = new StartElement();
        test.setElementSize(width, height);
        sheet.getChildren().add(test);
//        new DragAndDropElement(sheet,  test);
        DraggingController nature = new DraggingController(new Draggable(start, true));
        nature.addListener((draggingController, dragEvent) -> {
            if(dragEvent== DraggingController.Event.DragEnd) {
                System.out.println(draggingController.getEventNode().getTranslateX());
                if(sheet.contains(draggingController.getEventNode().getLayoutX(), draggingController.getEventNode().getLayoutY())
                && !sheet.getChildren().contains(draggingController.getEventNode())){
                    sheet.getChildren().add(draggingController.getEventNode());
//                    draggingController.getEventNode().setLayoutX(100);
//                    draggingController.getEventNode().setLayoutY(100);
                    System.out.println(draggingController.getEventNode().getTranslateX());
                }
            }
        });
//        new DraggingController(test);
//        new DraggingController(sheet);
    }
}
