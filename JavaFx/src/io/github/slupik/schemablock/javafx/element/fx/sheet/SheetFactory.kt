package io.github.slupik.schemablock.javafx.element.fx.sheet

import javafx.scene.layout.Pane

/**
 * All rights reserved & copyright ©
 */
object SheetFactory {

    fun make(elementsContainer: Pane): Sheet {
        return BasicFeaturedSheet(
                elementsContainer,
                VisibleSheet(elementsContainer)
        )
    }

}