package io.github.slupik.schemablock.javafx.element.fx.port.connection.storage

import io.github.slupik.schemablock.javafx.element.fx.port.connection.ConditionalPortsConnection
import io.github.slupik.schemablock.javafx.element.fx.port.connection.PortConnectionConfiguration
import io.github.slupik.schemablock.javafx.element.fx.port.connection.StandardPortsConnection
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

/**
 * All rights reserved & copyright ©
 */
class SheetPortConnectionsHolder @Inject constructor() : PortConnectionsHolder {

    private val connectionsStorage: MutableMap<ConnectionStorageKey, TargetPort> = mutableMapOf()
    private val deletionsPublisher: PublishSubject<Pair<ConnectionStorageKey, TargetPort>> = PublishSubject.create()
    private val establishmentsPublisher: PublishSubject<PortConnectionConfiguration> = PublishSubject.create()

    override val connections: Map<ConnectionStorageKey, TargetPort>
        get() = connectionsStorage
    override val deletions: Observable<Pair<ConnectionStorageKey, TargetPort>>
        get() = deletionsPublisher
    override val establishments: Observable<PortConnectionConfiguration>
        get() = establishmentsPublisher

    override fun add(configuration: PortConnectionConfiguration) {
        val key = getConnectionKey(configuration)

        if (connectionsStorage.containsKey(key)) {
            remove(key)
        }
        connectionsStorage[key] = configuration.target
        establishmentsPublisher.onNext(configuration)
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

    override fun remove(key: ConnectionStorageKey) {
        val target = connectionsStorage[key]
        if(null != target) {
            connectionsStorage.remove(key)
            deletionsPublisher.onNext(
                Pair(key, target)
            )
        }
    }
}