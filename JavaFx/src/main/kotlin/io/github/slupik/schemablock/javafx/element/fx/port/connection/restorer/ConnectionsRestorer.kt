package io.github.slupik.schemablock.javafx.element.fx.port.connection.restorer

import io.github.slupik.schemablock.javafx.element.fx.schema.Schema

/**
 * All rights reserved & copyright ©
 */
interface ConnectionsRestorer {

    fun restore(schema: Schema, connectionsToRestore: List<String>)

}