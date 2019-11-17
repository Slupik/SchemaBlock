package io.github.slupik.schemablock.logic.executor.diagram

import io.github.slupik.schemablock.javafx.element.UiElementType
import io.github.slupik.schemablock.javafx.element.block.Block
import io.github.slupik.schemablock.javafx.element.fx.element.holder.BlocksHolder
import io.github.slupik.schemablock.javafx.element.fx.port.connection.ChainedElementProvider
import io.github.slupik.schemablock.logic.executor.block.BlockExecutor
import io.github.slupik.schemablock.logic.executor.block.ExecutionResult
import io.github.slupik.schemablock.logic.executor.block.Logic
import io.github.slupik.schemablock.logic.executor.block.Void
import io.github.slupik.schemablock.logic.executor.diagram.exception.NextBlockNotFound
import io.github.slupik.schemablock.logic.executor.diagram.exception.StartBlockNotFound
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

/**
 * All rights reserved & copyright ©
 */
class OneTimeDiagramExecutor @Inject constructor(
    private val blocksHolder: BlocksHolder,
    private val elementProvider: ChainedElementProvider,
    private val blockExecutor: BlockExecutor
) : DiagramExecutor {

    private val publisher = PublishSubject.create<ExecutionEvent>()

    override fun run(): Observable<ExecutionEvent> =
        debug(ContinuousExecutionController())

    override fun debug(controller: DiagramExecutionController): Observable<ExecutionEvent> {
        val startingId = getStartBlock()
        if (startingId != null) {
            executeBlock(publisher, controller, startingId)
        } else {
            onStartBlockNotFound()
        }
        return publisher
    }

    private fun onStartBlockNotFound() {
        publisher.onError(
            StartBlockNotFound()
        )
    }

    private fun executeBlock(
        publisher: PublishSubject<ExecutionEvent>,
        controller: DiagramExecutionController,
        block: Block
    ) {
        if (block.type == UiElementType.STOP) {
            publisher.onComplete()
        } else {
            onPreExecute(block)
            val result = blockExecutor.execute(block)
            onPostExecute(block, result)

            executeNextBlock(controller, block, result)
        }
    }

    private fun onNextBlockNotFound(currentBlock: Block) {
        publisher.onError(
            NextBlockNotFound(currentBlock)
        )
    }

    private fun onPostExecute(block: Block, result: ExecutionResult) {
        publisher.onNext(
            PostExecutionEvent(
                block,
                result
            )
        )
    }

    private fun onPreExecute(block: Block) {
        publisher.onNext(
            PreExecutionEvent(
                block
            )
        )
    }

    private fun executeNextBlock(
        controller: DiagramExecutionController,
        currentBlock: Block,
        result: ExecutionResult
    ) {
        controller.resumeExecutionOnDemand {
            val nextBlock = getNextBlock(currentBlock.elementId, result)
            if (nextBlock != null) {
                executeBlock(publisher, controller, nextBlock)
            } else {
                onNextBlockNotFound(currentBlock)
            }
        }
    }

    private fun getNextBlock(
        currentBlockId: String,
        result: ExecutionResult
    ): Block? =
        when (result) {
            is Void -> {
                elementProvider.getNextElement(currentBlockId)
            }
            is Logic -> {
                elementProvider.getNextElement(currentBlockId, result.value)
            }
        }?.let { nextBlockId ->
            blocksHolder.getBlock(nextBlockId)
        }

    private fun getStartBlock(): Block? =
        elementProvider
            .getStartElementId()
            ?.let { id ->
                blocksHolder.getBlock(id)
            }

}