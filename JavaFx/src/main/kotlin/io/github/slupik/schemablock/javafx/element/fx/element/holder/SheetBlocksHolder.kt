package io.github.slupik.schemablock.javafx.element.fx.element.holder

import io.github.slupik.schemablock.javafx.dagger.LogicalSheet
import io.github.slupik.schemablock.javafx.element.block.Block
import io.github.slupik.schemablock.javafx.element.fx.port.holder.PortsHolder
import io.github.slupik.schemablock.javafx.element.fx.sheet.Sheet
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

/**
 * All rights reserved & copyright ©
 */
class SheetBlocksHolder @Inject constructor(
    @LogicalSheet private val sheet: Sheet,
    private val portsHolder: PortsHolder
) : BlocksHolder {

    override val blocks: MutableList<Block> = mutableListOf()

    private val additionsPublisher: PublishSubject<Block> = PublishSubject.create()
    override val additions: Observable<Block> = additionsPublisher

    private val deletionsPublisher: PublishSubject<Block> = PublishSubject.create()
    override val deletions: Observable<Block> = deletionsPublisher

    override fun addBlock(element: Block) {
        deleteBlock(element.elementId)
        sheet.removeElement(element)

        sheet.addElement(element)
        blocks.add(element)
        additionsPublisher.onNext(element)
    }

    override fun deleteBlock(elementId: String) {
        deletePorts(elementId)

        blocks.filter {
            it.elementId == elementId
        }.forEach {
            blocks.remove(it)
            deletionsPublisher.onNext(it)
        }
        sheet.removeElement(elementId)
    }

    private fun deletePorts(ownerId: String) {
        portsHolder.ports.filter {
            it.key.owner.elementId == ownerId
        }.forEach {
            portsHolder.deletePort(it.key)
        }
    }

}