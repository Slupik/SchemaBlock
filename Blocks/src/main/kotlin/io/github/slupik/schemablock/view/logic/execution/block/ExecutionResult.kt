package io.github.slupik.schemablock.view.logic.execution.block

/**
 * All rights reserved & copyright ©
 */
sealed class ExecutionResult

object Void: ExecutionResult()

data class Logic(
    val value: Boolean
): ExecutionResult()