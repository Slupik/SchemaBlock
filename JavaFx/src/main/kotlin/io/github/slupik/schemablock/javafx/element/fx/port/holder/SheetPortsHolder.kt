package io.github.slupik.schemablock.javafx.element.fx.port.holder

import io.github.slupik.schemablock.javafx.element.fx.port.element.Port
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject


/**
 * All rights reserved & copyright ©
 */
class SheetPortsHolder : PortsHolder {

    override val ports: HashMap<Port, PortAccessibility> = hashMapOf()

    private val additionsPublisher: PublishSubject<Pair<Port, PortAccessibility>> = PublishSubject.create()
    override val additions: Observable<Pair<Port, PortAccessibility>> = additionsPublisher

    private val deletionsPublisher: PublishSubject<Pair<Port, PortAccessibility>> = PublishSubject.create()
    override val deletions: Observable<Pair<Port, PortAccessibility>> = deletionsPublisher

    override fun addPort(port: Port, configuration: PortAccessibility) {
        deletePort(port.elementId)
        ports[port] = configuration
        deletionsPublisher.onNext(Pair(
                port,
                configuration
        ))
    }

    override fun deletePort(portId: String) {
        ports.filterKeys {
            it.elementId == portId
        }.forEach {
            ports.remove(it.key)
            deletionsPublisher.onNext(Pair(
                    it.key,
                    it.value
            ))
        }
    }

}