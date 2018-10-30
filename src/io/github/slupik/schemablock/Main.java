package io.github.slupik.schemablock;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/main.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 900, 556));
        primaryStage.setMinWidth(900);
        primaryStage.setMinHeight(556);
        primaryStage.show();
        primaryStage.sizeToScene();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
