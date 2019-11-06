package io.github.slupik.schemablock.javafx.element.fx.port.connection

import io.github.slupik.schemablock.javafx.element.fx.port.connection.storage.ConditionalConnectionKey
import io.github.slupik.schemablock.javafx.element.fx.port.connection.storage.ConnectionStorageKey
import io.github.slupik.schemablock.javafx.element.fx.port.connection.storage.StandardConnectionKey
import io.github.slupik.schemablock.javafx.element.fx.port.connection.storage.TargetElementId

/**
 * All rights reserved & copyright ©
 */
interface ChainedElementProvider {

    fun getNextElement(sourceBlockId: String): TargetElementId? =
            getNextElement(StandardConnectionKey(sourceBlockId))

    fun getNextElement(sourceBlockId: String, result: Boolean): TargetElementId? =
            getNextElement(ConditionalConnectionKey(sourceBlockId, result))

    fun getNextElement(key: ConnectionStorageKey): TargetElementId?

}