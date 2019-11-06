package io.github.slupik.schemablock.javafx.element.block.settings

/**
 * All rights reserved & copyright ©
 */

sealed class UiBlockSettings

data class CodeAndDescription(
        val code: String,
        val description: String
) : UiBlockSettings()

data class DescriptionAndIO(
        val description: String,
        val operations: List<IoOperation>
) : UiBlockSettings()
