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
            println("ADD: IT'S A BLOCK: $element")
            blocksHolder.addBlock(element)
        } else {
            println("ADD: IT'S NOT A BLOCK: $element")
        }
    }

    override fun removeElement(element: Element) {
        super.addElement(element)
        if(element is Block) {
            println("REMOVE: IT'S A BLOCK: $element")
            blocksHolder.deleteBlock(element)
        } else {
            println("REMOVE: IT'S NOT A BLOCK: $element")
        }
    }

}