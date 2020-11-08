package io.github.slupik.schemablock.view.logic.execution.dagger;

import dagger.Module;
import dagger.Provides;
import io.github.slupik.schemablock.view.logic.communication.UIIOCommunicator;

import javax.inject.Inject;

/**
 * All rights reserved & copyright ©
 */
@Module
public class ExecutionElementsModule {

    //    private final Executor executor;
    private final UIIOCommunicator communicator;

    @Inject
    public ExecutionElementsModule(/*Executor executor, */UIIOCommunicator communicator) {
//        this.executor = executor;
        this.communicator = communicator;
    }

//    @Provides
//    public Executor provideExecutor() {
//        return executor;
//    }

    @Provides
    public UIIOCommunicator provideCommunicator() {
        return communicator;
    }

}
