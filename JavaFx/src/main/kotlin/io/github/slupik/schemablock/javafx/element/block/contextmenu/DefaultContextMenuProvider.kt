package io.github.slupik.schemablock.javafx.element.block.contextmenu

import io.github.slupik.schemablock.javafx.element.block.Block
import io.github.slupik.schemablock.javafx.element.fx.element.holder.BlocksHolder
import javafx.scene.control.ContextMenu
import javafx.scene.control.MenuItem
import javax.inject.Inject

/**
 * All rights reserved & copyright ©
 */
class DefaultContextMenuProvider @Inject constructor(
    private val blocksHolder: BlocksHolder
) : BlockContextMenuProvider {

    override fun getFor(block: Block): ContextMenu {
        val contextMenu = ContextMenu()
        contextMenu.items.add(getDeletionItem(block))
        return contextMenu
    }

    private fun getDeletionItem(block: Block): MenuItem {
        val deletionItem = MenuItem("Delete")
        val deletionLogic = BlockDeletionOption(block, blocksHolder)
        deletionItem.setOnAction {
            deletionLogic.invokeAction()
        }
        return deletionItem
    }

}