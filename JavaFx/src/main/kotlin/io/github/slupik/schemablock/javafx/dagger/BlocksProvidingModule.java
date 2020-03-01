package io.github.slupik.schemablock.javafx.dagger;

import dagger.Module;
import dagger.Provides;
import io.github.slupik.schemablock.javafx.element.fx.port.holder.SheetPortsHolder;
import io.github.slupik.schemablock.javafx.element.fx.sheet.Sheet;
import io.github.slupik.schemablock.javafx.element.fx.sheet.VisibleSheet;
import javafx.scene.layout.Pane;

import javax.inject.Singleton;

/**
 * All rights reserved & copyright ©
 */
@Module
public class BlocksProvidingModule {

    @Provides
    @Singleton
    SheetPortsHolder providerHolder(@LogicalSheet Sheet sheet) {
        return new SheetPortsHolder(sheet);
    }

    @Provides
    @LogicalSheet
    @Singleton
    Sheet provideSheet(@JavaFxSheet Pane elementsContainer) {
        return new VisibleSheet(elementsContainer);
    }

}
