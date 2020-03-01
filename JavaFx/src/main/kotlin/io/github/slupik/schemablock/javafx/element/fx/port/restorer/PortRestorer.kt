package io.github.slupik.schemablock.javafx.element.fx.port.restorer

import io.github.slupik.schemablock.javafx.element.fx.schema.Schema

/**
 * All rights reserved & copyright ©
 */
interface PortRestorer {

    fun restore(schema: Schema, portsToRestore: List<String>)

}