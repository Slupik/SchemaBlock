package io.github.slupik.schemablock.javafx.element.block.stringifier

import com.google.gson.Gson
import io.github.slupik.schemablock.javafx.element.block.Block
import io.github.slupik.schemablock.javafx.element.block.ConditionBlock
import io.github.slupik.schemablock.javafx.element.block.IOBlock
import io.github.slupik.schemablock.javafx.element.block.OperationsBlock
import io.github.slupik.schemablock.javafx.element.block.settings.IoOperation
import javax.inject.Inject

/**
 * All rights reserved & copyright ©
 */
class BlockJsonStringifier @Inject constructor(
    private val jsonConverter: Gson
) : BlockStringifier {

    override fun stringify(block: Block): String =
        jsonConverter.toJson(
            when (block) {
                is OperationsBlock, is ConditionBlock -> {
                    CodeAwareBlockSpecification(
                        type = block.type,
                        id = block.elementId,
                        description = block.description,
                        layoutX = block.background.layoutX,
                        layoutY = block.background.layoutY,
                        content = getCode(block)
                    )
                }
                is IOBlock -> {
                    IoBlockSpecification(
                        type = block.type,
                        id = block.elementId,
                        description = block.description,
                        layoutX = block.background.layoutX,
                        layoutY = block.background.layoutY,
                        operations = getOperations(block.operations)
                    )
                }
                else -> {
                    FunctionalBlockSpecification(
                        type = block.type,
                        id = block.elementId,
                        description = block.description,
                        layoutX = block.background.layoutX,
                        layoutY = block.background.layoutY
                    )
                }
            }
        )

    private fun getCode(block: Block): String =
        when (block) {
            is OperationsBlock -> block.code
            is ConditionBlock -> block.code
            else -> ""
        }

    private fun getOperations(operations: List<IoOperation>): List<IoOperationSpecification> =
        operations.map {
            IoOperationSpecification(
                input = it.input,
                command = it.code
            )
        }

}