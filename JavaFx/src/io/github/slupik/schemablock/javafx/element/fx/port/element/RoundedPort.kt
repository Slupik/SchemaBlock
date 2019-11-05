package io.github.slupik.schemablock.javafx.element.fx.port.element

import io.github.slupik.schemablock.javafx.element.block.Block
import javafx.scene.Node
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.Pane
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import org.apache.commons.lang3.RandomStringUtils

/**
 * All rights reserved & copyright ©
 */
class RoundedPort(
        override val owner: Block,
        override val elementId: String = RandomStringUtils.random(16, false, true)
): Port {

    override val graphic: Pane
        get() = mainElement
    override val mask: Node
        get() = circle

    private val mainElement = AnchorPane()
    private val circle = Circle()

    init {
        setupCircle()
        mainElement.children.add(circle)
        graphic.toFront()
    }

    override fun setRelativePos(percentOfWidth: Double, percentOfHeight: Double) {
        graphic.layoutXProperty().bind(owner.graphic.layoutXProperty().add(owner.graphic.widthProperty().multiply(percentOfWidth)))
        graphic.layoutYProperty().bind(owner.graphic.layoutYProperty().add(owner.graphic.heightProperty().multiply(percentOfHeight)))
        graphic.layoutXProperty().addListener { _ -> graphic.toFront() }
        graphic.layoutYProperty().addListener { _ -> graphic.toFront() }
    }

    private fun setupCircle() {
        circle.radius = 3.0
        markAsNeutral()
    }

    override fun markAsNeutral() {
        circle.fill = Color.BLACK
    }

    override fun markAsActive() {
        circle.fill = Color.GREEN
    }

    override fun markAsDisabled() {
        circle.fill = Color.DARKRED
    }

}