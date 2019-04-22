package com.gorbel.controller;

import com.gorbel.scene.MScene;
import com.gorbel.scene.scenes.Scenes;
import com.gorbel.scene.singleton.StageManager;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;

import java.io.File;

public class Controller {

    @FXML
    private TextField fileTextField;

    private File dir;

    private StageManager stageManager = StageManager.getInstance();

    public void onClickDirectoryButton() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        dir = directoryChooser.showDialog(stageManager.getStage());
        if (dir != null) {
            System.out.println(dir.getAbsolutePath());
            fileTextField.setText(dir.getAbsolutePath());
        }
    }

    public void doSearch() {
        System.out.println("SEARCH!");
        searchFiles(dir.listFiles());
    }

    private void searchFiles(File[] files) {
        if (files == null || files.length == 0) {
            return;
        } else {
            for (File f : files ) {
                if (f != null) {
                    System.out.println("P: " + f.getAbsolutePath());
                    File[] newFiles = f.listFiles();
                    if (newFiles != null) searchFiles(newFiles);
                }
            }
        }
    }

    public void doDeleteSelected() {
        StageManager sm = StageManager.getInstance();
        sm.setScene(Scenes.SECOND.getFileName(), Scenes.path);
        System.out.println("IC: " + MScene.instanceCount);
    }

    public void doDeleteFiles() {

    }
}
