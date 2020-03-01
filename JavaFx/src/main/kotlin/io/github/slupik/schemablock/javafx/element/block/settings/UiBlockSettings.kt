package io.github.slupik.schemablock.javafx.element.block.settings

/**
 * All rights reserved & copyright ©
 */

sealed class UiBlockSettings

data class CodeAndDescription(
        val description: String,
        val code: String
) : UiBlockSettings()

data class DescriptionAndIO(
        val description: String,
        val operations: List<IoOperation>
) : UiBlockSettings()
