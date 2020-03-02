package io.github.slupik.schemablock.javafx.element.block.contextmenu

import io.github.slupik.schemablock.javafx.element.UiElementType
import io.github.slupik.schemablock.javafx.element.block.Block
import io.github.slupik.schemablock.javafx.element.block.wrapper.edition.BlockEdition
import io.github.slupik.schemablock.javafx.element.fx.element.holder.BlocksHolder
import io.github.slupik.schemablock.javafx.element.fx.port.connection.storage.PortConnectionsHolder
import io.github.slupik.schemablock.javafx.element.fx.port.holder.PortsHolder
import javafx.scene.control.ContextMenu
import javafx.scene.control.MenuItem
import javax.inject.Inject

/**
 * All rights reserved & copyright ©
 */
class DefaultContextMenuProvider @Inject constructor(
    private val blocksHolder: BlocksHolder,
    private val portsHolder: PortsHolder,
    private val connectionsHolder: PortConnectionsHolder,
    private val blockEdition: BlockEdition
) : BlockContextMenuProvider {

    override fun getFor(block: Block): ContextMenu {
        val contextMenu = ContextMenu()
        if(UiElementType.START != block.type && UiElementType.STOP != block.type) {
            contextMenu.items.addAll(getBlockEditionItem(block))
        }
        if(UiElementType.START != block.type) {
            contextMenu.items.add(getDeletionItem(block))
        }
        contextMenu.items.addAll(getClearIncomingConnectionsItem(block))
        contextMenu.items.addAll(getClearOutgoingConnectionsItem(block))
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

    private fun getClearIncomingConnectionsItem(block: Block): MenuItem {
        val clearIncomingItem = MenuItem("Clear incoming")
        val clearIncomingLogic = ClearIncomingConnectionsOption(block, connectionsHolder)
        clearIncomingItem.setOnAction {
            clearIncomingLogic.invokeAction()
        }
        return clearIncomingItem
    }

    private fun getClearOutgoingConnectionsItem(block: Block): MenuItem {
        val clearOutgoingItem = MenuItem("Clear outgoing")
        val clearOutgoingLogic = ClearOutgoingConnectionsOption(block, portsHolder, connectionsHolder)
        clearOutgoingItem.setOnAction {
            clearOutgoingLogic.invokeAction()
        }
        return clearOutgoingItem
    }

    private fun getBlockEditionItem(block: Block): MenuItem {
        val blockEditionItem = MenuItem("Edit")
        val blockEditionLogic = BlockEditionOption(block, blockEdition)
        blockEditionItem.setOnAction {
            blockEditionLogic.invokeAction()
        }
        return blockEditionItem
    }

}