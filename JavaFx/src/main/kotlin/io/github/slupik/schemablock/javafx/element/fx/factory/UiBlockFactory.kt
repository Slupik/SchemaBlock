package io.github.slupik.schemablock.javafx.element.fx.factory

import io.github.slupik.schemablock.javafx.element.UiElementType
import io.github.slupik.schemablock.javafx.element.block.Block
import io.github.slupik.schemablock.javafx.element.block.implementation.*
import io.github.slupik.schemablock.javafx.element.block.wrapper.edition.BlockEditionFeature
import io.github.slupik.schemablock.javafx.logic.drag.DragEventState
import io.github.slupik.schemablock.javafx.logic.drag.node.DraggableElementContainer
import io.github.slupik.schemablock.javafx.logic.drag.node.ElementDragController

/**
 * All rights reserved & copyright ©
 */

object UiBlockFactory {

    /*
        This function must exists (default arguments cannot be used) because of java compatibility
     */
    fun createUsableBlock(type: UiElementType): DescribedBlockPrototype {
        return createUsableBlock(type, null)
    }

    fun createUsableBlock(type: UiElementType, id: String?): DescribedBlockPrototype {
        val element = createBase(type, id)
        element.setElementSize(50.0, 31.0)
        element.makeDraggable()
        return element
    }

    fun createIcon(type: UiElementType): DescribedBlockPrototype {
        val element = createBase(type)
        element.setElementSize(50.0, 31.0)
        return element
    }

    private fun createBase(type: UiElementType, id: String? = null): DescribedBlockPrototype {
        val element: DescribedBlockPrototype = if(null == id) {
            when (type) {
                UiElementType.CALCULATION -> OperationsUiBlock()
                UiElementType.IF -> ConditionalUiBlock()
                UiElementType.START -> StartUiBlock()
                UiElementType.STOP -> StopUiBlock()
                UiElementType.IO -> IoUiBlock()
            }
        } else {
            when (type) {
                UiElementType.CALCULATION -> OperationsUiBlock(id)
                UiElementType.IF -> ConditionalUiBlock(id)
                UiElementType.START -> StartUiBlock(id)
                UiElementType.STOP -> StopUiBlock(id)
                UiElementType.IO -> IoUiBlock(id)
            }
        }
        element.setup()
        BlockEditionFeature(element)
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
