package io.github.slupik.schemablock.view;

import com.jfoenix.controls.JFXButton;
import de.tesis.dynaware.grapheditor.Commands;
import de.tesis.dynaware.grapheditor.GraphEditor;
import de.tesis.dynaware.grapheditor.GraphEditorContainer;
import de.tesis.dynaware.grapheditor.core.skins.BlockType;
import de.tesis.dynaware.grapheditor.core.skins.defaults.connection.SimpleConnectionSkin;
import de.tesis.dynaware.grapheditor.demo.customskins.DefaultSkinController;
import de.tesis.dynaware.grapheditor.demo.customskins.SkinController;
import de.tesis.dynaware.grapheditor.demo.utils.AwesomeIcon;
import de.tesis.dynaware.grapheditor.model.GNode;
import de.tesis.dynaware.grapheditor.window.WindowPosition;
import io.github.slupik.schemablock.view.dagger.DaggerViewComponent;
import io.github.slupik.schemablock.view.dagger.ViewElementsModule;
import io.github.slupik.schemablock.view.entity.Diagram;
import io.github.slupik.schemablock.view.logic.Zoomer;
import io.github.slupik.schemablock.view.persistence.DiagramLoader;
import io.github.slupik.schemablock.view.persistence.DiagramSaver;
import io.github.slupik.schemablock.view.persistence.FileChooser;
import io.github.slupik.schemablock.view.persistence.graph.SampleLoader;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
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

    @FXML
    private JFXButton btnContinue;
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

    private final ObjectProperty<SkinController> activeSkinController = new SimpleObjectProperty<>();
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DaggerViewComponent.builder()
                .addViewElementsModule(new ViewElementsModule(graphEditorContainer))
                .build().inject(this);

        graphEditorContainer.setGraphEditor(graphEditor);
        activeSkinController.set(defaultSkinController);
        btnContinue.setDisable(true);
        addActiveSkinControllerListener();

        enableResizing();
        initializeMenuBar();
        Platform.runLater(this::addStartNode);
    }

    private void addStartNode() {
        activeSkinController.get().addNode(zoomer.getCurrentZoomFactor(), BlockType.START);
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
        activeSkinController.get().addNode(zoomer.getCurrentZoomFactor(), BlockType.OPERATIONS);
    }

    @FXML
    public void addConditionNode() {
        activeSkinController.get().addNode(zoomer.getCurrentZoomFactor(), BlockType.CONDITION);
    }

    @FXML
    public void addIoNode() {
        activeSkinController.get().addNode(zoomer.getCurrentZoomFactor(), BlockType.IO);
    }

    @FXML
    public void addStopNode() {
        activeSkinController.get().addNode(zoomer.getCurrentZoomFactor(), BlockType.STOP);
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
