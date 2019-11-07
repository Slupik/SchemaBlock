package io.github.slupik.schemablock.javafx.element.fx.port.connection.storage

import io.github.slupik.schemablock.javafx.element.fx.port.connection.ChainedElementProvider
import io.github.slupik.schemablock.javafx.element.fx.port.element.Port
import javax.inject.Inject

/**
 * All rights reserved & copyright ©
 */

typealias TargetElementId = String

class PortsConnectionsStorage @Inject constructor() : PortsConnectionsModifier, ChainedElementProvider, PortsConnectionProvider {

    override val connections: MutableMap<ConnectionStorageKey, TargetPort> = mutableMapOf()

    override fun getNextElement(key: ConnectionStorageKey): TargetElementId? =
            connections[key]?.owner?.elementId

    override fun add(key: ConnectionStorageKey, target: Port) {
        connections[key] = target
    }

    override fun remove(key: ConnectionStorageKey) {
        connections.remove(key)
    }

}