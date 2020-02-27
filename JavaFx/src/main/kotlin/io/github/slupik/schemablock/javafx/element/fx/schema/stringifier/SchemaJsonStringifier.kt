package io.github.slupik.schemablock.javafx.element.fx.schema.stringifier

import com.google.gson.Gson
import io.github.slupik.schemablock.javafx.element.block.stringifier.BlockStringifier
import io.github.slupik.schemablock.javafx.element.fx.element.holder.BlocksHolder
import io.github.slupik.schemablock.javafx.element.fx.port.connection.storage.PortsConnectionProvider
import io.github.slupik.schemablock.javafx.element.fx.port.connection.stringifier.PortConnectionStringifier
import io.github.slupik.schemablock.javafx.element.fx.port.holder.PortsHolder
import io.github.slupik.schemablock.javafx.element.fx.port.stringifier.PortStringifier
import io.github.slupik.schemablock.javafx.element.fx.schema.Schema
import javax.inject.Inject

/**
 * All rights reserved & copyright ©
 */
class SchemaJsonStringifier @Inject constructor(
    private val jsonConverter: Gson,
    private val blocksHolder: BlocksHolder,
    private val blockStringifier: BlockStringifier,
    private val portsHolder: PortsHolder,
    private val portStringifier: PortStringifier,
    private val portsConnectionProvider: PortsConnectionProvider,
    private val portConnectionStringifier: PortConnectionStringifier
) : SchemaStringifier {

    //TODO use parameter
    override fun stringify(schema: Schema): String =
        jsonConverter.toJson(
            SchemaSpecification(
                version = "",
                blocks = getBlocksJson(),
                connections = getConnectionsJson(),
                ports = getPortsJson()
            )
        )

    private fun getBlocksJson(): String =
        blocksHolder.blocks.map {
            blockStringifier.stringify(it)
        }.let {
            jsonConverter.toJson(it)
        }

    private fun getConnectionsJson(): String =
        portsConnectionProvider.connections.entries.map {
            portConnectionStringifier.stringify(it.key, it.value)
        }.let {
            jsonConverter.toJson(it)
        }

    private fun getPortsJson(): String =
        portsHolder.ports.entries.map {
            portStringifier.stringify(it.key, it.value)
        }.let {
            jsonConverter.toJson(it)
        }

}