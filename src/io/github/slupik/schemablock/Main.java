package io.github.slupik.schemablock;

import io.github.slupik.schemablock.javafx.view.MainViewController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

//        Parent root = FXMLLoader.restore(getClass().getClassLoader().getResource("view/main.fxml"));
//        primaryStage.setTitle("Hello World");

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/main.fxml"));
        Parent root = loader.load();
        MainViewController controller = loader.getController();

        primaryStage.setTitle("SchemaBlock");
        primaryStage.setScene(new Scene(root, 900, 556));
        primaryStage.setMinWidth(900);
        primaryStage.setMinHeight(556);
        primaryStage.show();
        primaryStage.sizeToScene();

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                controller.onCloseSheet();
            }
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
