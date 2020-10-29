package io.github.slupik.schemablock.javafx.element.fx.sheet

import io.github.slupik.schemablock.javafx.element.Element
import javafx.scene.layout.Pane
import javax.inject.Inject
import io.github.slupik.schemablock.javafx.dagger.JavaFxSheet as UiSheet

/**
 * All rights reserved & copyright ©
 */
class VisibleSheet @Inject constructor(
    @UiSheet val container: Pane
) : Sheet {

    private val elements = mutableListOf<Element>()

    override fun addElement(element: Element) {
        container.children.add(element.graphic)
        elements.add(element)
    }

    override fun removeElement(elementId: String) {
        container
            .children
            .filter { child ->
                elements.firstOrNull {
                    it.elementId == elementId && it.graphic == child
                } != null
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

    override fun getElements(): List<Element> =
        elements

}