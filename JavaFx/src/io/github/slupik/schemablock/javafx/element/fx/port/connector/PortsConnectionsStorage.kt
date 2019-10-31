package io.github.slupik.schemablock.javafx.element.fx.port.connector

/**
 * All rights reserved & copyright ©
 */
typealias TargetElementId = String

class PortsConnectionsStorage: PortsConnectionsModifier, PortsConnectionsProvider {

    val connections: MutableMap<ConnectionStorageKey, TargetElementId> = mutableMapOf()

    override fun add(configuration: PortConnectionConfiguration) {
        val key =
        when(configuration) {
            is StandardPortsConnection -> {
                StandardConnection(
                        configuration.source.parentElementId
                )
            }
            is ConditionalPortsConnection -> {
                ConditionalConnection(
                        configuration.source.parentElementId,
                        configuration.value
                )
            }
        }
        connections[key] = configuration.targetBlockId
    }

    override fun remove(configuration: PortDisconnectionConfiguration) {
        val key =
        when(configuration) {
            is StandardDisconnection -> {
                StandardConnection(
                        configuration.source.parentElementId
                )
            }
            is ConditionalPortsDisconnection -> {
                ConditionalConnection(
                        configuration.source.parentElementId,
                        configuration.value
                )
            }
        }
        connections.remove(key)
    }

    override fun getNextElement(key: ConnectionStorageKey): TargetElementId? =
            connections[key]

}