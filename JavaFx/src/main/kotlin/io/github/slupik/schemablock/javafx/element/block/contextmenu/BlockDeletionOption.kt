package io.github.slupik.schemablock.javafx.element.block.contextmenu

import io.github.slupik.schemablock.javafx.element.block.Block
import io.github.slupik.schemablock.javafx.element.fx.element.holder.BlocksHolder

/**
 * All rights reserved & copyright ©
 */
class BlockDeletionOption constructor(
    private val block: Block,
    private val blocksHolder: BlocksHolder
): MenuOption {

    override fun getType(): MenuOptionType =
        MenuOptionType.DELETE

    override fun invokeAction() {
        blocksHolder.deleteBlock(block)
    }

}