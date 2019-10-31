package io.github.slupik.schemablock.javafx.element.fx.port.connector

import io.github.slupik.schemablock.javafx.element.fx.port.PortInfo

/**
 * All rights reserved & copyright ©
 */
sealed class PortConnectionConfiguration(
        val targetBlockId: String
)

data class StandardPortsConnection(
        val source: PortInfo,
        val target: PortInfo
) : PortConnectionConfiguration(targetBlockId = target.parentElementId)

data class ConditionalPortsConnection(
        val source: PortInfo,
        val target: PortInfo,
        val value: Boolean
) : PortConnectionConfiguration(targetBlockId = target.parentElementId)