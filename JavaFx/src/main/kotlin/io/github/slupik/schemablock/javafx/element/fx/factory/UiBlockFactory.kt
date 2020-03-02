package io.github.slupik.schemablock.javafx.element.fx.factory

import io.github.slupik.schemablock.javafx.element.UiElementType
import io.github.slupik.schemablock.javafx.element.block.Block
import io.github.slupik.schemablock.javafx.element.block.contextmenu.BlockContextMenuProvider
import io.github.slupik.schemablock.javafx.element.block.implementation.*
import io.github.slupik.schemablock.javafx.element.block.wrapper.edition.BlockEdition
import io.github.slupik.schemablock.javafx.logic.drag.DragEventState
import io.github.slupik.schemablock.javafx.logic.drag.node.DraggableElementContainer
import io.github.slupik.schemablock.javafx.logic.drag.node.ElementDragController
import javafx.scene.control.ContextMenu
import javafx.scene.input.MouseButton
import javafx.scene.input.MouseEvent
import javafx.scene.layout.Pane
import javax.inject.Inject

/**
 * All rights reserved & copyright ©
 */

class UiBlockFactory @Inject constructor(
    private val contextMenuProvider: BlockContextMenuProvider,
    private val blockEdition: BlockEdition
) {

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
        element.setContextMenu(contextMenuProvider.getFor(element))
        return element
    }

    fun createIcon(type: UiElementType): DescribedBlockPrototype {
        val element = createBase(type)
        element.setElementSize(50.0, 31.0)
        return element
    }

    private fun createBase(type: UiElementType, id: String? = null): DescribedBlockPrototype {
        val element: DescribedBlockPrototype = if (null == id) {
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
        element.makeEditable()
        return element
    }

    private fun DescribedBlockPrototype.makeEditable() {
        this.draggingMask.addEventHandler(MouseEvent.MOUSE_CLICKED) { event ->
            if (this.background.contains(event.x, event.y)) {
                if (event.button == MouseButton.PRIMARY) {
                    if (event.clickCount == 2) {
                        blockEdition.openFor(this)
                    }
                }
            }
        }
    }

}

private fun Pane.setContextMenu(contextMenu: ContextMenu) {
    this.setOnMousePressed { event ->
        if (event.isSecondaryButtonDown) {
            contextMenu.show(this, event.screenX, event.screenY)
        }
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
