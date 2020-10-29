package io.github.slupik.schemablock.view.dialog

import de.tesis.dynaware.grapheditor.core.skins.defaults.node.Block
import de.tesis.dynaware.grapheditor.core.skins.defaults.node.ConditionalBlock
import de.tesis.dynaware.grapheditor.core.skins.defaults.node.IoBlock
import de.tesis.dynaware.grapheditor.core.skins.defaults.node.OperationsBlock
import io.github.slupik.schemablock.view.dialog.data.CodeAndDescription
import io.github.slupik.schemablock.view.dialog.data.DescriptionAndIO
import io.github.slupik.schemablock.view.dialog.data.UiBlockSettings
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
            is ConditionalBlock -> {
                if (data is CodeAndDescription) {
                    block.setDescription(data.description)
                    block.code = data.code
                }
            }
            is OperationsBlock -> {
                if (data is CodeAndDescription) {
                    block.setDescription(data.description)
                    block.code = data.code
                }
            }
            is IoBlock -> {
                if (data is DescriptionAndIO) {
                    block.setDescription(data.description)
                    block.operations = data.operations
                }
            }
        }
    }

    private fun showDialog(block: Block): Optional<UiBlockSettings>? {
        var dialog: Dialog<UiBlockSettings>? = null
        when (block) {
            is ConditionalBlock -> {
                dialog = DialogFactory.buildWithDescAndShortContent(
                    CodeAndDescription(description = block.description, code = block.code)
                )
            }
            is OperationsBlock -> {
                dialog = DialogFactory.buildWithDescAndContent(
                    CodeAndDescription(description = block.description, code = block.code)
                )
            }
            is IoBlock -> {
                dialog = DialogFactory.buildIO(
                    DescriptionAndIO(description = block.description, operations = block.operations)
                )
            }
        }
        return dialog?.showAndWait()
    }

}