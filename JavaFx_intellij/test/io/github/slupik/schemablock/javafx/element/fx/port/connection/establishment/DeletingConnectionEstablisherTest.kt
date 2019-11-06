package io.github.slupik.schemablock.javafx.element.fx.port.connection.establishment

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.github.slupik.schemablock.javafx.element.fx.port.connection.BlockClearanceConfiguration
import io.github.slupik.schemablock.javafx.element.fx.port.connection.StandardPortsConnection
import io.github.slupik.schemablock.javafx.element.fx.port.connection.checker.ConnectionAvailabilityChecker
import io.github.slupik.schemablock.javafx.element.fx.port.connection.deleter.PortConnectionDeleter
import io.github.slupik.schemablock.javafx.element.fx.port.connection.storage.PortsConnectionsModifier
import io.github.slupik.schemablock.javafx.element.fx.port.element.RoundedPort
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito

/**
 * All rights reserved & copyright ©
 */
internal class DeletingConnectionEstablisherTest {

    @Mock
    private val checker: ConnectionAvailabilityChecker = Mockito.mock(ConnectionAvailabilityChecker::class.java)

    @Mock
    private val deleter: PortConnectionDeleter = Mockito.mock(PortConnectionDeleter::class.java)

    @Mock
    private val connectionsModifier: PortsConnectionsModifier = Mockito.mock(PortsConnectionsModifier::class.java)

    private lateinit var sut: DeletingConnectionEstablisher

//    companion object {
//        @BeforeAll
//        @JvmStatic
//        fun init() {
//            println("TEST")
//            MockitoAnnotations.initMocks(this)
//        }
//    }

//    @BeforeEach
//    fun initSut(){
//        MockitoAnnotations.initMocks(this)
//        sut = DeletingConnectionEstablisher(checker, deleter, connectionsModifier)
//    }

    @Test
    fun `add possible standard connection and check is added`() {
        sut = DeletingConnectionEstablisher(checker, deleter, connectionsModifier)

        whenever(checker.isConnectionPossible(any())).thenReturn(true)
        sut.establishConnection(
                StandardPortsConnection(
                        RoundedPort(EmptyBlock()),
                        RoundedPort(EmptyBlock())
                )
        )

        verify(connectionsModifier, times(1)).add(any(), any())
    }

    @Test
    fun `add possible standard connection and check is added and other connections are deleted`() {
        sut = DeletingConnectionEstablisher(checker, deleter, connectionsModifier)

        whenever(checker.isConnectionPossible(any())).thenReturn(true)
        whenever(checker.isExistingSimilarConnections(any())).thenReturn(true)

        sut.establishConnection(
                StandardPortsConnection(
                        RoundedPort(EmptyBlock()),
                        RoundedPort(EmptyBlock())
                )
        )

        verify(deleter, times(1)).clearConnections(any<BlockClearanceConfiguration>())
        verify(connectionsModifier, times(1)).add(any(), any())
    }

}