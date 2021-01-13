package io.github.slupik.schemablock.logic.executor.block

import io.github.slupik.schemablock.javafx.element.block.Block
import io.github.slupik.schemablock.javafx.element.block.ConditionBlock
import io.github.slupik.schemablock.javafx.element.block.IOBlock
import io.github.slupik.schemablock.javafx.element.block.OperationsBlock
import io.github.slupik.schemablock.javafx.element.block.settings.IoOperation
import io.github.slupik.schemablock.model.ui.implementation.element.specific.IOCommunicable
import io.github.slupik.schemablock.model.ui.newparser.HeapController
import io.github.slupik.schemablock.newparser.executor.Executor
import io.github.slupik.schemablock.newparser.memory.ValueFactory
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
        when(block) {
            is OperationsBlock -> {
                executeOperations(block.code)
                Void
            }
            is IOBlock -> {
                executeIoOperations(block.operations)
                Void
            }
            is ConditionBlock -> {
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
        for(operation in operations) {
            if(operation.input) {
                val input = communicator.input
                if (input == null) {
                    System.err.println("Execution of block was interrupted by null string");
                    return
                }
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