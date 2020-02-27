package io.github.slupik.schemablock.javafx.element.fx.schema

import io.github.slupik.schemablock.javafx.element.Element
import io.github.slupik.schemablock.javafx.element.block.Block
import io.github.slupik.schemablock.javafx.element.fx.port.connection.PortConnectionConfiguration
import io.github.slupik.schemablock.javafx.element.fx.port.connection.storage.PortsConnectionProvider
import io.github.slupik.schemablock.javafx.element.fx.port.element.Port
import io.github.slupik.schemablock.javafx.element.fx.port.holder.PortsHolder
import io.github.slupik.schemablock.javafx.element.fx.sheet.Sheet
import javax.inject.Inject

/**
 * All rights reserved & copyright ©
 */
class VisibleSchema @Inject constructor(
    private val sheet: Sheet,
    private val portsHolder: PortsHolder,
    private val portsConnectionProvider: PortsConnectionProvider
): Schema {

    override fun getElements(): List<Element> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getBlocks(): List<Block> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPorts(): List<Port> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getConnections(): List<PortConnectionConfiguration> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}