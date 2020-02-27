package io.github.slupik.schemablock.javafx.element.fx.schema

import io.github.slupik.schemablock.javafx.element.Element
import io.github.slupik.schemablock.javafx.element.block.Block
import io.github.slupik.schemablock.javafx.element.fx.port.connection.storage.ConnectionStorageKey
import io.github.slupik.schemablock.javafx.element.fx.port.connection.storage.TargetPort
import io.github.slupik.schemablock.javafx.element.fx.port.element.Port
import io.github.slupik.schemablock.javafx.element.fx.port.holder.PortAccessibility

/**
 * All rights reserved & copyright ©
 */
interface Schema {

    fun getElements(): List<Element>
    fun getBlocks(): List<Block>
    fun getPorts(): Map<Port, PortAccessibility>
    fun getConnections(): Map<ConnectionStorageKey, TargetPort>

}