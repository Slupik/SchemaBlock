package io.github.slupik.schemablock.view.dialog.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebView;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;

/**
 * All rights reserved & copyright ©
 */
public class AboutProgramDialogController implements Initializable {

    private static final String PATH = "/de/tesis/dynaware/grapheditor/demo/dialog/about/";
    private static final String USED_LIBRARIES_FILENAME = "libraries.html";

    @FXML
    private WebView wvUsedLibraries;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        wvUsedLibraries.getEngine().loadContent(getUsedLibraries());
    }

    private String getUsedLibraries() {
        try {
            return loadInternalFileContent(PATH + USED_LIBRARIES_FILENAME);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    private String loadInternalFileContent(String path) throws IOException {
        return IOUtils.toString(getClass().getResourceAsStream(path), StandardCharsets.UTF_8.name());
    }
}
