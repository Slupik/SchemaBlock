package io.github.slupik.schemablock.javafx.element.block.contextmenu

import io.github.slupik.schemablock.javafx.element.block.Block
import javafx.scene.control.ContextMenu

/**
 * All rights reserved & copyright ©
 */
interface BlockContextMenuProvider {

    fun getFor(block: Block): ContextMenu

}