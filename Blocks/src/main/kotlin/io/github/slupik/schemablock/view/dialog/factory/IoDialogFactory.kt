package io.github.slupik.schemablock.view.dialog.factory

import io.github.slupik.schemablock.view.dialog.controller.IoDialogController
import io.github.slupik.schemablock.view.dialog.data.DescriptionAndIO

/**
 * All rights reserved & copyright ©
 */
class IoDialogFactory constructor(
    xmlFileName: String,
    input: DescriptionAndIO
) : DialogFactory<IoDialogController, DescriptionAndIO>(xmlFileName, input) {

    override fun getMinimumHeight(): Double =
        500.0

}