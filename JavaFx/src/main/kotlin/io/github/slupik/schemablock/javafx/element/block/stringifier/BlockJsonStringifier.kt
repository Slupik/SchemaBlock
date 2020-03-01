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
        when (block) {
            is OperationsBlock, is ConditionBlock -> {
                jsonConverter.toJson(
                    CodeAwareBlockSpecification(
                        type = block.type,
                        id = block.elementId,
                        description = block.description,
                        layoutX = block.graphic.layoutX,
                        layoutY = block.graphic.layoutY,
                        content = getCode(block)
                    )
                )
            }
            is IOBlock -> {
                jsonConverter.toJson(
                    IoBlockSpecification(
                        type = block.type,
                        id = block.elementId,
                        description = block.description,
                        layoutX = block.graphic.layoutX,
                        layoutY = block.graphic.layoutY,
                        operations = getOperations(block.operations)
                    )
                )
            }
            else -> {
                jsonConverter.toJson(
                    FunctionalBlockSpecification(
                        type = block.type,
                        id = block.elementId,
                        description = block.description,
                        layoutX = block.graphic.layoutX,
                        layoutY = block.graphic.layoutY
                    )
                )
            }
        }

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