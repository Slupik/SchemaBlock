package io.github.slupik.schemablock.javafx.element.fx.schema

import io.github.slupik.schemablock.javafx.dagger.LogicalSheet
import io.github.slupik.schemablock.javafx.element.Element
import io.github.slupik.schemablock.javafx.element.block.Block
import io.github.slupik.schemablock.javafx.element.fx.element.holder.BlocksHolder
import io.github.slupik.schemablock.javafx.element.fx.port.connection.PortConnectionConfiguration
import io.github.slupik.schemablock.javafx.element.fx.port.connection.storage.ConnectionStorageKey
import io.github.slupik.schemablock.javafx.element.fx.port.connection.storage.PortsConnectionProvider
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
    private val portsConnectionProvider: PortsConnectionProvider
): Schema {

    override fun getElements(): List<Element> =
        sheet.getElements()

    override fun getBlocks(): List<Block> =
        blocksHolder.blocks

    override fun getPorts(): Map<Port, PortAccessibility> =
        portsHolder.ports

    override fun getConnections(): Map<ConnectionStorageKey, TargetPort> =
        portsConnectionProvider.connections

    override fun addBlock(block: Block) {
        sheet.addElement(block)
    }

    override fun addPort(port: Port) {
        sheet.addElement(port)
    }

    override fun establishConnection(configuration: PortConnectionConfiguration) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clearAll() {
        blocksHolder.blocks.copy().forEach {
            sheet.removeElement(it)
        }
    }

}

private fun <E> List<E>.copy(): List<E> =
    this.map { it }
