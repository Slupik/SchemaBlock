package io.github.slupik.schemablock.javafx.element.block.size

import io.github.slupik.schemablock.javafx.element.background.CustomShape
import javafx.application.Platform
import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.layout.Pane
import javafx.scene.layout.Region
import javafx.scene.layout.VBox
import javafx.scene.shape.Ellipse

/**
 * All rights reserved & copyright ©
 */
class ReactiveElementSizeController constructor(
        private val container: Pane,
        private val background: CustomShape,
        private val description: Label
) : ElementSizeController {

    private val textSizeProvider: TextSizeProvider = TextSizeProvider(description)

    override fun bindElements() {
        container.prefWidth = background.outerWidth
        container.prefHeight = background.outerHeight

        background.outerWidthProperty().bind(container.prefWidthProperty())
        background.outerHeightProperty().bind(container.prefHeightProperty())

        bindDescriptionContainerSize()

        description.maxWidthProperty().bind(background.innerWidthProperty())
        description.maxHeightProperty().bind(background.innerHeightProperty())

        setupListenersTriggeringFontChange()
    }

    private fun bindDescriptionContainerSize() {
        val descContainer = getDescContainer()
        if (descContainer != null) {
            descContainer.minWidthProperty().bind(container.widthProperty())
            descContainer.minHeightProperty().bind(container.heightProperty())
            descContainer.prefWidthProperty().bind(container.widthProperty())
            descContainer.prefHeightProperty().bind(container.heightProperty())
            descContainer.maxWidthProperty().bind(container.widthProperty())
            descContainer.maxHeightProperty().bind(container.heightProperty())

            if(descContainer is VBox) {
                descContainer.alignment = Pos.CENTER
            }
        } else {
            throw RuntimeException("Container of description of element should be a type of Region but it's not.")
        }
    }

    private fun getDescContainer(): Region? =
            if (description.parent is Region) {
                description.parent as Region
            } else {
                null
            }

    private fun setupListenersTriggeringFontChange() {
        background.innerWidthProperty().addListener { _, _, newValue ->
            resetFontSize(newValue.toDouble(), background.innerHeight)
        }
        background.innerHeightProperty().addListener { _, _, newValue ->
            resetFontSize(background.innerWidth, newValue.toDouble())
        }

        description.textProperty().addListener { _, _, _ ->
            resetFontSize(background.innerWidth, background.innerHeight)
        }
    }

    private fun resetFontSize(width: Double, height: Double) {
        val font = textSizeProvider.getFontForContainerSize(width, height)
        description.font = font
    }

    override fun setSize(width: Double, height: Double) {
        container.prefWidth = width
        container.prefHeight = height
        resetPos()
    }

    override fun setWidth(width: Double) {
        container.prefWidth = width
        resetPos()
    }

    override fun setHeight(height: Double) {
        container.prefHeight = height
        resetPos()
    }

    private fun resetPos() {
        Platform.runLater {
            if (background is Ellipse) {
                container.layoutX = container.width / 2
                container.layoutY = container.height / 2
            }
        }
    }

}