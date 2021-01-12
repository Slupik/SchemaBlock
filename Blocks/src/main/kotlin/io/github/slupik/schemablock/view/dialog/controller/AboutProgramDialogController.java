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
    private static final String ECLIPSE_LICENSE_FILENAME = "eclipse_license.html";

    @FXML
    private WebView wvUsedLibraries;

    @FXML
    private WebView wvEclipseLicense;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupWebView(wvUsedLibraries, USED_LIBRARIES_FILENAME);
        setupWebView(wvEclipseLicense, ECLIPSE_LICENSE_FILENAME);
    }

    private void setupWebView(WebView webView, String filename) {
        String content = loadInternalFileContent(PATH + filename);
        webView.getEngine().loadContent(content);
        enableOpeningLinksInSystemBrowser(webView);
    }

    private void enableOpeningLinksInSystemBrowser(WebView webView) {
        webView.getEngine().getLoadWorker().stateProperty().addListener(new HyperLinkRedirectListener(webView));
    }

    private String loadInternalFileContent(String path) {
        try {
            return IOUtils.toString(getClass().getResourceAsStream(path), StandardCharsets.UTF_8.name());
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
