package io.github.slupik.schemablock.logic.executor.dagger;

import dagger.Module;
import dagger.Provides;
import io.github.slupik.schemablock.logic.executor.diagram.ExecutionEvent;
import io.reactivex.subjects.PublishSubject;

import javax.inject.Inject;

/**
 * All rights reserved & copyright ©
 */
@Module
public class DiagramExecutorElementsModule {

    private final PublishSubject<ExecutionEvent> eventPublisher;

    @Inject
    public DiagramExecutorElementsModule(PublishSubject<ExecutionEvent> eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Provides
    public PublishSubject<ExecutionEvent> provideEventPublisher() {
        return eventPublisher;
    }

}
