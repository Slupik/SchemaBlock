package io.github.slupik.schemablock.javafx.element.fx.port.connection.deleter

import io.github.slupik.schemablock.javafx.element.fx.port.connection.BlockClearanceConfiguration
import io.github.slupik.schemablock.javafx.element.fx.port.connection.PortDisconnectionConfiguration

/**
 * All rights reserved & copyright ©
 */

interface ConnectionDeleter {

    fun deleteConnection(configuration: PortDisconnectionConfiguration)
    fun clearConnections(configuration: BlockClearanceConfiguration)
    fun clearConnections(blockId: String)

}