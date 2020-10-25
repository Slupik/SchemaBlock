package io.github.slupik.schemablock.view.logic.provider;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * All rights reserved & copyright ©
 */
public class DynawareChainedElementProvider implements ChainedElementProvider {

    @Nullable
    @Override
    public String getStartElementId() {
        return null;
    }

    @Nullable
    @Override
    public String getNextElement(@NotNull String sourceBlockId) {
        return null;
    }

    @Nullable
    @Override
    public String getNextElement(@NotNull String sourceBlockId, boolean result) {
        return null;
    }
}
