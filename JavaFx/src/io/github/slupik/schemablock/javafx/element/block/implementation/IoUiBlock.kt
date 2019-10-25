package io.github.slupik.schemablock.javafx.element.block.implementation

import io.github.slupik.schemablock.javafx.element.UiElementType
import io.github.slupik.schemablock.javafx.element.background.CustomShapeBase
import io.github.slupik.schemablock.javafx.element.background.Parallelogram
import io.github.slupik.schemablock.javafx.element.block.IOBlock
import io.github.slupik.schemablock.javafx.element.block.implementation.DEFAULT_DESC
import io.github.slupik.schemablock.javafx.element.block.settings.IoOperation
import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.layout.Pane
import javafx.scene.layout.VBox

/**
 * All rights reserved & copyright ©
 */
private const val DEFAULT_DESC = ""

class IoUiBlock : EditableBlockPrototype, IOBlock {

    @FXML
    private lateinit var elementContainer: Pane

    @FXML
    private lateinit var descContainer: VBox

    @FXML
    private lateinit var desc: Label

    private var shape: Parallelogram = Parallelogram()

    override var operations: List<IoOperation>
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
        set(value) {}

    constructor(id: String) : super(UiElementType.IO, id)

    constructor() : super(UiElementType.IO)

    init {
        description = DEFAULT_DESC
    }

    override val resourcePath: String
        get() = "/element/ioElement.fxml"

    override fun getDescriptionLabel(): Label =
            desc

    override fun createBackgroundElement(): CustomShapeBase =
            shape

}