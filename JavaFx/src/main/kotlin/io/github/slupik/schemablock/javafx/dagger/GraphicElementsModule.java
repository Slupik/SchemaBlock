package io.github.slupik.schemablock.javafx.dagger;

import dagger.Module;
import dagger.Provides;
import io.github.slupik.schemablock.javafx.element.fx.element.holder.BlocksHolder;
import io.github.slupik.schemablock.javafx.element.fx.port.spawner.PortSpawner;
import io.github.slupik.schemablock.javafx.element.fx.sheet.BasicFeaturedSheet;
import io.github.slupik.schemablock.javafx.element.fx.sheet.ElementsSyncingSheet;
import io.github.slupik.schemablock.javafx.element.fx.sheet.Sheet;
import io.github.slupik.schemablock.javafx.element.fx.sheet.VisibleSheet;
import javafx.scene.layout.Pane;

import javax.inject.Singleton;

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
    @JavaFxSheet
    Pane provideJavaFxSheet() {
        return sheet;
    }

    @Provides
    @Singleton
    Sheet provideSheet(BlocksHolder blocksHolder, PortSpawner portSpawner, @JavaFxSheet Pane elementsContainer) {
        return new BasicFeaturedSheet(
                elementsContainer,
                portSpawner,
                new ElementsSyncingSheet(
                        new VisibleSheet(elementsContainer),
                        blocksHolder
                )
        );
    }

}
