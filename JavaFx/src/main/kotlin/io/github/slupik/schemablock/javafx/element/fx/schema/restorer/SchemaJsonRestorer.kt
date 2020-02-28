package io.github.slupik.schemablock.javafx.element.fx.schema.restorer

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.github.slupik.schemablock.javafx.element.UiElementType
import io.github.slupik.schemablock.javafx.element.block.restorer.BlockRestorer
import io.github.slupik.schemablock.javafx.element.fx.schema.Schema
import io.github.slupik.schemablock.javafx.element.fx.schema.stringifier.SchemaSpecification
import java.lang.reflect.Type
import javax.inject.Inject

/**
 * All rights reserved & copyright ©
 */

class SchemaJsonRestorer @Inject constructor(
    private val jsonConverter: Gson,
    private val blockRestorer: BlockRestorer
) : SchemaRestorer {

    override fun load(schema: Schema, input: String) {
        val specification = jsonConverter.fromJson(input, SchemaSpecification::class.java)

        schema.clearAll()

        specification.blocks
            .convertToStringList(jsonConverter)
            .restoreBlocks(blockRestorer, schema)

//        val stringifiedPorts: List<String> = jsonConverter.fromJson(specification.ports, listType)
//        val stringifiedConnections: List<String> = jsonConverter.fromJson(specification.connections, listType)
    }

}

private fun List<String>.restoreBlocks(
    blockRestorer: BlockRestorer,
    schema: Schema
) {
    blockRestorer.restore(schema, this)
}

private fun String.convertToStringList(jsonConverter: Gson): List<String> {
    val listType: Type = object : TypeToken<List<String>>() {}.type
    return jsonConverter.fromJson(this, listType)
}

data class BlockTypeContainer(
    val type: UiElementType
)
