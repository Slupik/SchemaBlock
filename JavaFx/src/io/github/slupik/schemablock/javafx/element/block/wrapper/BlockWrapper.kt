package io.github.slupik.schemablock.javafx.element.block.wrapper

import io.github.slupik.schemablock.javafx.element.UiElementType
import io.github.slupik.schemablock.javafx.element.block.Block
import javafx.scene.Node
import javafx.scene.layout.Pane

/**
 * All rights reserved & copyright ©
 */
internal open class BlockWrapper constructor(
        protected val wrappee: Block
) : Block {

    override var description: String
        get() = wrappee.description
        set(value) {
            wrappee.description = value
        }
    override val type: UiElementType
        get() = wrappee.type
    override val elementId: String
        get() = wrappee.elementId
    override val background: Pane
        get() = wrappee.background
    override val graphic: Node
        get() = wrappee.graphic

    override fun setup() {
        wrappee.setup()
    }

    override fun setElementSize(width: Double, height: Double) {
        wrappee.setElementSize(width, height)
    }

    override fun setElementWidth(width: Double) {
        wrappee.setElementWidth(width)
    }

    override fun setElementHeight(height: Double) {
        wrappee.setElementHeight(height)
    }

    override fun markAsError() {
        wrappee.markAsError()
    }

    override fun markAsExecuting() {
        wrappee.markAsExecuting()
    }

    override fun markAsStop() {
        wrappee.markAsStop()
    }

}