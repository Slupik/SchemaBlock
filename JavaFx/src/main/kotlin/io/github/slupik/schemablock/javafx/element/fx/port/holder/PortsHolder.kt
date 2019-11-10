package io.github.slupik.schemablock.javafx.element.fx.port.holder

import io.github.slupik.schemablock.javafx.element.fx.port.element.Port
import io.reactivex.Observable

/**
 * All rights reserved & copyright ©
 */
interface PortsHolder {

    val ports: Map<Port, PortAccessibility>
    val additions: Observable<Pair<Port, PortAccessibility>>
    val deletions: Observable<Pair<Port, PortAccessibility>>

    fun addPort(port: Port, configuration: PortAccessibility)

    fun deletePort(port: Port) {
        deletePort(port.elementId)
    }

    fun deletePort(portId: String)

    fun getPort(portId: String): Port? =
            ports.keys.firstOrNull { port -> portId == port.elementId }

    fun getAccessibilityFor(port: Port): PortAccessibility?

}