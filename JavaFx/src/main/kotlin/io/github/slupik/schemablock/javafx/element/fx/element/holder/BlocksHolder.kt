package io.github.slupik.schemablock.javafx.element.fx.element.holder

import io.github.slupik.schemablock.javafx.element.block.Block
import io.reactivex.Observable

/**
 * All rights reserved & copyright ©
 */
interface BlocksHolder {

    val blocks: List<Block>
    val additions: Observable<Block>
    val deletions: Observable<Block>

    fun addBlock(element: Block)

    fun deleteBlock(element: Block) {
        deleteBlock(element.elementId)
    }

    fun deleteBlock(elementId: String)

    fun getBlock(elementId: String): Block? =
        blocks.firstOrNull { block -> elementId == block.elementId }

}