package io.github.slupik.schemablock.javafx.element.block.contextmenu

import io.github.slupik.schemablock.javafx.element.block.Block
import io.github.slupik.schemablock.javafx.element.fx.port.connection.storage.PortConnectionsHolder

/**
 * All rights reserved & copyright ©
 */
class ClearIncomingConnectionsOption constructor(
    private val block: Block,
    private val connectionsHolder: PortConnectionsHolder
): MenuOption {

    override fun getType(): MenuOptionType =
        MenuOptionType.CLEAR_INCOMING

    override fun invokeAction() {
        connectionsHolder.connections.filter {
            it.value.owner.elementId == block.elementId
        }.forEach {
            connectionsHolder.remove(it.key)
        }
    }

}