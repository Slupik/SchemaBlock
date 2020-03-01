package io.github.slupik.schemablock.logic.executor.diagram

import io.github.slupik.schemablock.javafx.element.block.Block
import io.github.slupik.schemablock.logic.executor.block.ExecutionResult

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