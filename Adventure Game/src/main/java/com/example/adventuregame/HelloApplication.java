package com.example.adventuregame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("Adventure Game");
        stage.setScene(scene);

        // Set window size to better suit user
        stage.setWidth(800);
        stage.setHeight(600);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}