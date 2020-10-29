package io.github.slupik.schemablock.logic.executor.dagger;

import dagger.Module;
import dagger.Provides;
import io.github.slupik.schemablock.javafx.element.fx.element.holder.BlocksHolder;
import io.github.slupik.schemablock.javafx.element.fx.port.connection.ChainedElementProvider;

import javax.inject.Inject;

/**
 * All rights reserved & copyright ©
 */
@Module
public class BlockElementsModule {

    private final BlocksHolder holder;
    private final ChainedElementProvider chainedElementProvider;

    @Inject
    public BlockElementsModule(BlocksHolder holder, ChainedElementProvider chainedElementProvider) {
        this.holder = holder;
        this.chainedElementProvider = chainedElementProvider;
    }

    @Provides
    public BlocksHolder provideBlocksHolder() {
        return holder;
    }

    @Provides
    public ChainedElementProvider provideChainedElementProvider() {
        return chainedElementProvider;
    }

}
