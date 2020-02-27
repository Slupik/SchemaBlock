package io.github.slupik.schemablock.javafx.element.block.stringifier

import io.github.slupik.schemablock.javafx.element.UiElementType

/**
 * All rights reserved & copyright ©
 */
sealed class BlockSpecification

data class CodeAwareBlockSpecification(
    val type: UiElementType,
    val id: String,
    val description: String,
    val layoutX: Double,
    val layoutY: Double,
    val content: String
) : BlockSpecification()

data class IoBlockSpecification(
    val type: UiElementType,
    val id: String,
    val description: String,
    val layoutX: Double,
    val layoutY: Double,
    val operations: List<IoOperationSpecification>
    ) : BlockSpecification()

data class FunctionalBlockSpecification(
    val type: UiElementType,
    val id: String,
    val description: String,
    val layoutX: Double,
    val layoutY: Double
) : BlockSpecification()