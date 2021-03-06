/*
 * Copyright (C) 2005 - 2014 by TESIS DYNAware GmbH
 */
package de.tesis.dynaware.grapheditor.core;

import de.tesis.dynaware.grapheditor.*;
import de.tesis.dynaware.grapheditor.core.connections.ConnectionEventManager;
import de.tesis.dynaware.grapheditor.core.skins.SkinManager;
import de.tesis.dynaware.grapheditor.model.GConnection;
import de.tesis.dynaware.grapheditor.model.GModel;
import de.tesis.dynaware.grapheditor.utils.GraphEditorProperties;
import io.github.slupik.schemablock.view.dialog.BlockEditionWithDialog;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.layout.Region;
import org.eclipse.emf.common.command.CompoundCommand;

import java.util.function.BiConsumer;

/**
 * Default implementation of the {@link GraphEditor}.
 */
public class DefaultGraphEditor implements GraphEditor {

    private final SkinManager skinManager;
    private final ConnectionEventManager connectionEventManager;

    private final GraphEditorController controller;

    private final ObjectProperty<GModel> modelProperty = new SimpleObjectProperty<>();

    /**
     * Creates a new default implementation of the {@link GraphEditor}.
     */
    public DefaultGraphEditor() {
        // Skin manager needs 'this' reference so users can access GraphEditor inside their custom skins.
        skinManager = new SkinManager(this, new BlockEditionWithDialog());
        connectionEventManager = new ConnectionEventManager();

        controller = new GraphEditorController(skinManager, connectionEventManager);

        // Create some default layout properties in case the user never sets any.
        setProperties(new GraphEditorProperties());

        addModelPropertyListener();
    }

    @Override
    public void setNodeSkin(final String type, final Class<? extends GNodeSkin> skin) {
        skinManager.setNodeSkin(type, skin);
    }

    @Override
    public void setConnectorSkin(final String type, final Class<? extends GConnectorSkin> skin) {
        skinManager.setConnectorSkin(type, skin);
    }

    @Override
    public void setConnectionSkin(final String type, final Class<? extends GConnectionSkin> skin) {
        skinManager.setConnectionSkin(type, skin);
    }

    @Override
    public void setJointSkin(final String type, final Class<? extends GJointSkin> skin) {
        skinManager.setJointSkin(type, skin);
    }

    @Override
    public void setTailSkin(final String type, final Class<? extends GTailSkin> skin) {
        skinManager.setTailSkin(type, skin);
    }

    @Override
    public void setConnectorValidator(final GConnectorValidator validator) {
        controller.setConnectorValidator(validator);
    }

    @Override
    public GModel getModel() {
        return modelProperty.get();
    }

    @Override
    public void setModel(final GModel model) {
        modelProperty.set(model);
    }

    @Override
    public void reload() {
        controller.initializeAll();
    }

    @Override
    public ObjectProperty<GModel> modelProperty() {
        return modelProperty;
    }

    @Override
    public Region getView() {
        return controller.getView();
    }

    @Override
    public GraphEditorProperties getProperties() {
        return controller.getEditorProperties();
    }

    @Override
    public void setProperties(final GraphEditorProperties editorProperties) {
        controller.setEditorProperties(editorProperties);
    }

    @Override
    public SkinLookup getSkinLookup() {
        return skinManager;
    }

    @Override
    public SelectionManager getSelectionManager() {
        return controller.getSelectionManager();
    }

    @Override
    public void setOnConnectionCreated(final BiConsumer<GConnection, CompoundCommand> consumer) {
        connectionEventManager.setOnConnectionCreated(consumer);
    }

    @Override
    public void setOnConnectionRemoved(final BiConsumer<GConnection, CompoundCommand> consumer) {
        connectionEventManager.setOnConnectionRemoved(consumer);
    }

    /**
     * Adds a listener to the model property to set the controller value whenever the model property is updated.
     */
    private void addModelPropertyListener() {

        modelProperty.addListener((observable, oldValue, newValue) -> {
            controller.setModel(newValue);
        });
    }
}
