package io.github.slupik.schemablock.javafx.element.fx.port.connection.checker

import com.nhaarman.mockitokotlin2.whenever
import io.github.slupik.schemablock.javafx.element.block.Block
import io.github.slupik.schemablock.javafx.element.fx.port.connection.ConditionalPortsConnection
import io.github.slupik.schemablock.javafx.element.fx.port.connection.StandardPortsConnection
import io.github.slupik.schemablock.javafx.element.fx.port.connection.storage.ConditionalConnectionKey
import io.github.slupik.schemablock.javafx.element.fx.port.connection.storage.PortConnectionsHolder
import io.github.slupik.schemablock.javafx.element.fx.port.connection.storage.StandardConnectionKey
import io.github.slupik.schemablock.javafx.element.fx.port.element.Port
import io.github.slupik.schemablock.javafx.element.fx.port.holder.PortAccessibility
import io.github.slupik.schemablock.javafx.element.fx.port.holder.PortsHolder
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * All rights reserved & copyright ©
 */
internal class OwnerAwareAvailabilityCheckerTest {

    @Mock
    private val holder: PortsHolder = Mockito.mock(PortsHolder::class.java)

    @Mock
    private val connHolder: PortConnectionsHolder = Mockito.mock(PortConnectionsHolder::class.java)

    private lateinit var sut: OwnerAwareAvailabilityChecker

    companion object {
        @BeforeAll
        @JvmStatic
        fun init() {
            MockitoAnnotations.initMocks(this)
        }
    }

    @BeforeEach
    fun initSut() {
        sut = OwnerAwareAvailabilityChecker(holder, connHolder)
    }

    @Test
    fun `check connection without conflict`() {
        val source = getMockedPort("block1", "port1.1")
        val target = getMockedPort("block2", "port2.1")

        val ports = mapOf(
            Pair(source, PortAccessibility.TWO_WAY),
            Pair(getMockedPort("block1", "port1.2"), PortAccessibility.TWO_WAY),
            Pair(getMockedPort("block1", "port1.3"), PortAccessibility.TWO_WAY),
            Pair(getMockedPort("block1", "port1.4"), PortAccessibility.TWO_WAY),

            Pair(target, PortAccessibility.TWO_WAY),
            Pair(getMockedPort("block2", "port2.2"), PortAccessibility.TWO_WAY),
            Pair(getMockedPort("block2", "port2.3"), PortAccessibility.TWO_WAY),
            Pair(getMockedPort("block2", "port2.4"), PortAccessibility.TWO_WAY)
        )
        whenever(holder.ports).thenReturn(ports)

        whenever(connHolder.connections).thenReturn(
            mapOf()
        )

        assertTrue(
            sut.isConnectionPossible(
                StandardPortsConnection(
                    source,
                    target
                )
            )
        )
        assertFalse(
            sut.isExistingSimilarConnections(
                StandardPortsConnection(
                    source,
                    target
                )
            )
        )
    }

    @Test
    fun `check connection with existing connection with the same source`() {
        val source = getMockedPort("block1", "port1.1")
        val target = getMockedPort("block2", "port2.1")

        val existingTarget = getMockedPort("block2", "port2.2")

        val ports = mapOf(
            Pair(source, PortAccessibility.TWO_WAY),
            Pair(getMockedPort("block1", "port1.2"), PortAccessibility.TWO_WAY),
            Pair(getMockedPort("block1", "port2.3"), PortAccessibility.TWO_WAY),
            Pair(getMockedPort("block1", "port2.4"), PortAccessibility.TWO_WAY),

            Pair(target, PortAccessibility.TWO_WAY),
            Pair(existingTarget, PortAccessibility.TWO_WAY),
            Pair(getMockedPort("block2", "port2.3"), PortAccessibility.TWO_WAY),
            Pair(getMockedPort("block2", "port2.4"), PortAccessibility.TWO_WAY)
        )
        whenever(holder.ports).thenReturn(ports)

        val connection = Pair(StandardConnectionKey(source.elementId), existingTarget)
        whenever(connHolder.connections).thenReturn(
            mapOf(connection)
        )

        assertTrue(
            sut.isConnectionPossible(
                StandardPortsConnection(
                    source,
                    target
                )
            )
        )
        assertTrue(
            sut.isExistingSimilarConnections(
                StandardPortsConnection(
                    source,
                    target
                )
            )
        )
    }

