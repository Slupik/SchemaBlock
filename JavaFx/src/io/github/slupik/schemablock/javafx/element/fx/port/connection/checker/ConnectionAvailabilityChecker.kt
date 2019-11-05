package io.github.slupik.schemablock.javafx.element.fx.port.connection.checker

import io.github.slupik.schemablock.javafx.element.fx.port.connection.PortConnectionConfiguration

/**
 * All rights reserved & copyright ©
 */
interface ConnectionAvailabilityChecker {

    fun isExistingSimilarConnections(
            configuration: PortConnectionConfiguration
    ): Boolean

    fun isConnectionPossible(
            configuration: PortConnectionConfiguration
    ): Boolean

}