package io.github.slupik.schemablock.view.dialog.factory

import io.github.slupik.schemablock.view.dialog.controller.OperationsDialogController
import io.github.slupik.schemablock.view.dialog.data.CodeAndDescription

/**
 * All rights reserved & copyright ©
 */
class OperationsDialogFactory constructor(
    xmlFileName: String,
    input: CodeAndDescription
) : DialogFactory<OperationsDialogController, CodeAndDescription>(xmlFileName, input) {

    override fun getMinimumHeight(): Double =
        500.0

}