package io.github.slupik.schemablock.javafx.element.fx.sheet

import io.github.slupik.schemablock.javafx.element.Element

/**
 * All rights reserved & copyright ©
 */
abstract class SheetWrapper constructor(
        private val wrapee: Sheet
): Sheet {

    override fun addElement(element: Element) {
        wrapee.addElement(element)
    }

    override fun removeElement(elementId: String) {
        wrapee.removeElement(elementId)
    }

    override fun getElements(): List<Element> =
        wrapee.getElements()

}