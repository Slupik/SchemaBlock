package io.github.slupik.schemablock.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import de.tesis.dynaware.grapheditor.Commands;
import de.tesis.dynaware.grapheditor.GraphEditor;
import de.tesis.dynaware.grapheditor.GraphEditorContainer;
import de.tesis.dynaware.grapheditor.core.skins.UiElementType;
import de.tesis.dynaware.grapheditor.core.skins.defaults.connection.SimpleConnectionSkin;
import de.tesis.dynaware.grapheditor.demo.customskins.DefaultSkinController;
import de.tesis.dynaware.grapheditor.demo.customskins.SkinController;
import de.tesis.dynaware.grapheditor.demo.utils.AwesomeIcon;
import de.tesis.dynaware.grapheditor.model.GNode;
import de.tesis.dynaware.grapheditor.window.WindowPosition;
import io.github.slupik.schemablock.view.dagger.DaggerViewComponent;
import io.github.slupik.schemablock.view.dagger.ViewElementsModule;
import io.github.slupik.schemablock.view.entity.Diagram;
import io.github.slupik.schemablock.view.logic.communication.UIIOCommunicator;
import io.github.slupik.schemablock.view.logic.communication.output.ErrorTranslator;
import io.github.slupik.schemablock.view.logic.execution.dagger.DiagramExecutorElementsModule;
import io.github.slupik.schemablock.view.logic.execution.dagger.ExecutionElementsModule;
import io.github.slupik.schemablock.view.logic.execution.dagger.HeapControllerCallbackModule;
import io.github.slupik.schemablock.view.logic.execution.diagram.DiagramExecutor;
import io.github.slupik.schemablock.view.logic.execution.diagram.ErrorEvent;
import io.github.slupik.schemablock.view.logic.execution.diagram.ExecutionEvent;
import io.github.slupik.schemablock.view.logic.execution.diagram.PostExecutionEvent;
import io.github.slupik.schemablock.view.logic.execution.diagram.exception.NextBlockNotFound;
import io.github.slupik.schemablock.view.logic.marker.BlockExecutionStateMarker;
import io.github.slupik.schemablock.view.logic.memory.HeapValueFx;
import io.github.slupik.schemablock.view.logic.memory.NewHeapSpy;
import io.github.slupik.schemablock.view.logic.zoom.Zoomer;
import io.github.slupik.schemablock.view.persistence.DiagramLoader;
import io.github.slupik.schemablock.view.persistence.DiagramSaver;
import io.github.slupik.schemablock.view.persistence.FileChooser;
import io.github.slupik.schemablock.view.persistence.graph.SampleLoader;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;

import javax.inject.Inject;
import java.io.File;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * All rights reserved & copyright ©
 */
public class MainViewController implements Initializable {

    @Inject
    DefaultSkinController defaultSkinController;
    @Inject
    GraphEditor graphEditor;
    @Inject
    Zoomer zoomer;
    @Inject
    FileChooser fileChooser;
    @Inject
    DiagramSaver graphSaver;
    @Inject
    DiagramLoader graphLoader;
    @Inject
    Diagram diagram;
    @Inject
    SampleLoader sampleLoader;
    @Inject
    DiagramExecutor executor;
    @Inject
    NewHeapSpy memory;
    @Inject
    BlockExecutionStateMarker stateMarker;
    @Inject
    ErrorTranslator translator;
    @FXML
    private JFXButton btnRun;
    @FXML
    private JFXButton btnDebug;
    @FXML
    private JFXButton btnContinue;
    @FXML
    private JFXButton btnStop;
    @FXML
    private AnchorPane root;
    @FXML
    private VBox mainContainer;
    @FXML
    private MenuBar menuBar;
    @FXML
    private MenuItem addConnectorButton;
    @FXML
    private MenuItem clearConnectorsButton;
    @FXML
    private Menu connectorTypeMenu;
    @FXML
    private Menu connectorPositionMenu;
    @FXML
    private RadioMenuItem inputConnectorTypeButton;
    @FXML
    private RadioMenuItem outputConnectorTypeButton;
    @FXML
    private RadioMenuItem leftConnectorPositionButton;
    @FXML
    private RadioMenuItem rightConnectorPositionButton;
    @FXML
    private RadioMenuItem topConnectorPositionButton;
    @FXML
    private RadioMenuItem bottomConnectorPositionButton;
    @FXML
    private RadioMenuItem showGridButton;
    @FXML
    private RadioMenuItem snapToGridButton;
    @FXML
    private Menu intersectionStyle;
    @FXML
    private RadioMenuItem gappedStyleButton;
    @FXML
    private RadioMenuItem detouredStyleButton;
    @FXML
    private Menu zoomOptions;
    @FXML
    private ToggleButton minimapButton;
    @FXML
    private GraphEditorContainer graphEditorContainer;
    @FXML
    private TextField tfInput;
    @FXML
    private JFXButton btnEnter;
    @FXML
    private WebView outputView;
    @FXML
    private JFXTreeTableView<HeapValueFx> tvVariables;
    @FXML
    private JFXTreeTableColumn<HeapValueFx, String> tcVarType;
    @FXML
    private JFXTreeTableColumn<HeapValueFx, String> tcVarName;
    @FXML
    private JFXTreeTableColumn<HeapValueFx, String> tcVarValue;

