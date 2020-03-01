package io.github.slupik.schemablock.javafx.element.fx.port.stringifier

import io.github.slupik.schemablock.javafx.element.fx.port.element.Port
import io.github.slupik.schemablock.javafx.element.fx.port.holder.PortAccessibility

/**
 * All rights reserved & copyright ©
 */
interface PortStringifier {

    fun stringify(port: Port, accessibility: PortAccessibility): String

}