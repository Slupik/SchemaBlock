package io.github.slupik.schemablock.javafx.element.fx.port.connection.storage

import io.github.slupik.schemablock.javafx.element.fx.port.element.Port

/**
 * All rights reserved & copyright ©
 */

interface PortsConnectionsModifier {

    fun add(key: ConnectionStorageKey, target: Port)
    fun remove(key: ConnectionStorageKey)

}