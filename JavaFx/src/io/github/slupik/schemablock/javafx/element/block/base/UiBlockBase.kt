package io.github.slupik.schemablock.javafx.element.block.base

import io.github.slupik.schemablock.javafx.element.UiElementType
import io.github.slupik.schemablock.javafx.element.background.CustomShapeBase
import io.github.slupik.schemablock.javafx.element.block.UiBlock
import io.github.slupik.schemablock.javafx.element.block.extension.size.ElementSizeController
import io.github.slupik.schemablock.javafx.element.block.extension.size.ReactiveElementSizeController
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.control.Label
import javafx.scene.layout.Pane
import org.apache.commons.lang3.RandomStringUtils
import java.io.IOException

/**
 * All rights reserved & copyright ©
 */
abstract class UiBlockBase constructor(
        override val type: UiElementType,
        override val elementId: String
) : Pane(), UiBlock {

    private lateinit var background: CustomShapeBase
    private lateinit var sizeController: ElementSizeController

    override var description: String
        get() = getDescriptionLabel().text
        set(value) {
            getDescriptionLabel().text = value
        }

    constructor(type: UiElementType) : this(type, RandomStringUtils.random(16))

    init {
        initStructure()
        initBackground()
        setupResizing()
    }

    private fun setupResizing() {
        sizeController = ReactiveElementSizeController(
                this,
                background,
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

    protected abstract val resourcePath: String

    private fun getJavaClass() = this.javaClass::class.java

    private fun initBackground() {
        background = createBackgroundElement()
        background.resetColor()
        children.add(background)
        background.toBack()
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
        background.markAsError()
    }

    override fun markAsExecuting() {
        background.markAsExecuting()
    }

    override fun markAsStop() {
        background.resetColor()
    }

}
