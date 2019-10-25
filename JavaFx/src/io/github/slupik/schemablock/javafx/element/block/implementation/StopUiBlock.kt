package io.github.slupik.schemablock.javafx.element.block.implementation

import io.github.slupik.schemablock.javafx.element.UiElementType
import io.github.slupik.schemablock.javafx.element.background.CustomShapeBase
import io.github.slupik.schemablock.javafx.element.background.MyRectangle
import io.github.slupik.schemablock.javafx.element.block.StopBlock
import io.github.slupik.schemablock.javafx.element.block.implementation.DEFAULT_DESC
import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.layout.Pane
import javafx.scene.layout.VBox

/**
 * All rights reserved & copyright ©
 */
private const val DEFAULT_DESC = "STOP"

class StopUiBlock : DescribedBlockPrototype, StopBlock {

    @FXML
    private lateinit var elementContainer: Pane

    @FXML
    private lateinit var descContainer: VBox

    @FXML
    private lateinit var desc: Label

    private var shape: MyRectangle = MyRectangle()

    constructor(id: String) : super(UiElementType.STOP, id)

    constructor() : super(UiElementType.STOP)

    init {
        description = DEFAULT_DESC
    }

    override val resourcePath: String
        get() = "/element/specialElement.fxml"

    override fun getDescriptionLabel(): Label =
            desc

    override fun createBackgroundElement(): CustomShapeBase =
            shape

}