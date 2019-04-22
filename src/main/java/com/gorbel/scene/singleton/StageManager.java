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
    private String currentScene;
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
                FXMLLoader loader = getLoader(sceneName, path);
                Parent parent = null;
                try {
                    parent = loader.load();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                if (parent != null) {
                    MScene scene = new MScene(parent, sceneName, loader.getController());
                    stage.setScene(scene);
                    currentScene = sceneName;
                    stage.show();
                    if (!containsScene(sceneName)) {
                        scenes.put(sceneName, scene);
                    }
                }
            }
        }
    }

    public MScene getCurrentScene() {
        return scenes.get(currentScene);
    }

    private void switchScene(String sceneId) {
        currentScene = sceneId;
        stage.setScene(scenes.get(sceneId));
        stage.show();
    }

    private FXMLLoader getLoader(String fileName, String path) {
        String resStr = path + fileName + ".fxml";
        return new FXMLLoader(getClass().getResource(resStr));
    }

    private boolean containsScene(String sceneId) {
        return scenes.containsKey(sceneId);
    }
}
