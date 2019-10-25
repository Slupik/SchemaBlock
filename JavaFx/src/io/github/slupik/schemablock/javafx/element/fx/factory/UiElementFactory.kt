package io.github.slupik.schemablock.javafx.element.fx.factory

import io.github.slupik.schemablock.javafx.element.UiElementType
import io.github.slupik.schemablock.javafx.element.block.implementation.*

/**
 * All rights reserved & copyright ©
 */

fun createUiBlockByType(type: UiElementType): DescribedBlockPrototype {
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