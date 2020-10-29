package io.github.slupik.schemablock.javafx.element.block.contextmenu

import io.github.slupik.schemablock.javafx.element.block.Block
import io.github.slupik.schemablock.javafx.element.block.wrapper.edition.BlockEdition

/**
 * All rights reserved & copyright ©
 */
class BlockEditionOption(
    private val block: Block,
    private val blockEdition: BlockEdition
): MenuOption {

    override fun getType(): MenuOptionType =
        MenuOptionType.EDIT

    override fun invokeAction() {
        blockEdition.openFor(block)
    }

}