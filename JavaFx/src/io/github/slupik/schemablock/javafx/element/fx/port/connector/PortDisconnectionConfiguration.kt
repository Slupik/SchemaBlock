package io.github.slupik.schemablock.javafx.element.fx.port.connector

import io.github.slupik.schemablock.javafx.element.fx.port.PortInfo

/**
 * All rights reserved & copyright ©
 */
sealed class PortDisconnectionConfiguration

data class StandardDisconnection(
        val source: PortInfo
): PortDisconnectionConfiguration()

data class ConditionalPortsDisconnection(
        val source: PortInfo,
        val value: Boolean
): PortDisconnectionConfiguration()