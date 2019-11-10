package io.github.slupik.schemablock.javafx.dagger;

import dagger.Module;
import dagger.Provides;
import javafx.scene.layout.Pane;

/**
 * All rights reserved & copyright ©
 */
@Module
public class GraphicElementsModule {

    private final Pane sheet;

    public GraphicElementsModule(Pane sheet) {
        this.sheet = sheet;
    }

    @Provides
    @Sheet
    Pane provideSheet(){
        return sheet;
    }

}
