package io.github.slupik.schemablock.logic.executor.diagram

import io.github.slupik.schemablock.logic.executor.dagger.Continuous
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.PublishSubject

/**
 * All rights reserved & copyright ©
 */
class AsyncDiagramExecutor constructor(
    @Continuous private val executor: DiagramExecutor
) : DiagramExecutor {

    override fun run(): Observable<ExecutionEvent> {
        val publisher = PublishSubject.create<ExecutionEvent>()
        Thread {
            val observable = executor.run()
            observable.makeAsSourceFor(publisher)
        }.run()
        return publisher
    }

    override fun debug(controller: DiagramExecutionController): Observable<ExecutionEvent> {
        val publisher = PublishSubject.create<ExecutionEvent>()
        Thread {
            val observable = executor.debug(controller)
            observable.makeAsSourceFor(publisher)
        }.run()
        return publisher
    }

    private fun Observable<ExecutionEvent>.makeAsSourceFor(publisher: PublishSubject<ExecutionEvent>) {
        this.subscribeBy(
            onError = {
                publisher.onError(it)
            },
            onComplete = {
                publisher.onComplete()
            },
            onNext = {
                publisher.onNext(it)
            }
        )
    }


}