package io.github.slupik.schemablock.javafx.view;

import io.github.slupik.schemablock.javafx.dagger.DaggerJavaFxComponent;
import io.github.slupik.schemablock.javafx.dagger.ExecutionElementsModule;
import io.github.slupik.schemablock.javafx.dagger.GraphicElementsModule;
import io.github.slupik.schemablock.javafx.dagger.HeapControllerCallbackModule;
import io.github.slupik.schemablock.javafx.element.UiElementType;
import io.github.slupik.schemablock.javafx.element.fx.communication.UIIOCommunicator;
import io.github.slupik.schemablock.javafx.element.fx.element.holder.BlocksHolder;
import io.github.slupik.schemablock.javafx.element.fx.port.connection.drawer.ConnectionDrawer;
import io.github.slupik.schemablock.javafx.element.fx.port.holder.PortsHolder;
import io.github.slupik.schemablock.javafx.element.fx.sheet.Sheet;
import io.github.slupik.schemablock.javafx.element.fx.sheet.SheetFactory;
import io.github.slupik.schemablock.javafx.logic.drag.icon.DragGhostIcon;
import io.github.slupik.schemablock.javafx.logic.drag.icon.GhostDragController;
import io.github.slupik.schemablock.javafx.logic.heap.HeapValueFx;
import io.github.slupik.schemablock.javafx.logic.persistence.SchemaSaver;
import io.github.slupik.schemablock.javafx.view.resize.BlocksAwareSizeProvider;
import io.github.slupik.schemablock.javafx.view.resize.ResizingTool;
import io.github.slupik.schemablock.logic.executor.diagram.DiagramExecutor;
import io.github.slupik.schemablock.logic.executor.diagram.ExecutionEvent;
import io.github.slupik.schemablock.logic.executor.diagram.exception.NextBlockNotFound;
import io.github.slupik.schemablock.model.ui.implementation.container.ExecutionCallback;
import io.reactivex.Observable;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import javax.inject.Inject;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    @FXML
    private BorderPane mainContainer;

    @FXML
    private MenuItem miSave;

    @FXML
    private MenuItem miLoad;

    @FXML
    private MenuItem miClose;

    @FXML
    private MenuItem miNew;

    @FXML
    private MenuItem miDelete;

    @FXML
    private MenuItem miAbout;

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
    private VBox vbIOContainer;

    @FXML
    private WebView outputView;

    @FXML
    private HBox hbInputContainer;

    @FXML
    private TextField tfInput;

    @FXML
    private Button btnEnter;

    @FXML
    private Button btnContinue;

    @FXML
    private Rectangle resizingIcon;

    @FXML
    private TableView<HeapValueFx> tvVariables;

    @FXML
    private TableColumn<HeapValueFx, String> tcVarType;

    @FXML
    private TableColumn<HeapValueFx, String> tcVarName;

    @FXML
    private TableColumn<HeapValueFx, String> tcVarValue;

    @Inject
    ConnectionDrawer drawer;

    @Inject
    PortsHolder holder;

    @Inject
    DiagramExecutor executor;

    @Inject
    BlocksHolder blocksHolder;
//    @Inject
//    @Async
//    DiagramExecutor executor;

    private Sheet container;
    //    private SheetWithElements container;
    private GhostDragController ghost;
    private SchemaSaver saver;
    private SchemaLoader loader;

