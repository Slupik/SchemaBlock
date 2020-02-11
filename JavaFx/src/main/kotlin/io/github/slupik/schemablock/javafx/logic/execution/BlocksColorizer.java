package io.github.slupik.schemablock.javafx.logic.execution;

import io.github.slupik.schemablock.logic.executor.diagram.ExecutionEvent;
import io.reactivex.Observable;
import org.jetbrains.annotations.NotNull;

/**
 * All rights reserved & copyright ©
 */
public interface BlocksColorizer {

    void inject(@NotNull Observable<ExecutionEvent> eventsEmitter);

}
