package io.github.slupik.schemablock.logic.executor.dagger;

import dagger.Binds;
import dagger.Module;
import io.github.slupik.schemablock.javafx.dagger.ExecutionElementsModule;
import io.github.slupik.schemablock.javafx.dagger.HeapControllerCallbackModule;
import io.github.slupik.schemablock.javafx.element.fx.communication.UIIOCommunicator;
import io.github.slupik.schemablock.javafx.logic.heap.NewHeapSpy;
import io.github.slupik.schemablock.logic.executor.block.BlockExecutor;
import io.github.slupik.schemablock.logic.executor.block.StatelessBlockExecutor;
import io.github.slupik.schemablock.logic.executor.diagram.ContinuousDiagramExecutor;
import io.github.slupik.schemablock.logic.executor.diagram.DiagramExecutor;
import io.github.slupik.schemablock.logic.executor.diagram.OneTimeDiagramExecutor;
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

import javax.inject.Singleton;

/**
 * All rights reserved & copyright ©
 */
@Module(includes = {HeapControllerCallbackModule.class, ExecutionElementsModule.class})
public abstract class ExecutorBinding {

    @OneTime
    @Binds
    public abstract DiagramExecutor oneTimeDiagramExecutor(OneTimeDiagramExecutor executor);

//    @Async
//    @Binds
//    public abstract DiagramExecutor asyncDiagramExecutor(AsyncDiagramExecutor executor);

    @Continuous
    @Binds
    @Singleton
    public abstract DiagramExecutor continuousDiagramExecutor(ContinuousDiagramExecutor executor);

    @Binds
    @Singleton
    public abstract BlockExecutor blockExecutor(StatelessBlockExecutor executor);

    @Binds
    @Singleton
    public abstract Register register(RegisterImpl register);

    @Binds
    @Singleton
    public abstract Memory memory(MemoryImpl memory);

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

}
