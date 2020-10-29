package io.github.slupik.schemablock.javafx.view;

import io.github.slupik.schemablock.javafx.element.fx.schema.Schema;
import io.github.slupik.schemablock.javafx.element.fx.schema.restorer.SchemaRestorer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;

import static io.github.slupik.schemablock.javafx.logic.persistence.ProgramFile.FILE_EXTENSION;

/**
 * All rights reserved & copyright ©
 */
public class SchemaLoader {

    private final Stage stage;

    public SchemaLoader(Stage stage) {
        this.stage = stage;
    }

    public File getFileToLoad() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Pliki programu", "*."+FILE_EXTENSION)
        );
        return fileChooser.showOpenDialog(stage);
    }

    //TODO this function doesn't need so many parameters
    public void loadFile(Schema schema, SchemaRestorer sheet, File file) {
        String content = getContent(file);
        sheet.load(schema, content);
    }

    private String getContent(File file) {
        try {
            List<String> lines = Files.readAllLines(file.toPath(), Charset.forName("UTF-8"));
            StringBuilder content = new StringBuilder();
            for(int i=0;i<lines.size();i++) {
                if(i>0) {
                    content.append('\n').append(lines.get(i));
                } else {
                    content.append(lines.get(i));
                }
            }
            return content.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
