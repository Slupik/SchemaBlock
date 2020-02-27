package io.github.slupik.schemablock.javafx.element.fx.sheet

import io.github.slupik.schemablock.javafx.element.Element
import javafx.scene.layout.Pane

/**
 * All rights reserved & copyright ©
 */
class VisibleSheet constructor(
    val container: Pane
) : Sheet {

    private val elements = mutableListOf<Element>()

    override fun addElement(element: Element) {
        container.children.add(element.graphic)
        elements.add(element)
    }

    override fun removeElement(elementId: String) {
        container
            .children
            .filter {
                it is Element && it.elementId == elementId
            }.forEach {
                container.children.remove(it)
            }
        elements
            .filter {
                it.elementId == elementId
            }.forEach {
                elements.remove(it)
            }
    }

}