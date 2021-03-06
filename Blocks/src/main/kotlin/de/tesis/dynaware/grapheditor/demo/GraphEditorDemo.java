/*
 * Copyright (C) 2005 - 2014 by TESIS DYNAware GmbH
 */
package de.tesis.dynaware.grapheditor.demo;

import de.tesis.dynaware.grapheditor.GraphEditor;
import io.github.slupik.schemablock.view.MainViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.net.URL;

/**
 * A de.tesis.dynaware.grapheditor.demo application to show uses of the {@link GraphEditor} library.
 */
public class GraphEditorDemo extends Application {

    public static final String CUSTOM_RESOURCE_ROOT = "/io/github/slupik/schemablock/";
    public static final String MAIN_RESOURCE_ROOT = "/de/tesis/dynaware/grapheditor/demo/";
    public static final String MAIN_STYLESHEET = MAIN_RESOURCE_ROOT + "demo.css";
    private static final String TREE_SKIN_STYLESHEET = "treeskins.css";
    private static final String TITLED_SKIN_STYLESHEET = "titledskins.css";
    private static final String FONT_AWESOME = "fontawesome.ttf";
    private static final String APPLICATION_TITLE = "SchemaBlock";

    public static void launchApp(final String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage stage) throws Exception {
        final URL location = getClass().getResource("MainView.fxml");
        final FXMLLoader loader = new FXMLLoader();
        final Parent root = loader.load(location.openStream());
        final MainViewController controller = loader.getController();

        final Scene scene = new Scene(root, 830, 630);
        stage.setMinWidth(scene.getWidth());
        stage.setMinHeight(scene.getHeight());

        scene.getStylesheets().add(GraphEditorDemo.class.getResource(MAIN_STYLESHEET).toExternalForm());
        scene.getStylesheets().add(getClass().getResource(TREE_SKIN_STYLESHEET).toExternalForm());
        scene.getStylesheets().add(getClass().getResource(TITLED_SKIN_STYLESHEET).toExternalForm());
        Font.loadFont(getClass().getResource(FONT_AWESOME).toExternalForm(), 12);

        stage.setScene(scene);
        stage.setTitle(APPLICATION_TITLE);
        stage.getIcons().add(new Image(CUSTOM_RESOURCE_ROOT + "program_icon.png"));
        stage.setMaximized(true);

        stage.setOnCloseRequest(event -> System.exit(0));

        stage.show();

        controller.panToCenter();
    }

}