package io.github.slupik.schemablock.javafx.element.fx.port.connection.deleter

import io.github.slupik.schemablock.javafx.element.fx.port.connection.BlockClearanceConfiguration
import io.github.slupik.schemablock.javafx.element.fx.port.connection.PortDisconnectionConfiguration
import io.github.slupik.schemablock.javafx.element.fx.port.connection.storage.ConnectionStorageKey
import io.reactivex.Observable

/**
 * All rights reserved & copyright ©
 */

interface ConnectionDeleter {

    val deletions: Observable<ConnectionStorageKey>

    fun deleteConnection(configuration: PortDisconnectionConfiguration)
    fun clearConnections(configuration: BlockClearanceConfiguration)
    fun clearConnections(blockId: String)

}