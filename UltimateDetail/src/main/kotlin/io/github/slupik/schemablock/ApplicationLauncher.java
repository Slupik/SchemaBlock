package io.github.slupik.schemablock;

import io.github.slupik.schemablock.javafx.view.MainViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * All rights reserved & copyright ©
 */
public class ApplicationLauncher extends Application {

    public static void launchApp(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

//        Parent root = FXMLLoader.restore(getClass().getClassLoader().getResource("view/main.fxml"));
//        primaryStage.setTitle("Hello World");

        FXMLLoader loader = new FXMLLoader(ApplicationLauncher.class.getResource("/view/main.fxml"));
        Thread.sleep(200);
        Parent root = loader.load();
        MainViewController controller = loader.getController();

        primaryStage.setTitle("SchemaBlock");
        primaryStage.setScene(new Scene(root, 900, 556));
        primaryStage.setMinWidth(900);
        primaryStage.setMinHeight(556);
        primaryStage.show();
        primaryStage.sizeToScene();

        primaryStage.setOnCloseRequest(event -> controller.onCloseSheet());
    }

}
