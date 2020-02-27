package io.github.slupik.schemablock.javafx.element.fx.port.element

import io.github.slupik.schemablock.javafx.element.Element
import io.github.slupik.schemablock.javafx.element.block.Block
import javafx.scene.Node

/**
 * All rights reserved & copyright ©
 */
interface Port : Element {

    val owner: Block
    val mask: Node

    fun markAsNeutral()
    fun markAsActive()
    fun markAsDisabled()

    fun setRelativePos(percentOfWidth: Double, percentOfHeight: Double)
    fun getRelativeX(): Double
    fun getRelativeY(): Double

}