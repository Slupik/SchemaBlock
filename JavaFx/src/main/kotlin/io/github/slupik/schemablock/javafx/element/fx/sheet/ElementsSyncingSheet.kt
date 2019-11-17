package io.github.slupik.schemablock.javafx.element.fx.sheet

import io.github.slupik.schemablock.javafx.element.Element
import io.github.slupik.schemablock.javafx.element.block.Block
import io.github.slupik.schemablock.javafx.element.fx.element.holder.BlocksHolder
import javax.inject.Inject

/**
 * All rights reserved & copyright ©
 */
class ElementsSyncingSheet @Inject constructor(
    wrapee: Sheet,
    private val blocksHolder: BlocksHolder
) : SheetWrapper(wrapee), Sheet {

    override fun addElement(element: Element) {
        super.addElement(element)
        if(element is Block) {
            blocksHolder.addBlock(element)
        }
    }

    override fun removeElement(element: Element) {
        super.addElement(element)
        if(element is Block) {
            blocksHolder.deleteBlock(element)
        }
    }

}