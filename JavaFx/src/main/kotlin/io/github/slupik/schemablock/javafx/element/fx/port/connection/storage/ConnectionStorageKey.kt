package io.github.slupik.schemablock.javafx.element.fx.port.connection.storage

/**
 * All rights reserved & copyright ©
 */
sealed class ConnectionStorageKey(
        open val sourcePortId: String
)

data class StandardConnectionKey(
        override val sourcePortId: String
) : ConnectionStorageKey(sourcePortId)

data class ConditionalConnectionKey(
        override val sourcePortId: String,
        val value: Boolean
) : ConnectionStorageKey(sourcePortId)