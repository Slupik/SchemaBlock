package io.github.slupik;

import io.github.slupik.schemablock.javafx.view.MainViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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

        primaryStage.setOnCloseRequest(event -> controller.onCloseSheet());
    }


    public static void main(String[] args) {
        launch(args);
    }
}
