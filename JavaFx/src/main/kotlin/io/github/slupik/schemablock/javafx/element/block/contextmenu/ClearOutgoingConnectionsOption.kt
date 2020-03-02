package io.github.slupik.schemablock.javafx.element.block.contextmenu

import io.github.slupik.schemablock.javafx.element.block.Block
import io.github.slupik.schemablock.javafx.element.fx.port.connection.storage.PortConnectionsHolder
import io.github.slupik.schemablock.javafx.element.fx.port.holder.PortsHolder

/**
 * All rights reserved & copyright ©
 */
class ClearOutgoingConnectionsOption constructor(
    private val block: Block,
    private val portsHolder: PortsHolder,
    private val connectionsHolder: PortConnectionsHolder
): MenuOption {

    override fun getType(): MenuOptionType =
        MenuOptionType.CLEAR_OUTGOING

    override fun invokeAction() {
        val portsOfBlock =
            portsHolder.ports.keys.filter {
                it.owner.elementId == block.elementId
            }

        connectionsHolder.connections.filter { connection ->
            portsOfBlock.any { port ->
                port.elementId == connection.key.sourcePortId
            }
        }.forEach {
            connectionsHolder.remove(it.key)
        }
    }

}
