package io.github.slupik.schemablock.view.logic.execution.diagram

/**
 * All rights reserved & copyright ©
 */
class ContinuousExecutionController : DiagramExecutionController {

    override fun resumeExecutionOnDemand(execution: () -> Unit) {
        execution.invoke()
    }

}