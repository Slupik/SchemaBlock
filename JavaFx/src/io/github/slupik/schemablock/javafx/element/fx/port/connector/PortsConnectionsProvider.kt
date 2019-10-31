package io.github.slupik.schemablock.javafx.element.fx.port.connector

/**
 * All rights reserved & copyright ©
 */
interface PortsConnectionsProvider {

    fun getNextElement(sourceBlockId: String): TargetElementId? =
            getNextElement(StandardConnection(sourceBlockId))

    fun getNextElement(sourceBlockId: String, result: Boolean): TargetElementId? =
            getNextElement(ConditionalConnection(sourceBlockId, result))

    fun getNextElement(key: ConnectionStorageKey): TargetElementId?

}