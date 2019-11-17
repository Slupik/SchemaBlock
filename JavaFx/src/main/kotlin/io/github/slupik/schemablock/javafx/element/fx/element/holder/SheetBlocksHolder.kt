package io.github.slupik.schemablock.javafx.element.fx.element.holder

import io.github.slupik.schemablock.javafx.element.block.Block
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

/**
 * All rights reserved & copyright ©
 */
class SheetBlocksHolder @Inject constructor() : BlocksHolder {

    override val blocks: MutableList<Block> = mutableListOf()

    private val additionsPublisher: PublishSubject<Block> = PublishSubject.create()
    override val additions: Observable<Block> = additionsPublisher

    private val deletionsPublisher: PublishSubject<Block> = PublishSubject.create()
    override val deletions: Observable<Block> = deletionsPublisher

    override fun addBlock(element: Block) {
        deleteBlock(element.elementId)
        blocks.add(element)
        additionsPublisher.onNext(element)
    }

    override fun deleteBlock(elementId: String) {
        blocks.filter {
            it.elementId == elementId
        }.forEach {
            blocks.remove(it)
            deletionsPublisher.onNext(it)
        }
    }

}