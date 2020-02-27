package io.github.slupik.schemablock.javafx.element.fx.schema

import io.github.slupik.schemablock.javafx.element.Element
import io.github.slupik.schemablock.javafx.element.block.Block
import io.github.slupik.schemablock.javafx.element.fx.port.connection.PortConnectionConfiguration
import io.github.slupik.schemablock.javafx.element.fx.port.element.Port

/**
 * All rights reserved & copyright ©
 */
interface Schema {

    fun getElements(): List<Element>
    fun getBlocks(): List<Block>
    fun getPorts(): List<Port>
    fun getConnections(): List<PortConnectionConfiguration>

}