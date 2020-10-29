package io.github.slupik.schemablock.view.entity

import io.github.slupik.schemablock.view.dialog.data.CodeAndDescription
import io.github.slupik.schemablock.view.dialog.data.DescriptionAndIO

/**
 * All rights reserved & copyright ©
 */
typealias BlockId = String

data class AdditionalBlockModel constructor(
    val codingBlocks: MutableMap<BlockId, CodeAndDescription>,
    val ioBlocks: MutableMap<BlockId, DescriptionAndIO>
)