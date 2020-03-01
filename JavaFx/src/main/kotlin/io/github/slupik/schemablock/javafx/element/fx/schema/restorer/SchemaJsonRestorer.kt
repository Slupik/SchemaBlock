package io.github.slupik.schemablock.javafx.element.fx.schema.restorer

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.github.slupik.schemablock.javafx.element.block.restorer.BlockRestorer
import io.github.slupik.schemablock.javafx.element.fx.port.connection.restorer.ConnectionsRestorer
import io.github.slupik.schemablock.javafx.element.fx.port.restorer.PortRestorer
import io.github.slupik.schemablock.javafx.element.fx.schema.Schema
import io.github.slupik.schemablock.javafx.element.fx.schema.stringifier.SchemaSpecification
import java.lang.reflect.Type
import javax.inject.Inject

/**
 * All rights reserved & copyright ©
 */

class SchemaJsonRestorer @Inject constructor(
    private val jsonConverter: Gson,
    private val blockRestorer: BlockRestorer,
    private val portRestorer: PortRestorer,
    private val connectionsRestorer: ConnectionsRestorer
) : SchemaRestorer {

    override fun load(schema: Schema, input: String) {
        val specification = jsonConverter.fromJson(input, SchemaSpecification::class.java)

        schema.clearAll()

        specification.blocks
            .convertToStringList(jsonConverter)
            .restoreBlocks(blockRestorer, schema)

        specification.ports
            .convertToStringList(jsonConverter)
            .restorePorts(portRestorer, schema)

        specification.connections
            .convertToStringList(jsonConverter)
            .restoreConnections(connectionsRestorer, schema)
    }

}

private fun List<String>.restoreBlocks(
    blockRestorer: BlockRestorer,
    schema: Schema
) {
    blockRestorer.restore(schema, this)
}

private fun List<String>.restorePorts(
    portRestorer: PortRestorer,
    schema: Schema
) {
    portRestorer.restore(schema, this)
}

private fun List<String>.restoreConnections(
    connectionsRestorer: ConnectionsRestorer,
    schema: Schema
) {
    connectionsRestorer.restore(schema, this)
}

private fun String.convertToStringList(jsonConverter: Gson): List<String> {
    val listType: Type = object : TypeToken<List<String>>() {}.type
    return jsonConverter.fromJson(this, listType)
}