//    private Compilator compilator = new DefaultCompilator();
//    private Memory memory = new MemoryImpl();
//    private RegisterImpl register = new RegisterImpl();
//    private NewHeapSpy heap = new NewHeapSpy(memory, new Runnable(){
//        @Override
//        public void run() {
//            tvVariables.refresh();
//        }
//    });
//    private Executor executor = new ExecutorImpl(compilator, heap, register);
//    private ElementParser elementParser = new ElementParser(executor, heap);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UIIOCommunicator communicable = new UIIOCommunicator(tfInput, outputView, btnEnter);
        DaggerJavaFxComponent
                .builder()
                .addElementsModule(
                        new GraphicElementsModule(sheet)
                )
                .addElementsModule(
                        new HeapControllerCallbackModule(
                                () -> tvVariables.refresh()
                        )
                )
                .addElementsModule(
                        new ExecutionElementsModule(communicable)
                )
                .build()
                .inject(this);
        drawer.run();
        setupDragging();
    }

    private void setupDragging() {

//        DefaultElementContainer elementContainer = new DefaultElementContainer(register, memory, elementParser);
//        ExecutionController executionController = new ExecutionController(communicable, elementContainer, btnContinue);
//        elementContainer.setExecutionFlowController(executionController);
        container = SheetFactory.INSTANCE.make(holder, blocksHolder, sheet);
//        container = new DefaultSheetWithElements(sheet, communicable, elementContainer, executor, heap);

//        ghost = new GhostDragController(mainContainer, sheet, new GhostDragElementFactoryImpl(container.getPortSpawner()), container.getChildrenHandler());
        ghost = new GhostDragController(mainContainer, sheet, new GhostDragElementFactoryImpl(), container);

        addIconsToMenu();
        bindIOView();
        setupMenu();
        bindTable();
        bindResizer();
        ExecutionCallback callback =
                new ExecutionCallback() {
                    @Override
                    public void onStart() {
                        btnRun.setDisable(true);
                        btnDebug.setDisable(true);
                    }

                    @Override
                    public void onStop() {
                        btnRun.setDisable(false);
                        btnDebug.setDisable(false);
                    }
                };
        btnRun.setOnAction((event) -> {
            Observable<ExecutionEvent> observable = executor.run();
            observable.subscribe(
                    executionEvent -> {
                        System.out.println("executionEvent = " + executionEvent);
                    },
                    throwable -> {
                        System.out.println("throwable = " + throwable);
                        if(throwable instanceof NextBlockNotFound) {
                            System.out.println("type "+ ((NextBlockNotFound) throwable).getCurrentBlock());
                        }
                    },
                    () -> {
                        System.out.println("COMPLETE");
                    }
            ).dispose();
//            heap.clear();
//            executor.run();
//            executionController.execute(false, callback);
        });
        btnDebug.setOnAction((event) -> {
//            heap.clear();
//            executionController.execute(true, callback);
        });
    }

    private void bindResizer() {
        BlocksAwareSizeProvider sizeProvider = new BlocksAwareSizeProvider(sheet);
        new ResizingTool(sheet, resizingIcon, sizeProvider).init();
    }

    private void bindTable() {
//        ObservableList<HeapValueFx> valueList = heap.getVariableList();
//        tvVariables.setItems(valueList);
//        tcVarType.setCellValueFactory(
//                new PropertyValueFactory<>("type")
//        );
//        tcVarName.setCellValueFactory(
//                new PropertyValueFactory<>("name")
//        );
//        tcVarValue.setCellValueFactory(
//                new PropertyValueFactory<>("value")
//        );
    }

    private void setupMenu() {
        Platform.runLater(() -> {
            saver = new SchemaSaver(((Stage) mainContainer.getScene().getWindow()));
            loader = new SchemaLoader(((Stage) mainContainer.getScene().getWindow()));
            miSave.setOnAction(event -> {
                //TODO repair
//                String content = container.stringify();
//                saver.save(content);
            });
            miLoad.setOnAction(event -> {
                File file = loader.getFileToLoad();
                if (file != null) {
                    //TODO repair
//                    loader.loadFile(container, file);
//                    saver.setDestFile(file);
                }
            });
        });
    }

    private void bindIOView() {
        hbInputContainer.prefWidthProperty().bind(vbIOContainer.widthProperty());
        hbInputContainer.prefHeightProperty().bind(tfInput.heightProperty());

        outputView.prefWidthProperty().bind(vbIOContainer.widthProperty());
        outputView.prefHeightProperty().bind(vbIOContainer.heightProperty().subtract(hbInputContainer.heightProperty()));

        Platform.runLater(() -> tfInput.prefWidthProperty().bind(hbInputContainer.widthProperty().subtract(btnEnter.widthProperty()).subtract(16)));
    }

    private void addIconsToMenu() {
        addDragDetection(new DragGhostIconUiElement(UiElementType.STOP));
        addDragDetection(new DragGhostIconUiElement(UiElementType.CALCULATION));
        addDragDetection(new DragGhostIconUiElement(UiElementType.IF));
        addDragDetection(new DragGhostIconUiElement(UiElementType.IO));
    }

    private void addDragDetection(DragGhostIcon dragIcon) {
        availableBlocks.getChildren().add(dragIcon);
        ghost.addDragDetection(dragIcon);
    }

    public void onCloseSheet() {
        //TODO repair
//        String data = container.stringify();
//        if(!saver.isSavedNewestVersion(data)){
//            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//
//            ButtonType btnOk = new ButtonType("Tak");
//            ButtonType btnNo = new ButtonType("Nie");
//            alert.getButtonTypes().setAll(btnOk, btnNo);
//
//            alert.setTitle("");
//            alert.setHeaderText("Czy zapisać zmiany?");
//            alert.setContentText("Wprowadzono zmiany, które mogą wymagać zapisania. Czy chcesz to zrobić?");
//
//            Optional<ButtonType> result = alert.showAndWait();
//            if (result.isPresent() && result.get() == btnOk){
//                saver.save(data);
//            }
//        }
    }
}
