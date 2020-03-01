package io.github.slupik.schemablock.javafx.element.fx.sheet.spawner

import io.github.slupik.schemablock.javafx.dagger.JavaFxSheet
import io.github.slupik.schemablock.javafx.dagger.LogicalSheet
import io.github.slupik.schemablock.javafx.element.UiElementType
import io.github.slupik.schemablock.javafx.element.block.implementation.StartUiBlock
import io.github.slupik.schemablock.javafx.element.fx.factory.UiBlockFactory
import io.github.slupik.schemablock.javafx.element.fx.port.spawner.PortSpawner
import io.github.slupik.schemablock.javafx.element.fx.sheet.Sheet
import javafx.application.Platform
import javafx.scene.layout.Pane
import javax.inject.Inject

/**
 * All rights reserved & copyright ©
 */
class StartElementSpawner @Inject constructor(
    @LogicalSheet private val sheet: Sheet,
    @JavaFxSheet private val container: Pane,
    private val portSpawner: PortSpawner
) : ElementsSpawner {

    override fun spawn() {
        val startBlock = createStartElement()
        sheet.addElement(startBlock)
        Platform.runLater {
            setupStartBlock(startBlock)
            portSpawner.spawnFor(startBlock)
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