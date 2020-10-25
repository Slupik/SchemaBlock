package io.github.slupik.schemablock.view.logic.provider

import de.tesis.dynaware.grapheditor.core.skins.defaults.node.Block

/**
 * All rights reserved & copyright ©
 */
interface BlocksProvider {

    val blocks: List<Block>

    fun getBlock(elementId: String): Block? =
        blocks.firstOrNull { block -> elementId == block.node.id }

}