package io.github.slupik.schemablock.logic.executor.diagram

/**
 * All rights reserved & copyright ©
 */
class ContinuousExecutionController : DiagramExecutionController {

    override fun resumeExecutionOnDemand(execution: () -> Unit) {
        execution.invoke()
    }

}