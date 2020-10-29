package io.github.slupik.schemablock.view.logic.execution.diagram

/**
 * All rights reserved & copyright ©
 */
class ContinuousExecutionController : DiagramExecutionController {

    private var stop = false;

    override fun resumeExecutionOnDemand(execution: () -> Unit) {
        if (!stop) {
            execution.invoke()
        } else {
            stop = false
        }
    }

    fun stop() {
        stop = true
    }

}