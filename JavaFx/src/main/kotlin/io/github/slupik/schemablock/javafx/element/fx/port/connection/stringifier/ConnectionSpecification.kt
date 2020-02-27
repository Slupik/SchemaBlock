package io.github.slupik.schemablock.javafx.element.fx.port.connection.stringifier

/**
 * All rights reserved & copyright ©
 */
sealed class ConnectionSpecification

data class StandardConnectionSpecification(
    private val sourcePortId: String,
    private val targetPortId: String
) : ConnectionSpecification()

data class ConditionalConnectionSpecification(
    private val sourcePortId: String,
    private val targetPortId: String,
    private val condition: Boolean
) : ConnectionSpecification()