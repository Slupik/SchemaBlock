package io.github.slupik.schemablock.view.logic.execution.diagram

import io.github.slupik.schemablock.view.logic.execution.dagger.OneTime
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Provider

/**
 * All rights reserved & copyright ©
 */
class ContinuousDiagramExecutor @Inject constructor(
    @OneTime private val executorProvider: Provider<DiagramExecutor>
) : DiagramExecutor {

    private val executionController = ContinuousExecutionController()

    override fun run(): Observable<ExecutionEvent> =
        debug(executionController)

    override fun debug(controller: DiagramExecutionController): Observable<ExecutionEvent> {
        val executor = createOneTimeExecutor()
        return executor.debug(controller)
    }

    override fun stop() {
        executionController.stop();
    }

    private fun createOneTimeExecutor(): DiagramExecutor =
        executorProvider.get()

}