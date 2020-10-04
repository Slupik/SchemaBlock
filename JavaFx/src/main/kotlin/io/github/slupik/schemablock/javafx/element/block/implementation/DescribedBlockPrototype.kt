package io.github.slupik.schemablock.javafx.element.block.implementation

import io.github.slupik.schemablock.javafx.element.UiElementType
import io.github.slupik.schemablock.javafx.element.background.CustomShapeBase
import io.github.slupik.schemablock.javafx.element.block.Block
import io.github.slupik.schemablock.javafx.element.block.size.ElementSizeController
import io.github.slupik.schemablock.javafx.element.block.size.ReactiveElementSizeController
import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.control.Label
import javafx.scene.layout.Pane
import org.apache.commons.lang3.RandomStringUtils
import java.io.IOException

/**
 * All rights reserved & copyright ©
 */
abstract class DescribedBlockPrototype constructor(
        override val type: UiElementType,
        override val elementId: String = RandomStringUtils.random(16)
) : Pane(), Block {

    private lateinit var backgroundShape: CustomShapeBase
    override val background: Pane
        get() = backgroundShape
    override var description: String
        get() = getDescriptionLabel().text
        set(value) {
            getDescriptionLabel().text = value
        }
    override val graphic: Pane
        get() = this
    override val draggingMask: Node
        get() = graphic

    protected abstract val resourcePath: String
    private lateinit var sizeController: ElementSizeController

    init {
        initStructure()
    }

    override fun setup() {
        initBackground()
        setupResizing()
    }

    private fun setupResizing() {
        sizeController = ReactiveElementSizeController(
                this,
                backgroundShape,
                getDescriptionLabel()
        )
        sizeController.bindElements()
    }

    abstract fun getDescriptionLabel(): Label

    private fun initStructure() {
        val fxmlLoader = FXMLLoader(getJavaClass().getResource(resourcePath))
        fxmlLoader.setRoot(this)
        fxmlLoader.setController(this)

        try {
            /* Without: "val content"
            [ERROR] Kotlin: Type inference failed: Not enough information to infer parameter T in fun <T : Any!> load(): T!
            Please specify it explicitly.
             */
            val content: Parent = fxmlLoader.load()
        } catch (exception: IOException) {
            throw RuntimeException(exception)
        }

    }

    private fun getJavaClass() = DescribedBlockPrototype::class.java

    private fun initBackground() {
        backgroundShape = createBackgroundElement()
        backgroundShape.resetColor()
        children.add(backgroundShape)
        backgroundShape.toBack()
    }

    protected abstract fun createBackgroundElement(): CustomShapeBase

    override fun setElementSize(width: Double, height: Double) {
        sizeController.setSize(width, height)
    }

    override fun setElementWidth(width: Double) {
        sizeController.setWidth(width)
    }

    override fun setElementHeight(height: Double) {
        sizeController.setHeight(height)
    }

    override fun markAsError() {
        backgroundShape.markAsError()
    }

    override fun markAsExecuting() {
        backgroundShape.markAsExecuting()
    }

    override fun markAsStop() {
        backgroundShape.resetColor()
    }

}
