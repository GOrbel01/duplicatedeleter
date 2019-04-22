package com.gorbel.scene.singleton;

import com.gorbel.scene.MScene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class StageManager {
    private Stage stage;
    private Map<String, MScene> scenes;

    private static StageManager instance;

    private StageManager() {
        scenes = new HashMap<>();
    }

    public static StageManager getInstance() {
        if (instance == null) {
            instance = new StageManager();
        }
        return instance;
    }

    public Stage getStage() {
        return instance.stage;
    }

    public void setStage(Stage stage) {
        if (instance != null) {
            instance.stage = stage;
        }
    }

    public void setScene(String sceneName, String path) {
        if (stage != null) {
            if (containsScene(sceneName)) {
                switchScene(sceneName);
            } else {
                Parent parent = getParent(sceneName, path);
                if (parent != null) {
                    MScene scene = new MScene(parent, sceneName);
                    stage.setScene(scene);
                    stage.show();
                    if (!containsScene(sceneName)) {
                        scenes.put(sceneName, scene);
                    }
                }
            }
        }
    }

    public void switchScene(String sceneId) {
        stage.setScene(scenes.get(sceneId));
        stage.show();
    }

    private Parent getParent(String fileName, String path) {
        try {
            String resStr = path + fileName + ".fxml";
            return FXMLLoader.load(getClass().getResource(resStr));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private boolean containsScene(String sceneId) {
        return scenes.containsKey(sceneId);
    }
}
