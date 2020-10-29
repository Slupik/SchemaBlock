package io.github.slupik.schemablock.logic.executor.block

/**
 * All rights reserved & copyright ©
 */
sealed class ExecutionResult

object Void: ExecutionResult()

data class Logic(
    val value: Boolean
): ExecutionResult()