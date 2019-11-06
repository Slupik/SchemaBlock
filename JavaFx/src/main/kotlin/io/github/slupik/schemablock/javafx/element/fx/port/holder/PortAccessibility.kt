package io.github.slupik.schemablock.javafx.element.fx.port.holder

/**
 * All rights reserved & copyright ©
 */
enum class PortAccessibility constructor(
        val target: Boolean,
        val source: Boolean
) {
    TWO_WAY(true, true),
    ONLY_SOURCE(false, true),
    ONLY_TARGET(true, false),
    CONDITIONAL_INPUT(true, true);

}