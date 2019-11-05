package io.github.slupik.schemablock.javafx.element.fx.port.connection

/**
 * All rights reserved & copyright ©
 */
sealed class BlockClearanceConfiguration(
        open val ownerId: String
)

data class StandardOwnerClearance(
        override val ownerId: String
) : BlockClearanceConfiguration(ownerId)

data class ConditionalOwnerClearance(
        override val ownerId: String,
        val value: Boolean
) : BlockClearanceConfiguration(ownerId)