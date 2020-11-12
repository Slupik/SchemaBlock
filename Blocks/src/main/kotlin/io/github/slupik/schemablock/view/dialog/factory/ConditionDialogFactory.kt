package io.github.slupik.schemablock.view.dialog.factory

import io.github.slupik.schemablock.view.dialog.controller.ConditionDialogController
import io.github.slupik.schemablock.view.dialog.data.CodeAndDescription
import javafx.stage.Stage

/**
 * All rights reserved & copyright ©
 */
class ConditionDialogFactory constructor(
    xmlFileName: String,
    input: CodeAndDescription
) : DialogFactory<ConditionDialogController, CodeAndDescription>(xmlFileName, input) {

    override fun getMinimumHeight(): Double =
        170.0

    override fun additionalSetup(stage: Stage) {
        stage.maxHeight = 170.0
    }

}