    private final ObjectProperty<SkinController> activeSkinController = new SimpleObjectProperty<>();
    private final CompositeDisposable composite = new CompositeDisposable();
    private Disposable continuationDispose = null;
    private UIIOCommunicator output;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        output = new UIIOCommunicator(tfInput, outputView, btnEnter);
        DaggerViewComponent.builder()
                .addViewElementsModule(new ViewElementsModule(graphEditorContainer))
                .addViewElementsModule(new DiagramExecutorElementsModule(null))
                .addViewElementsModule(new HeapControllerCallbackModule(
                        () -> tvVariables.refresh()
                ))
                .addViewElementsModule(new ExecutionElementsModule(output))
                .build().inject(this);

        graphEditorContainer.setGraphEditor(graphEditor);
        activeSkinController.set(defaultSkinController);
        btnContinue.setDisable(true);
        btnStop.setDisable(true);
        addActiveSkinControllerListener();

        enableResizing();
        initializeMenuBar();
        bindTable();
        Platform.runLater(this::addStartNode);
    }

    private void bindTable() {
        final TreeItem<HeapValueFx> root = new RecursiveTreeItem<>(memory.getVariableList(), RecursiveTreeObject::getChildren);
        tvVariables.setRoot(root);
        tvVariables.setShowRoot(false);

        tcVarType.setCellValueFactory(param -> param.getValue().getValue().getTypeProperty());
        tcVarName.setCellValueFactory(param -> param.getValue().getValue().getNameProperty());
        tcVarValue.setCellValueFactory(param -> param.getValue().getValue().getValueProperty());
    }

    private void addStartNode() {
        activeSkinController.get().addNode(zoomer.getCurrentZoomFactor(), UiElementType.START);
    }

    /**
     * Initializes the menu bar.
     */
    private void initializeMenuBar() {
        graphEditor.getView().getTransforms().add(zoomer.getScale());

        final ToggleGroup connectionStyleGroup = new ToggleGroup();
        connectionStyleGroup.getToggles().addAll(gappedStyleButton, detouredStyleButton);

        final ToggleGroup connectorTypeGroup = new ToggleGroup();
        connectorTypeGroup.getToggles().addAll(inputConnectorTypeButton, outputConnectorTypeButton);

        final ToggleGroup positionGroup = new ToggleGroup();
        positionGroup.getToggles().addAll(leftConnectorPositionButton, rightConnectorPositionButton);
        positionGroup.getToggles().addAll(topConnectorPositionButton, bottomConnectorPositionButton);

        graphEditor.getProperties().gridVisibleProperty().bind(showGridButton.selectedProperty());
        graphEditor.getProperties().snapToGridProperty().bind(snapToGridButton.selectedProperty());

        minimapButton.setGraphic(AwesomeIcon.MAP.node());

        initializeZoomOptions();

        final ListChangeListener<? super GNode> selectedNodesListener = change -> checkConnectorButtonsToDisable();

        graphEditor.getSelectionManager().getSelectedNodes().addListener(selectedNodesListener);
        checkConnectorButtonsToDisable();
    }

    /**
     * Initializes the list of zoom options.
     */
    private void initializeZoomOptions() {
        final ToggleGroup toggleGroup = new ToggleGroup();
        addZoomItem(toggleGroup, 10);
        addZoomItem(toggleGroup, 25);
        addZoomItem(toggleGroup, 50);
        addZoomItem(toggleGroup, 75);
        addZoomItem(toggleGroup, 100);
        addZoomItem(toggleGroup, 200);
        addZoomItem(toggleGroup, 300);
        addZoomItem(toggleGroup, 400);
        addZoomItem(toggleGroup, 500);
    }

    private void addZoomItem(ToggleGroup toggleGroup, double zoomValue) {
        final RadioMenuItem zoomOption = new RadioMenuItem();
        final double zoomFactor = zoomValue / 100;

        zoomOption.setText(zoomValue + "%");
        zoomOption.setOnAction(event -> zoomer.zoom(zoomFactor));

        toggleGroup.getToggles().add(zoomOption);
        zoomOptions.getItems().add(zoomOption);

        if (zoomFactor == 1) {
            zoomOption.setSelected(true);
        }
    }

    /**
     * Adds a listener to make changes to available menu options when the skin type changes.
     */
    private void addActiveSkinControllerListener() {
        activeSkinController.addListener((observable, oldValue, newValue) -> {
            handleActiveSkinControllerChange();
        });
    }

    /**
     * Enables & disables certain menu options and sets CSS classes based on the new skin type that was set active.
     */
    private void handleActiveSkinControllerChange() {
        graphEditor.setConnectorValidator(null);
        graphEditor.getSelectionManager().setConnectionSelectionPredicate(null);
        clearAll();
    }

    private void clearAll() {
        clearModel();
        stopExecution();
        flushCommandStack();
        checkConnectorButtonsToDisable();
        graphEditor.getSelectionManager().clearMemory();
    }

    /**
     * Flushes the command stack, so that the undo/redo history is cleared.
     */
    private void flushCommandStack() {
        final EditingDomain editingDomain = AdapterFactoryEditingDomain.getEditingDomainFor(graphEditor.getModel());
        if (editingDomain != null) {
            editingDomain.getCommandStack().flush();
        }
    }

    /**
     * Checks if the connector buttons need disabling (e.g. because no nodes are selected).
     */
    private void checkConnectorButtonsToDisable() {
        final boolean nothingSelected = graphEditor.getSelectionManager().getSelectedNodes().isEmpty();
        if (nothingSelected) {
            addConnectorButton.setDisable(true);
            clearConnectorsButton.setDisable(true);
        } else {
            addConnectorButton.setDisable(false);
            clearConnectorsButton.setDisable(false);
        }
        connectorTypeMenu.setDisable(false);
        connectorPositionMenu.setDisable(false);
    }

    private void enableResizing() {
        mainContainer.prefWidthProperty().bind(root.widthProperty());
        mainContainer.prefHeightProperty().bind(root.heightProperty());
    }

    @FXML
    public void loadSample() {
        setDefaultSkin();
        sampleLoader.loadSmallSample(graphEditor);
    }

    @FXML
    public void loadSampleLarge() {
        setDefaultSkin();
        sampleLoader.loadBigSample(graphEditor);
    }

    @FXML
    public void setDefaultSkin() {
        activeSkinController.set(defaultSkinController);
    }

    @FXML
    public void load() {
        File file = fileChooser.choseForOpen();
        if (file != null) {
            graphLoader.loadDiagram(diagram, file);
        }
        checkSkinType();
    }

    /**
     * Crudely inspects the model's first node and sets the new skin type accordingly.
     */
    private void checkSkinType() {
        if (!graphEditor.getModel().getNodes().isEmpty()) {
            final GNode firstNode = graphEditor.getModel().getNodes().get(0);
            final String type = firstNode.getType();
            activeSkinController.set(defaultSkinController);
        }
    }

    @FXML
    public void debugDiagram() {
        resetUserView();
        executor.resetState();
        handleExecutionStates(executor.getEventSource());
        Observable<Boolean> continuationTrigger = getContinuationTrigger();
        executor.debug(execution -> {
                    if (continuationDispose != null) {
                        continuationDispose.dispose();
                    }
                    continuationDispose = continuationTrigger.subscribe(
                            aBoolean -> execution.invoke(),
                            Throwable::printStackTrace
                    );
                }
        );
        onExecutionStart();
        btnContinue.setDisable(false);
    }

    private Observable<Boolean> getContinuationTrigger() {
        PublishSubject<Boolean> continuationTrigger = PublishSubject.create();
        btnContinue.addEventHandler(MouseEvent.MOUSE_CLICKED, event ->
                continuationTrigger.onNext(true));
        return continuationTrigger;
    }

    @FXML
    public void runDiagram() {
        resetUserView();
        executor.resetState();
        handleExecutionStates(executor.getEventSource());
        executor.run();
        onExecutionStart();
    }

    @FXML
    public void stopExecution() {
        onExecutionEnd();
        resetUserView();
        executor.stop();
    }

    private void onExecutionStart() {
        btnRun.setDisable(true);
        btnDebug.setDisable(true);
        btnContinue.setDisable(true);
        btnStop.setDisable(false);
    }

    private void onExecutionEnd() {
        btnRun.setDisable(false);
        btnDebug.setDisable(false);
        btnContinue.setDisable(true);
        btnStop.setDisable(true);
    }

    private void handleExecutionStates(Observable<ExecutionEvent> observable) {
        stateMarker.handleObservable(observable);
        Disposable disp = observable.subscribe(
                executionEvent -> {
                    if (executionEvent instanceof PostExecutionEvent) {
                        memory.refresh();
                    } else if (executionEvent instanceof ErrorEvent) {
                        output.printAlgorithmError(translator.translateError(((ErrorEvent) executionEvent).getError()));
                    }
                    System.out.println("executionEvent = " + executionEvent);
                },
                throwable -> {
                    System.out.println("throwable = " + throwable);
                    if (throwable instanceof NextBlockNotFound) {
                        System.out.println("type " + ((NextBlockNotFound) throwable).getCurrentBlock());
                    }
                    output.printProgramError(throwable.getMessage());
                    onExecutionEnd();
                },
                () -> {
                    System.out.println("END");
                    onExecutionEnd();
                }
        );
        composite.add(disp);
    }

    @FXML
    public void save() {
        File file = fileChooser.choseForSave();
        if (file != null) {
            graphSaver.saveToFile(diagram, file);
        }
    }

    @FXML
    public void clearModel() {
        Commands.clear(graphEditor.getModel());
        addStartNode();
    }

    @FXML
    public void createNew() {
        save();
        clearModel();
        stopExecution();
    }

    private void resetUserView() {
        memory.clear();
        output.clear();
    }

    @FXML
    public void exit() {
        Platform.exit();
    }

    @FXML
    public void undo() {
        Commands.undo(graphEditor.getModel());
    }

    @FXML
    public void redo() {
        Commands.redo(graphEditor.getModel());
    }

    @FXML
    public void cut() {
        graphEditor.getSelectionManager().cut();
    }

    @FXML
    public void copy() {
        graphEditor.getSelectionManager().copy();
    }

    @FXML
    public void paste() {
        activeSkinController.get().handlePaste();
    }

    @FXML
    public void selectAll() {
        activeSkinController.get().handleSelectAll();
    }

    @FXML
    public void deleteSelection() {
        graphEditor.getSelectionManager().deleteSelection();
    }

    @FXML
    public void addCalculationsNode() {
        activeSkinController.get().addNode(zoomer.getCurrentZoomFactor(), UiElementType.OPERATIONS);
    }

    @FXML
    public void addConditionNode() {
        activeSkinController.get().addNode(zoomer.getCurrentZoomFactor(), UiElementType.CONDITION);
    }

    @FXML
    public void addIoNode() {
        activeSkinController.get().addNode(zoomer.getCurrentZoomFactor(), UiElementType.IO);
    }

    @FXML
    public void addStopNode() {
        activeSkinController.get().addNode(zoomer.getCurrentZoomFactor(), UiElementType.STOP);
    }

    @FXML
    public void addConnector() {
        activeSkinController.get().addConnector(getSelectedConnectorPosition(), inputConnectorTypeButton.isSelected());
    }

    /**
     * Gets the side corresponding to the currently selected connector position in the menu.
     *
     * @return the {@link Side} corresponding to the currently selected connector position
     */
    private Side getSelectedConnectorPosition() {
        if (leftConnectorPositionButton.isSelected()) {
            return Side.LEFT;
        } else if (rightConnectorPositionButton.isSelected()) {
            return Side.RIGHT;
        } else if (topConnectorPositionButton.isSelected()) {
            return Side.TOP;
        } else {
            return Side.BOTTOM;
        }
    }

    @FXML
    public void clearConnectors() {
        activeSkinController.get().clearConnectors();
    }

    @FXML
    public void setGappedStyle() {
        graphEditor.getProperties().getCustomProperties().remove(SimpleConnectionSkin.SHOW_DETOURS_KEY);
        graphEditor.reload();
    }

    @FXML
    public void setDetouredStyle() {
        final Map<String, String> customProperties = graphEditor.getProperties().getCustomProperties();
        customProperties.put(SimpleConnectionSkin.SHOW_DETOURS_KEY, Boolean.toString(true));
        graphEditor.reload();
    }

    @FXML
    public void toggleMinimap() {
        graphEditorContainer.getMinimap().visibleProperty().bind(minimapButton.selectedProperty());
    }

    public void panToCenter() {
        graphEditorContainer.panTo(WindowPosition.CENTER);
    }

}