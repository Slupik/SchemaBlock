package io.github.slupik.schemablock.javafx.element.fx.port.connection.establishment

import io.github.slupik.schemablock.javafx.element.fx.port.connection.storage.PortsConnectionsStorage

/**
 * All rights reserved & copyright ©
 */
internal class PortsConnectionsStorageTest {

    private val sut: PortsConnectionsStorage = PortsConnectionsStorage()

    //TODO repair
//    @Test
//    fun `add standard connection and remove it`() {
//        val source = getRandomPort()
//        val target = getRandomPort()
//
//        sut.add(
//                StandardPortsConnection(
//                        source,
//                        target
//                )
//        )
//        assertEquals(1, sut.connections.size)
//        sut.remove(
//                StandardDisconnection(source)
//        )
//
//        assertEquals(0, sut.connections.size)
//    }
//
//    @Test
//    fun `add conditional connection and remove it`() {
//        val source = getRandomPort()
//        val target = getRandomPort()
//
//        sut.add(
//                ConditionalPortsConnection(
//                        source,
//                        target,
//                        true
//                )
//        )
//        sut.remove(
//                ConditionalPortsDisconnection(source, true)
//        )
//
//        assertEquals(0, sut.connections.size)
//    }
//
//    @Test
//    fun `add conditional connection and try to remove with wrong value`() {
//        val source = getRandomPort()
//        val target = getRandomPort()
//
//        sut.add(
//                ConditionalPortsConnection(
//                        source,
//                        target,
//                        true
//                )
//        )
//        sut.remove(
//                ConditionalPortsDisconnection(source, false)
//        )
//
//        assertEquals(1, sut.connections.size)
//    }
//
//    @Test
//    fun `add conditional connection and get that connection`() {
//        val source = getRandomPort()
//        val target = getRandomPort()
//
//        sut.add(
//                ConditionalPortsConnection(
//                        source,
//                        target,
//                        true
//                )
//        )
//
//        assertEquals(
//                target.owner.elementId,
//                sut.getNextElement(
//                        source.owner.elementId,
//                        true
//                )
//        )
//    }
//
//    @Test
//    fun `add conditional connection and get null because of wrong request`() {
//        val source = getRandomPort()
//        val target = getRandomPort()
//
//        sut.add(
//                ConditionalPortsConnection(
//                        source,
//                        target,
//                        false
//                )
//        )
//
//        assertEquals(
//                null,
//                sut.getNextElement(
//                        source.owner.elementId,
//                        true
//                )
//        )
//    }
//
//    @Test
//    fun `add standard connection and get that connection`() {
//        val source = getRandomPort()
//        val target = getRandomPort()
//
//        sut.add(
//                StandardPortsConnection(
//                        source,
//                        target
//                )
//        )
//
//        assertEquals(
//                target.owner.elementId,
//                sut.getNextElement(source.owner.elementId)
//        )
//    }
//
//    private fun getRandomPort(): Port = RoundedPort(
//                owner = EmptyBlock(),
//                elementId = RandomString.generate(4)
//        )

}