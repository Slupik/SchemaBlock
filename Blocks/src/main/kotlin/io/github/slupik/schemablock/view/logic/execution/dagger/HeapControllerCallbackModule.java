package io.github.slupik.schemablock.view.logic.execution.dagger;

import dagger.Module;
import dagger.Provides;

/**
 * All rights reserved & copyright ©
 */
@Module
public class HeapControllerCallbackModule {

    private final Runnable callback;

    public HeapControllerCallbackModule(Runnable callback) {
        this.callback = callback;
    }

    @Provides
    @HeapSpy
    Runnable provideCallback(){
        return callback;
    }

}
