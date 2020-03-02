package io.github.slupik.schemablock.javafx.dagger;

import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
import io.github.slupik.schemablock.javafx.element.fx.port.connection.deleter.PortConnectionsDeleter;
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
    SheetPortsHolder providerHolder(@LogicalSheet Sheet sheet, Lazy<PortConnectionsDeleter> portConnectionsDeleter) {
        return new SheetPortsHolder(sheet, portConnectionsDeleter);
    }

    @Provides
    @LogicalSheet
    @Singleton
    Sheet provideSheet(@JavaFxSheet Pane elementsContainer) {
        return new VisibleSheet(elementsContainer);
    }

}
