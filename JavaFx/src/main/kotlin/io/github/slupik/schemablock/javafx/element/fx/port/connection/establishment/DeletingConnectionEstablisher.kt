package io.github.slupik.schemablock.javafx.element.fx.port.connection.establishment

import io.github.slupik.schemablock.javafx.element.fx.port.connection.*
import io.github.slupik.schemablock.javafx.element.fx.port.connection.checker.ConnectionAvailabilityChecker
import io.github.slupik.schemablock.javafx.element.fx.port.connection.deleter.ConnectionDeleter
import io.github.slupik.schemablock.javafx.element.fx.port.connection.storage.ConditionalConnectionKey
import io.github.slupik.schemablock.javafx.element.fx.port.connection.storage.ConnectionStorageKey
import io.github.slupik.schemablock.javafx.element.fx.port.connection.storage.PortsConnectionsModifier
import io.github.slupik.schemablock.javafx.element.fx.port.connection.storage.StandardConnectionKey
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

/**
 * All rights reserved & copyright ©
 */

class DeletingConnectionEstablisher @Inject constructor(
    private val checker: ConnectionAvailabilityChecker,
    private val deleter: ConnectionDeleter,
    private val connectionsModifier: PortsConnectionsModifier
) : ConnectionEstablisher {

    private val establishmentsPublisher: PublishSubject<PortConnectionConfiguration> = PublishSubject.create()
    override val establishments: Observable<PortConnectionConfiguration> = establishmentsPublisher

    override fun establishConnection(configuration: PortConnectionConfiguration) {
        if (checker.isConnectionPossible(configuration)) {
            if (checker.isExistingSimilarConnections(configuration)) {
                deleter.clearConnections(getKeyForClearingBlock(configuration))
            }

            val key = getConnectionKey(configuration)

            connectionsModifier.add(key, configuration.target)
            establishmentsPublisher.onNext(configuration)
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

    private fun getConnectionKey(configuration: PortConnectionConfiguration): ConnectionStorageKey =
        when (configuration) {
            is StandardPortsConnection -> {
                StandardConnectionKey(
                    configuration.source.elementId
                )
            }
            is ConditionalPortsConnection -> {
                ConditionalConnectionKey(
                    configuration.source.elementId,
                    configuration.value
                )
            }
        }

}