    @Test
    fun `check connection with existing connection from the same block`() {
        val source = getMockedPort("block1", "port1.1")
        val target = getMockedPort("block2", "port2.1")

        val existingSource = getMockedPort("block1", "port1.2")
        val existingTarget = getMockedPort("block2", "port2.2")

        val ports = mapOf(
            Pair(source, PortAccessibility.TWO_WAY),
            Pair(existingSource, PortAccessibility.TWO_WAY),
            Pair(getMockedPort("block1", "port2.3"), PortAccessibility.TWO_WAY),
            Pair(getMockedPort("block1", "port2.4"), PortAccessibility.TWO_WAY),

            Pair(target, PortAccessibility.TWO_WAY),
            Pair(existingTarget, PortAccessibility.TWO_WAY),
            Pair(getMockedPort("block2", "port2.3"), PortAccessibility.TWO_WAY),
            Pair(getMockedPort("block2", "port2.4"), PortAccessibility.TWO_WAY)
        )
        whenever(holder.ports).thenReturn(ports)

        val connection = Pair(StandardConnectionKey(existingSource.elementId), existingTarget)
        whenever(connHolder.connections).thenReturn(
            mapOf(connection)
        )

        assertTrue(
            sut.isConnectionPossible(
                StandardPortsConnection(
                    source,
                    target
                )
            )
        )
        assertTrue(
            sut.isExistingSimilarConnections(
                StandardPortsConnection(
                    source,
                    target
                )
            )
        )
    }

    @Test
    fun `check connection to the same block`() {
        val source = getMockedPort("block1", "port1.1")
        val target = getMockedPort("block1", "port1.2")

        val ports = mapOf(
            Pair(source, PortAccessibility.TWO_WAY),
            Pair(target, PortAccessibility.TWO_WAY),
            Pair(getMockedPort("block1", "port2.3"), PortAccessibility.TWO_WAY),
            Pair(getMockedPort("block1", "port2.4"), PortAccessibility.TWO_WAY)
        )
        whenever(holder.ports).thenReturn(ports)

        whenever(connHolder.connections).thenReturn(
            mapOf()
        )

        assertFalse(
            sut.isConnectionPossible(
                StandardPortsConnection(
                    source,
                    target
                )
            )
        )
    }

    @Test
    fun `check connection to the same port`() {
        val source = getMockedPort("block1", "port1.1")

        val ports = mapOf(
            Pair(source, PortAccessibility.TWO_WAY),
            Pair(getMockedPort("block1", "port1.2"), PortAccessibility.TWO_WAY),
            Pair(getMockedPort("block1", "port2.3"), PortAccessibility.TWO_WAY),
            Pair(getMockedPort("block1", "port2.4"), PortAccessibility.TWO_WAY)
        )
        whenever(holder.ports).thenReturn(ports)

        whenever(connHolder.connections).thenReturn(
            mapOf()
        )

        assertFalse(
            sut.isConnectionPossible(
                StandardPortsConnection(
                    source,
                    source
                )
            )
        )
    }

    @Test
    fun `check connection from start to stop`() {
        val source = getMockedPort("start", "port1.1")
        val target = getMockedPort("stop", "port2.1")

        val ports = mapOf(
            Pair(source, PortAccessibility.ONLY_SOURCE),
            Pair(getMockedPort("start", "port1.2"), PortAccessibility.ONLY_SOURCE),
            Pair(getMockedPort("start", "port2.3"), PortAccessibility.ONLY_SOURCE),
            Pair(getMockedPort("start", "port2.4"), PortAccessibility.ONLY_SOURCE),

            Pair(target, PortAccessibility.ONLY_TARGET),
            Pair(getMockedPort("stop", "port2.2"), PortAccessibility.ONLY_TARGET),
            Pair(getMockedPort("stop", "port2.3"), PortAccessibility.ONLY_TARGET),
            Pair(getMockedPort("stop", "port2.4"), PortAccessibility.ONLY_TARGET)
        )
        whenever(holder.ports).thenReturn(ports)

        whenever(connHolder.connections).thenReturn(
            mapOf()
        )

        assertTrue(
            sut.isConnectionPossible(
                StandardPortsConnection(
                    source,
                    target
                )
            )
        )
    }

