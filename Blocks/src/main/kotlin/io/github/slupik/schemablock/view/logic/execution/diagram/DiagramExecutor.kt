package io.github.slupik.schemablock.view.logic.execution.diagram

import io.reactivex.Observable


/**
 * All rights reserved & copyright ©
 */
interface DiagramExecutor {

    fun run(): Observable<ExecutionEvent>

    fun debug(controller: DiagramExecutionController): Observable<ExecutionEvent>

}