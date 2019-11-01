package io.github.slupik.schemablock.javafx.element.fx.factory

import io.github.slupik.schemablock.javafx.element.UiElementType
import io.github.slupik.schemablock.javafx.element.block.Block
import io.github.slupik.schemablock.javafx.element.block.implementation.*
import io.github.slupik.schemablock.javafx.logic.drag.DragEventState
import io.github.slupik.schemablock.javafx.logic.drag.node.DraggableElementContainer
import io.github.slupik.schemablock.javafx.logic.drag.node.ElementDragController

/**
 * All rights reserved & copyright ©
 */

object UiBlockFactory {

    fun createUsableBlock(type: UiElementType): DescribedBlockPrototype {
        val element = createIcon(type)
        element.makeDraggable()
        return element
    }

    fun createIcon(type: UiElementType): DescribedBlockPrototype {
        val element = createBase(type)
        element.setElementSize(50.0, 31.0)
        return element
    }

    private fun createBase(type: UiElementType): DescribedBlockPrototype {
        val element: DescribedBlockPrototype = when (type) {
            UiElementType.CALCULATION -> OperationsUiBlock()
            UiElementType.IF -> ConditionalUiBlock()
            UiElementType.START -> StartUiBlock()
            UiElementType.STOP -> StopUiBlock()
            UiElementType.IO -> IoUiBlock()
        }
        element.setup()
        return element
    }

}

private fun Block.makeDraggable() {
    val element = this
    ElementDragController(DraggableElementContainer(element, false)).addListener { newState, _ ->
        if (newState == DragEventState.DRAG_START) {
            element.graphic.toFront()
        }
    }
}
