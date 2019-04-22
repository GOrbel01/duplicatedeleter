package com.gorbel.controller;

import com.gorbel.scene.MScene;
import com.gorbel.scene.scenes.Scenes;
import com.gorbel.scene.singleton.StageManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private TextField fileTextField;

    @FXML
    private RadioButton fileSearchRadioButtonExt;

    @FXML
    private RadioButton fileSearchRadioButtonName;

    private File dir;

    private StageManager stageManager = StageManager.getInstance();

    private ToggleGroup toggleGroup = new ToggleGroup();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.fileSearchRadioButtonExt.setToggleGroup(toggleGroup);
        if (!toggleGroup.hasProperties()) {
            fileSearchRadioButtonExt.setSelected(true);
        }
        this.fileSearchRadioButtonName.setToggleGroup(toggleGroup);
    }

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
        StageManager sm = StageManager.getInstance();
        Controller c = (Controller) sm.getCurrentScene().getController();
        System.out.println(c.dir.getAbsolutePath());
    }
}
