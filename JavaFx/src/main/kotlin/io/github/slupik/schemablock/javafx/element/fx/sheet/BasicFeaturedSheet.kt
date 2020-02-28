package io.github.slupik.schemablock.javafx.element.fx.sheet

import io.github.slupik.schemablock.javafx.element.UiElementType
import io.github.slupik.schemablock.javafx.element.block.implementation.StartUiBlock
import io.github.slupik.schemablock.javafx.element.fx.factory.UiBlockFactory
import javafx.application.Platform
import javafx.scene.layout.Pane

/**
 * All rights reserved & copyright ©
 */
//TODO spawn blocks not on sheet
class BasicFeaturedSheet constructor(
        private val container: Pane,
//        portSpawner: PortSpawner,
        wrapee: Sheet
): SheetWrapper(wrapee), Sheet {

    init {
        val startBlock = createStartElement()
        addElement(startBlock)
        Platform.runLater{
            setupStartBlock(startBlock)
//            portSpawner.spawnFor(startBlock)
        }
    }

    private fun setupStartBlock(block: StartUiBlock) {
        when {
            container.width < 150 -> block.layoutX = 10.0
            container.width < 600 -> {
                val elementWidth = block.boundsInLocalProperty().get().width
                block.layoutX = container.width / 2 - elementWidth / 2
            }
            else -> block.layoutX = 300.0
        }
        block.layoutY = 10.0
    }

    private fun createStartElement(): StartUiBlock =
            UiBlockFactory.createUsableBlock(UiElementType.START) as StartUiBlock

}