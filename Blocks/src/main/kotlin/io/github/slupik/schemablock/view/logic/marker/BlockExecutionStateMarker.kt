package io.github.slupik.schemablock.view.logic.marker

import de.tesis.dynaware.grapheditor.core.skins.defaults.node.Block
import io.github.slupik.schemablock.view.logic.execution.diagram.ErrorEvent
import io.github.slupik.schemablock.view.logic.execution.diagram.ExecutionEnd
import io.github.slupik.schemablock.view.logic.execution.diagram.ExecutionEvent
import io.github.slupik.schemablock.view.logic.execution.diagram.PreExecutionEvent
import io.github.slupik.schemablock.view.logic.provider.BlocksProvider
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * All rights reserved & copyright ©
 */
class BlockExecutionStateMarker @Inject constructor(
    private val blocksProvider: BlocksProvider
) {

    private val composite = CompositeDisposable()
    private var lastExecutedBlock: Block? = null
    private var wasError = false

    fun handleObservable(observable: Observable<ExecutionEvent>) {
        blocksProvider.blocks.forEach { block -> block.markAsNeutral() }
        wasError = false
        composite.add(observable.subscribe(
            { executionEvent: ExecutionEvent ->
                if (executionEvent is PreExecutionEvent) {
                    executionEvent.executingBlock.markAsCurrent()
                    lastExecutedBlock?.markAsNeutral()
                    lastExecutedBlock = executionEvent.executingBlock
                }
                if (executionEvent is ExecutionEnd) {
                    if (!wasError) {
                        lastExecutedBlock?.markAsNeutral()
                    }
                    lastExecutedBlock = null
                }
                if (executionEvent is ErrorEvent) {
                    lastExecutedBlock?.markAsError()
                    wasError = true
                }
            },
            {
                lastExecutedBlock?.markAsError()
                wasError = true
            }
        ))
    }

}