    @Test
    fun `check connection from stop to start`() {
        val source = getMockedPort("stop", "port2.1")
        val target = getMockedPort("start", "port1.1")

        val ports = mapOf(
            Pair(target, PortAccessibility.ONLY_SOURCE),
            Pair(getMockedPort("start", "port1.2"), PortAccessibility.ONLY_SOURCE),
            Pair(getMockedPort("start", "port2.3"), PortAccessibility.ONLY_SOURCE),
            Pair(getMockedPort("start", "port2.4"), PortAccessibility.ONLY_SOURCE),

            Pair(source, PortAccessibility.ONLY_TARGET),
            Pair(getMockedPort("stop", "port2.2"), PortAccessibility.ONLY_TARGET),
            Pair(getMockedPort("stop", "port2.3"), PortAccessibility.ONLY_TARGET),
            Pair(getMockedPort("stop", "port2.4"), PortAccessibility.ONLY_TARGET)
        )
        whenever(holder.ports).thenReturn(ports)

        whenever(connHolder.connections).thenReturn(
            mapOf()
        )

        assertFalse(
            sut.isConnectionPossible(
                StandardPortsConnection(
                    source,
                    target
                )
            )
        )
    }

    @Test
    fun `check connection from start to any normal block`() {
        val source = getMockedPort("start", "port1.1")
        val target = getMockedPort("operations", "port2.1")

        val ports = mapOf(
            Pair(source, PortAccessibility.ONLY_SOURCE),
            Pair(getMockedPort("start", "port1.2"), PortAccessibility.ONLY_SOURCE),
            Pair(getMockedPort("start", "port2.3"), PortAccessibility.ONLY_SOURCE),
            Pair(getMockedPort("start", "port2.4"), PortAccessibility.ONLY_SOURCE),

            Pair(target, PortAccessibility.TWO_WAY),
            Pair(getMockedPort("operations", "port2.2"), PortAccessibility.TWO_WAY),
            Pair(getMockedPort("operations", "port2.3"), PortAccessibility.TWO_WAY),
            Pair(getMockedPort("operations", "port2.4"), PortAccessibility.TWO_WAY)
        )
        whenever(holder.ports).thenReturn(ports)

        whenever(connHolder.connections).thenReturn(
            mapOf()
        )

        assertTrue(
            sut.isConnectionPossible(
                StandardPortsConnection(
                    source,
                    target
                )
            )
        )
    }

    @Test
    fun `check connection from start to conditional block`() {
        val source = getMockedPort("start", "port1.1")
        val target = getMockedPort("if", "port2.1")

        val ports = mapOf(
            Pair(source, PortAccessibility.ONLY_SOURCE),
            Pair(getMockedPort("start", "port1.2"), PortAccessibility.ONLY_SOURCE),
            Pair(getMockedPort("start", "port2.3"), PortAccessibility.ONLY_SOURCE),
            Pair(getMockedPort("start", "port2.4"), PortAccessibility.ONLY_SOURCE),

            Pair(target, PortAccessibility.CONDITIONAL_INPUT),
            Pair(getMockedPort("if", "port2.2"), PortAccessibility.CONDITIONAL_INPUT),
            Pair(getMockedPort("if", "port2.3"), PortAccessibility.CONDITIONAL_INPUT),
            Pair(getMockedPort("if", "port2.4"), PortAccessibility.CONDITIONAL_INPUT)
        )
        whenever(holder.ports).thenReturn(ports)

        whenever(connHolder.connections).thenReturn(
            mapOf()
        )

        assertTrue(
            sut.isConnectionPossible(
                StandardPortsConnection(
                    source,
                    target
                )
            )
        )
    }

    @Test
    fun `check connection from any normal block to stop`() {
        val source = getMockedPort("operations", "port1.1")
        val target = getMockedPort("stop", "port2.1")

        val ports = mapOf(
            Pair(source, PortAccessibility.TWO_WAY),
            Pair(getMockedPort("operations", "port1.2"), PortAccessibility.TWO_WAY),
            Pair(getMockedPort("operations", "port2.3"), PortAccessibility.TWO_WAY),
            Pair(getMockedPort("operations", "port2.4"), PortAccessibility.TWO_WAY),

            Pair(target, PortAccessibility.ONLY_TARGET),
            Pair(getMockedPort("stop", "port2.2"), PortAccessibility.ONLY_TARGET),
            Pair(getMockedPort("stop", "port2.3"), PortAccessibility.ONLY_TARGET),
            Pair(getMockedPort("stop", "port2.4"), PortAccessibility.ONLY_TARGET)
        )
        whenever(holder.ports).thenReturn(ports)

        whenever(connHolder.connections).thenReturn(
            mapOf()
        )

        assertTrue(
            sut.isConnectionPossible(
                StandardPortsConnection(
                    source,
                    target
                )
            )
        )
    }

