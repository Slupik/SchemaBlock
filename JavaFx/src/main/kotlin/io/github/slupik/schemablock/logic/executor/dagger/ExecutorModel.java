package io.github.slupik.schemablock.logic.executor.dagger;

import dagger.Module;
import dagger.Provides;
import io.github.slupik.schemablock.logic.executor.diagram.AsyncDiagramExecutor;
import io.github.slupik.schemablock.logic.executor.diagram.DiagramExecutor;

/**
 * All rights reserved & copyright ©
 */
@Module(includes = {ExecutorBinding.class})
public class ExecutorModel {

    @Provides
    public DiagramExecutor provideDiagramExecutor(
            @Continuous DiagramExecutor continuous
    ) {
        return new AsyncDiagramExecutor(continuous);
    }

}
