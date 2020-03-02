package io.github.slupik.schemablock.javafx.element.fx.schema

import io.github.slupik.schemablock.javafx.dagger.LogicalSheet
import io.github.slupik.schemablock.javafx.element.Element
import io.github.slupik.schemablock.javafx.element.block.Block
import io.github.slupik.schemablock.javafx.element.fx.element.holder.BlocksHolder
import io.github.slupik.schemablock.javafx.element.fx.port.connection.PortConnectionConfiguration
import io.github.slupik.schemablock.javafx.element.fx.port.connection.establishment.ConnectionEstablisher
import io.github.slupik.schemablock.javafx.element.fx.port.connection.storage.ConnectionStorageKey
import io.github.slupik.schemablock.javafx.element.fx.port.connection.storage.PortConnectionsHolder
import io.github.slupik.schemablock.javafx.element.fx.port.connection.storage.TargetPort
import io.github.slupik.schemablock.javafx.element.fx.port.element.Port
import io.github.slupik.schemablock.javafx.element.fx.port.holder.PortAccessibility
import io.github.slupik.schemablock.javafx.element.fx.port.holder.PortsHolder
import io.github.slupik.schemablock.javafx.element.fx.sheet.Sheet
import javax.inject.Inject

/**
 * All rights reserved & copyright ©
 */
class VisibleSchema @Inject constructor(
    @LogicalSheet private val sheet: Sheet,
    private val blocksHolder: BlocksHolder,
    private val portsHolder: PortsHolder,
    private val connectionsHolder: PortConnectionsHolder,
    private val connectionEstablisher: ConnectionEstablisher
): Schema {

    override fun getElements(): List<Element> =
        sheet.getElements()

    override fun getBlocks(): List<Block> =
        blocksHolder.blocks

    override fun getPorts(): Map<Port, PortAccessibility> =
        portsHolder.ports

    override fun getConnections(): Map<ConnectionStorageKey, TargetPort> =
        connectionsHolder.connections

    override fun addBlock(block: Block) {
        blocksHolder.addBlock(block)
    }

    override fun addPort(port: Port, configuration: PortAccessibility) {
        portsHolder.addPort(port, configuration)
    }

    override fun establishConnection(configuration: PortConnectionConfiguration) {
        connectionEstablisher.establishConnection(configuration)
    }

    override fun clearAll() {
        blocksHolder.blocks.copy().forEach {
            blocksHolder.deleteBlock(it)
        }
        portsHolder.ports.keys.toList().copy().forEach {
            portsHolder.deletePort(it)
        }
    }

}

private fun <E> List<E>.copy(): List<E> =
    this.map { it }
