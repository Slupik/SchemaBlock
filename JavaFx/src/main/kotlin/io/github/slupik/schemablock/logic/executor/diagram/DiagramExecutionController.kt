package io.github.slupik.schemablock.logic.executor.diagram

/**
 * All rights reserved & copyright ©
 */
interface DiagramExecutionController {

    fun resumeExecutionOnDemand(execution: () -> Unit)

}