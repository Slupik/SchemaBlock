package io.github.slupik.schemablock.javafx.element.fx.port.connection.stringifier

/**
 * All rights reserved & copyright ©
 */
sealed class ConnectionSpecification

data class StandardConnectionSpecification(
    val type: Int = 0,
    val sourcePortId: String,
    val targetPortId: String
) : ConnectionSpecification()

data class ConditionalConnectionSpecification(
    val type: Int = 1,
    val sourcePortId: String,
    val targetPortId: String,
    val condition: Boolean
) : ConnectionSpecification()