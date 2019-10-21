package io.github.slupik.schemablock.javafx.element.block

import io.github.slupik.schemablock.javafx.element.UiElementType
import io.github.slupik.schemablock.javafx.element.fx.port.PortInfo

/**
 * All rights reserved & copyright ©
 */
interface UiBlock {

    val type: UiElementType
    val elementId: String
    var description: String
    val portsInfo: List<PortInfo>

    fun setElementSize(width: Double, height: Double)
    fun setElementWidth(width: Double)
    fun setElementHeight(height: Double)

    fun markAsError()
    fun markAsExecuting()
    fun markAsStop()

}
