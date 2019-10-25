package io.github.slupik.schemablock.javafx.element.block.implementation

import io.github.slupik.schemablock.javafx.element.UiElementType
import io.github.slupik.schemablock.javafx.element.background.CustomShapeBase
import io.github.slupik.schemablock.javafx.element.background.Rhombus
import io.github.slupik.schemablock.javafx.element.block.ConditionBlock
import io.github.slupik.schemablock.javafx.element.block.implementation.DEFAULT_DESC
import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.layout.Pane
import javafx.scene.layout.VBox

/**
 * All rights reserved & copyright ©
 */
private const val DEFAULT_CODE = ""
private const val DEFAULT_DESC = ""

class ConditionalUiBlock : EditableBlockPrototype, ConditionBlock {

    @FXML
    private lateinit var elementContainer: Pane

    @FXML
    private lateinit var descContainer: VBox

    @FXML
    private lateinit var desc: Label

    private var shape: Rhombus = Rhombus()

    constructor(id: String) : super(UiElementType.IF, id)

    constructor() : super(UiElementType.IF)

    init {
        description = DEFAULT_DESC
    }

    override val resourcePath: String
        get() = "/element/conditionElement.fxml"

    override var code: String = DEFAULT_CODE

    override fun getDescriptionLabel(): Label =
            desc

    override fun createBackgroundElement(): CustomShapeBase =
            shape

}