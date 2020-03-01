package io.github.slupik.schemablock.logic.executor.block

import io.github.slupik.schemablock.javafx.element.block.Block

/**
 * All rights reserved & copyright ©
 */
interface BlockExecutor {

    fun execute(block: Block): ExecutionResult

}