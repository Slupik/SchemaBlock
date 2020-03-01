package io.github.slupik.schemablock.javafx.element.block.restorer

import com.google.gson.Gson
import io.github.slupik.schemablock.javafx.element.UiElementType
import io.github.slupik.schemablock.javafx.element.block.Block
import io.github.slupik.schemablock.javafx.element.block.implementation.*
import io.github.slupik.schemablock.javafx.element.block.settings.IoOperation
import io.github.slupik.schemablock.javafx.element.block.stringifier.BlockSpecification
import io.github.slupik.schemablock.javafx.element.block.stringifier.CodeAwareBlockSpecification
import io.github.slupik.schemablock.javafx.element.block.stringifier.FunctionalBlockSpecification
import io.github.slupik.schemablock.javafx.element.block.stringifier.IoBlockSpecification
import io.github.slupik.schemablock.javafx.element.fx.factory.UiBlockFactory
import io.github.slupik.schemablock.javafx.element.fx.schema.Schema
import javax.inject.Inject

/**
 * All rights reserved & copyright ©
 */
class BlockJsonRestorer @Inject constructor(
    private val jsonConverter: Gson
) : BlockRestorer {

    override fun restore(schema: Schema, blocksToRestore: List<String>) {
        blocksToRestore.convertToSpecification(jsonConverter)
            .forEach { specification ->
                val block = createBlock(specification)
                schema.addBlock(block)
                setupPosition(block, specification)
            }
    }

    private fun setupPosition(block: Block, specification: BlockSpecification) {
        when (specification) {
            is CodeAwareBlockSpecification -> {
                block.graphic.layoutX = specification.layoutX
                block.graphic.layoutY = specification.layoutY
            }
            is FunctionalBlockSpecification -> {
                block.graphic.layoutX = specification.layoutX
                block.graphic.layoutY = specification.layoutY
            }
            is IoBlockSpecification -> {
                block.graphic.layoutX = specification.layoutX
                block.graphic.layoutY = specification.layoutY
            }
        }
    }

    private fun createBlock(specification: BlockSpecification): Block =
        when (specification) {
            is CodeAwareBlockSpecification -> {
                if (specification.type == UiElementType.CALCULATION) {
                    createOperationsBlock(specification)
                } else {
                    createConditionalBlock(specification)
                }
            }
            is FunctionalBlockSpecification -> {
                if (specification.type == UiElementType.START) {
                    createStartBlock(specification)
                } else {
                    createStopBlock(specification)
                }
            }
            is IoBlockSpecification -> {
                createIoBlock(specification)
            }
        }

    private fun createIoBlock(specification: IoBlockSpecification): IoUiBlock =
        IoUiBlock(
            id = specification.id
        ).apply {
            description = specification.description

            specification.operations.map {
                IoOperation(
                    input = it.input,
                    code = it.command
                )
            }.forEach {
                addOperation(it)
            }
        }

    private fun createConditionalBlock(specification: CodeAwareBlockSpecification): ConditionalUiBlock =
        (UiBlockFactory.createUsableBlock(specification.type, specification.id) as ConditionalUiBlock)
            .apply {
                description = specification.description
                code = specification.content
            }

    private fun createOperationsBlock(specification: CodeAwareBlockSpecification): OperationsUiBlock =
        (UiBlockFactory.createUsableBlock(specification.type, specification.id) as OperationsUiBlock)
            .apply {
                description = specification.description
                code = specification.content
            }

    private fun createStopBlock(specification: FunctionalBlockSpecification): StopUiBlock =
        (UiBlockFactory.createUsableBlock(specification.type, specification.id) as StopUiBlock)
            .apply {
                description = specification.description
            }

    private fun createStartBlock(specification: FunctionalBlockSpecification): StartUiBlock =
        (UiBlockFactory.createUsableBlock(specification.type, specification.id) as StartUiBlock)
            .apply {
                description = specification.description
            }

}

private fun List<String>.convertToSpecification(jsonConverter: Gson): List<BlockSpecification> {
    return this.map { json ->
        val type = jsonConverter.fromJson(json, BlockTypeContainer::class.java).type
        when (type) {
            UiElementType.CALCULATION, UiElementType.IF -> {
                jsonConverter.fromJson(json, CodeAwareBlockSpecification::class.java)
            }
            UiElementType.IO -> {
                jsonConverter.fromJson(json, IoBlockSpecification::class.java)
            }
            else -> {
                jsonConverter.fromJson(json, FunctionalBlockSpecification::class.java)
            }
        }
    }
}

private data class BlockTypeContainer(
    val type: UiElementType
)
