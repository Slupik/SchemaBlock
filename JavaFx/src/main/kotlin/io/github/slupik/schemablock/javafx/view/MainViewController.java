package io.github.slupik.schemablock.javafx.view;

import io.github.slupik.schemablock.javafx.dagger.*;
import io.github.slupik.schemablock.javafx.element.UiElementType;
import io.github.slupik.schemablock.javafx.element.fx.communication.UIIOCommunicator;
import io.github.slupik.schemablock.javafx.element.fx.element.holder.BlocksHolder;
import io.github.slupik.schemablock.javafx.element.fx.factory.UiBlockFactory;
import io.github.slupik.schemablock.javafx.element.fx.port.connection.drawer.ConnectionDrawer;
import io.github.slupik.schemablock.javafx.element.fx.port.holder.PortsHolder;
import io.github.slupik.schemablock.javafx.element.fx.port.spawner.PortSpawner;
import io.github.slupik.schemablock.javafx.element.fx.schema.Schema;
import io.github.slupik.schemablock.javafx.element.fx.schema.restorer.SchemaRestorer;
import io.github.slupik.schemablock.javafx.element.fx.schema.stringifier.SchemaStringifier;
import io.github.slupik.schemablock.javafx.element.fx.sheet.Sheet;
import io.github.slupik.schemablock.javafx.element.fx.sheet.spawner.ElementsSpawner;
import io.github.slupik.schemablock.javafx.logic.drag.icon.DragGhostIcon;
import io.github.slupik.schemablock.javafx.logic.drag.icon.GhostDragController;
import io.github.slupik.schemablock.javafx.logic.execution.BlocksColorizer;
import io.github.slupik.schemablock.javafx.logic.heap.HeapValueFx;
import io.github.slupik.schemablock.javafx.logic.heap.NewHeapSpy;
import io.github.slupik.schemablock.javafx.logic.persistence.SchemaSaver;
import io.github.slupik.schemablock.javafx.view.resize.BlocksAwareSizeProvider;
import io.github.slupik.schemablock.javafx.view.resize.ResizingTool;
import io.github.slupik.schemablock.logic.executor.diagram.DiagramExecutor;
import io.github.slupik.schemablock.logic.executor.diagram.ExecutionEvent;
import io.github.slupik.schemablock.logic.executor.diagram.exception.NextBlockNotFound;
import io.github.slupik.schemablock.model.ui.newparser.HeapController;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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

    @Inject
    NewHeapSpy memory;

    @Inject
    HeapController heapController;

    @Inject
    BlocksColorizer blocksColorizer;

    @Inject
    Schema schema;

    @Inject
    SchemaStringifier schemaStringifier;

    @Inject
    SchemaRestorer schemaRestorer;

    @Inject
    @LogicalSheet
    Sheet container;

    @Inject
    PortSpawner portSpawner;

    @Inject
    ElementsSpawner elementsSpawner;

    @Inject
    UiBlockFactory factory;

    private GhostDragController ghost;
    private SchemaSaver saver;
    private SchemaLoader loader;

    private CompositeDisposable composite = new CompositeDisposable();
    private Disposable continuationDispose = null;

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
        bindIOView();
        setupMenu();
        bindTable();
        bindResizer();
        btnRun.setOnAction((event) -> {
            Observable<ExecutionEvent> observable = executor.run();
            handleExecutionStates(observable);
        });
        Observable<Boolean> continuationTrigger = getContinuationTrigger();
        btnDebug.setOnAction((event) -> {
            Observable<ExecutionEvent> observable = executor.debug(execution -> {
                        if (continuationDispose != null) {
                            continuationDispose.dispose();
                        }
                        continuationDispose = continuationTrigger.subscribe(
                                aBoolean -> execution.invoke(),
                                Throwable::printStackTrace
                        );
                    }
            );
            handleExecutionStates(observable);
        });
        elementsSpawner.spawn();
    }

    private void handleExecutionStates(Observable<ExecutionEvent> observable) {
        memory.clear();
        setUiStateToExecuting();
        blocksColorizer.inject(observable);
        Disposable disp = observable.subscribe(
                executionEvent -> {
                    System.out.println("executionEvent = " + executionEvent);
                },
                throwable -> {
                    System.out.println("throwable = " + throwable);
                    if (throwable instanceof NextBlockNotFound) {
                        System.out.println("type " + ((NextBlockNotFound) throwable).getCurrentBlock());
                    }
                },
                () -> {
                    setUiStateForEdition();
                    if (continuationDispose != null) {
                        continuationDispose.dispose();
                    }
                }
        );
        composite.add(disp);
    }

    private Observable<Boolean> getContinuationTrigger() {
        PublishSubject<Boolean> continuationTrigger = PublishSubject.create();
        btnContinue.addEventHandler(MouseEvent.MOUSE_CLICKED, event ->
                continuationTrigger.onNext(true));
        return continuationTrigger;
    }

    private void setUiStateToExecuting() {
        btnRun.setDisable(true);
        btnDebug.setDisable(true);
    }

    private void setUiStateForEdition() {
        btnRun.setDisable(false);
        btnDebug.setDisable(false);
    }

    private void setupDragging() {
        ghost = new GhostDragController(mainContainer, sheet, new GhostDragElementFactoryImpl(factory), blocksHolder, portSpawner);

        addIconsToMenu();
    }

    private void bindResizer() {
        BlocksAwareSizeProvider sizeProvider = new BlocksAwareSizeProvider(sheet);
        new ResizingTool(sheet, resizingIcon, sizeProvider).init();
    }

    private void bindTable() {
        tvVariables.setItems(memory.getVariableList());
        tcVarType.setCellValueFactory(
                new PropertyValueFactory<>("type")
        );
        tcVarName.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );
        tcVarValue.setCellValueFactory(
                new PropertyValueFactory<>("value")
        );
    }

    private void setupMenu() {
        Platform.runLater(() -> {
            saver = new SchemaSaver(((Stage) mainContainer.getScene().getWindow()));
            loader = new SchemaLoader(((Stage) mainContainer.getScene().getWindow()));
            miSave.setOnAction(event -> {
                String output = schemaStringifier.stringify(schema);
                saver.save(output);
            });
            miLoad.setOnAction(event -> {
                File file = loader.getFileToLoad();
                if (file != null) {
                    loader.loadFile(schema, schemaRestorer, file);
                    saver.setDestFile(file);
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
        addDragDetection(new DragGhostIconUiElement(UiElementType.STOP, factory));
        addDragDetection(new DragGhostIconUiElement(UiElementType.CALCULATION, factory));
        addDragDetection(new DragGhostIconUiElement(UiElementType.IF, factory));
        addDragDetection(new DragGhostIconUiElement(UiElementType.IO, factory));
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
