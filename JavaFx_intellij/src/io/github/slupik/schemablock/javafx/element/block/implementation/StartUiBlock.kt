package io.github.slupik.schemablock.javafx.element.block.implementation

import io.github.slupik.schemablock.javafx.element.UiElementType
import io.github.slupik.schemablock.javafx.element.background.CustomShapeBase
import io.github.slupik.schemablock.javafx.element.background.MyEllipse
import io.github.slupik.schemablock.javafx.element.block.StartBlock
import io.github.slupik.schemablock.javafx.element.block.implementation.DEFAULT_DESC as DEFAULT_DESC
import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.layout.Pane
import javafx.scene.layout.VBox

/**
 * All rights reserved & copyright ©
 */
private const val DEFAULT_DESC = "START"

class StartUiBlock : DescribedBlockPrototype, StartBlock {

    @FXML
    private lateinit var elementContainer: Pane

    @FXML
    private lateinit var descContainer: VBox

    @FXML
    private lateinit var desc: Label

    private var shape: MyEllipse = MyEllipse()

    constructor(id: String) : super(UiElementType.START, id)

    constructor() : super(UiElementType.START)

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