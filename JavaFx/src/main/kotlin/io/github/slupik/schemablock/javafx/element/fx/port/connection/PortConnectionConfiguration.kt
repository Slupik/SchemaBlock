package io.github.slupik.schemablock.javafx.element.fx.port.connection

import io.github.slupik.schemablock.javafx.element.fx.port.element.Port

/**
 * All rights reserved & copyright ©
 */
sealed class PortConnectionConfiguration(
        open val source: Port,
        open val target: Port
)

data class StandardPortsConnection(
        override val source: Port,
        override val target: Port
) : PortConnectionConfiguration(source, target)

data class ConditionalPortsConnection(
        override val source: Port,
        override val target: Port,
        val value: Boolean
) : PortConnectionConfiguration(source, target)
