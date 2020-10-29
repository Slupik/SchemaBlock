package io.github.slupik.schemablock.javafx.element.fx.port.connection.establishment

import io.github.slupik.schemablock.javafx.element.fx.port.connection.*
import io.github.slupik.schemablock.javafx.element.fx.port.connection.checker.ConnectionAvailabilityChecker
import io.github.slupik.schemablock.javafx.element.fx.port.connection.deleter.ConnectionDeleter
import io.github.slupik.schemablock.javafx.element.fx.port.connection.storage.PortConnectionsHolder
import javax.inject.Inject

/**
 * All rights reserved & copyright ©
 */

class DeletingConnectionEstablisher @Inject constructor(
    private val checker: ConnectionAvailabilityChecker,
    private val deleter: ConnectionDeleter,
    private val connectionsHolder: PortConnectionsHolder
) : ConnectionEstablisher {

    override fun establishConnection(configuration: PortConnectionConfiguration) {
        if (checker.isConnectionPossible(configuration)) {
            if (checker.isExistingSimilarConnections(configuration)) {
                deleter.clearConnections(getKeyForClearingBlock(configuration))
            }

            connectionsHolder.add(configuration)
        }
    }

    private fun getKeyForClearingBlock(configuration: PortConnectionConfiguration): BlockClearanceConfiguration =
        when (configuration) {
            is StandardPortsConnection ->
                StandardOwnerClearance(configuration.source.owner.elementId)
            is ConditionalPortsConnection ->
                ConditionalOwnerClearance(
                    configuration.source.owner.elementId,
                    configuration.value
                )
        }

}