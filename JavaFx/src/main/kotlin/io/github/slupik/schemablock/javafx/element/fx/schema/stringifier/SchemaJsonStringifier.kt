package io.github.slupik.schemablock.javafx.element.fx.schema.stringifier

import com.google.gson.Gson
import io.github.slupik.schemablock.javafx.element.block.Block
import io.github.slupik.schemablock.javafx.element.block.stringifier.BlockStringifier
import io.github.slupik.schemablock.javafx.element.fx.port.connection.storage.ConnectionStorageKey
import io.github.slupik.schemablock.javafx.element.fx.port.connection.storage.TargetPort
import io.github.slupik.schemablock.javafx.element.fx.port.connection.stringifier.PortConnectionStringifier
import io.github.slupik.schemablock.javafx.element.fx.port.element.Port
import io.github.slupik.schemablock.javafx.element.fx.port.holder.PortAccessibility
import io.github.slupik.schemablock.javafx.element.fx.port.stringifier.PortStringifier
import io.github.slupik.schemablock.javafx.element.fx.schema.Schema
import javax.inject.Inject

/**
 * All rights reserved & copyright ©
 */
const val CONVERTER_VERSION = "1.0.0"

class SchemaJsonStringifier @Inject constructor(
    private val jsonConverter: Gson,
    private val blockStringifier: BlockStringifier,
    private val portStringifier: PortStringifier,
    private val portConnectionStringifier: PortConnectionStringifier
) : SchemaStringifier {

    override fun stringify(schema: Schema): String =
        jsonConverter.toJson(
            SchemaSpecification(
                version = CONVERTER_VERSION,
                blocks = getBlocksJson(schema.getBlocks()),
                connections = getConnectionsJson(schema.getConnections()),
                ports = getPortsJson(schema.getPorts())
            )
        )

    private fun getBlocksJson(blocks: List<Block>): String =
        blocks.map {
            blockStringifier.stringify(it)
        }.let {
            jsonConverter.toJson(it)
        }

    private fun getConnectionsJson(connections: Map<ConnectionStorageKey, TargetPort>): String =
        connections.map {
            portConnectionStringifier.stringify(it.key, it.value)
        }.let {
            jsonConverter.toJson(it)
        }

    private fun getPortsJson(ports: Map<Port, PortAccessibility>): String =
        ports.entries.map {
            portStringifier.stringify(it.key, it.value)
        }.let {
            jsonConverter.toJson(it)
        }

}