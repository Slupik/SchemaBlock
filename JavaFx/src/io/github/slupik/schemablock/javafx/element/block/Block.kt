package io.github.slupik.schemablock.javafx.element.block

import io.github.slupik.schemablock.javafx.element.Element
import io.github.slupik.schemablock.javafx.element.UiElementType
import javafx.scene.layout.Pane

/**
 * All rights reserved & copyright ©
 */
interface Block: Element {

    val type: UiElementType
    val background: Pane
    var description: String

    fun setup()

    fun setElementSize(width: Double, height: Double)
    fun setElementWidth(width: Double)
    fun setElementHeight(height: Double)

    fun markAsError()
    fun markAsExecuting()
    fun markAsStop()

}
