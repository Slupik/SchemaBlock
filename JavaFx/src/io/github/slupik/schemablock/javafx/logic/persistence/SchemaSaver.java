package io.github.slupik.schemablock.javafx.logic.persistence;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

import static io.github.slupik.schemablock.javafx.logic.persistence.ProgramFile.FILE_EXTENSION;

/**
 * All rights reserved & copyright ©
 */
public class SchemaSaver {

    private final Stage stage;
    private File destFile = null;
    private String lastSavedContent = "";

    public SchemaSaver(Stage stage){
        this.stage = stage;
    }

    public void save(String content) {
        if(destFile==null) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialFileName("schemat."+FILE_EXTENSION);
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Pliki programu", "*."+FILE_EXTENSION)
            );
            fileChooser.setTitle("Zapisz schemat");
            destFile = fileChooser.showSaveDialog(stage);
        }
        if(destFile!=null) {
            try (PrintStream out = new PrintStream(new FileOutputStream(destFile))) {
                out.print(content);
                lastSavedContent = content;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isSavedNewestVersion(String currentVersion){
        return lastSavedContent.equals(currentVersion);
    }
}
