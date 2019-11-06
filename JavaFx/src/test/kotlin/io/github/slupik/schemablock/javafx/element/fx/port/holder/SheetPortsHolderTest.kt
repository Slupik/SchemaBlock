package io.github.slupik.schemablock.javafx.element.fx.port.holder

import io.github.slupik.schemablock.javafx.element.fx.port.connection.establishment.EmptyBlock
import io.github.slupik.schemablock.javafx.element.fx.port.element.RoundedPort
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

/**
 * All rights reserved & copyright ©
 */
internal class SheetPortsHolderTest {

    private val sut: SheetPortsHolder = SheetPortsHolder()

    @Test
    fun `add port`(){
        val port = RoundedPort(EmptyBlock())
        assertEquals(0, sut.ports.size)

        sut.addPort(port, PortAccessibility.TWO_WAY)
        assertEquals(1, sut.ports.size)
    }

    @Test
    fun `add 2 ports with the same id`(){
        val exampleId = "example id"
        assertEquals(0, sut.ports.size)

        val port = RoundedPort(EmptyBlock(), exampleId)
        sut.addPort(port, PortAccessibility.TWO_WAY)
        assertEquals(1, sut.ports.size)

        val copy = RoundedPort(EmptyBlock(), exampleId)
        sut.addPort(copy, PortAccessibility.TWO_WAY)
        assertEquals(1, sut.ports.size)
    }

    @Test
    fun `add and remove port`(){
        val exampleId = "example id"
        assertEquals(0, sut.ports.size)

        val port = RoundedPort(EmptyBlock(), exampleId)
        sut.addPort(port, PortAccessibility.TWO_WAY)
        assertEquals(1, sut.ports.size)

        sut.deletePort(exampleId)
        assertEquals(0, sut.ports.size)
    }

}