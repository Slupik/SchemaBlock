package io.github.slupik.schemablock.javafx.element.fx.port.connection.checker

import io.github.slupik.schemablock.javafx.element.fx.port.connection.ConditionalPortsConnection
import io.github.slupik.schemablock.javafx.element.fx.port.connection.PortConnectionConfiguration
import io.github.slupik.schemablock.javafx.element.fx.port.connection.StandardPortsConnection
import io.github.slupik.schemablock.javafx.element.fx.port.connection.storage.*
import io.github.slupik.schemablock.javafx.element.fx.port.element.Port
import io.github.slupik.schemablock.javafx.element.fx.port.holder.PortAccessibility
import io.github.slupik.schemablock.javafx.element.fx.port.holder.PortsHolder
import javax.inject.Inject

/**
 * All rights reserved & copyright ©
 */
class OwnerAwareAvailabilityChecker @Inject constructor(
        private val holder: PortsHolder,
        private val connectionsProvider: PortsConnectionProvider
) : ConnectionAvailabilityChecker {

    override fun isExistingSimilarConnections(configuration: PortConnectionConfiguration): Boolean {
        val ownerId = configuration.source.owner.elementId

        val otherPortsInGroup = holder.ports
                .getPortsOfOwner(ownerId)
                .excludePort(configuration.source.elementId)

        val existingConnections = connectionsProvider
                .connections
                .filterWithTheSameValue(configuration)
                .filterConnectionsForPorts(otherPortsInGroup)

        return existingConnections.isNotEmpty()
    }

    private fun Map<Port, PortAccessibility>.getPortsOfOwner(ownerId: String): Map<Port, PortAccessibility> =
            this.filterKeys { it.owner.elementId == ownerId }

    private fun Map<Port, PortAccessibility>.excludePort(excludedId: String): Map<Port, PortAccessibility> =
            this.filterKeys { it.elementId != excludedId }

    private fun Map<ConnectionStorageKey, TargetPort>.filterWithTheSameValue(configuration: PortConnectionConfiguration):
            Map<ConnectionStorageKey, TargetPort> =
            this.filterKeys {
                when (it) {
                    is StandardConnectionKey ->
                        configuration is StandardPortsConnection
                    is ConditionalConnectionKey ->
                        configuration is ConditionalPortsConnection && configuration.value == it.value
                }
            }

    private fun Map<ConnectionStorageKey, TargetPort>.filterConnectionsForPorts(otherPortsInGroup: Map<Port, PortAccessibility>):
            Map<ConnectionStorageKey, TargetPort> =
            this.filterKeys {
                it.isInGroup(otherPortsInGroup)
            }

    private fun ConnectionStorageKey.isInGroup(group: Map<Port, PortAccessibility>): Boolean =
            group.filter {
                it.key.elementId == this.sourcePortId
            }.isNotEmpty()


    override fun isConnectionPossible(configuration: PortConnectionConfiguration): Boolean {
        val source = holder.ports[configuration.source]
        val target = holder.ports[configuration.target]

        return source.isSource() && target.isTarget() && configuration.source.elementId != configuration.target.elementId &&
                configuration.source.owner.elementId != configuration.target.owner.elementId
    }

    private fun PortAccessibility?.isSource(): Boolean =
            this != null && this.source

    private fun PortAccessibility?.isTarget(): Boolean =
            this != null && this.target

}
