package io.github.slupik.schemablock.view.logic.execution.diagram

import io.reactivex.Observable


/**
 * All rights reserved & copyright ©
 */
interface DiagramExecutor {

    val eventSource: Observable<ExecutionEvent>

    fun resetState()
    fun run()
    fun debug(controller: DiagramExecutionController)
    fun stop()

}