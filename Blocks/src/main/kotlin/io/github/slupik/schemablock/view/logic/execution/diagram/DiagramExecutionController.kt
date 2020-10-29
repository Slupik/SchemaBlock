package io.github.slupik.schemablock.view.logic.execution.diagram

/**
 * All rights reserved & copyright ©
 */
interface DiagramExecutionController {

    fun resumeExecutionOnDemand(execution: () -> Unit)

}