package io.github.slupik.schemablock.javafx.element.fx

import io.github.slupik.schemablock.javafx.element.UiElementType
import io.github.slupik.schemablock.javafx.element.block.Block
import javafx.scene.Node
import javafx.scene.layout.Pane
import org.apache.commons.lang3.RandomStringUtils

/**
 * All rights reserved & copyright ©
 */
class MockedBlock constructor(
    override val elementId: String = RandomStringUtils.random(16)
): Block {

    override val type: UiElementType = UiElementType.START
    override val background: Pane = Pane()
    override var description: String = "def desc"

    override fun setup() {}

    override fun setElementSize(width: Double, height: Double) {}

    override fun setElementWidth(width: Double) {}

    override fun setElementHeight(height: Double) {}

    override fun markAsError() {}

    override fun markAsExecuting() {}

    override fun markAsStop() {}

    override val draggingMask: Node = Pane()
    override val graphic: Pane = Pane()

}