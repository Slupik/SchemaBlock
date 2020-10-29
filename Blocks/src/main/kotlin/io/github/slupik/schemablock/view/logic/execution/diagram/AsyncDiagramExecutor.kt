package io.github.slupik.schemablock.view.logic.execution.diagram

import de.tesis.dynaware.grapheditor.core.skins.UiElementType
import de.tesis.dynaware.grapheditor.core.skins.defaults.node.Block
import io.github.slupik.schemablock.view.logic.execution.block.BlockExecutor
import io.github.slupik.schemablock.view.logic.execution.block.ExecutionResult
import io.github.slupik.schemablock.view.logic.execution.block.Logic
import io.github.slupik.schemablock.view.logic.execution.block.Void
import io.github.slupik.schemablock.view.logic.execution.diagram.exception.NextBlockNotFound
import io.github.slupik.schemablock.view.logic.execution.diagram.exception.StartBlockNotFound
import io.github.slupik.schemablock.view.logic.provider.BlocksProvider
import io.github.slupik.schemablock.view.logic.provider.ChainedElementProvider
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Inject

/**
 * All rights reserved & copyright ©
 */
class AsyncDiagramExecutor @Inject constructor(
    private val blocksProvider: BlocksProvider,
    private val elementProvider: ChainedElementProvider,
    private val blockExecutor: BlockExecutor
) : DiagramExecutor {

    private val executor: ExecutorService = Executors.newSingleThreadExecutor()

    private var stop = false;
    private var publisher = PublishSubject.create<ExecutionEvent>()
    override val eventSource: Observable<ExecutionEvent>
        get() = publisher


    override fun resetState() {
        publisher = PublishSubject.create<ExecutionEvent>()
    }

    override fun run(): Unit =
        debug(ContinuousExecutionController())

    override fun debug(controller: DiagramExecutionController) {
         executor.submit {
            val startingId = getStartBlock()
            if (startingId != null) {
                publisher.onNext(ExecutionStart)
                executeBlock(publisher, controller, startingId)
            } else {
                onStartBlockNotFound()
            }
        }
    }

    override fun stop() {
        stop = true
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
            publisher.onNext(ExecutionEnd)
            publisher.onComplete()
        } else {
            try {
                onPreExecute(block)
                val result = blockExecutor.execute(block)
                onPostExecute(block, result)

                if (!stop) {
                    executeNextBlock(controller, block, result)
                } else {
                    stop = false
                }
            } catch (t: Throwable) {
                publisher.onNext(ErrorEvent(t))
                publisher.onNext(ExecutionEnd)
                publisher.onComplete()
            }
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
            executor.submit {
                val nextBlock = getNextBlock(currentBlock.node.id, result)
                if (nextBlock != null) {
                    executeBlock(publisher, controller, nextBlock)
                } else {
                    onNextBlockNotFound(currentBlock)
                }
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
            blocksProvider.getBlock(nextBlockId)
        }

    private fun getStartBlock(): Block? =
        elementProvider
            .getStartElementId()
            ?.let { id ->
                blocksProvider.getBlock(id)
            }

}