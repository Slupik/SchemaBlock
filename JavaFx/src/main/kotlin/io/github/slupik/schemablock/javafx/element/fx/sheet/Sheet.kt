package io.github.slupik.schemablock.javafx.element.fx.sheet

import io.github.slupik.schemablock.javafx.element.Element

/**
 * All rights reserved & copyright ©
 */
interface Sheet {

    fun addElement(element: Element)

    fun removeElement(element: Element) =
        removeElement(element.elementId)
    fun removeElement(elementId: String)

    fun getElements(): List<Element>

}