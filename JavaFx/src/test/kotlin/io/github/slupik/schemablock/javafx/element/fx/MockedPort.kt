package io.github.slupik.schemablock.javafx.element.fx

import io.github.slupik.schemablock.javafx.element.block.Block
import io.github.slupik.schemablock.javafx.element.fx.port.element.Port
import javafx.scene.Node
import javafx.scene.layout.Pane
import org.apache.commons.lang3.RandomStringUtils
import org.mockito.Mockito

/**
 * All rights reserved & copyright ©
 */
class MockedPort constructor(
    override val elementId: String = RandomStringUtils.random(16),
    override val owner: Block = MockedBlock()
) : Port {

    override val mask: Node
        get() = Mockito.mock(Node::class.java)

    override val graphic: Pane
        get() = Mockito.mock(Pane::class.java)

    override fun markAsNeutral() {
    }

    override fun markAsActive() {
    }

    override fun markAsDisabled() {
    }

    override fun setRelativePos(percentOfWidth: Double, percentOfHeight: Double) {
    }

}