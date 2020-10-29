package io.github.slupik.schemablock.javafx.element.fx.port.stringifier

import io.github.slupik.schemablock.javafx.element.fx.port.holder.PortAccessibility

/**
 * All rights reserved & copyright ©
 */
data class PortSpecification(
    val elementId: String,
    val ownerId: String,
    val accessibility: PortAccessibility,
    val relativeX: Double,
    val relativeY: Double
)