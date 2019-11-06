package io.github.slupik.schemablock.javafx.element.fx.sheet

import io.github.slupik.schemablock.javafx.element.Element
import javafx.scene.layout.Pane

/**
 * All rights reserved & copyright ©
 */
class VisibleSheet constructor(
        val container: Pane
) : Sheet {

    override fun addElement(element: Element) {
        container.children.add(element.graphic)
    }

    override fun removeElement(elementId: String) {
        container
                .children
                .filter {
                    it is Element && it.elementId == elementId
                }.forEach {
                    container.children.remove(it)
                }
    }

}