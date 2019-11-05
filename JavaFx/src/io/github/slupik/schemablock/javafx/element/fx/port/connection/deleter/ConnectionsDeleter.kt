package io.github.slupik.schemablock.javafx.element.fx.port.connection.deleter

import io.github.slupik.schemablock.javafx.element.fx.port.connection.*
import io.github.slupik.schemablock.javafx.element.fx.port.connection.storage.*
import io.github.slupik.schemablock.javafx.element.fx.port.element.Port
import io.github.slupik.schemablock.javafx.element.fx.port.holder.PortsHolder
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * All rights reserved & copyright ©
 */

typealias TargetPort = Port

class ConnectionsDeleter constructor(
        private val holder: PortsHolder,
        private val connectionsProvider: PortsConnectionProvider,
        private val modifier: PortsConnectionsModifier
) : PortConnectionDeleter {

    private val deletionsPublisher: PublishSubject<ConnectionStorageKey> = PublishSubject.create()
    override val deletions: Observable<ConnectionStorageKey> = deletionsPublisher

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
        connectionsProvider.connections
                .filterConnectionsWithWrongValues(configuration)
                .filterConnectionsWithTheSameOwner(holder, configuration.ownerId)
                .deleteAll()
    }

    override fun clearConnections(ownerId: String) {
        connectionsProvider.connections
                .filterConnectionsWithTheSameOwner(holder, ownerId)
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
        modifier.remove(key)
        deletionsPublisher.onNext(key)
    }

}