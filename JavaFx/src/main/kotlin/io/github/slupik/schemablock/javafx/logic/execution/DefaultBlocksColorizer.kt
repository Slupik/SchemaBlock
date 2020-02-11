package io.github.slupik.schemablock.javafx.logic.execution

import io.github.slupik.schemablock.javafx.element.block.Block
import io.github.slupik.schemablock.logic.executor.diagram.*
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

/**
 * All rights reserved & copyright ©
 */
class DefaultBlocksColorizer @Inject constructor(): BlocksColorizer {

    private var disposable: Disposable? = null
    private val blockExecutingState = mutableMapOf<Block, Boolean>()

    override fun inject(eventsEmitter: Observable<ExecutionEvent>) {
        disposable = eventsEmitter.subscribeBy(
            onNext = { executionEvent ->
                when (executionEvent) {
                    is ExecutionStart -> {
                        eraseAllMarks()
                    }
                    is PreExecutionEvent -> {
                        unmarkPreviouslyExecuted()
                        executionEvent.executingBlock.markAsExecuting()
                        blockExecutingState[executionEvent.executingBlock] = false
                    }
                    is PostExecutionEvent -> {
                        blockExecutingState[executionEvent.executedBlock] = true
                    }
                    is ErrorEvent -> {
                        handleError(executionEvent.error)
                    }
                    is ExecutionEnd -> {
                        unmarkPreviouslyExecuted()
                    }
                }
            },
            onError = {
                handleError(it)
            }
        )
    }

    private fun eraseAllMarks() {
        blockExecutingState.forEach { (block, _) ->
            block.markAsStop()
        }
        blockExecutingState.clear()
    }

    private fun handleError(error: Throwable) {
        error.printStackTrace()
        markAsErrorAllExecutingBlocks()
        unmarkPreviouslyExecuted()
    }

    private fun markAsErrorAllExecutingBlocks() {
        blockExecutingState.filter { entry ->
            entry.value.not()
        }.forEach { (block, _) ->
            block.markAsError()
        }
    }

    private fun unmarkPreviouslyExecuted() {
        blockExecutingState.filter { entry ->
            entry.value
        }.forEach { (block, _) ->
            block.markAsStop()
            blockExecutingState.remove(block)
        }
    }

}