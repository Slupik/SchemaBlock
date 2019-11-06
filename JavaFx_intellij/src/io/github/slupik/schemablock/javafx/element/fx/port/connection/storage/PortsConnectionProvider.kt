package io.github.slupik.schemablock.javafx.element.fx.port.connection.storage

import io.github.slupik.schemablock.javafx.element.fx.port.element.Port

/**
 * All rights reserved & copyright ©
 */
typealias TargetPort = Port

interface PortsConnectionProvider {

    val connections: Map<ConnectionStorageKey, TargetPort>

}