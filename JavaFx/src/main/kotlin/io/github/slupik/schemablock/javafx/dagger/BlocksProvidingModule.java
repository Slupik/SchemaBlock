package io.github.slupik.schemablock.javafx.dagger;

import dagger.Module;
import dagger.Provides;
import io.github.slupik.schemablock.javafx.element.fx.port.holder.SheetPortsHolder;

import javax.inject.Singleton;

/**
 * All rights reserved & copyright ©
 */
@Module
public class BlocksProvidingModule {

    @Provides
    @Singleton
    SheetPortsHolder providerHolder(){
        return new SheetPortsHolder();
    }

}
