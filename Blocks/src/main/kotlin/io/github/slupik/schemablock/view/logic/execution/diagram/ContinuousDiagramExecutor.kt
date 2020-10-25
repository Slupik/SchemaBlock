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

    override fun run(): Observable<ExecutionEvent> =
        debug(ContinuousExecutionController())

    override fun debug(controller: DiagramExecutionController): Observable<ExecutionEvent> {
        val executor = createOneTimeExecutor()
        return executor.debug(controller)
    }

    private fun createOneTimeExecutor(): DiagramExecutor =
        executorProvider.get()

}