    @Test
    fun `check conditional connection between standard blocks`() {
        val source = getMockedPort("block1", "port1.1")
        val target = getMockedPort("block2", "port2.1")

        val ports = mapOf(
            Pair(source, PortAccessibility.TWO_WAY),
            Pair(getMockedPort("block1", "port1.2"), PortAccessibility.TWO_WAY),
            Pair(getMockedPort("block1", "port1.3"), PortAccessibility.TWO_WAY),
            Pair(getMockedPort("block1", "port1.4"), PortAccessibility.TWO_WAY),

            Pair(target, PortAccessibility.TWO_WAY),
            Pair(getMockedPort("block2", "port2.2"), PortAccessibility.TWO_WAY),
            Pair(getMockedPort("block2", "port2.3"), PortAccessibility.TWO_WAY),
            Pair(getMockedPort("block2", "port2.4"), PortAccessibility.TWO_WAY)
        )
        whenever(holder.ports).thenReturn(ports)

        whenever(connHolder.connections).thenReturn(
            mapOf()
        )

        assertFalse(
            sut.isConnectionPossible(
                ConditionalPortsConnection(
                    source,
                    target,
                    true
                )
            )
        )
        assertFalse(
            sut.isExistingSimilarConnections(
                StandardPortsConnection(
                    source,
                    target
                )
            )
        )
    }

    @Test
    fun `check standard connection from conditional block`() {
        val source = getMockedPort("if", "port1.1")
        val target = getMockedPort("block2", "port2.1")

        val ports = mapOf(
            Pair(source, PortAccessibility.CONDITIONAL_INPUT),
            Pair(getMockedPort("if", "port1.2"), PortAccessibility.CONDITIONAL_INPUT),
            Pair(getMockedPort("if", "port2.3"), PortAccessibility.CONDITIONAL_INPUT),
            Pair(getMockedPort("if", "port2.4"), PortAccessibility.CONDITIONAL_INPUT),

            Pair(target, PortAccessibility.TWO_WAY),
            Pair(getMockedPort("block2", "port2.2"), PortAccessibility.TWO_WAY),
            Pair(getMockedPort("block2", "port2.3"), PortAccessibility.TWO_WAY),
            Pair(getMockedPort("block2", "port2.4"), PortAccessibility.TWO_WAY)
        )
        whenever(holder.ports).thenReturn(ports)

        whenever(connHolder.connections).thenReturn(
            mapOf()
        )

        assertFalse(
            sut.isConnectionPossible(
                StandardPortsConnection(
                    source,
                    target
                )
            )
        )
    }

    @Test
    fun `check connection from conditional to conditional block`() {
        val source = getMockedPort("if1", "port1.1")
        val target = getMockedPort("if2", "port2.1")

        val ports = mapOf(
            Pair(source, PortAccessibility.CONDITIONAL_INPUT),
            Pair(getMockedPort("if1", "port1.2"), PortAccessibility.CONDITIONAL_INPUT),
            Pair(getMockedPort("if1", "port2.3"), PortAccessibility.CONDITIONAL_INPUT),
            Pair(getMockedPort("if1", "port2.4"), PortAccessibility.CONDITIONAL_INPUT),

            Pair(target, PortAccessibility.CONDITIONAL_INPUT),
            Pair(getMockedPort("if2", "port2.2"), PortAccessibility.CONDITIONAL_INPUT),
            Pair(getMockedPort("if2", "port2.3"), PortAccessibility.CONDITIONAL_INPUT),
            Pair(getMockedPort("if2", "port2.4"), PortAccessibility.CONDITIONAL_INPUT)
        )
        whenever(holder.ports).thenReturn(ports)

        whenever(connHolder.connections).thenReturn(
            mapOf()
        )

        assertTrue(
            sut.isConnectionPossible(
                ConditionalPortsConnection(
                    source,
                    target,
                    true
                )
            )
        )
    }

