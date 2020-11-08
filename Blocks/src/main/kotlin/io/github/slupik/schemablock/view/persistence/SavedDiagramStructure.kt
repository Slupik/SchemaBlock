package io.github.slupik.schemablock.view.persistence

import io.github.slupik.schemablock.view.entity.AdditionalBlockModel

/**
 * All rights reserved & copyright ©
 */
data class SavedDiagramStructure(
    val graphModel: String,
    val additionalModel: AdditionalBlockModel
)