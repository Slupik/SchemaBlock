package io.github.slupik.schemablock.javafx.element.fx.port.connection

import io.github.slupik.schemablock.javafx.element.fx.port.element.Port

/**
 * All rights reserved & copyright ©
 */
sealed class PortDisconnectionConfiguration

data class StandardDisconnection(
        val source: Port
) : PortDisconnectionConfiguration()

data class ConditionalPortsDisconnection(
        val source: Port,
        val value: Boolean
) : PortDisconnectionConfiguration()
