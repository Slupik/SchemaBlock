package io.github.slupik.schemablock.javafx.element.fx.port.connector

import io.github.slupik.schemablock.javafx.element.fx.port.PortInfo
import io.github.slupik.schemablock.model.utils.RandomString
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

/**
 * All rights reserved & copyright ©
 */
internal class PortsConnectionsStorageTest {

    private val sut: PortsConnectionsStorage = PortsConnectionsStorage()

    @Test
    fun `add standard connection and remove it`() {
        val source = getRandomPort()
        val target = getRandomPort()

        sut.add(
                StandardPortsConnection(
                        source,
                        target
                )
        )
        sut.remove(
                StandardDisconnection(source)
        )

        assertEquals(0, sut.connections.size)
    }

    @Test
    fun `add conditional connection and remove it`() {
        val source = getRandomPort()
        val target = getRandomPort()

        sut.add(
                ConditionalPortsConnection(
                        source,
                        target,
                        true
                )
        )
        sut.remove(
                ConditionalPortsDisconnection(source, true)
        )

        assertEquals(0, sut.connections.size)
    }

    @Test
    fun `add conditional connection and try to remove with wrong value`() {
        val source = getRandomPort()
        val target = getRandomPort()

        sut.add(
                ConditionalPortsConnection(
                        source,
                        target,
                        true
                )
        )
        sut.remove(
                ConditionalPortsDisconnection(source, false)
        )

        assertEquals(1, sut.connections.size)
    }

    @Test
    fun `add conditional connection and get that connection`() {
        val source = getRandomPort()
        val target = getRandomPort()

        sut.add(
                ConditionalPortsConnection(
                        source,
                        target,
                        true
                )
        )

        assertEquals(
                target.parentElementId,
                sut.getNextElement(
                        source.parentElementId,
                        true
                )
        )
    }

    @Test
    fun `add conditional connection and get null because of wrong request`() {
        val source = getRandomPort()
        val target = getRandomPort()

        sut.add(
                ConditionalPortsConnection(
                        source,
                        target,
                        false
                )
        )

        assertEquals(
                null,
                sut.getNextElement(
                        source.parentElementId,
                        true
                )
        )
    }

    @Test
    fun `add standard connection and get that connection`() {
        val source = getRandomPort()
        val target = getRandomPort()

        sut.add(
                StandardPortsConnection(
                        source,
                        target
                )
        )

        assertEquals(
                target.parentElementId,
                sut.getNextElement(source.parentElementId)
        )
    }

    private fun getRandomPort(): PortInfo {
        val port = PortInfo()
        port.id = RandomString.generate(4)
        port.endPortName = RandomString.generate(4)
        port.parentElementId = RandomString.generate(4)
        return port
    }

}