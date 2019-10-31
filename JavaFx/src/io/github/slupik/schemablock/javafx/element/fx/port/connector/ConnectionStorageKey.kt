package io.github.slupik.schemablock.javafx.element.fx.port.connector

/**
 * All rights reserved & copyright ©
 */
sealed class ConnectionStorageKey

data class StandardConnection(
        val sourceId: String
) : ConnectionStorageKey()

data class ConditionalConnection(
        val sourceId: String,
        val value: Boolean
) : ConnectionStorageKey()