package io.github.slupik.schemablock;

import de.tesis.dynaware.grapheditor.demo.GraphEditorDemo;

public class Main {

    private static final boolean NEW_UI = true;

    public static void main(String[] args) {
        if (NEW_UI) {
            GraphEditorDemo.launchApp(args);
        } else {
            ApplicationLauncher.launchApp(args);
        }
    }

}
