package io.github.slupik.schemablock.javafx.element.fx.port.connection.stringifier

import io.github.slupik.schemablock.javafx.element.fx.port.connection.PortConnectionConfiguration
import io.github.slupik.schemablock.javafx.element.fx.port.connection.storage.ConnectionStorageKey
import io.github.slupik.schemablock.javafx.element.fx.port.connection.storage.TargetPort

/**
 * All rights reserved & copyright ©
 */
interface PortConnectionStringifier {

    fun stringify(connection: PortConnectionConfiguration): String

    fun stringify(key: ConnectionStorageKey, target: TargetPort): String

}