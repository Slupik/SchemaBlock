package io.github.slupik.schemablock.javafx.element.fx.port.connection.storage

import io.github.slupik.schemablock.javafx.element.fx.port.element.Port
import io.reactivex.Observable

/**
 * All rights reserved & copyright ©
 */

typealias TargetPort = Port

interface PortConnectionsHolder {

    val connections: Map<ConnectionStorageKey, TargetPort>

    val deletions: Observable<Pair<ConnectionStorageKey, TargetPort>>
    val additions: Observable<Pair<ConnectionStorageKey, TargetPort>>

    fun add(key: ConnectionStorageKey, target: Port)
    fun remove(key: ConnectionStorageKey)

}