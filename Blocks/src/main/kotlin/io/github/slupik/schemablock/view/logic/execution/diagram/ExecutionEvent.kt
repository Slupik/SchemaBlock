package io.github.slupik.schemablock.view.logic.execution.diagram

import de.tesis.dynaware.grapheditor.core.skins.defaults.node.Block
import io.github.slupik.schemablock.view.logic.execution.block.ExecutionResult

/**
 * All rights reserved & copyright ©
 */
sealed class ExecutionEvent

object ExecutionStart: ExecutionEvent()

data class ErrorEvent(
    val error: Throwable
) : ExecutionEvent()

data class PreExecutionEvent(
    val executingBlock: Block
) : ExecutionEvent()

data class PostExecutionEvent(
    val executedBlock: Block,
    val result: ExecutionResult
) : ExecutionEvent()

object ExecutionEnd: ExecutionEvent()