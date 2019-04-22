package com.gorbel.main;

import com.gorbel.scene.scenes.Scenes;
import com.gorbel.scene.singleton.StageManager;
import javafx.application.Application;
import javafx.stage.Stage;


public class HelloFX extends Application {
    @Override
    public void start(Stage stage) {
        StageManager stageManager = StageManager.getInstance();
        stageManager.setStage(stage);
        stageManager.getStage().setTitle("Duplicate Deletion Tool");

        stageManager.setScene(Scenes.MAIN.getFileName(), Scenes.path);
    }

    public static void main(String[] args) {
        launch();
    }
}
