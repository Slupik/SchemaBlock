package io.github.slupik.schemablock.javafx.element.block.implementation

import io.github.slupik.schemablock.javafx.element.UiElementType
import io.github.slupik.schemablock.javafx.element.background.CustomShapeBase
import io.github.slupik.schemablock.javafx.element.background.MyRectangle
import io.github.slupik.schemablock.javafx.element.block.OperationsBlock
import io.github.slupik.schemablock.javafx.element.block.implementation.DEFAULT_DESC
import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.layout.Pane
import javafx.scene.layout.VBox

/**
 * All rights reserved & copyright ©
 */
private const val DEFAULT_CODE = ""
private const val DEFAULT_DESC = "Operacje"

class OperationsUiBlock : EditableBlockPrototype, OperationsBlock {

    @FXML
    private lateinit var elementContainer: Pane

    @FXML
    private lateinit var descContainer: VBox

    @FXML
    private lateinit var desc: Label

    private var shape: MyRectangle = MyRectangle()

    override var code: String = DEFAULT_CODE

    constructor(id: String) : super(UiElementType.CALCULATION, id)

    constructor() : super(UiElementType.CALCULATION)

    init {
        description = DEFAULT_DESC
    }

    override val resourcePath: String
        get() = "/element/operatingElement.fxml"

    override fun getDescriptionLabel(): Label =
            desc

    override fun createBackgroundElement(): CustomShapeBase =
            shape

}