package io.github.slupik.schemablock.javafx.element.fx.port.connection.deleter

import io.github.slupik.schemablock.javafx.element.fx.port.connection.*
import io.github.slupik.schemablock.javafx.element.fx.port.connection.storage.ConditionalConnectionKey
import io.github.slupik.schemablock.javafx.element.fx.port.connection.storage.ConnectionStorageKey
import io.github.slupik.schemablock.javafx.element.fx.port.connection.storage.PortConnectionsHolder
import io.github.slupik.schemablock.javafx.element.fx.port.connection.storage.StandardConnectionKey
import io.github.slupik.schemablock.javafx.element.fx.port.element.Port
import io.github.slupik.schemablock.javafx.element.fx.port.holder.PortsHolder
import javax.inject.Inject

/**
 * All rights reserved & copyright ©
 */

typealias TargetPort = Port

class PortConnectionsDeleter @Inject constructor(
        private val holder: PortsHolder,
        private val connectionsHolder: PortConnectionsHolder
) : ConnectionDeleter {

    override fun deleteConnection(configuration: PortDisconnectionConfiguration) {
        val key =
                when (configuration) {
                    is StandardDisconnection -> {
                        StandardConnectionKey(
                                configuration.source.elementId
                        )
                    }
                    is ConditionalPortsDisconnection -> {
                        ConditionalConnectionKey(
                                configuration.source.elementId,
                                configuration.value
                        )
                    }
                }
        deleteConnection(key)
    }

    override fun clearConnections(configuration: BlockClearanceConfiguration) {
        if(configuration is PortClearance) {
            connectionsHolder.connections
                .filter {
                    it.key.sourcePortId == configuration.ownerId ||
                            it.value.elementId == configuration.ownerId
                }.deleteAll()
        } else {
            connectionsHolder.connections
                .filterConnectionsWithWrongValues(configuration)
                .filterConnectionsWithTheSameOwner(holder, configuration.ownerId)
                .deleteAll()
        }
    }

    override fun clearConnections(blockId: String) {
        connectionsHolder.connections
                .filterConnectionsWithTheSameOwner(holder, blockId)
                .deleteAll()
    }

    private fun Map<ConnectionStorageKey, TargetPort>.filterConnectionsWithTheSameOwner(holder: PortsHolder, ownerId: String):
            Map<ConnectionStorageKey, TargetPort> =
            this.filterKeys {
                val port = holder.getPort(it.sourcePortId)
                if (port != null) {
                    port.owner.elementId == ownerId
                } else {
                    false
                }
            }

    private fun Map<ConnectionStorageKey, TargetPort>.filterConnectionsWithWrongValues(configuration: BlockClearanceConfiguration):
            Map<ConnectionStorageKey, TargetPort> =
            this.filterKeys {
                (it is ConditionalConnectionKey && configuration is ConditionalOwnerClearance
                        && it.value == configuration.value)
                        .not()
            }

    private fun Map<ConnectionStorageKey, TargetPort>.deleteAll() {
        this.forEach { (key, _) -> deleteConnection(key) }
    }

    private fun deleteConnection(key: ConnectionStorageKey) {
        connectionsHolder.remove(key)
    }

}