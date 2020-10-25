package io.github.slupik.schemablock.view.logic.provider


/**
 * All rights reserved & copyright ©
 */

typealias TargetElementId = String

interface ChainedElementProvider {

    fun getStartElementId(): TargetElementId?

    fun getNextElement(sourceBlockId: String): TargetElementId?

    fun getNextElement(sourceBlockId: String, result: Boolean): TargetElementId?

}