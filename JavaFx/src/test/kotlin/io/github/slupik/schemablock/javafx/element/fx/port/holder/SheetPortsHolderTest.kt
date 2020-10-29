package io.github.slupik.schemablock.javafx.element.fx.port.holder

import io.github.slupik.schemablock.javafx.element.fx.MockedBlock
import io.github.slupik.schemablock.javafx.element.fx.port.element.RoundedPort
import io.github.slupik.schemablock.javafx.element.fx.sheet.Sheet
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals

/**
 * All rights reserved & copyright ©
 */
internal class SheetPortsHolderTest {

    @InjectMocks
    lateinit var sut: SheetPortsHolder

    @Mock
    lateinit var sheet: Sheet

    @BeforeEach
    fun init() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `add port`(){
        val port = RoundedPort(MockedBlock())
        assertEquals(0, sut.ports.size)

        sut.addPort(port, PortAccessibility.TWO_WAY)
        assertEquals(1, sut.ports.size)
    }

    @Test
    fun `add 2 ports with the same id`(){
        val exampleId = "example id"
        assertEquals(0, sut.ports.size)

        val port = RoundedPort(MockedBlock(), exampleId)
        sut.addPort(port, PortAccessibility.TWO_WAY)
        assertEquals(1, sut.ports.size)

        val copy = RoundedPort(MockedBlock(), exampleId)
        sut.addPort(copy, PortAccessibility.TWO_WAY)
        assertEquals(1, sut.ports.size)
    }

    @Test
    fun `add and remove port`(){
        val exampleId = "example id"
        assertEquals(0, sut.ports.size)

        val port = RoundedPort(MockedBlock(), exampleId)
        sut.addPort(port, PortAccessibility.TWO_WAY)
        assertEquals(1, sut.ports.size)

        sut.deletePort(exampleId)
        assertEquals(0, sut.ports.size)
    }

}