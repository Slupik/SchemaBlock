package io.github.slupik.schemablock.javafx.element.fx.port.connection


/**
 * All rights reserved & copyright ©
 */

typealias TargetElementId = String

interface ChainedElementProvider {

    fun getStartElementId(): TargetElementId?

    fun getNextElement(sourceBlockId: String): TargetElementId?

    fun getNextElement(sourceBlockId: String, result: Boolean): TargetElementId?

}