package io.github.slupik.schemablock.javafx.element.fx.port.connection.storage

import io.github.slupik.schemablock.javafx.element.fx.port.element.Port
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

/**
 * All rights reserved & copyright ©
 */
class SheetPortConnectionsHolder @Inject constructor() : PortConnectionsHolder {

    private val connectionsStorage: MutableMap<ConnectionStorageKey, TargetPort> = mutableMapOf()
    private val deletionsPublisher: PublishSubject<Pair<ConnectionStorageKey, TargetPort>> = PublishSubject.create()
    private val additionsPublisher: PublishSubject<Pair<ConnectionStorageKey, TargetPort>> = PublishSubject.create()

    override val connections: Map<ConnectionStorageKey, TargetPort>
        get() = connectionsStorage
    override val deletions: Observable<Pair<ConnectionStorageKey, TargetPort>>
        get() = deletionsPublisher
    override val additions: Observable<Pair<ConnectionStorageKey, TargetPort>>
        get() = additionsPublisher

    override fun add(key: ConnectionStorageKey, target: Port) {
        if (connectionsStorage.containsKey(key)) {
            remove(key)
        }
        connectionsStorage[key] = target
        additionsPublisher.onNext(
            Pair(key, target)
        )
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