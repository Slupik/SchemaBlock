package io.github.slupik.schemablock.javafx.element.fx.factory

import io.github.slupik.schemablock.javafx.element.UiElementType
import io.github.slupik.schemablock.javafx.element.block.implementation.*
import io.github.slupik.schemablock.javafx.logic.drag.DragEventState
import io.github.slupik.schemablock.javafx.logic.drag.node.DraggableElement
import io.github.slupik.schemablock.javafx.logic.drag.node.ElementDragController

/**
 * All rights reserved & copyright ©
 */

object UiBlockFactory {

    fun createDraggable(type: UiElementType): DescribedBlockPrototype {
        val element = createStatic(type)
        ElementDragController(DraggableElement(element, false)).addListener { newState, _ ->
            if (newState == DragEventState.DRAG_START) {
                element.toFront()
            }
        }
        return element
    }

    fun createStatic(type: UiElementType): DescribedBlockPrototype {
        val element: DescribedBlockPrototype = when (type) {
            UiElementType.CALCULATION -> OperationsUiBlock()
            UiElementType.IF -> ConditionalUiBlock()
            UiElementType.START -> StartUiBlock()
            UiElementType.STOP -> StopUiBlock()
            UiElementType.IO -> IoUiBlock()
        }
        element.setup()
        element.setElementSize(50.0, 31.0)
        return element
    }

}