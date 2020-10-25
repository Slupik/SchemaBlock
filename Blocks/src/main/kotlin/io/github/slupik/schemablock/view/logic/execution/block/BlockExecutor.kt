package io.github.slupik.schemablock.view.logic.execution.block

import de.tesis.dynaware.grapheditor.core.skins.defaults.node.Block

/**
 * All rights reserved & copyright ©
 */
interface BlockExecutor {

    fun execute(block: Block): ExecutionResult

}