package io.github.slupik.schemablock.javafx.element.fx.port.connection.event

import io.github.slupik.schemablock.javafx.element.fx.port.element.Port

/**
 * All rights reserved & copyright ©
 */
sealed class ConnectionEvent

data class StartSearchingConnectionEvent(
    val source: Port
): ConnectionEvent()

data class StopSearchingConnectionEvent(
    val subject: Port
): ConnectionEvent()

data class CheckConnectionReadinessEvent(
    val subject: Port
): ConnectionEvent()

data class StopCheckingPortEvent(
    val subject: Port
): ConnectionEvent()

data class CheckConnectionPossibilityEvent(
    val source: Port,
    val target: Port
): ConnectionEvent()

data class TryConnectionEvent(
    val source: Port,
    val target: Port
): ConnectionEvent()