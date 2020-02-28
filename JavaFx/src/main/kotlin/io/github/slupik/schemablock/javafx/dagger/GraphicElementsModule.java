package io.github.slupik.schemablock.javafx.dagger;

import dagger.Module;
import dagger.Provides;
import io.github.slupik.schemablock.javafx.element.fx.element.holder.BlocksHolder;
import io.github.slupik.schemablock.javafx.element.fx.port.holder.PortsHolder;
import io.github.slupik.schemablock.javafx.element.fx.sheet.*;
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
    Pane provideJavaFxSheet(){
        return sheet;
    }

    @Provides
    @Singleton
    Sheet provideSheet(PortsHolder portsHolder, BlocksHolder blocksHolder, @JavaFxSheet Pane elementsContainer) {
        return new BasicFeaturedSheet(
                elementsContainer,
                new PortsAddingSheet(
                        elementsContainer,
                        portsHolder,
                        new ElementsSyncingSheet(
                                new VisibleSheet(elementsContainer),
                                blocksHolder
                        )
                )
        );
    }

}
