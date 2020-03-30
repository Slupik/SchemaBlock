package io.github.slupik.schemablock.javafx.element.fx.port.element

import io.github.slupik.schemablock.javafx.element.block.Block
import javafx.scene.Node
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.Pane
import javafx.scene.paint.Color
import javafx.scene.paint.Color.web
import javafx.scene.paint.Paint
import javafx.scene.shape.Circle
import org.apache.commons.lang3.RandomStringUtils

/**
 * All rights reserved & copyright ©
 */
class RoundedPort(
    override val owner: Block,
    override val elementId: String = RandomStringUtils.random(16, false, true)
) : Port {

    override val graphic: Pane
        get() = mainElement
    override val mask: Node
        get() = circle

    private val mainElement = AnchorPane()
    private val circle = Circle()

    private var percentOfOwnerWidth: Double = 0.0
    private var percentOfOwnerHeight: Double = 0.0

    init {
        setupCircle()
        mainElement.children.add(circle)
        graphic.toFront()
    }

    override fun setRelativePos(percentOfWidth: Double, percentOfHeight: Double) {
        percentOfOwnerWidth = percentOfWidth
        percentOfOwnerHeight = percentOfHeight

        graphic.layoutXProperty()
            .bind(owner.graphic.layoutXProperty().add(owner.graphic.widthProperty().multiply(percentOfWidth)))
        graphic.layoutYProperty()
            .bind(owner.graphic.layoutYProperty().add(owner.graphic.heightProperty().multiply(percentOfHeight)))
        graphic.layoutXProperty().addListener { _ -> graphic.toFront() }
        graphic.layoutYProperty().addListener { _ -> graphic.toFront() }
    }

    override fun getRelativeX() =
        percentOfOwnerWidth

    override fun getRelativeY() =
        percentOfOwnerHeight

    private fun setupCircle() {
        circle.radius = 4.0
        circle.strokeWidth = 1.5
        circle.stroke = Color.BLACK
        markAsNeutral()
    }

    override fun markAsNeutral() {
        circle.fill = Color.WHITE
    }

    override fun markAsActive() {
        circle.fill = Color.LIGHTGREEN
    }

    override fun markAsDisabled() {
        circle.fill = Color.DARKRED
    }

}