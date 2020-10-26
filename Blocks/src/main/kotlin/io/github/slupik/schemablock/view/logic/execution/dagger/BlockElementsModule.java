package io.github.slupik.schemablock.view.logic.execution.dagger;

import dagger.Module;
import dagger.Provides;
import io.github.slupik.schemablock.view.logic.provider.BlocksProvider;
import io.github.slupik.schemablock.view.logic.provider.ChainedElementProvider;

import javax.inject.Inject;

/**
 * All rights reserved & copyright ©
 */
@Module
public class BlockElementsModule {

    private final BlocksProvider provider;
    private final ChainedElementProvider chainedElementProvider;

    @Inject
    public BlockElementsModule(BlocksProvider provider, ChainedElementProvider chainedElementProvider) {
        this.provider = provider;
        this.chainedElementProvider = chainedElementProvider;
    }

    @Provides
    public BlocksProvider provideBlocksHolder() {
        return provider;
    }

    @Provides
    public ChainedElementProvider provideChainedElementProvider() {
        return chainedElementProvider;
    }

}
