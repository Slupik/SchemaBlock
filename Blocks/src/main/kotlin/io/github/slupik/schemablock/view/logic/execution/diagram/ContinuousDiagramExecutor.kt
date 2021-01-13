package io.github.slupik.schemablock.view.logic.execution.diagram

import io.github.slupik.schemablock.view.logic.execution.dagger.OneTime
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject
import javax.inject.Provider

/**
 * All rights reserved & copyright ©
 */
class ContinuousDiagramExecutor @Inject constructor(
    @OneTime private val executorProvider: Provider<DiagramExecutor>
) : DiagramExecutor {

    private var publisher = PublishSubject.create<ExecutionEvent>()
    override val eventSource: Observable<ExecutionEvent>
        get() = publisher
    private var executionController = ContinuousExecutionController()

    override fun resetState() {
        publisher = PublishSubject.create<ExecutionEvent>()
    }

    override fun run() {
        executionController = ContinuousExecutionController()
        debug(executionController)
    }

    override fun debug(controller: DiagramExecutionController) {
        val executor = createOneTimeExecutor()
        executor.eventSource.subscribe(publisher)
        executor.debug(controller)
    }

    override fun stop() {
        executionController.stop()
    }

    private fun createOneTimeExecutor(): DiagramExecutor =
        executorProvider.get()

}