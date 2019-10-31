package io.github.slupik.schemablock.javafx.element.fx.port.connector

/**
 * All rights reserved & copyright ©
 */
interface PortsConnectionsModifier {

    fun add(configuration: PortConnectionConfiguration)
    fun remove(configuration: PortDisconnectionConfiguration)

}