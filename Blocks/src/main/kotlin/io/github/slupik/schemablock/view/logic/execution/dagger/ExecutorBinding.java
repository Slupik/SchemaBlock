package io.github.slupik.schemablock.view.logic.execution.dagger;

import dagger.Binds;
import dagger.Module;
import io.github.slupik.schemablock.model.ui.implementation.element.specific.IOCommunicable;
import io.github.slupik.schemablock.model.ui.newparser.HeapController;
import io.github.slupik.schemablock.newparser.compilator.Compilator;
import io.github.slupik.schemablock.newparser.compilator.implementation.DefaultCompilator;
import io.github.slupik.schemablock.newparser.executor.Executor;
import io.github.slupik.schemablock.newparser.executor.implementation.ExecutorImpl;
import io.github.slupik.schemablock.newparser.memory.Memory;
import io.github.slupik.schemablock.newparser.memory.MemoryImpl;
import io.github.slupik.schemablock.newparser.memory.Register;
import io.github.slupik.schemablock.newparser.memory.RegisterImpl;
import io.github.slupik.schemablock.view.logic.communication.UIIOCommunicator;
import io.github.slupik.schemablock.view.logic.execution.block.BlockExecutor;
import io.github.slupik.schemablock.view.logic.execution.block.StatelessBlockExecutor;
import io.github.slupik.schemablock.view.logic.execution.diagram.AsyncDiagramExecutor;
import io.github.slupik.schemablock.view.logic.execution.diagram.ContinuousDiagramExecutor;
import io.github.slupik.schemablock.view.logic.execution.diagram.DiagramExecutor;
import io.github.slupik.schemablock.view.logic.memory.NewHeapSpy;
import io.github.slupik.schemablock.view.logic.provider.BlocksProvider;
import io.github.slupik.schemablock.view.logic.provider.ChainedElementProvider;
import io.github.slupik.schemablock.view.logic.provider.DynawareBlocksProvider;
import io.github.slupik.schemablock.view.logic.provider.DynawareChainedElementProvider;

import javax.inject.Singleton;

/**
 * All rights reserved & copyright ©
 */
@Module(includes = {HeapControllerCallbackModule.class, ExecutionElementsModule.class})
public abstract class ExecutorBinding {

    @Binds
    @Singleton
    public abstract DiagramExecutor continuousDiagramExecutor(ContinuousDiagramExecutor executor);

    @OneTime
    @Binds
    public abstract DiagramExecutor oneTimeDiagramExecutor(AsyncDiagramExecutor executor);

    @Binds
    @Singleton
    public abstract BlockExecutor blockExecutor(StatelessBlockExecutor executor);

    @Binds
    @Singleton
    public abstract Register register(RegisterImpl register);

    @Binds
    @Singleton
    @AtomicMemory
    public abstract Memory memory(MemoryImpl memory);

    @Binds
    @Singleton
    public abstract Memory spyAsMemory(NewHeapSpy memory);

    @Binds
    @Singleton
    public abstract Compilator compilator(DefaultCompilator compilator);

    @Binds
    @Singleton
    public abstract IOCommunicable communicator(UIIOCommunicator communicator);

    @Binds
    @Singleton
    public abstract Executor provideExecutor(ExecutorImpl holder);

    @Binds
    @Singleton
    public abstract HeapController provideHeapSpy(NewHeapSpy spy);

    @Binds
    @Singleton
    public abstract BlocksProvider provideBlockProvider(DynawareBlocksProvider provider);

    @Binds
    @Singleton
    public abstract ChainedElementProvider provideChainedElementProvider(DynawareChainedElementProvider provider);

}
