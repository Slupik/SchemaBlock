package io.github.slupik.schemablock.javafx.element.fx.port.connection

import io.github.slupik.schemablock.javafx.element.fx.port.connection.storage.TargetElementId

/**
 * All rights reserved & copyright ©
 */
interface ChainedElementProvider {

    fun getStartElementId(): TargetElementId?

    fun getNextElement(sourceBlockId: String): TargetElementId?

    fun getNextElement(sourceBlockId: String, result: Boolean): TargetElementId?

}