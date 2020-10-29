package io.github.slupik.schemablock.javafx.element.fx.port.connection.restorer

import com.google.gson.Gson
import io.github.slupik.schemablock.javafx.element.fx.port.connection.ConditionalPortsConnection
import io.github.slupik.schemablock.javafx.element.fx.port.connection.StandardPortsConnection
import io.github.slupik.schemablock.javafx.element.fx.port.connection.stringifier.ConditionalConnectionSpecification
import io.github.slupik.schemablock.javafx.element.fx.port.connection.stringifier.ConnectionSpecification
import io.github.slupik.schemablock.javafx.element.fx.port.connection.stringifier.StandardConnectionSpecification
import io.github.slupik.schemablock.javafx.element.fx.port.element.Port
import io.github.slupik.schemablock.javafx.element.fx.port.holder.PortsHolder
import io.github.slupik.schemablock.javafx.element.fx.schema.Schema
import javax.inject.Inject

/**
 * All rights reserved & copyright ©
 */
class ConnectionsJsonRestorer @Inject constructor(
    private val jsonConverter: Gson,
    private val portsHolder: PortsHolder
) : ConnectionsRestorer {

    override fun restore(schema: Schema, connectionsToRestore: List<String>) {
        connectionsToRestore.forEach {
            println(it)
        }
        connectionsToRestore.convertToSpecification(jsonConverter)
            .forEach { specification ->
                establishConnection(schema, specification)
            }
    }

    private fun establishConnection(
        schema: Schema,
        specification: ConnectionSpecification
    ) {
        val configuration =
            when (specification) {
                is StandardConnectionSpecification -> {
                    val source = getPort(specification.sourcePortId)
                    val target = getPort(specification.targetPortId)
                    StandardPortsConnection(
                        source = source,
                        target = target
                    )
                }
                is ConditionalConnectionSpecification -> {
                    val source = getPort(specification.sourcePortId)
                    val target = getPort(specification.targetPortId)
                    ConditionalPortsConnection(
                        source = source,
                        target = target,
                        value = specification.condition
                    )
                }
            }

        schema.establishConnection(configuration)
    }

    private fun getPort(portId: String): Port =
        //If port doesn't exists (output is null) then saved data is corrupted
        portsHolder.ports.keys.first {
            it.elementId == portId
        }

}

private fun List<String>.convertToSpecification(jsonConverter: Gson): List<ConnectionSpecification> {
    return this.map { json ->
        val type = jsonConverter.fromJson(json, ConnectionTypeContainer::class.java).type
        when (type) {
            1 -> {
                jsonConverter.fromJson(json, ConditionalConnectionSpecification::class.java)
            }
            else -> {
                jsonConverter.fromJson(json, StandardConnectionSpecification::class.java)
            }
        }
    }
}

private data class ConnectionTypeContainer(
    val type: Int
)
