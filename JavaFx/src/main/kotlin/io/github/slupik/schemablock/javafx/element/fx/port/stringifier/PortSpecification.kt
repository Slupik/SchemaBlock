package io.github.slupik.schemablock.javafx.element.fx.port.stringifier

import io.github.slupik.schemablock.javafx.element.fx.port.holder.PortAccessibility

/**
 * All rights reserved & copyright ©
 */
data class PortSpecification(
    private val elementId: String,
    private val ownerId: String,
    private val accessibility: PortAccessibility,
    private val relativeX: Double,
    private val relativeY: Double
)