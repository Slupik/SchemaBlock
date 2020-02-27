package io.github.slupik.schemablock.javafx.element.block.stringifier

import io.github.slupik.schemablock.javafx.element.UiElementType

/**
 * All rights reserved & copyright ©
 */
sealed class BlockSpecification(
    open val type: UiElementType,
    open val id: String,
    open val description: String,
    open val layoutX: Double,
    open val layoutY: Double
)

data class CodeAwareBlockSpecification(
    override val type: UiElementType,
    override val id: String,
    override val description: String,
    override val layoutX: Double,
    override val layoutY: Double,
    val content: String
) : BlockSpecification(
    type = type,
    id = id,
    description = description,
    layoutX = layoutX,
    layoutY = layoutY
)

data class IoBlockSpecification(
    override val type: UiElementType,
    override val id: String,
    override val description: String,
    override val layoutX: Double,
    override val layoutY: Double,
    val operations: List<IoOperationSpecification>
    ) : BlockSpecification(
    type = type,
    id = id,
    description = description,
    layoutX = layoutX,
    layoutY = layoutY
)

data class FunctionalBlockSpecification(
    override val type: UiElementType,
    override val id: String,
    override val description: String,
    override val layoutX: Double,
    override val layoutY: Double
) : BlockSpecification(
    type = type,
    id = id,
    description = description,
    layoutX = layoutX,
    layoutY = layoutY
)