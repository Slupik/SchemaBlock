package io.github.slupik.schemablock.javafx.element.block.wrapper.edition

import io.github.slupik.schemablock.javafx.element.block.Block
import io.github.slupik.schemablock.javafx.element.block.ConditionBlock
import io.github.slupik.schemablock.javafx.element.block.IOBlock
import io.github.slupik.schemablock.javafx.element.block.OperationsBlock
import io.github.slupik.schemablock.javafx.element.block.settings.CodeAndDescription
import io.github.slupik.schemablock.javafx.element.block.settings.DescriptionAndIO
import io.github.slupik.schemablock.javafx.element.block.settings.UiBlockSettings
import javafx.scene.control.Dialog
import java.util.*
import javax.inject.Inject

/**
 * All rights reserved & copyright ©
 */
class BlockEditionWithDialog @Inject constructor() : BlockEdition {

    override fun openFor(block: Block) {
        val response = showDialog(block)
        if (response != null && response.isPresent) {
            applyChanges(block, response.get())
        }
    }

    private fun applyChanges(block: Block, data: UiBlockSettings) {
        when (block) {
            is ConditionBlock -> {
                if (data is CodeAndDescription) {
                    block.description = data.description
                    block.code = data.code
                }
            }
            is OperationsBlock -> {
                if (data is CodeAndDescription) {
                    block.description = data.description
                    block.code = data.code
                }
            }
            is IOBlock -> {
                if (data is DescriptionAndIO) {
                    block.description = data.description
                    block.operations = data.operations
                }
            }
        }
    }

    private fun showDialog(block: Block): Optional<UiBlockSettings>? {
        var dialog: Dialog<UiBlockSettings>? = null
        when (block) {
            is ConditionBlock -> {
                dialog = DialogFactory.buildWithDescAndShortContent(
                    CodeAndDescription(description = block.description, code = block.code)
                )
            }
            is OperationsBlock -> {
                dialog = DialogFactory.buildWithDescAndContent(
                    CodeAndDescription(description = block.description, code = block.code)
                )
            }
            is IOBlock -> {
                dialog = DialogFactory.buildIO(
                    DescriptionAndIO(description = block.description, operations = block.operations)
                )
            }
        }
        return dialog?.showAndWait()
    }

}