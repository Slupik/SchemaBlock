package io.github.slupik.schemablock.view.logic.communication.output

/**
 * All rights reserved & copyright ©
 */
interface ErrorTranslator {

    fun translateError(error: Throwable): String

}