    @Test
    fun `check connection from conditional block without any existing`() {
        val source = getMockedPort("if", "port1.1")
        val target = getMockedPort("block2", "port2.1")

        val ports = mapOf(
            Pair(source, PortAccessibility.CONDITIONAL_INPUT),
            Pair(getMockedPort("if", "port1.2"), PortAccessibility.CONDITIONAL_INPUT),
            Pair(getMockedPort("if", "port2.3"), PortAccessibility.CONDITIONAL_INPUT),
            Pair(getMockedPort("if", "port2.4"), PortAccessibility.CONDITIONAL_INPUT),

            Pair(target, PortAccessibility.TWO_WAY),
            Pair(getMockedPort("block2", "port2.2"), PortAccessibility.TWO_WAY),
            Pair(getMockedPort("block2", "port2.3"), PortAccessibility.TWO_WAY),
            Pair(getMockedPort("block2", "port2.4"), PortAccessibility.TWO_WAY)
        )
        whenever(holder.ports).thenReturn(ports)

        whenever(connHolder.connections).thenReturn(
            mapOf()
        )

        val newConnection =
            ConditionalPortsConnection(
                source,
                target,
                true
            )
        assertTrue(
            sut.isConnectionPossible(newConnection)
        )
        assertFalse(
            sut.isExistingSimilarConnections(newConnection)
        )
    }

    @Test
    fun `check connection from conditional block with existing connection but with different value`() {
        val source = getMockedPort("if", "port1.1")
        val target = getMockedPort("block2", "port2.1")

        val ports = mapOf(
            Pair(source, PortAccessibility.CONDITIONAL_INPUT),
            Pair(getMockedPort("if", "port1.2"), PortAccessibility.CONDITIONAL_INPUT),
            Pair(getMockedPort("if", "port2.3"), PortAccessibility.CONDITIONAL_INPUT),
            Pair(getMockedPort("if", "port2.4"), PortAccessibility.CONDITIONAL_INPUT),

            Pair(target, PortAccessibility.TWO_WAY),
            Pair(getMockedPort("block2", "port2.2"), PortAccessibility.TWO_WAY),
            Pair(getMockedPort("block2", "port2.3"), PortAccessibility.TWO_WAY),
            Pair(getMockedPort("block2", "port2.4"), PortAccessibility.TWO_WAY)
        )
        whenever(holder.ports).thenReturn(ports)

        val connection = Pair(
            ConditionalConnectionKey(
                sourcePortId = source.elementId,
                value = false
            ),
            target
        )
        whenever(connHolder.connections).thenReturn(
            mapOf(connection)
        )

        val newConnection =
            ConditionalPortsConnection(
                source,
                target,
                true
            )
        assertTrue(
            sut.isConnectionPossible(newConnection)
        )
        assertFalse(
            sut.isExistingSimilarConnections(newConnection)
        )
    }

    @Test
    fun `check connection from conditional block with existing connection but with same value`() {
        val source = getMockedPort("if", "port1.1")
        val target = getMockedPort("block2", "port2.1")

        val ports = mapOf(
            Pair(source, PortAccessibility.CONDITIONAL_INPUT),
            Pair(getMockedPort("if", "port1.2"), PortAccessibility.CONDITIONAL_INPUT),
            Pair(getMockedPort("if", "port2.3"), PortAccessibility.CONDITIONAL_INPUT),
            Pair(getMockedPort("if", "port2.4"), PortAccessibility.CONDITIONAL_INPUT),

            Pair(target, PortAccessibility.TWO_WAY),
            Pair(getMockedPort("block2", "port2.2"), PortAccessibility.TWO_WAY),
            Pair(getMockedPort("block2", "port2.3"), PortAccessibility.TWO_WAY),
            Pair(getMockedPort("block2", "port2.4"), PortAccessibility.TWO_WAY)
        )
        whenever(holder.ports).thenReturn(ports)

        val connection = Pair(
            ConditionalConnectionKey(
                sourcePortId = source.elementId,
                value = true
            ),
            target
        )
        whenever(connHolder.connections).thenReturn(
            mapOf(connection)
        )

        val newConnection =
            ConditionalPortsConnection(
                source,
                target,
                true
            )
        assertTrue(
            sut.isConnectionPossible(newConnection)
        )
        assertTrue(
            sut.isExistingSimilarConnections(newConnection)
        )
    }

    private fun getMockedPort(blockId: String, portId: String): Port {
        val block = Mockito.mock(Block::class.java)
        whenever(block.elementId).thenReturn(blockId)

        val port = Mockito.mock(Port::class.java)
        whenever(port.owner).thenReturn(block)
        whenever(port.elementId).thenReturn(portId)

        return port
    }

}