package io.github.slupik.schemablock.javafx.element.block.wrapper.edition

import io.github.slupik.schemablock.javafx.element.block.Block
import io.github.slupik.schemablock.javafx.element.block.ConditionBlock
import io.github.slupik.schemablock.javafx.element.block.IOBlock
import io.github.slupik.schemablock.javafx.element.block.OperationsBlock
import io.github.slupik.schemablock.javafx.element.block.settings.CodeAndDescription
import io.github.slupik.schemablock.javafx.element.block.settings.DescriptionAndIO
import io.github.slupik.schemablock.javafx.element.block.settings.UiBlockSettings
import javafx.scene.control.Dialog
import javafx.scene.input.MouseButton
import javafx.scene.input.MouseEvent
import java.util.*

/**
 * All rights reserved & copyright ©
 */
internal class BlockEditionFeature(
    private val wrappee: Block
) {

    init {
        observeDoubleClicks()
    }

    private fun observeDoubleClicks() {
        wrappee.draggingMask.addEventHandler(MouseEvent.MOUSE_CLICKED) { event ->
            if (wrappee.background.contains(event.x, event.y)) {
                if (event.button == MouseButton.PRIMARY) {
                    if (event.clickCount == 2) {
                        onDoubleClick()
                    }
                }
            }
        }
    }

    private fun onDoubleClick() {
        val response = showDialog()
        if (response != null && response.isPresent) {
            applyChanges(response.get())
        }
    }

    private fun applyChanges(data: UiBlockSettings) {
        when (wrappee) {
            is ConditionBlock -> {
                if(data is CodeAndDescription) {
                    wrappee.description = data.description
                    wrappee.code = data.code
                }
            }
            is OperationsBlock -> {
                if(data is CodeAndDescription) {
                    wrappee.description = data.description
                    wrappee.code = data.code
                }
            }
            is IOBlock -> {
                if(data is DescriptionAndIO) {
                    wrappee.description = data.description
                    wrappee.operations = data.operations
                }
            }
        }
    }

    private fun showDialog(): Optional<UiBlockSettings>? {
        var dialog: Dialog<UiBlockSettings>? = null
        when (wrappee) {
            is ConditionBlock -> {
                dialog = DialogFactory.buildWithDescAndShortContent(
                        CodeAndDescription(description = wrappee.description, code = wrappee.code)
                )
            }
            is OperationsBlock -> {
                dialog = DialogFactory.buildWithDescAndContent(
                        CodeAndDescription(description = wrappee.description, code = wrappee.code)
                )
            }
            is IOBlock -> {
                dialog = DialogFactory.buildIO(
                        DescriptionAndIO(description = wrappee.description, operations = wrappee.operations)
                )
            }
        }
        return dialog?.showAndWait()
    }

}