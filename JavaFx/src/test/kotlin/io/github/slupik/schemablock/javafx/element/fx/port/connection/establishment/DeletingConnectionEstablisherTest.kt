package io.github.slupik.schemablock.javafx.element.fx.port.connection.establishment

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.github.slupik.schemablock.javafx.element.fx.MockedBlock
import io.github.slupik.schemablock.javafx.element.fx.port.connection.BlockClearanceConfiguration
import io.github.slupik.schemablock.javafx.element.fx.port.connection.StandardPortsConnection
import io.github.slupik.schemablock.javafx.element.fx.port.connection.checker.ConnectionAvailabilityChecker
import io.github.slupik.schemablock.javafx.element.fx.port.connection.deleter.ConnectionDeleter
import io.github.slupik.schemablock.javafx.element.fx.port.connection.storage.PortConnectionsHolder
import io.github.slupik.schemablock.javafx.element.fx.port.element.RoundedPort
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * All rights reserved & copyright ©
 */
internal class DeletingConnectionEstablisherTest {

    @Mock
    private val checker: ConnectionAvailabilityChecker = Mockito.mock(ConnectionAvailabilityChecker::class.java)

    @Mock
    private val deleter: ConnectionDeleter = Mockito.mock(ConnectionDeleter::class.java)

    @Mock
    private val connectionsModifier: PortConnectionsHolder = Mockito.mock(PortConnectionsHolder::class.java)

    private lateinit var sut: DeletingConnectionEstablisher

    companion object {
        @BeforeAll
        @JvmStatic
        fun init() {
            MockitoAnnotations.initMocks(this)
        }
    }

    @BeforeEach
    fun initSut() {
        sut = DeletingConnectionEstablisher(checker, deleter, connectionsModifier)
    }

    @Test
    fun `add possible standard connection and check is added`() {
        whenever(checker.isConnectionPossible(any())).thenReturn(true)
        val configuration = StandardPortsConnection(
            RoundedPort(MockedBlock()),
            RoundedPort(MockedBlock())
        )

        sut.establishConnection(configuration)

        verify(connectionsModifier, times(1)).add(any())
    }

    @Test
    fun `add possible standard connection and check is added and other connections are deleted`() {
        whenever(checker.isConnectionPossible(any())).thenReturn(true)
        whenever(checker.isExistingSimilarConnections(any())).thenReturn(true)
        val configuration =
            StandardPortsConnection(
                RoundedPort(MockedBlock()),
                RoundedPort(MockedBlock())
            )

        sut.establishConnection(configuration)

        verify(deleter, times(1)).clearConnections(any<BlockClearanceConfiguration>())
        verify(connectionsModifier, times(1)).add(any())
    }

}