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
    private static final String ECLIPSE1_LICENSE_FILENAME = "eclipse1_license.html";
    private static final String ECLIPSE2_LICENSE_FILENAME = "eclipse2_license.html";
    private static final String APACHE_LICENSE_FILENAME = "apache_license.html";
    private static final String OPENFX_LICENSE_FILENAME = "openfx_license.html";
    private static final String MIT_LICENSE_FILENAME = "mit_license.html";
    private static final String BSD3_LICENSE_FILENAME = "bsd3_license.html";

    @FXML
    private WebView wvUsedLibraries;
    @FXML
    private WebView wvEclipse1License;
    @FXML
    private WebView wvEclipse2License;
    @FXML
    private WebView wvApacheLicense;
    @FXML
    private WebView wvOpenFxLicense;
    @FXML
    private WebView wvMitLicense;
    @FXML
    private WebView wvBsd3License;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupWebView(wvUsedLibraries, USED_LIBRARIES_FILENAME);
        setupWebView(wvEclipse1License, ECLIPSE1_LICENSE_FILENAME);
        setupWebView(wvEclipse2License, ECLIPSE2_LICENSE_FILENAME);
        setupWebView(wvApacheLicense, APACHE_LICENSE_FILENAME);
        setupWebView(wvOpenFxLicense, OPENFX_LICENSE_FILENAME);
        setupWebView(wvMitLicense, MIT_LICENSE_FILENAME);
        setupWebView(wvBsd3License, BSD3_LICENSE_FILENAME);
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
