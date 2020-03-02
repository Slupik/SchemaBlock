package io.github.slupik.schemablock.javafx.element.fx.port.holder

import io.github.slupik.schemablock.javafx.dagger.LogicalSheet
import io.github.slupik.schemablock.javafx.element.fx.port.connection.storage.PortConnectionsHolder
import io.github.slupik.schemablock.javafx.element.fx.port.element.Port
import io.github.slupik.schemablock.javafx.element.fx.sheet.Sheet
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject


/**
 * All rights reserved & copyright ©
 */
class SheetPortsHolder @Inject constructor(
    @LogicalSheet private val sheet: Sheet,
    private val connectionsHolder: PortConnectionsHolder
) : PortsHolder {

    override val ports: HashMap<Port, PortAccessibility> = hashMapOf()

    private val additionsPublisher: PublishSubject<Pair<Port, PortAccessibility>> = PublishSubject.create()
    override val additions: Observable<Pair<Port, PortAccessibility>> = additionsPublisher

    private val deletionsPublisher: PublishSubject<Pair<Port, PortAccessibility>> = PublishSubject.create()
    override val deletions: Observable<Pair<Port, PortAccessibility>> = deletionsPublisher

    override fun addPort(port: Port, configuration: PortAccessibility) {
        deletePort(port.elementId)
        ports[port] = configuration
        additionsPublisher.onNext(
            Pair(
                port,
                configuration
            )
        )
    }

    override fun deletePort(portId: String) {
        ports.filterKeys {
            it.elementId == portId
        }.forEach {
            ports.remove(it.key)
            deletionsPublisher.onNext(
                Pair(
                    it.key,
                    it.value
                )
            )
        }
        clearConnections(portId)
        sheet.removeElement(portId)
    }

    private fun clearConnections(portId: String) {
        connectionsHolder.connections.filter {
            it.key.sourcePortId == portId ||
                    it.value.elementId == portId
        }.forEach {
            connectionsHolder.remove(it.key)
        }
    }

    override fun getAccessibilityFor(port: Port): PortAccessibility? =
        ports[port] ?: ports.filter {
            it.key.elementId == port.elementId
        }.values.firstOrNull()

}