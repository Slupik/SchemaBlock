package io.github.slupik.schemablock.javafx.element.fx.communication;

import io.github.slupik.schemablock.model.ui.implementation.element.specific.IOCommunicable;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.util.concurrent.CountDownLatch;

/**
 * All rights reserved & copyright ©
 */
public class UIIOCommunicator implements IOCommunicable {

    private final CountDownLatch countDownLatch = new CountDownLatch(1);
    private final TextField input;
    private final WebView output;

    private String outputData = "";

    private String text = null;
    private boolean stop = false;

    public UIIOCommunicator(TextField input, WebView output, Button btnSend) {
        this.input = input;
        this.output = output;

        btnSend.setOnAction(event -> flushInputControl());
        input.setOnKeyReleased(event -> {
            if(event.getCode() == KeyCode.ENTER) {
                flushInputControl();
            }
        });
    }

    private void flushInputControl() {
        text = input.getText();
        printOnUiThread("<font color=\"#28B463\">"+text+"</font>"+'\n');
        input.setText("");
    }

    @Override
    public String getInput() {
        while (text == null && !stop) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        stop = false;
        String temp = text;
        text = null;
        return temp;
    }

    @Override
    public void print(String value) {
        printOnUiThread(value, countDownLatch::countDown);
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clear() {
        outputData = "";
        printOnUiThread("");
        text = null;
    }

    @Override
    public void printAlgorithmError(String text) {
        printOnUiThread("<font color=\"#FF0000\">[ERROR] "+text+"</font>"+'\n');
    }

    @Override
    public void printProgramError(String text) {
        printOnUiThread("<font color=\"#FF0000\"><b>[INTERNAL ERROR] "+text+"</b></font>"+'\n');
    }

    @Override
    public void stop() {
        stop = true;
    }

    private void printOnUiThread(String value) {
        printOnUiThread(value, ()->{});
    }

    private void printOnUiThread(String value, Runnable callback) {
        value = value.replace("\n", "</br>");
        outputData += value;
        Platform.runLater(()->{
            output.getEngine().loadContent(outputData, "text/html");

            WebEngine webEngine = output.getEngine();
            String html = "<html>" +
                    "<head>" +
                    "   <script language=\"javascript\" type=\"text/javascript\">" +
                    "       function toBottom(){" +
                    "           window.scrollTo(0, document.body.scrollHeight);" +
                    "       }" +
                    "   </script>" +
                    "</head>" +
                    "<body onload='toBottom()'>" +
                        outputData +
                    "</body></html>";
            webEngine.loadContent(html, "text/html");

            callback.run();
        });
    }
}
