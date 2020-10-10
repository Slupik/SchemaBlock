package io.github.slupik.schemablock.view;

import de.tesis.dynaware.grapheditor.GraphEditor;
import de.tesis.dynaware.grapheditor.GraphEditorContainer;
import de.tesis.dynaware.grapheditor.core.DefaultGraphEditor;
import de.tesis.dynaware.grapheditor.model.GModel;
import de.tesis.dynaware.grapheditor.model.GraphFactory;
import de.tesis.dynaware.grapheditor.window.WindowPosition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * All rights reserved & copyright ©
 */
public class MainViewController implements Initializable {

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
    private RadioMenuItem defaultSkinButton;
    @FXML
    private RadioMenuItem treeSkinButton;
    @FXML
    private RadioMenuItem titledSkinButton;
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

    private final GraphEditor graphEditor = new DefaultGraphEditor();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        final GModel model = GraphFactory.eINSTANCE.createGModel();

        graphEditor.setModel(model);
        graphEditorContainer.setGraphEditor(graphEditor);
    }

    @FXML
    public void load() {
//        graphEditorPersistence.loadFromFile(graphEditor);
//        checkSkinType();
    }

    @FXML
    public void loadSample() {
        defaultSkinButton.setSelected(true);
        setDefaultSkin();
//        graphEditorPersistence.loadSample(graphEditor);
    }

    @FXML
    public void loadSampleLarge() {
        defaultSkinButton.setSelected(true);
        setDefaultSkin();
//        graphEditorPersistence.loadSampleLarge(graphEditor);
    }

    @FXML
    public void loadTree() {
        treeSkinButton.setSelected(true);
        setTreeSkin();
//        graphEditorPersistence.loadTree(graphEditor);
    }

    @FXML
    public void loadTitled() {
        titledSkinButton.setSelected(true);
        setTitledSkin();
//        graphEditorPersistence.loadTitled(graphEditor);
    }

    @FXML
    public void save() {
//        graphEditorPersistence.saveToFile(graphEditor);
    }

    @FXML
    public void clearAll() {
//        Commands.clear(graphEditor.getModel());
    }

    @FXML
    public void createNew() {
        System.out.println("Stubbed action: createNew");
    }

    @FXML
    public void exit() {
        Platform.exit();
    }

    @FXML
    public void undo() {
//        Commands.undo(graphEditor.getModel());
    }

    @FXML
    public void redo() {
//        Commands.redo(graphEditor.getModel());
    }

    @FXML
    public void cut() {
//        graphEditor.getSelectionManager().cut();
    }

    @FXML
    public void copy() {
//        graphEditor.getSelectionManager().copy();
    }

    @FXML
    public void paste() {
//        activeSkinController.get().handlePaste();
    }

    @FXML
    public void selectAll() {
//        activeSkinController.get().handleSelectAll();
    }

    @FXML
    public void deleteSelection() {
//        graphEditor.getSelectionManager().deleteSelection();
    }

    @FXML
    public void addNode() {
//        activeSkinController.get().addNode(currentZoomFactor);
    }

    @FXML
    public void addConnector() {
//        activeSkinController.get().addConnector(getSelectedConnectorPosition(), inputConnectorTypeButton.isSelected());
    }

    @FXML
    public void clearConnectors() {
//        activeSkinController.get().clearConnectors();
    }

    @FXML
    public void setDefaultSkin() {
//        activeSkinController.set(defaultSkinController);
    }

    @FXML
    public void setTreeSkin() {
//        activeSkinController.set(treeSkinController);
    }

    @FXML
    public void setTitledSkin() {
//        activeSkinController.set(titledSkinController);
    }

    @FXML
    public void setGappedStyle() {
//        graphEditor.getProperties().getCustomProperties().remove(SimpleConnectionSkin.SHOW_DETOURS_KEY);
//        graphEditor.reload();
    }

    @FXML
    public void setDetouredStyle() {
//        final Map<String, String> customProperties = graphEditor.getProperties().getCustomProperties();
//        customProperties.put(SimpleConnectionSkin.SHOW_DETOURS_KEY, Boolean.toString(true));
//        graphEditor.reload();
    }

    @FXML
    public void toggleMinimap() {
        graphEditorContainer.getMinimap().visibleProperty().bind(minimapButton.selectedProperty());
    }

    public void panToCenter() {
        graphEditorContainer.panTo(WindowPosition.CENTER);
    }

}
