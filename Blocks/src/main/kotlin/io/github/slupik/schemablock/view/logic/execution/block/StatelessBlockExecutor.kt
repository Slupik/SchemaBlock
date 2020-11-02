package io.github.slupik.schemablock.view.logic.execution.block

import de.tesis.dynaware.grapheditor.core.skins.defaults.node.Block
import de.tesis.dynaware.grapheditor.core.skins.defaults.node.ConditionalBlock
import de.tesis.dynaware.grapheditor.core.skins.defaults.node.IoBlock
import de.tesis.dynaware.grapheditor.core.skins.defaults.node.OperationsBlock
import io.github.slupik.schemablock.model.ui.implementation.element.specific.IOCommunicable
import io.github.slupik.schemablock.model.ui.newparser.HeapController
import io.github.slupik.schemablock.newparser.executor.Executor
import io.github.slupik.schemablock.newparser.memory.ValueFactory
import io.github.slupik.schemablock.view.dialog.data.IoOperation
import javax.inject.Inject

/**
 * All rights reserved & copyright ©
 */
class StatelessBlockExecutor @Inject constructor(
    private val executor: Executor,
    private val communicator: IOCommunicable,
    private val heap: HeapController
) : BlockExecutor {

    override fun execute(block: Block): ExecutionResult =
        when (block) {
            is OperationsBlock -> {
                executeOperations(block.code)
                Void
            }
            is IoBlock -> {
                executeIoOperations(block.operations)
                Void
            }
            is ConditionalBlock -> {
                val result = executeCondition(block.code)
                Logic(result)
            }
            else -> {
                Void
            }
        }

    private fun executeOperations(code: String) {
        executor.execute(code)
    }

    private fun executeIoOperations(operations: List<IoOperation>) {
        for (operation in operations) {
            if (operation.input) {
                val input = communicator.input
                val value = ValueFactory.createValue(
                    heap.getVariableType(operation.code),
                    input
                )
                heap.setVariableValue(operation.code, value)
            } else {
                val output = executor.getResult(operation.code).value.toString()
                communicator.print(output)
            }
        }
    }

    private fun executeCondition(code: String): Boolean {
        val result = executor.getResult(code)
        return result.getCastedValue()
    }

}